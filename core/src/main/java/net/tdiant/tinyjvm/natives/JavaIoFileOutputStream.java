package net.tdiant.tinyjvm.natives;

import net.tdiant.tinyjvm.TinyJVM;
import net.tdiant.tinyjvm.runtime.Field;
import net.tdiant.tinyjvm.runtime.Instance;
import net.tdiant.tinyjvm.runtime.PrimitiveArray;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;

public class JavaIoFileOutputStream {

    public static void registerNatives () {
        TinyJVM.vm.getHeap().registerMethod("java/io/FileOutputStream_open0_(Ljava/lang/String;Z)V", frame -> {
        });

        TinyJVM.vm.getHeap().registerMethod("java/io/FileOutputStream_initIDs_()V", frame -> {
        });

        TinyJVM.vm.getHeap().registerMethod("java/io/FileOutputStream_close0_()V", frame -> {
            // TODO real close
            frame.getOperandStack().popRef();
        });

        TinyJVM.vm.getHeap().registerMethod("java/io/FileDescriptor_sync_()V", frame -> {
            frame.getOperandStack().popRef();
        });

        TinyJVM.vm.getHeap().registerMethod("java/io/FileOutputStream_write_(IZ)V", frame -> {
            boolean append = frame.getOperandStack().popInt() == 1;
            Integer val = frame.getOperandStack().popInt();
            Instance thisObj = frame.getOperandStack().popRef();
            Field fd = thisObj.getField("fd", "Ljava/io/FileDescriptor;");
            Instance fdObj = fd.getVal().getRef();
            int realFd = fdObj.getField("fd", "I").getVal().getInt();

            if (realFd == 1) {
                try {
                    new FileOutputStream(FileDescriptor.out).write(val);
                } catch (IOException e) {
                    throw new IllegalStateException();
                }
            } else if (realFd == 2) {
                try {
                    new FileOutputStream(FileDescriptor.err).write(val);
                } catch (IOException e) {
                    throw new IllegalStateException();
                }
            }
        });

        TinyJVM.vm.getHeap().registerMethod("java/io/FileOutputStream_writeBytes_([BIIZ)V", frame -> {
            boolean append = frame.getOperandStack().popInt() == 1;
            int len = frame.getOperandStack().popInt();
            int off = frame.getOperandStack().popInt();
            PrimitiveArray arg1 = (PrimitiveArray) frame.getOperandStack().popRef();

            byte[] bytes = new byte[len];
            for (int i = off; i < len; i++) {
                bytes[i - off] = (byte) arg1.ints[i];
            }

            Instance thisObj = frame.getOperandStack().popRef();
            Field fd = thisObj.getField("fd", "Ljava/io/FileDescriptor;");
            Instance fdObj = fd.getVal().getRef();
            int realFd = fdObj.getField("fd", "I").getVal().getInt();

            if (realFd == 1) {
                try {
                    new FileOutputStream(FileDescriptor.out).write(bytes);
                } catch (IOException e) {
                    throw new IllegalStateException();
                }
            }
            if (realFd == 2) {
                try {
                    new FileOutputStream(FileDescriptor.err).write(bytes);
                } catch (IOException e) {
                    throw new IllegalStateException();
                }
            }
        });
    }
}
