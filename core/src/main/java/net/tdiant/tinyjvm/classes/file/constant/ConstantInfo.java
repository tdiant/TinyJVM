package net.tdiant.tinyjvm.classes.file.constant;

public class ConstantInfo {

    private final int tag;

    public ConstantInfo(int tag) {
        this.tag = tag;
    }

    public int getTag() {
        return tag;
    }
}
