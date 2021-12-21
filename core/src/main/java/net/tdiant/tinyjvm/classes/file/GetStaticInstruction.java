package net.tdiant.tinyjvm.classes.file;

import net.tdiant.tinyjvm.classes.instruction.Instruction;

public class GetStaticInstruction extends Instruction {

    private final String className;
    private final String name;
    private final String descriptor;

    public GetStaticInstruction(String className, String name, String descriptor) {
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
