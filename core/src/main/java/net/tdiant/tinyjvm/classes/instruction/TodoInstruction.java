package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;

public class TodoInstruction extends Instruction {

    private final String str;

    public TodoInstruction(String str) {
        this.str = str;
    }

    public TodoInstruction(String str, Object obj) {
        this.str = str;
    }

    @Override
    public void run(Frame frame) {

    }

    @Override
    public String toString() {
        return str + " todo";
    }
}
