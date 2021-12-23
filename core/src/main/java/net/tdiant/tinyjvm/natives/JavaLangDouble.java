package net.tdiant.tinyjvm.natives;

import net.tdiant.tinyjvm.TinyJVM;
import net.tdiant.tinyjvm.runtime.Clazz;
import net.tdiant.tinyjvm.runtime.Instance;
import net.tdiant.tinyjvm.runtime.Slot;
import net.tdiant.tinyjvm.runtime.UnionSlot;

public class JavaLangDouble {

    public static void registerNatives() {

        TinyJVM.vm.getHeap().registerMethod("java/lang/Double_doubleToRawLongBits_(D)J", frame -> {
            double tmp = frame.getOperandStack().popDouble();
            long v = java.lang.Double.doubleToRawLongBits(tmp);
            frame.getOperandStack().pushLong(v);
        });

        TinyJVM.vm.getHeap().registerMethod("java/lang/Double_longBitsToDouble_(J)D", frame -> {
            long tmp = frame.getOperandStack().popLong();
            double v = java.lang.Double.longBitsToDouble(tmp);
            frame.getOperandStack().pushDouble(v);
        });

        TinyJVM.vm.getHeap().registerMethod("java/lang/Double_valueOf_(D)Ljava/lang/Double;", frame -> {
            Clazz clazz = TinyJVM.vm.getHeap().getClazz("java/lang/Double");
            Instance instance = clazz.newInstance();
            Slot a = frame.getOperandStack().pop();
            Slot b = frame.getOperandStack().pop();
            instance.setField("value", "D", UnionSlot.of(b, a));
            frame.getOperandStack().push(new Slot(instance));
        });

    }
}
