package net.tdiant.tinyjvm;

import net.tdiant.tinyjvm.classes.loader.ClazzLoader;
import net.tdiant.tinyjvm.natives.*;
import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.Method;
import net.tdiant.tinyjvm.runtime.Thread;

public class VMMain {

    private Thread mainThread;
    private TinyNativeHeap heap;

    public void run() {

        this.mainThread = new Thread(TinyJVM.args.getMaxThreadSize());
        this.heap = new TinyNativeHeap();

        ClazzLoader clazzLoader = new ClazzLoader("vm_startup", )

        initNatives();

    }

    private void initNatives() {

        JavaLangObject.registerNatives();
        JavaLangClass.registerNatives();
        JavaLangSystem.registerNatives();
        JavaLangFloat.registerNatives();
        JavaLangDouble.registerNatives();

    }

    public Thread getMainThread() {
        return mainThread;
    }

    public TinyNativeHeap getHeap() {
        return heap;
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
            kargs[i] = Utils.str2Obj(args[i], frame.method.clazz.classLoader);
        }
        Class arrClazz = Heap.findClass("[Ljava/lang/String;");
        if (arrClazz == null) {
            arrClazz = new Class(1, "[Ljava/lang/String;", method.clazz.classLoader, null);
            Heap.registerClass(arrClazz.name, arrClazz);
        }
        InstanceArray array = new InstanceArray(arrClazz, kargs);
        frame.setRef(0, array);

        execute(frame);
    }

    public void loop(Thread thread) {
        if (EnvHolder.debug) {
            try {
                System.out.println("正在初始化jdb...");
                DebugContextHolder.scanner = new Scanner(System.in);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        do {
            Frame frame = thread.topFrame();
            int pc = frame.nextPc;

            Instruction inst = frame.getInst();
            if (inst == null) {
                StringBuilder sb = new StringBuilder();
                sb.append(pc).append("\n");
                sb.append("class: ").append(frame.method.clazz.name).append("\n");
                sb.append("method: ").append(frame.method.name).append("\n");
                sb.append("methodDescriptor: ").append(frame.method.descriptor).append("\n");
                frame.method.instructionMap.forEach((key, val) -> {
                    sb.append(key).append(" ").append(val.format()).append("\n");
                });
                String str = sb.toString();
                System.err.println(str);
                throw new IllegalStateException();
            }
            traceBefore(inst, frame);

            frame.nextPc += inst.offset();
            try {
                inst.execute(frame);
            } catch (Exception e) {
                e.printStackTrace();

                String name = frame.getCurrentMethodFullName();
                String msg = name + "(" + frame.getCurrentSource() + ":" + frame.getCurrentLine() + ")";
                System.out.println(msg);
                throw new IllegalStateException();
            }

        } while (!thread.empty());
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

