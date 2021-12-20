package net.tdiant.tinyjvm.classes.file.constant;

//CONSTANT_InvokeDynamic_info {
//    u1 tag;
//    u2 bootstrap_method_attr_index;
//    u2 name_and_type_index;
//}

public class InvokeDynamicConstantInfo extends ConstantInfo {

    private int bootstrapMethodAttrIndex;
    private int nameAndTypeIndex;

    public InvokeDynamicConstantInfo(int tag, int bootstrapMethodAttrIndex, int nameAndTypeIndex) {
        super(tag);
        this.bootstrapMethodAttrIndex = bootstrapMethodAttrIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }

    public int getBootstrapMethodAttrIndex() {
        return bootstrapMethodAttrIndex;
    }

    public void setBootstrapMethodAttrIndex(int bootstrapMethodAttrIndex) {
        this.bootstrapMethodAttrIndex = bootstrapMethodAttrIndex;
    }

    public int getNameAndTypeIndex() {
        return nameAndTypeIndex;
    }

    public void setNameAndTypeIndex(int nameAndTypeIndex) {
        this.nameAndTypeIndex = nameAndTypeIndex;
    }
}
