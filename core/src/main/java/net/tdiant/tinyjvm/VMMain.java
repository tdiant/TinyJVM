package net.tdiant.tinyjvm;

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

    public void execute(Method method) {
        Frame newFrame = new Frame(method);
        int slots = method.getArgSlotSize();
        if (slots > 0) {
            Frame old = this.mainThread.now();
            for (int i = slots - 1; i >= 0; i--) {
                newFrame.set(i, old.pop());
            }
        }
        execute(newFrame);
    }

    public void execute(Frame newFrame) {
        final Thread env = MetaSpace.getMainEnv();
        env.pushFrame(newFrame);

        newFrame.stat = Const.FAKE_FRAME;
        do {
            Frame frame = env.topFrame();
            Instruction instruction = frame.getInst();
            frame.nextPc += instruction.offset();
            traceBefore(instruction, frame);
            instruction.execute(frame);
        } while (newFrame.stat == Const.FAKE_FRAME);
    }

    public void runMain(Method method, String[] args) {
        Frame frame = new Frame(method);

        Instance[] kargs = new Instance[args.length];
        for (int i = 0; i < args.length; i++) {
            kargs[i] = Utils.str2Obj(args[i], frame.method.clazz.clazzLoader);
        }
        Class arrClazz = Heap.findClass("[Ljava/lang/String;");
        if (arrClazz == null) {
            arrClazz = new Class(1, "[Ljava/lang/String;", method.clazz.clazzLoader, null);
            Heap.registerClass(arrClazz.name, arrClazz);
        }
        InstanceArray array = new InstanceArray(arrClazz, kargs);
        frame.setRef(0, array);

        execute(frame);
    }

    private void traceBefore(Instruction inst, Frame frame) {
        if (EnvHolder.verboseDebug) {
            debugBefore(inst, frame);
        }
        // verboseTrace
        if (EnvHolder.verboseTrace) {
            trace(inst, frame);
        }
        // verboseCall
        if (EnvHolder.verboseCall) {
            call(inst, frame);
        }
    }

    private void call(Instruction inst, Frame frame) {
        if (!inst.format().startsWith("invoke")) {
            return;
        }
        String space = genSpace((frame.thread.size() - 1) * 2);
        Logger.trace(space.concat(Integer.toString(frame.getPc()).concat(" ").concat(inst.format())));
    }

    private void trace(Instruction inst, Frame frame) {
        String space = genSpace((frame.thread.size() - 1) * 2);
        Logger.trace(space.concat(Integer.toString(frame.getPc()).concat(" ").concat(inst.format())));
    }

    public String genSpace(int size) {
        String x = "";
        for (int i = 0; i < size; i++) {
            x = x.concat(" ");
        }
        return x;
    }

}

