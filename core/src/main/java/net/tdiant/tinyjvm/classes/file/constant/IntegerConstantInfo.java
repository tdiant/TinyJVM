package net.tdiant.tinyjvm.classes.file.constant;

//CONSTANT_Integer_info {
//    u1 tag;
//    u4 bytes;
//}

public class IntegerConstantInfo extends ConstantInfo {

    private int bytes;

    public IntegerConstantInfo(int tag, int bytes) {
        super(tag);
        this.bytes = bytes;
    }

    public int getBytes() {
        return bytes;
    }

    public void setBytes(int bytes) {
        this.bytes = bytes;
    }
}
