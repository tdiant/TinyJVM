package net.tdiant.tinyjvm.natives;

import net.tdiant.tinyjvm.TinyJVM;
import net.tdiant.tinyjvm.runtime.Instance;
import net.tdiant.tinyjvm.runtime.InstanceArrayInstance;
import net.tdiant.tinyjvm.runtime.PrimitiveArray;
import net.tdiant.tinyjvm.runtime.Slot;

public class JavaLangSystem {

    public static void registerNatives() {

        TinyJVM.vm.getHeap().registerEmptyMethod("java/lang/System_registerNatives_()V");
        TinyJVM.vm.getHeap().registerEmptyMethod("java/lang/System_setIn0_(Ljava/io/InputStream;)V");
        TinyJVM.vm.getHeap().registerEmptyMethod("java/lang/System_setOut0_(Ljava/io/PrintStream;)V");
        TinyJVM.vm.getHeap().registerEmptyMethod("java/lang/System_setErr0_(Ljava/io/PrintStream;)V");
        TinyJVM.vm.getHeap().registerEmptyMethod("java/lang/System_initProperties_(Ljava/util/Properties;)Ljava/util/Properties;");
        TinyJVM.vm.getHeap().registerEmptyMethod("java/lang/System_mapLibraryName_(Ljava/lang/String;)Ljava/lang/String;");

        TinyJVM.vm.getHeap().registerMethod("java/lang/System_currentTimeMillis_()J", (frame) -> frame.getOperandStack().push(new Slot(java.lang.System.currentTimeMillis())));
        TinyJVM.vm.getHeap().registerMethod("java/lang/System_nanoTime_()J", (frame) -> frame.getOperandStack().push(new Slot(java.lang.System.nanoTime())));
        TinyJVM.vm.getHeap().registerMethod("java/lang/System_identityHashCode_(Ljava/lang/Object;)I", (frame) -> frame.getOperandStack().push(new Slot(frame.getOperandStack().pop().getInstance().hashCode())));

        TinyJVM.vm.getHeap().registerMethod("java/lang/System_arraycopy_(Ljava/lang/Object;ILjava/lang/Object;II)V", (frame) -> {
            int len = frame.getOperandStack().pop().getInt();
            int dsp = frame.getOperandStack().pop().getInt();
            Instance dest = frame.getOperandStack().pop().getInstance();

            if (dest instanceof InstanceArrayInstance) {
                InstanceArrayInstance da = (InstanceArrayInstance) dest;
                int ssp = frame.getOperandStack().pop().getInt();
                final InstanceArrayInstance sa = (InstanceArrayInstance) frame.getOperandStack().pop().getInstance();
                for (int i = 0; i < len; i++)
                    da.set(dsp++, sa.get(ssp++));

            } else {
                PrimitiveArray da = (PrimitiveArray) dest;
                int ssp = frame.getOperandStack().pop().getInt();
                PrimitiveArray sa = (PrimitiveArray) frame.getOperandStack().pop().getInstance();

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
