package net.tdiant.tinyjvm.classes.file.constant;

// CONSTANT_Class_info {
//    u1 tag;
//    u2 name_index;
//}

public class ClassConstantInfo extends ConstantInfo {

    private int nameIndex; // 类的名字对应的UTF8数据的位置

    public ClassConstantInfo(int tag, int nameIndex) {
        super(tag);
        this.nameIndex = nameIndex;
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public void setNameIndex(int nameIndex) {
        this.nameIndex = nameIndex;
    }
}
