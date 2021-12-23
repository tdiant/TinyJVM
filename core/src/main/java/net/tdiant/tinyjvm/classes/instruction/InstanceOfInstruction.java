package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.Instance;
import net.tdiant.tinyjvm.runtime.Slot;

public class InstanceOfInstruction extends Instruction {

    public final String clazz;

    public InstanceOfInstruction(String clazz) {
        this.clazz = clazz;
    }

    @Override
    public int delta() {
        return 3;
    }

    @Override
    public void run(Frame frame) {
        Instance ins = frame.getOperandStack().popRef();
        if (ins == null) {
            frame.getOperandStack().push(new Slot(0));
            return;
        }

        if (!ins.getClazz().instanceOf(clazz)) {
            frame.getOperandStack().push(new Slot(0));
        } else {
            frame.getOperandStack().push(new Slot(1));
        }
    }

    @Override
    public String toString() {
        return "instanceof " + clazz;
    }
}
