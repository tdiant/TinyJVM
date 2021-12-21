package net.tdiant.tinyjvm.classes.instruction;

public class LdcWInstruction extends Instruction {

    public final String descriptor;
    public final Object obj;

    public LdcWInstruction(String descriptor, Object val) {
        this.descriptor = descriptor;
        this.obj = val;
    }

    @Override
    public int delta() {
        return 3;
    }


}
