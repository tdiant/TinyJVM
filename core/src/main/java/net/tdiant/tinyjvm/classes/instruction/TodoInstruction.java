package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;

public class TodoInstruction extends Instruction {

    private final String str;
    private final int delta;

    public TodoInstruction(String str,int delta) {
        this.str = str;
        this.delta = delta;
    }

    public TodoInstruction(String str, int delta, Object obj) {
        this.str = str;
        this.delta = delta;
    }

    @Override
    public int delta() {
        return delta;
    }

    @Override
    public void run(Frame frame) {

    }

    @Override
    public String toString() {
        return str + " todo";
    }
}
