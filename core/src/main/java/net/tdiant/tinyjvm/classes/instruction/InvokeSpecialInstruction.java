package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.TinyJVM;
import net.tdiant.tinyjvm.runtime.Clazz;
import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.Method;
import net.tdiant.tinyjvm.runtime.NativeMethod;
import net.tdiant.tinyjvm.util.RuntimeUtils;

public class InvokeSpecialInstruction extends Instruction {

    public final String clazz;
    public final String methodName;
    public final String methodDescriptor;

    public InvokeSpecialInstruction(String clazz, String methodName, String methodDescriptor) {
        this.clazz = clazz;
        this.methodName = methodName;
        this.methodDescriptor = methodDescriptor;
    }

    @Override
    public int delta() {
        return 3;
    }

    @Override
    public void run(Frame frame) {
        NativeMethod nm = TinyJVM.vm.getHeap().getMethod(RuntimeUtils.nativeMethodKey(clazz, methodName, methodDescriptor));
        if (nm != null) {
            nm.invoke(frame);
            return;
        }

        Clazz clz = TinyJVM.vm.getHeap().getClazz(clazz);
        if (clz == null) {
            throw new IllegalStateException();
        }

        Method method = clz.getMethod(methodName, methodDescriptor);
        if (method == null || method.isNative()) {
            throw new IllegalStateException();
        }

        RuntimeUtils.invokeMethod(method);
    }

    @Override
    public String toString() {
        return "invokespecail " + clazz + " " + methodName + " " + methodDescriptor;
    }
}
