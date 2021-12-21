package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.TinyJVM;
import net.tdiant.tinyjvm.classes.file.ClassFile;
import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.Thread;

public class IReturnInstruction extends Instruction {
    @Override
    public void run(Frame frame) {
        Thread thread = TinyJVM.vm.getMainThread();
        Frame f = thread.pop();
        if (f.getStat() == ClassFile.FAKE_FRAME)
            f.setStat(ClassFile.FAKE_FRAME_END);
        Frame now = TinyJVM.vm.getMainThread().now();

        now.getOperandStack().push(
                f.getOperandStack().pop()
        );

    }

    @Override
    public String toString() {
        return "ireturn";
    }
}
