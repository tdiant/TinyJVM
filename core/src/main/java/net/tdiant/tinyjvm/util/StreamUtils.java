package net.tdiant.tinyjvm.util;

import java.io.ByteArrayInputStream;

public class StreamUtils {
    public static int readPadding(ByteArrayInputStream in) {
        int offset = 0;
        while (((TmpByteArrayInputStream) in).pos() % 4 != 0) {
            in.read();
            offset++;
        }
        return offset;
    }

    private static class TmpByteArrayInputStream extends ByteArrayInputStream {
        public TmpByteArrayInputStream(byte[] buf) {
            super(buf);
        }

        public int pos() {
            return this.pos;
        }
    }

}
