package net.tdiant.tinyjvm.natives;

import net.tdiant.tinyjvm.TinyJVM;

public class JavaIoFileDescriptor {

    public static void registerNatives() {
        TinyJVM.vm.getHeap().registerEmptyMethod("java/io/FileDescriptor_initIDs_()V");
        TinyJVM.vm.getHeap().registerEmptyMethod("java/io/FileDescriptor_sync_()V");
        TinyJVM.vm.getHeap().registerEmptyMethod("java/io/FileDescriptor_set_(I)J");
    }


}
