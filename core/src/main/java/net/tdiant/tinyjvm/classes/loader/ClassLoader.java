package net.tdiant.tinyjvm.classes.loader;

import java.io.DataInputStream;

public class ClassLoader {

    private DataInputStream in;

    public ClassLoader(DataInputStream in) {
        this.in = in;
    }

}
