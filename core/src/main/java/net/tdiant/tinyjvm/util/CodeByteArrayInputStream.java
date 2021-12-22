package net.tdiant.tinyjvm.util;

import java.io.ByteArrayInputStream;

public class CodeByteArrayInputStream extends ByteArrayInputStream {
    public CodeByteArrayInputStream(byte[] buf) {
        super(buf);
    }

    public int pos() {
        return this.pos;
    }
}
