package net.tdiant.tinyjvm.classes.file.constant;

//CONSTANT_Float_info {
//    u1 tag;
//    u4 bytes;
//}

public class FloatConstantInfo extends ConstantInfo {

    private float bytes;

    public FloatConstantInfo(int tag, float bytes) {
        super(tag);
        this.bytes = bytes;
    }

    public float getBytes() {
        return bytes;
    }

    public void setBytes(float bytes) {
        this.bytes = bytes;
    }
}
