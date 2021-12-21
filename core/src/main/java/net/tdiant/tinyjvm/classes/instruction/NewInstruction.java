package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.TinyJVM;
import net.tdiant.tinyjvm.classes.loader.ClazzLoader;
import net.tdiant.tinyjvm.runtime.*;

public class NewInstruction extends Instruction {

    public final String clazz;

    public NewInstruction(String clazz) {
        this.clazz = clazz;
    }

    @Override
    public int delta() {
        return 3;
    }

    @Override
    public void run(Frame frame) {
        Clazz cls = TinyJVM.vm.getHeap().getClazz(clazz);

        if (cls == null) {
            ClazzLoader loader = frame.getMethod().getClazz().getClazzLoader();
            cls = loader.loadClazz(clazz);
        }

        if (cls == null)
            throw new IllegalStateException(ClassNotFoundException.class.getName());

        if (cls.getStat() <= 0) {
            Method cinit = cls.getClinitMethod();
            if (cinit == null) {
                cls.setStat(2);
                frame.setNextPc(frame.getPc());
                return;
            }

            String clNm = cinit.nativeMethodKey();
            NativeMethod clm = TinyJVM.vm.getHeap().getMethod(clNm);
            if (clm != null) {
                clm.invoke(frame);
            } else {
                cls.setStat(1);
                TinyJVM.vm.execute(cinit);
                cls.setStat(2);
            }
        }

        Instance obj = cls.newInstance();
        frame.getOperandStack().push(new Slot(obj));
    }

    @Override
    public String toString() {
        return "new " + clazz;
    }
}
