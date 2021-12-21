package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.TinyJVM;
import net.tdiant.tinyjvm.runtime.*;
import net.tdiant.tinyjvm.util.RuntimeUtils;

public class InvokeInterfaceInstruction extends Instruction {

    public final String clazzName;
    public final String methodName;
    public final String methodDescriptor;

    public final int count;
    public final int zero;

    public InvokeInterfaceInstruction(String clazzName, String methodName, String methodDescriptor, int count, int zero) {
        this.clazzName = clazzName;
        this.methodName = methodName;
        this.methodDescriptor = methodDescriptor;
        this.count = count;
        this.zero = zero;
    }

    @Override
    public int delta() {
        return 5;
    }

    @Override
    public void run(Frame frame) {
        NativeMethod nm = TinyJVM.vm.getHeap().getMethod(RuntimeUtils.genNativeMethodKey(clazzName, methodName, methodDescriptor));
        if (nm != null) {
            nm.invoke(frame);
            return;
        }

        Clazz clazz = TinyJVM.vm.getHeap().getClazz(this.clazzName);
        if (clazz == null)
            clazz = frame.getMethod().getClazz().getClazzLoader().loadClazz(clazzName);

        RuntimeUtils.clinit(clazz);

        Method method = clazz.getMethod(methodName, methodDescriptor);

        if (method == null) {
            if (clazz.getInterfaceNames().isEmpty())
                throw new IllegalStateException();

            if (clazz.getInterfaces().isEmpty()) {
                clazz.interfaceInit(frame);
            }
            if (!clazz.getInterfaces().isEmpty()) {
                for (Clazz intClass : clazz.getInterfaces()) {
                    method = intClass.getMethod(methodName, methodDescriptor);
                    if (method != null) {
                        break;
                    }
                }
            }
        }

        if (method == null || method.isNative()) {
            throw new IllegalStateException();
        }

        Instance ref = frame.getThis(method.getArgSlotSize());
        Method implMethod = ref.getClazz().getMethod(methodName, methodDescriptor);
        if (implMethod == null) {
            implMethod = method;
        }
        RuntimeUtils.invokeMethod(implMethod);
    }

    @Override
    public String toString() {
        return "invokeinterface " + clazzName + " " + methodName + " " + methodDescriptor;
    }
}
