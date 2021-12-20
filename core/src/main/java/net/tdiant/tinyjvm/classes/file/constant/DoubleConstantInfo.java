package net.tdiant.tinyjvm.classes.file.constant;

//CONSTANT_Double_info {
//    u1 tag;
//    u4 high_bytes;
//    u4 low_bytes;
//}

public class DoubleConstantInfo extends ConstantInfo {

    private double bytes;

    public DoubleConstantInfo(int tag, double bytes) {
        super(tag);
        this.bytes = bytes;
    }

    public double getBytes() {
        return bytes;
    }

    public void setBytes(double bytes) {
        this.bytes = bytes;
    }
}
