package net.tdiant.tinyjvm.natives;

import net.tdiant.tinyjvm.TinyJVM;
import net.tdiant.tinyjvm.runtime.*;

public class JavaLangSystem {

    public static void registerNatives() {

        TinyJVM.vm.getHeap().registerEmptyMethod("java/lang/System_registerNatives_()V");
        TinyJVM.vm.getHeap().registerEmptyMethod("java/lang/System_setIn0_(Ljava/io/InputStream;)V");
        TinyJVM.vm.getHeap().registerEmptyMethod("java/lang/System_setOut0_(Ljava/io/PrintStream;)V");
        TinyJVM.vm.getHeap().registerEmptyMethod("java/lang/System_setErr0_(Ljava/io/PrintStream;)V");
        TinyJVM.vm.getHeap().registerMethod("java/lang/System_initProperties_(Ljava/util/Properties;)Ljava/util/Properties;", frame -> {
            Clazz cls = TinyJVM.vm.getHeap().getClazz("java/lang/System");
            cls.getField("props", "Ljava/util/Properties").setVal(
                    UnionSlot.of(TinyJVM.vm.getMainThread().now().getOperandStack().popRef())
            );
        });
        TinyJVM.vm.getHeap().registerEmptyMethod("java/lang/System_mapLibraryName_(Ljava/lang/String;)Ljava/lang/String;");

        TinyJVM.vm.getHeap().registerMethod("java/lang/System_currentTimeMillis_()J", (frame) -> frame.getOperandStack().pushLong(java.lang.System.currentTimeMillis()));
        TinyJVM.vm.getHeap().registerMethod("java/lang/System_nanoTime_()J", (frame) -> frame.getOperandStack().pushLong(java.lang.System.nanoTime()));
        TinyJVM.vm.getHeap().registerMethod("java/lang/System_identityHashCode_(Ljava/lang/Object;)I", (frame) -> frame.getOperandStack().pushInt(frame.getOperandStack().popRef().hashCode()));

        TinyJVM.vm.getHeap().registerMethod("java/lang/System_arraycopy_(Ljava/lang/Object;ILjava/lang/Object;II)V", (frame) -> {
            int len = frame.getOperandStack().popInt();
            int dsp = frame.getOperandStack().popInt();
            Instance dest = frame.getOperandStack().popRef();

            if (dest instanceof InstanceArrayInstance) {
                InstanceArrayInstance da = (InstanceArrayInstance) dest;
                int ssp = frame.getOperandStack().popInt();
                final InstanceArrayInstance sa = (InstanceArrayInstance) frame.getOperandStack().popRef();
                for (int i = 0; i < len; i++)
                    da.set(dsp++, sa.get(ssp++));

            } else {
                PrimitiveArray da = (PrimitiveArray) dest;
                int ssp = frame.getOperandStack().popInt();
                PrimitiveArray sa = (PrimitiveArray) frame.getOperandStack().popRef();

                for (int i = 0; i < len; i++) {
                    if (da.ints != null) {
                        da.ints[dsp++] = sa.ints[ssp++];
                    } else if (da.longs != null) {
                        da.longs[dsp++] = sa.longs[ssp++];
                    } else if (da.floats != null) {
                        da.floats[dsp++] = sa.floats[ssp++];
                    } else {
                        da.doubles[dsp++] = sa.doubles[ssp++];
                    }
                }
            }
        });
    }
}
