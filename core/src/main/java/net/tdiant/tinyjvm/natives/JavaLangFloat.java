package net.tdiant.tinyjvm.natives;

import net.tdiant.tinyjvm.TinyJVM;
import net.tdiant.tinyjvm.runtime.Slot;

public class JavaLangFloat {
    public static void registerNatives() {
        TinyJVM.vm.getHeap().registerMethod("java/lang/Float_intBitsToFloat_(I)F", frame -> {
            int tmp = frame.getOperandStack().popInt();
            float v = java.lang.Float.intBitsToFloat(tmp);
            frame.getOperandStack().pushFloat(v);
        });
        TinyJVM.vm.getHeap().registerMethod("java/lang/Float_floatToRawIntBits_(F)I", frame -> {
            float tmp = frame.getOperandStack().popFloat();
            int v = java.lang.Float.floatToRawIntBits(tmp);
            frame.getOperandStack().push(new Slot(v));
        });
    }
}
