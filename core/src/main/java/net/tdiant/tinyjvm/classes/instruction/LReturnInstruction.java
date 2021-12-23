package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.util.RuntimeUtils;

public class LReturnInstruction extends Instruction {
    @Override
    public void run(Frame frame) {
//        Thread thread = TinyJVM.vm.getMainThread();
//        Frame f = thread.pop();
//        if (f.getStat() == ClazzFile.FAKE_FRAME)
//            f.setStat(ClazzFile.FAKE_FRAME_END);
//        Frame now = TinyJVM.vm.getMainThread().now();
//
//        Slot a = f.getOperandStack().pop();
//        Slot b = f.getOperandStack().pop();
//
//        now.getOperandStack().push(b);
//        now.getOperandStack().push(a);

        RuntimeUtils.doReturn(2);
    }

    @Override
    public String toString() {
        return "lreturn";
    }
}
