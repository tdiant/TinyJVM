package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.TinyJVM;
import net.tdiant.tinyjvm.runtime.Clazz;
import net.tdiant.tinyjvm.runtime.Field;
import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.Method;

import java.util.ArrayList;
import java.util.List;

public class GetStaticInstruction extends Instruction {

    public final String clazz;
    public final String fieldName;
    public final String fieldDescriptor;

    public GetStaticInstruction(String clazz, String fieldName, String fieldDescriptor) {
        this.clazz = clazz;
        this.fieldName = fieldName;
        this.fieldDescriptor = fieldDescriptor;
    }

    @Override
    public int delta() {
        return 3;
    }

    @Override
    public void run(Frame frame) {
        Clazz cls = TinyJVM.vm.getHeap().getClazz(clazz);
        if (cls == null) {
            cls = frame.getMethod().getClazz().getClazzLoader().loadClazz(clazz);
        }

        if (cls.getStat() <= 0) {
            Method cinit = cls.getMethod("<clinit>", "()V");
            if (cinit == null) {
                throw new IllegalStateException();
            }

            cls.setStat(1);
            TinyJVM.vm.execute(cinit);
            cls.setStat(2);
        }

        Field field = cls.getField(fieldName, fieldDescriptor);
        if (field == null) {
            if (cls.getInterfaceNames().isEmpty())
                throw new IllegalStateException();

            if (cls.getInterfaces().isEmpty()) {
                List<Clazz> interfaces = new ArrayList<>();
                for (String interfaceName : cls.getInterfaceNames()) {
                    Clazz tmp = TinyJVM.vm.getHeap().getClazz(interfaceName);
                    if (tmp == null)
                        tmp = frame.getMethod().getClazz().getClazzLoader().loadClazz(interfaceName);

                    interfaces.add(tmp);

                    if (tmp.getStat() <= 0) {
                        Method cinit = tmp.getClinitMethod();
                        if (cinit == null) {
                            throw new IllegalStateException();
                        }

                        tmp.setStat(1);
                        TinyJVM.vm.execute(cinit);
                        tmp.setStat(2);
                    }
                }
                cls.setInterfaces(interfaces);
            }

            if (!cls.getInterfaces().isEmpty()) {
                for (Clazz intClass : cls.getInterfaces()) {
                    field = intClass.getField(fieldName, fieldDescriptor);
                    if (field != null) {
                        break;
                    }
                }
            }
        }

        if (field == null || field.getVal() == null) {
            throw new IllegalStateException();
        }

        field.get(frame);
    }


    @Override
    public String toString() {
        return "getstatic " + clazz + " " + fieldName + " " + fieldDescriptor;
    }
}
