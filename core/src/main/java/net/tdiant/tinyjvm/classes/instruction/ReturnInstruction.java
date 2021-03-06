package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.TinyJVM;
import net.tdiant.tinyjvm.classes.file.ClazzFile;
import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.Thread;
import net.tdiant.tinyjvm.util.RuntimeUtils;

public class ReturnInstruction extends Instruction {
    @Override
    public void run(Frame frame) {
//        Thread thread = TinyJVM.vm.getMainThread();
//        Frame f = thread.pop();
//        if (f.getStat() == ClazzFile.FAKE_FRAME)
//            f.setStat(ClazzFile.FAKE_FRAME_END);
        RuntimeUtils.doReturn(0);
    }

    @Override
    public String toString() {
        return "return";
    }
}
