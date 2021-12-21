package net.tdiant.tinyjvm.classes.file.attr;

import net.tdiant.tinyjvm.classes.file.ExceptionInfo;
import net.tdiant.tinyjvm.classes.instruction.Instruction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CodeAttribute extends Attribute {

    private final int maxStacks;
    private final int maxLocals;
    private final List<Instruction> instructions;
    private final ExceptionInfo[] exceptionInfos;
    private final List<Attribute> attributes;

    public CodeAttribute(int maxStacks, int maxLocals, List<Instruction> instructions, ExceptionInfo[] exceptionInfos, List<Attribute> attributes) {
        this.maxStacks = maxStacks;
        this.maxLocals = maxLocals;
        this.instructions = instructions;
        this.exceptionInfos = exceptionInfos;
        this.attributes = attributes;
    }

    public Map<Integer, Instruction> getInstructionsMap() {
        int i = 0; //pc
        Map<Integer, Instruction> mp = new HashMap<>();
        for (Instruction ins : instructions) {
            mp.put(i, ins);
            i += ins.delta();
        }
        return mp;
    }

    public int getMaxStacks() {
        return maxStacks;
    }

    public int getMaxLocals() {
        return maxLocals;
    }

    public List<Instruction> getInstructions() {
        return instructions;
    }

    public ExceptionInfo[] getExceptions() {
        return exceptionInfos;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }
}
