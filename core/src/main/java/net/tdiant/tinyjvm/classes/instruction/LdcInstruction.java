package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.TinyJVM;
import net.tdiant.tinyjvm.runtime.*;

public class LdcInstruction extends Instruction {

    private final String descriptor;
    private final Object val;

    public LdcInstruction(String descriptor, Object val) {
        this.descriptor = descriptor;
        this.val = val;
    }

    @Override
    public int delta() {
        return 2;
    }

    @Override
    public void run(Frame frame) {
        switch (descriptor) {
            case "I":
                frame.getOperandStack().pushInt(((Integer) val));
                break;
            case "F":
                frame.getOperandStack().pushFloat(((float) val));
                break;
            case "Ljava/lang/String":
                Clazz klass = TinyJVM.vm.getHeap().getClazz("java/lang/String");
                if (klass == null) {
                    klass = frame.getMethod().getClazz().getClazzLoader().loadClazz("java/lang/String");
                }
                if (klass.getStat() <= 0) {
                    Frame newFrame = new Frame(klass.getMethod("<clinit>", "()V"));
                    klass.setStat(1);
                    Clazz finalKlass = klass;
                    newFrame.setOnPop(() -> finalKlass.setStat(2));
                    frame.getThread().push(newFrame);

                    frame.setNextPc(frame.getPc());
                    return;
                }
                Instance object = klass.newInstance();
                Field field = object.getField("value", "[C");
                char[] chars = val.toString().toCharArray();

                final PrimitiveArray array = PrimitiveArray.charArray(chars.length);
                for (int i = 0; i < array.getLength(); i++) {
                    array.ints[i] = chars[i];
                }

                field.setVal(UnionSlot.of(array));
                frame.getOperandStack().pushRef(object);
                break;
            case "L":
                Clazz klass2 = TinyJVM.vm.getHeap().getClazz(val.toString());
                if (klass2 == null) {
                    klass2 = frame.getMethod().getClazz().getClazzLoader().loadClazz(val.toString());
                }
                frame.getOperandStack().pushRef(klass2.getRuntimeClass());
                break;
            default:
                frame.getOperandStack().pushRef((Instance) val);
                break;
        }
    }

    @Override
    public String toString() {
        return "ldc";
    }
}
