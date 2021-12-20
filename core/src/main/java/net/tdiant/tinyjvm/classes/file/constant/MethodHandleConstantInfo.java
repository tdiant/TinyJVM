package net.tdiant.tinyjvm.classes.file.constant;

//CONSTANT_MethodHandle_info {
//    u1 tag;
//    u1 reference_kind;
//    u2 reference_index;
//}

public class MethodHandleConstantInfo extends ConstantInfo {

    private int referenceKind;
    private int referenceIndex;

    public MethodHandleConstantInfo(int tag, int referenceKind, int referenceIndex) {
        super(tag);
        this.referenceKind = referenceKind;
        this.referenceIndex = referenceIndex;
    }

    public int getReferenceKind() {
        return referenceKind;
    }

    public void setReferenceKind(int referenceKind) {
        this.referenceKind = referenceKind;
    }

    public int getReferenceIndex() {
        return referenceIndex;
    }

    public void setReferenceIndex(int referenceIndex) {
        this.referenceIndex = referenceIndex;
    }
}
