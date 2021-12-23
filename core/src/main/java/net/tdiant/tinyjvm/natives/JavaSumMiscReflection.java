package net.tdiant.tinyjvm.natives;

import net.tdiant.tinyjvm.TinyJVM;
import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.Instance;

public class JavaSumMiscReflection {

    public static void registerNatives() {
        TinyJVM.vm.getHeap().registerMethod("sun/reflect/Reflection_getCallerClass_()Ljava/lang/Class;", frame -> {
            Frame callerFrame = frame.getThread().caller();
            Instance cls = callerFrame.getMethod().getClazz().getRuntimeClass();
            frame.getOperandStack().pushRef(cls);
        });
    }

}
