package net.tdiant.tinyjvm.natives;

import net.tdiant.tinyjvm.TinyJVM;
import net.tdiant.tinyjvm.runtime.Field;
import net.tdiant.tinyjvm.runtime.Instance;
import net.tdiant.tinyjvm.runtime.UnionSlot;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

public class SunMiscUnsafe {

    private static Map<Long, byte[]> mem = new HashMap<>();
    private static Long next = 1L;

    public static void registerNatives0() {
        TinyJVM.vm.getHeap().registerMethod("sun/misc/Unsafe_registerNatives_()V", frame -> {
        });
        TinyJVM.vm.getHeap().registerMethod("sun/misc/Unsafe_getUnsafe_()Lsun/misc/Unsafe;", frame -> {
            frame.getOperandStack().pushRef(null);
        });
        TinyJVM.vm.getHeap().registerMethod("sun/misc/Unsafe_objectFieldOffset_(Ljava/lang/reflect/Field;)J", frame -> {
            frame.getOperandStack().popRef();
            frame.getOperandStack().popRef();
            frame.getOperandStack().pushLong(1L);
        });
        TinyJVM.vm.getHeap().registerMethod("sun/misc/Unsafe_getAndAddInt_(Ljava/lang/Object;JI)I", frame -> {
            Integer delta = frame.getOperandStack().popInt();
            Long offset = frame.getOperandStack().popLong();
            Instance obj = (Instance) frame.getOperandStack().popRef();
            Object thisObj = frame.getOperandStack().popRef();

            Field field = obj.getField("value", "I");
            int val = field.getVal().getInt() + delta;
            field.setVal(UnionSlot.of(val));
            frame.getOperandStack().pushInt(val - delta);
        });

        TinyJVM.vm.getHeap().registerMethod("sun/misc/Unsafe_allocateMemory_(J)J", frame -> {
            Long val = frame.getOperandStack().popLong();
            frame.getOperandStack().popRef();

            byte[] data = new byte[val.intValue()];
            mem.put(next, data);
            next += val.intValue();

            frame.getOperandStack().pushLong(val);
        });

        TinyJVM.vm.getHeap().registerMethod("sun/misc/Unsafe_putLong_(JJ)V", frame -> {
            Long v2 = frame.getOperandStack().popLong();
            Long v1 = frame.getOperandStack().popLong();
            frame.getOperandStack().popRef(); // this

            ByteBuffer buffer = ByteBuffer.allocate(8);
            buffer.putLong(0, v2);
            byte[] bytes = buffer.array();
            mem.put(v1, bytes);
        });

        TinyJVM.vm.getHeap().registerMethod("sun/misc/Unsafe_getByte_(J)B", frame -> {
            Long arg = frame.getOperandStack().popLong();
            frame.getOperandStack().popRef();

            byte[] bytes = mem.get(arg);
            byte b = bytes[0];
            frame.getOperandStack().pushInt(((int) (b)));
        });
    }
}
