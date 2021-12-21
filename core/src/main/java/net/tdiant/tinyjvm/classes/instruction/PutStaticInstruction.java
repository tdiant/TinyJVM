package net.tdiant.tinyjvm.classes.instruction;

public class PutStaticInstruction extends Instruction {

    private final String className;
    private final String name;
    private final String descriptor;

    public PutStaticInstruction(String className, String name, String descriptor) {
        super();
    }

    public String getClassName() {
        return className;
    }

    public String getName() {
        return name;
    }

    public String getDescriptor() {
        return descriptor;
    }
}
