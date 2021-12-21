package net.tdiant.tinyjvm;

import net.tdiant.tinyjvm.runtime.Thread;

public class VMMain {

    private final Thread mainThread;
    private final TinyNativeHeap heap;

    public VMMain() {
        this.mainThread = new Thread(VMSettings.maxThreadSize);
        this.heap = new TinyNativeHeap();
    }

    public void run() {

    }

    public Thread getMainThread() {
        return mainThread;
    }

    public TinyNativeHeap getHeap() {
        return heap;
    }

}

