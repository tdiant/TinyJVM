package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.Instance;
import net.tdiant.tinyjvm.runtime.Slot;
import net.tdiant.tinyjvm.runtime.Thread;

public class AThrowInstruction extends Instruction {

    @Override
    public void run(Frame frame) {
        Thread thread = frame.getThread();
        Instance exc = frame.getOperandStack().pop().getInstance();
        String name = exc.getClazz().getName();

        Integer handlerPc = frame.getMethod().getHandlerPc(frame.getPc(), name);
        while (handlerPc == null && !thread.empty()) {
            Frame ef = thread.pop();
            String msg = ef.getCurrentMethodFullName() + "(" + ef.getMethod().getClazz().getClazzLoader().getSource().sourcePath() + ":" + ef.getCurrentLine() + ")";
            System.err.println(msg);
            if (thread.empty()) {
                break;
            }
            final Frame f = thread.now();
            handlerPc = f.getMethod().getHandlerPc(f.getPc(), name);
        }

        // no exception handler ...
        if (handlerPc == null) {
            System.err.println(exc);
            throw new RuntimeException("no exception handler");
        }

        thread.now().getOperandStack().push(new Slot(exc));
        thread.now().setNextPc(handlerPc);
    }

    @Override
    public String toString() {
        return "athrow";
    }

}
