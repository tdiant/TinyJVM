package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.TinyJVM;
import net.tdiant.tinyjvm.classes.file.ClazzFile;
import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.Slot;
import net.tdiant.tinyjvm.runtime.Thread;

public class LReturnInstruction extends Instruction {
    @Override
    public void run(Frame frame) {
        Thread thread = TinyJVM.vm.getMainThread();
        Frame f = thread.pop();
        if (f.getStat() == ClazzFile.FAKE_FRAME)
            f.setStat(ClazzFile.FAKE_FRAME_END);
        Frame now = TinyJVM.vm.getMainThread().now();

        Slot a = f.getOperandStack().pop();
        Slot b = f.getOperandStack().pop();

        now.getOperandStack().push(b);
        now.getOperandStack().push(a);

    }

    @Override
    public String toString() {
        return "lreturn";
    }
}
