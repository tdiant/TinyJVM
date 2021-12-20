package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;

public abstract class Instruction {

    // 执行一次让PC增加多少
    public int delta() {
        return 1;
    }

    public abstract void run(Frame frame);

    public abstract String toString();

}
