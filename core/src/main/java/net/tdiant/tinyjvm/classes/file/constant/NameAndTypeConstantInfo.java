package net.tdiant.tinyjvm.classes.file.constant;

//CONSTANT_NameAndType_info {
//    u1 tag;
//    u2 name_index;
//    u2 descriptor_index;
//}

public class NameAndTypeConstantInfo extends ConstantInfo {

    private int nameIndex;
    private int descriptorIndex;

    public NameAndTypeConstantInfo(int tag, int nameIndex, int descriptorIndex) {
        super(tag);
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public void setNameIndex(int nameIndex) {
        this.nameIndex = nameIndex;
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }

    public void setDescriptorIndex(int descriptorIndex) {
        this.descriptorIndex = descriptorIndex;
    }
}
