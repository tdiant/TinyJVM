package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.TinyJVM;
import net.tdiant.tinyjvm.runtime.*;
import net.tdiant.tinyjvm.util.RuntimeUtils;

import java.util.Arrays;
import java.util.Objects;

public class InvokeVirtualInstruction extends Instruction {

    public final String clazz;
    public final String methodName;
    public final String methodDescriptor;

    public InvokeVirtualInstruction(String clazz, String methodName, String methodDescriptor) {
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

        if (Objects.equals("sun/misc/Unsafe", clazz)
                || Objects.equals("java/util/Properties", clazz)
                || Objects.equals("java/util/zip/ZipFile", clazz)
                || Objects.equals("java/lang/Thread", clazz)
                || Objects.equals("java/lang/ThreadGroup", clazz)
        ) {
            NativeMethod nativeMethod = TinyJVM.vm.getHeap().getMethod(RuntimeUtils.nativeMethodKey(clazz, methodName, methodDescriptor));
            if (nativeMethod != null) {
                nativeMethod.invoke(frame);
                return;
            }
        }

        Clazz clazz = TinyJVM.vm.getHeap().getClazz(this.clazz);
        if (clazz == null)
            clazz = frame.getMethod().getClazz().getClazzLoader().loadClazz(this.clazz);

        Method method = clazz.getMethod(methodName, methodDescriptor);

        if (method == null) {
            if (clazz.getInterfaceNames().isEmpty())
                throw new IllegalStateException();

            if (!clazz.getInterfaces().isEmpty()) {
                for (Clazz intClass : clazz.getInterfaces()) {
                    method = intClass.getMethod(methodName, methodDescriptor);
                    if (method != null) {
                        break;
                    }
                }
            } else {
                clazz.interfaceInit(frame);
                return;
            }
        }

        if (method == null) {
            throw new IllegalStateException();
        }

        System.out.println(method);
        System.out.println(Arrays.toString(frame.getOperandStack().getSlots()));

        int size = method.getArgSlotSize();
        Instance self = frame.getThis(size);

        System.out.println(self);

        NativeMethod nm = TinyJVM.vm.getHeap().getMethod(this.clazz + "_" + methodName + "_" + methodDescriptor);
        if (self == null && nm != null) {
            nm.invoke(frame);
            return;
        }

        System.out.println(this.clazz + "_" + methodName + "_" + methodDescriptor);

        Method implMethod = self.getClazz().getMethod(methodName, methodDescriptor);
        nm = TinyJVM.vm.getHeap().getMethod(implMethod == null ? this.clazz + "_" + methodName + "_" + methodDescriptor : implMethod.nativeMethodKey());
        if (nm != null) {
            nm.invoke(frame);
            return;
        }

        if (implMethod.isNative()) {
            throw new IllegalStateException();
        }
        RuntimeUtils.invokeMethod(implMethod);
    }

    @Override
    public String toString() {
        return "invokevirtual " + clazz + " " + methodName + " " + methodDescriptor;
    }

}
