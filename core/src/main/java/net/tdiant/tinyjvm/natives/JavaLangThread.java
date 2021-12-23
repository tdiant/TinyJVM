package net.tdiant.tinyjvm.natives;

import net.tdiant.tinyjvm.TinyJVM;

public class JavaLangThread {

    public static void registerNatives() {

        TinyJVM.vm.getHeap().registerMethod("java/lang/Thread_currentThread_()Ljava/lang/Thread;", frame -> {
            TinyJVM.vm.getMainThread().now().getOperandStack().pushRef(null);
        });
        TinyJVM.vm.getHeap().registerMethod("java/lang/Thread_getThreadGroup_()Ljava/lang/ThreadGroup;", frame -> {
            TinyJVM.vm.getMainThread().now().getOperandStack().pushRef(null);
        });
        TinyJVM.vm.getHeap().registerEmptyMethod("java/lang/Thread_registerNatives_()V");
        TinyJVM.vm.getHeap().registerEmptyMethod("java/lang/Thread_yield_()V");
        TinyJVM.vm.getHeap().registerMethod("java/lang/Thread_sleep_(J)V", frame -> {
            TinyJVM.vm.getMainThread().now().getOperandStack().pop();
        });
        TinyJVM.vm.getHeap().registerEmptyMethod("java/lang/Thread_start0_()V");
        TinyJVM.vm.getHeap().registerMethod("java/lang/Thread_isInterrupted_(Z)Z", frame -> {
            TinyJVM.vm.getMainThread().now().getOperandStack().pop();
            TinyJVM.vm.getMainThread().now().getOperandStack().pushInt(0);
        });
        TinyJVM.vm.getHeap().registerMethod("java/lang/Thread_isAlive_()Z", frame -> {
            TinyJVM.vm.getMainThread().now().getOperandStack().pushInt(0);
        });
        TinyJVM.vm.getHeap().registerMethod("java/lang/Thread_countStackFrames_()I", frame -> {
            TinyJVM.vm.getMainThread().now().getOperandStack().pushInt(0);
        });
        TinyJVM.vm.getHeap().registerMethod("java/lang/Thread_holdsLock_(Ljava/lang/Object;)Z", frame -> {
            TinyJVM.vm.getMainThread().now().getOperandStack().pop();
            TinyJVM.vm.getMainThread().now().getOperandStack().pushInt(0);
        });
        TinyJVM.vm.getHeap().registerMethod("java/lang/Thread_dumpThreads_([Ljava/lang/Thread;)[[Ljava/lang/StackTraceElement;", frame -> {
            TinyJVM.vm.getMainThread().now().getOperandStack().pop();
            TinyJVM.vm.getMainThread().now().getOperandStack().pushRef(null);
        });
        TinyJVM.vm.getHeap().registerMethod("java/lang/Thread_setPriority0_(I)V", frame -> {
            TinyJVM.vm.getMainThread().now().getOperandStack().pop();
        });
        TinyJVM.vm.getHeap().registerMethod("java/lang/Thread_stop0_(Ljava/lang/Object;)V", frame -> {
            TinyJVM.vm.getMainThread().now().getOperandStack().pop();
        });
        TinyJVM.vm.getHeap().registerMethod("java/lang/Thread_suspend0_()V", frame -> {
            TinyJVM.vm.getMainThread().now().getOperandStack().pop();
        });
        TinyJVM.vm.getHeap().registerMethod("java/lang/Thread_resume0_()V", frame -> {
            TinyJVM.vm.getMainThread().now().getOperandStack().pop();
        });
        TinyJVM.vm.getHeap().registerMethod("java/lang/Thread_interrupt0_()V", frame -> {
            TinyJVM.vm.getMainThread().now().getOperandStack().pop();
        });
        TinyJVM.vm.getHeap().registerMethod("java/lang/Thread_setNativeName_(Ljava/lang/String;)V", frame -> {
            TinyJVM.vm.getMainThread().now().getOperandStack().pop();
        });
        TinyJVM.vm.getHeap().registerMethod("java/lang/Thread_getThreads_()[Ljava/lang/Thread;", frame -> {
            TinyJVM.vm.getMainThread().now().getOperandStack().pushRef(null);
        });
        TinyJVM.vm.getHeap().registerMethod("java/lang/Thread_isDaemon_()Z", frame -> {
            TinyJVM.vm.getMainThread().now().getOperandStack().pushInt(0);
        });
        TinyJVM.vm.getHeap().registerMethod("java/lang/Thread_getPriority_()I", frame -> {
            TinyJVM.vm.getMainThread().now().getOperandStack().pushInt(0);
        });
        TinyJVM.vm.getHeap().registerMethod("java/lang/Thread_getContextClassLoader_()Ljava/lang/ClassLoader;", frame -> {
            TinyJVM.vm.getMainThread().now().getOperandStack().pushRef(null);
        });
        TinyJVM.vm.getHeap().registerEmptyMethod("java/lang/ThreadGroup_checkAccess_()V");
        TinyJVM.vm.getHeap().registerEmptyMethod("java/lang/ThreadGroup_addUnstarted_()V");
    }

}
