package net.tdiant.tinyjvm.natives;

import net.tdiant.tinyjvm.TinyJVM;
import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.Instance;
import net.tdiant.tinyjvm.runtime.Method;

public class JavaSecurityAccessController {

    public static void registerNatives() {

        TinyJVM.vm.getHeap().registerMethod("java/security/AccessController_doPrivileged_(Ljava/security/PrivilegedAction;)Ljava/lang/Object;", frame -> {
            Instance thisObj = frame.getOperandStack().popRef();
            Method method = thisObj.getClazz().getMethod("run", "()Ljava/lang/Object;");
            Frame newFrame = new Frame(method);
            newFrame.getLocalVars().setRef(0, thisObj);
            frame.getThread().push(newFrame);
        });

        TinyJVM.vm.getHeap().registerMethod("java/security/AccessController_doPrivileged_(Ljava/security/PrivilegedExceptionAction;)Ljava/lang/Object;", frame -> {
            Instance thisObj = frame.getOperandStack().popRef();
            Method method = thisObj.getClazz().getMethod("run", "()Ljava/lang/Object;");
            Frame newFrame = new Frame(method);
            newFrame.getLocalVars().setRef(0, thisObj);
            frame.getThread().push(newFrame);
        });

        TinyJVM.vm.getHeap().registerMethod("java/security/AccessController_doPrivileged_(Ljava/security/PrivilegedAction;Ljava/security/AccessControlContext;)Ljava/lang/Object;", frame -> {
            frame.getThread().now().getOperandStack().pop();
            frame.getThread().now().getOperandStack().pushRef(null);
        });
        TinyJVM.vm.getHeap().registerMethod("java/security/AccessController_doPrivileged_(Ljava/security/PrivilegedExceptionAction;Ljava/security/AccessControlContext;)Ljava/lang/Object;", frame -> {
            frame.getThread().now().getOperandStack().pop();
            frame.getThread().now().getOperandStack().pushRef(null);
        });TinyJVM.vm.getHeap().registerMethod("java/security/AccessController_getStackAccessControlContext_()Ljava/security/AccessControlContext;", frame -> {
//            frame.getThread().now().getOperandStack().pop();
            frame.getThread().now().getOperandStack().pushRef(null);
        });TinyJVM.vm.getHeap().registerMethod("java/security/AccessController_getInheritedAccessControlContext_()Ljava/security/AccessControlContext;", frame -> {
//            frame.getThread().now().getOperandStack().pop();
            frame.getThread().now().getOperandStack().pushRef(null);
        });
    }
}
