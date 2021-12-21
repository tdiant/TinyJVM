package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;

import java.util.Map;

public class LookupSwitchInstruction extends Instruction {

    public final int offset;
    public final int def;
    public final int pairsCnt;
    public final Map<Integer, Integer> table;

    public LookupSwitchInstruction(int offset, int def, int pairsCnt, Map<Integer, Integer> table) {
        this.offset = offset;
        this.def = def;
        this.pairsCnt = pairsCnt;
        this.table = table;
    }

    @Override
    public int delta() {
        return this.offset;
    }

    @Override
    public void run(Frame frame) {
        Integer tmp = frame.getOperandStack().pop().getInt();
        Integer jump = table.getOrDefault(tmp, def);
        frame.setNextPc(frame.getPc() + jump);
    }

    @Override
    public String toString() {
        return "lookupswitch " + offset + " " + def + " " + pairsCnt + " " + table;
    }
}
