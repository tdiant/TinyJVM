package net.tdiant.tinyjvm.natives;

import net.tdiant.tinyjvm.TinyJVM;

public class JavaLangThrowable {

    public static void registerNatives() {
        TinyJVM.vm.getHeap().registerMethod("java/lang/Throwable_<clinit>_()V", frame -> {
        });
        TinyJVM.vm.getHeap().registerMethod("java/lang/Throwable_<init>_(Ljava/lang/String)Ljava/lang/Throwable;", frame -> {
        });
        TinyJVM.vm.getHeap().registerMethod("java/lang/Throwable_fillInStackTrace_(I)Ljava/lang/Throwable;", frame -> {
        });
        TinyJVM.vm.getHeap().registerMethod("java/lang/Throwable_fillInStackTrace_()Ljava/lang/Throwable;", frame -> {
        });
        TinyJVM.vm.getHeap().registerMethod("java/lang/Throwable_getStackTraceDepth_()I", frame -> {
        });
        TinyJVM.vm.getHeap().registerMethod("java/lang/Throwable_getStackTraceElement_(I)Ljava/lang/StackTraceElement;", frame -> {
        });
    }

}
