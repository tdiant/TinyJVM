package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.TinyJVM;
import net.tdiant.tinyjvm.runtime.*;
import net.tdiant.tinyjvm.util.RuntimeUtils;

public class ANewArrayInstruction extends Instruction {

    public final String className;

    public ANewArrayInstruction(String className) {
        this.className = className;
    }

    @Override
    public int delta() {
        return 3;
    }

    @Override
    public void run(Frame frame) {
        Clazz cls = frame.getMethod().getClazz().getClazzLoader().loadClazz(className);
        RuntimeUtils.clinit(cls);

        int count = frame.getOperandStack().pop().getInt();
        String name = "[L" + cls.getName() + ";";

        Clazz clazz = TinyJVM.vm.getHeap().getClazz(name);
        if (clazz == null) {
            clazz = new Clazz(1, name, superClassName, interfaceNames, methods, fields, bootstrapMethods, clzFile.cpInfo, cls.getClazzLoader(), null);
            clazz.setSuperClass(TinyJVM.vm.getHeap().getClazz("java/lang/Object"));
            clazz.setStat(2);
            TinyJVM.vm.getHeap().registerClass(name, clazz);
        }
        Instance[] objs = new Instance[count];
        InstanceArrayInstance instanceArray = new InstanceArrayInstance(clazz, objs);
        frame.getOperandStack().push(new Slot(instanceArray));
    }

    @Override
    public String toString() {
        return "anewarray " + className;
    }
}
