package net.tdiant.tinyjvm.classes.file.constant;

//CONSTANT_Long_info {
//    u1 tag;
//    u4 high_bytes;
//    u4 low_bytes;
//}

public class LongConstantInfo extends ConstantInfo {

    private long bytes;

    public LongConstantInfo(int tag, long bytes) {
        super(tag);
        this.bytes = bytes;
    }

    public long getBytes() {
        return bytes;
    }

    public void setBytes(long bytes) {
        this.bytes = bytes;
    }

    public long val() {
        return bytes;
    }
}
