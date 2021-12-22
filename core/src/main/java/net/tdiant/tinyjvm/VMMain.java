package net.tdiant.tinyjvm;

import net.tdiant.tinyjvm.classes.file.ClazzFile;
import net.tdiant.tinyjvm.classes.instruction.Instruction;
import net.tdiant.tinyjvm.classes.loader.ClazzLoader;
import net.tdiant.tinyjvm.natives.*;
import net.tdiant.tinyjvm.runtime.Thread;
import net.tdiant.tinyjvm.runtime.*;
import net.tdiant.tinyjvm.util.MeowUtils;

public class VMMain {

    private Thread mainThread;
    private TinyNativeHeap heap;

    public void run() {

        this.mainThread = new Thread(TinyJVM.args.getMaxThreadSize());
        this.heap = new TinyNativeHeap();

        ClazzLoader clazzLoader = new ClazzLoader("vm_startup", MeowUtils.getTempClazzSource());

        initNatives();
        initPrimitiveClasses(clazzLoader);

        initSystemOut(clazzLoader);

    }

    public Thread getMainThread() {
        return mainThread;
    }

    public TinyNativeHeap getHeap() {
        return heap;
    }

    private void initNatives() {

        JavaLangObject.registerNatives();
        JavaLangClass.registerNatives();
        JavaLangSystem.registerNatives();
        JavaLangFloat.registerNatives();
        JavaLangDouble.registerNatives();

    }

    private void initPrimitiveClasses(ClazzLoader clazzLoader) {

        Clazz clz = clazzLoader.loadClazz("java/lang/Class");

        for (Clazz cls : TinyJVM.vm.getHeap().getClasses()) {
            if (cls.getRuntimeClass() == null) {
                Instance obj = clz.newInstance();
                cls.setRuntimeClass(obj);
                obj.setMetaClass(cls);
            }
        }

        // Basic Types
        clazzLoader.loadPrimitiveClass("char");
        clazzLoader.loadPrimitiveClass("boolean");
        clazzLoader.loadPrimitiveClass("byte");
        clazzLoader.loadPrimitiveClass("short");
        clazzLoader.loadPrimitiveClass("int");
        clazzLoader.loadPrimitiveClass("long");
        clazzLoader.loadPrimitiveClass("float");
        clazzLoader.loadPrimitiveClass("double");
        clazzLoader.loadPrimitiveClass("void");

        // String
        clazzLoader.loadClazz("java/lang/String");

        // Wrappers
        clazzLoader.loadClazz("java/lang/Character");
        clazzLoader.loadClazz("java/lang/Boolean");
        clazzLoader.loadClazz("java/lang/Byte");
        clazzLoader.loadClazz("java/lang/Short");
        clazzLoader.loadClazz("java/lang/Integer");
        clazzLoader.loadClazz("java/lang/Long");
        clazzLoader.loadClazz("java/lang/Float");
        clazzLoader.loadClazz("java/lang/Double");
        clazzLoader.loadClazz("java/lang/Void");

        // Basic Arrays
        clazzLoader.loadPrimitiveClass("[B");
        clazzLoader.loadPrimitiveClass("[C");
        clazzLoader.loadPrimitiveClass("[Z");
        clazzLoader.loadPrimitiveClass("[S");
        clazzLoader.loadPrimitiveClass("[I");
        clazzLoader.loadPrimitiveClass("[F");
        clazzLoader.loadPrimitiveClass("[L");
        clazzLoader.loadPrimitiveClass("[D");

    }

    private void initSystemOut(ClazzLoader classLoader) {
        Clazz fdCls = classLoader.loadClazz("java/io/FileDescriptor");
        Instance outFdObj = fdCls.newInstance();
        Method fdInitMethod = fdCls.getMethod("<init>", "(I)V");

        Frame f1 = new Frame(fdInitMethod);
        f1.getOperandStack().set(0, new Slot(outFdObj));
        f1.getOperandStack().set(1, new Slot(1));
        execute(f1);

        Clazz fosCls = classLoader.loadClazz("java/io/FileOutputStream");
        Instance fosObj = fosCls.newInstance();
        Method fosInitMethod = fosCls.getMethod("<init>", "(Ljava/io/FileDescriptor;)V");

        Frame f2 = new Frame(fosInitMethod);
        f2.getOperandStack().set(0, new Slot(fosObj));
        f2.getOperandStack().set(1, new Slot(outFdObj));
        execute(f2);

        Clazz psCls = classLoader.loadClazz("java/io/PrintStream");
        Instance psObj = psCls.newInstance();
        Method psInitMethod = psCls.getMethod("<init>", "(Ljava/io/OutputStream;Z)V");
        Frame frame = new Frame(psInitMethod);
        f2.getOperandStack().set(0, new Slot(psObj));
        f2.getOperandStack().set(1, new Slot(fosObj));
        f2.getOperandStack().set(2, new Slot(1));
        execute(frame);

        Clazz sysCls = classLoader.loadClazz("java/lang/System");
        Field outField = sysCls.getField("out", "Ljava/io/PrintStream;");
        outField.setVal(new Slot(psObj));
    }

    public void execute(Method method) {
        Frame newFrame = new Frame(method);
        int slots = method.getArgSlotSize();
        if (slots > 0) {
            Frame old = this.mainThread.now();
            for (int i = slots - 1; i >= 0; i--) {
                newFrame.getLocalVars().set(i, old.getOperandStack().pop());
            }
        }
        execute(newFrame);
    }

    public void execute(Frame newFrame) {
        mainThread.push(newFrame);
        newFrame.setStat(ClazzFile.FAKE_FRAME);

        do {
            Frame frame = mainThread.now();
            frame.setPc(frame.getNextPc());
            Instruction instruction = frame.getInstructions().get(frame.getPc());
            frame.setNextPc(frame.getNextPc() + instruction.delta());

            instruction.run(frame);
        } while (newFrame.getStat() == ClazzFile.FAKE_FRAME);
    }

}

