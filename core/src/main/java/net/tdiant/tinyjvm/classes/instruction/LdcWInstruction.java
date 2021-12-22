package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.TinyJVM;
import net.tdiant.tinyjvm.runtime.*;

public class LdcWInstruction extends Instruction {

    public final String descriptor;
    public final Object val;

    public LdcWInstruction(String descriptor, Object val) {
        this.descriptor = descriptor;
        this.val = val;
    }

    @Override
    public int delta() {
        return 3;
    }

    @Override
    public void run(Frame frame) {
        switch (descriptor) {
            case "I":
                frame.getOperandStack().push(new Slot((int) val));
                break;
            case "F":
                frame.getOperandStack().push(new Slot((float) val));
                break;
            case "Ljava/lang/String":
                Clazz klass = TinyJVM.vm.getHeap().getClazz("java/lang/String");
                if (klass == null)
                    klass = frame.getMethod().getClazz().getClazzLoader().loadClazz("java/lang/String");

                if (klass.getStat() <= 0) {
                    klass.setStat(1);
                    TinyJVM.vm.execute(klass.getMethod("<clinit>", "()V"));
                    klass.setStat(2);
                }

                Instance object = klass.newInstance();
                Field field = object.getField("value", "[C");
                Clazz arrClazz = new Clazz(1, "[C", frame.getMethod().getClazz().getClazzLoader(), null);

                char[] chars = val.toString().toCharArray();
                Character[] characters = new Character[chars.length];
                for (int i = 0; i < chars.length; i++) {
                    characters[i] = chars[i];
                }
                InstanceArrayInstance arr = new InstanceArrayInstance(arrClazz, characters);
                field.setVal(new Slot(arr));
                frame.getOperandStack().push(new Slot(object));
                break;
            default:
                frame.getOperandStack().push(new Slot((Instance) val));
                break;
        }
    }

    @Override
    public String toString() {
        return "ldcw " + descriptor + " " + val;
    }

}
