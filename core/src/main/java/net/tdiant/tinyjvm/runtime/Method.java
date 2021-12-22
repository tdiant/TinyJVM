package net.tdiant.tinyjvm.runtime;

import net.tdiant.tinyjvm.classes.file.ExceptionInfo;
import net.tdiant.tinyjvm.classes.file.attr.LineNumTableAttribute;
import net.tdiant.tinyjvm.classes.instruction.Instruction;
import net.tdiant.tinyjvm.util.RuntimeUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 方法
 */
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

    /**
     * 获取返回值类型
     */
    public String getReturnType() {
        return this.getDescriptor().substring(this.getDescriptor().indexOf(")") + 1);
    }

    /**
     * 判断是否为Native方法
     */
    public boolean isNative() {
        return (this.getAccessFlags() & 0x0100) != 0;
    }

    /**
     * 判断是否为静态方法
     */
    public boolean isStatic() {
        return (this.getAccessFlags() & 0x0008) != 0;
    }

    public String nativeMethodKey() {
        return this.clazz.getName() + "_" + getName() + "_" + getDescriptor();
    }

    public Integer getHandlerPc(int pc, String name) {
        for (ExceptionInfo e : this.exceptionInfos) {
            if (e.getClazz() == null || Objects.equals(e.getClazz(), name)) {
                if (pc >= e.getStartPc() && pc < e.getEndPc())
                    return e.getHandlerPc();
            }
        }
        return null;
    }

    public int getArgSlotSize() {
        int cnt = 0;
        for (String it : RuntimeUtils.parseMethodDescriptor(this.getDescriptor())) {
            if (Objects.equals("J", it)) {
                cnt += 2;
                continue;
            }
            if (Objects.equals("D", it)) {
                cnt += 2;
                continue;
            }
            cnt++;
        }

        if (!isStatic())
            cnt++;

        return cnt;
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

    public List<ExceptionInfo> getExceptionInfos() {
        return exceptionInfos;
    }

    public void setExceptionInfos(List<ExceptionInfo> exceptionInfos) {
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

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }
}
