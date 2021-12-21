package net.tdiant.tinyjvm.classes.instruction;

public class InvokeDynamicInstruction extends Instruction {

    private final String methodName;
    private final String methodDescriptor;
    private final int idx;

    public InvokeDynamicInstruction(String methodName, String methodDescriptor, int idx) {
        this.methodName = methodName;
        this.methodDescriptor = methodDescriptor;
        this.idx = idx;
    }
}
