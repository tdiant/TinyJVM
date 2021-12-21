package net.tdiant.tinyjvm.runtime;

import net.tdiant.tinyjvm.classes.file.ExceptionInfo;
import net.tdiant.tinyjvm.classes.file.attr.LineNumTableAttribute;
import net.tdiant.tinyjvm.classes.instruction.Instruction;

import java.util.List;
import java.util.Map;

public class Method extends BaseNametag {

    private int maxStacks;
    private int maxLocals;
    private Map<Integer, Instruction> instructions;
    private List<ExceptionInfo> exceptionInfos;
    private LineNumTableAttribute lineNumAttr;

    private Clazz clazz;

    public Method(int accessFlags, String name, String descriptor, int maxStacks, int maxLocals, Map<Integer, Instruction> instructions, List<ExceptionInfo> exceptionInfos, LineNumTableAttribute lineNumAttr) {
        super(accessFlags, name, descriptor);
        this.maxStacks = maxStacks;
        this.maxLocals = maxLocals;
        this.instructions = instructions;
        this.exceptionInfos = exceptionInfos;
        this.lineNumAttr = lineNumAttr;
    }

    public int getMaxStacks() {
        return maxStacks;
    }

    public void setMaxStacks(int maxStacks) {
        this.maxStacks = maxStacks;
    }

    public int getMaxLocals() {
        return maxLocals;
    }

    public void setMaxLocals(int maxLocals) {
        this.maxLocals = maxLocals;
    }

    public Map<Integer, Instruction> getInstructions() {
        return instructions;
    }

    public void setInstructions(Map<Integer, Instruction> instructions) {
        this.instructions = instructions;
    }

    public List<ExceptionInfo> getExceptions() {
        return exceptionInfos;
    }

    public void setExceptions(List<ExceptionInfo> exceptionInfos) {
        this.exceptionInfos = exceptionInfos;
    }

    public LineNumTableAttribute getLineNumAttr() {
        return lineNumAttr;
    }

    public void setLineNumAttr(LineNumTableAttribute lineNumAttr) {
        this.lineNumAttr = lineNumAttr;
    }

    public Clazz getClazz() {
        return clazz;
    }

    public Integer getHandlerPc(int pc, String name) {
    }

    public String nativeMethodKey() {
    }

    public boolean isNative() {
    }

    public int getArgSlotSize() {
    }
}
