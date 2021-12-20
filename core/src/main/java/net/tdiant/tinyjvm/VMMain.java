package net.tdiant.tinyjvm;

import net.tdiant.tinyjvm.runtime.Thread;

public class VMMain {

    private final Thread mainThread;

    public VMMain() {
        this.mainThread = new Thread(VMSettings.maxThreadSize);
    }

    public void run() {

    }

    public Thread getMainThread() {
        return mainThread;
    }
}
