package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.TinyJVM;
import net.tdiant.tinyjvm.runtime.Clazz;
import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.Method;
import net.tdiant.tinyjvm.runtime.NativeMethod;
import net.tdiant.tinyjvm.util.RuntimeUtils;

public class InvokeStaticInstruction extends Instruction {

    public final String clazzName;
    public final String methodName;
    public final String descriptor;

    public InvokeStaticInstruction(String clazzName, String methodName, String descriptor) {
        this.clazzName = clazzName;
        this.methodName = methodName;
        this.descriptor = descriptor;
    }

    @Override
    public int delta() {
        return 3;
    }

    @Override
    public void run(Frame frame) {
        NativeMethod nm = TinyJVM.vm.getHeap().getMethod(RuntimeUtils.nativeMethodKey(clazzName, methodName, descriptor));
        if (nm != null) {
            nm.invoke(frame);
            return;
        }

        Clazz aClass = TinyJVM.vm.getHeap().getClazz(clazzName);
        if (aClass == null)
            aClass = frame.getMethod().getClazz().getClazzLoader().loadClazz(clazzName);
        RuntimeUtils.clinit(aClass);

        Method method = aClass.getMethod(methodName, descriptor);

        if (method.isNative())
            throw new IllegalStateException();

        RuntimeUtils.invokeMethod(method);
    }

    @Override
    public String toString() {
        return "invokestatic " + clazzName + " " + methodName + " " + descriptor;
    }
}
