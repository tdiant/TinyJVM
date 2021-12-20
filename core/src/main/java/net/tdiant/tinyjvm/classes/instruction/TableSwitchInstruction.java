package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;

import java.util.Map;

public class TableSwitchInstruction extends Instruction {

    private final int idx;
    private final int def;
    private final int high;
    private final int low;
    private final Map<Integer, Integer> map;

    public TableSwitchInstruction(int idx, int def, int high, int low, Map<Integer, Integer> map) {
        this.idx = idx;
        this.def = def;
        this.high = high;
        this.low = low;
        this.map = map;
    }

    @Override
    public int delta() {
        return idx;
    }

    @Override
    public void run(Frame frame) {
        //todo table switch
        frame.setNextPc(
                frame.getPc() + (
                        map.getOrDefault(
                                frame.getOperandStack().pop().getInt(),
                                def
                        )
                ));
    }

    @Override
    public String toString() {
        return "tableswitch";
    }
}
