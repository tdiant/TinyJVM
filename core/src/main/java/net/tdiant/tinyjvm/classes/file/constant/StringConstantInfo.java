package net.tdiant.tinyjvm.classes.file.constant;

//CONSTANT_String_info {
//    u1 tag;
//    u2 string_index;
//}

public class StringConstantInfo extends ConstantInfo {

    private int stringIndex; // UTF8数据位置

    public StringConstantInfo(int tag, int stringIndex) {
        super(tag);
        this.stringIndex = stringIndex;
    }

    public int getStringIndex() {
        return stringIndex;
    }

    public void setStringIndex(int stringIndex) {
        this.stringIndex = stringIndex;
    }
}
