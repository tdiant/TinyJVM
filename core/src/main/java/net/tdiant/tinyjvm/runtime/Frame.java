package net.tdiant.tinyjvm.runtime;

import net.tdiant.tinyjvm.TinyJVM;
import net.tdiant.tinyjvm.classes.instruction.Instruction;

import java.util.HashMap;
import java.util.Map;

/**
 * 运行栈帧
 */
public class Frame {

    private final Method method;

    private final LocalVariableTable localVars;
    private final OperandStack operandStack;
    private final Map<Integer, Instruction> instructions;
    private final Thread thread;

    private int nextPc;
    private int pc;

    private int stat;


    public Frame(Method method) {
        this.method = method;
        this.localVars = new LocalVariableTable(method.getMaxLocals());
        this.operandStack = new OperandStack(method.getMaxStacks());
        this.thread = TinyJVM.vm.getMainThread(); //todo 不支持多线程
        this.instructions = new HashMap<>(method.getInstructions());
    }

    public Method getMethod() {
        return method;
    }

    public LocalVariableTable getLocalVars() {
        return localVars;
    }

    public OperandStack getOperandStack() {
        return operandStack;
    }

    public Map<Integer, Instruction> getInstructions() {
        return instructions;
    }

    public Thread getThread() {
        return thread;
    }

    public int getNextPc() {
        return nextPc;
    }

    public void setNextPc(int nextPc) {
        this.nextPc = nextPc;
    }

    public int getPc() {
        return pc;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }

    public int getStat() {
        return stat;
    }

    public void setStat(int stat) {
        this.stat = stat;
    }
}