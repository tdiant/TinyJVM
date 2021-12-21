package net.tdiant.tinyjvm.natives;

import net.tdiant.tinyjvm.TinyJVM;
import net.tdiant.tinyjvm.runtime.Clazz;
import net.tdiant.tinyjvm.runtime.Instance;
import net.tdiant.tinyjvm.runtime.Slot;

public class JavaLangDouble {

    public static void registerNatives() {

        TinyJVM.vm.getHeap().registerMethod("java/lang/Double_doubleToRawLongBits_(D)J", frame -> {
            double tmp = frame.getOperandStack().pop().getDouble();
            long v = java.lang.Double.doubleToRawLongBits(tmp);
            frame.getOperandStack().push(new Slot(v));
        });

        TinyJVM.vm.getHeap().registerMethod("java/lang/Double_longBitsToDouble_(J)D", frame -> {
            long tmp = frame.getOperandStack().pop().getLong();
            double v = java.lang.Double.longBitsToDouble(tmp);
            frame.getOperandStack().push(new Slot(v));
        });

        TinyJVM.vm.getHeap().registerMethod("java/lang/Double_valueOf_(D)Ljava/lang/Double;", frame -> {
            Clazz clazz = TinyJVM.vm.getHeap().getClazz("java/lang/Double");
            Instance instance = clazz.newInstance();
            Slot a = frame.getOperandStack().pop();
            Slot b = frame.getOperandStack().pop();
            instance.setField("value", "D", new Slot(b.getHigh(), a.getHigh()));
            frame.getOperandStack().push(new Slot(instance));
        });

    }
}
