package net.tdiant.tinyjvm.natives;

import net.tdiant.tinyjvm.TinyJVM;
import net.tdiant.tinyjvm.runtime.Instance;
import net.tdiant.tinyjvm.runtime.Slot;

public class JavaLangObject {
    public static void registerNatives() {
        TinyJVM.vm.getHeap().registerEmptyMethod("java/lang/Object_registerNatives_()V");
        TinyJVM.vm.getHeap().registerEmptyMethod("java/lang/Object_wait_(J)V");
        TinyJVM.vm.getHeap().registerEmptyMethod("java/lang/Object_notify_()V");
        TinyJVM.vm.getHeap().registerEmptyMethod("java/lang/Object_notifyAll_()V");

        TinyJVM.vm.getHeap().registerEmptyMethod("java/lang/Object_clone_()Ljava/lang/Object;"); //todo not support yet

        TinyJVM.vm.getHeap().registerMethod("java/lang/Object_getClass_()Ljava/lang/Class;", (frame) -> {
            Instance val = frame.getOperandStack().popRef();
            frame.getOperandStack().push(new Slot(val.getClazz().getRuntimeClass()));
        });
        TinyJVM.vm.getHeap().registerMethod("java/lang/Object_hashCode_()I", (frame) -> {
            int val = frame.getOperandStack().popRef().hashCode();
            frame.getOperandStack().push(new Slot(val));
        });

    }
}
