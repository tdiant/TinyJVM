package net.tdiant.tinyjvm.classes.file.constant;

//CONSTANT_Utf8_info {
//    u1 tag;
//    u2 length;
//    u1 bytes[length];
//}

import java.io.ByteArrayInputStream;

public class Utf8ConstantInfo extends ConstantInfo {

    private int length;
    private byte[] bytes;

    public Utf8ConstantInfo(int tag, int length, byte[] bytes) {
        super(tag);
        this.length = length;
        this.bytes = bytes;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public String str() {
        StringBuilder sb = new StringBuilder();
        ByteArrayInputStream stream = new ByteArrayInputStream(bytes);
        while (stream.available() > 0) {
            int b = stream.read();
            if (b > 0)
                sb.append((char) b); //ASCII
            else {
                // UTF8
                int b2 = stream.read();
                if ((b2 & 0xf0) != 0xe0) {
                    sb.append((char) ((b & 0x1F) << 6 | b2 & 0x3F));
                } else {
                    int b3 = stream.read();
                    sb.append((char) ((b & 0x0F) << 12 | (b2 & 0x3F) << 6 | b3 & 0x3F));
                }
            }
        }
        return sb.toString();
    }

}
