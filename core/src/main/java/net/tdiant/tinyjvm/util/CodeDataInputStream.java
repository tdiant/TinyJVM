package net.tdiant.tinyjvm.util;

import java.io.DataInputStream;
import java.io.IOException;

public class CodeDataInputStream extends DataInputStream {

    public CodeDataInputStream(CodeByteArrayInputStream in) {
        super(in);
    }

    public int readPadding() {
        int offset = 0;
        while (((CodeByteArrayInputStream) in).pos() % 4 != 0) {
            try {
                in.read();
                offset++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return offset;
    }
}
