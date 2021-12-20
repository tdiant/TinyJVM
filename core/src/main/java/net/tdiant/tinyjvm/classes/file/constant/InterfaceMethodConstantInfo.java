package net.tdiant.tinyjvm.classes.file.constant;

//CONSTANT_InterfaceMethodref_info {
//    u1 tag;
//    u2 class_index;
//    u2 name_and_type_index;
//}

public class InterfaceMethodConstantInfo extends ConstantInfo {

    private int classIndex; // 对应的类ConstantInfo的位置
    private int nameAndTypeIndex; // 对应的NameAndTypeInfo的位置

    public InterfaceMethodConstantInfo(int tag, int classIndex, int nameAndTypeIndex) {
        super(tag);
        this.classIndex = classIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }

    public int getClassIndex() {
        return classIndex;
    }

    public void setClassIndex(int classIndex) {
        this.classIndex = classIndex;
    }

    public int getNameAndTypeIndex() {
        return nameAndTypeIndex;
    }

    public void setNameAndTypeIndex(int nameAndTypeIndex) {
        this.nameAndTypeIndex = nameAndTypeIndex;
    }
}
