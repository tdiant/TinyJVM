package net.tdiant.tinyjvm.classes.file.constant;

//CONSTANT_MethodType_info {
//    u1 tag;
//    u2 descriptor_index;
//}

public class MethodTypeConstantInfo extends ConstantInfo {

    private int descriptorIndex;

    public MethodTypeConstantInfo(int tag, int descriptorIndex) {
        super(tag);
        this.descriptorIndex = descriptorIndex;
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }

    public void setDescriptorIndex(int descriptorIndex) {
        this.descriptorIndex = descriptorIndex;
    }
}
