package net.tdiant.tinyjvm.classes.file.constant;

public class ConstantInfo {

    // 常量标记
    public static final int CONSTANT_TAG_EMPTY = 0;
    public static final int CONSTANT_TAG_UTF8 = 1;
    public static final int CONSTANT_TAG_BOM = 2;
    public static final int CONSTANT_TAG_INTEGER = 3;
    public static final int CONSTANT_TAG_FLOAT = 4;
    public static final int CONSTANT_TAG_LONG = 5;
    public static final int CONSTANT_TAG_DOUBLE = 6;
    public static final int CONSTANT_TAG_CLASS = 7;
    public static final int CONSTANT_TAG_STRING = 8;
    public static final int CONSTANT_TAG_FIELD = 9;
    public static final int CONSTANT_TAG_METHOD = 10;
    public static final int CONSTANT_TAG_INTERFACE_METHOD = 11;
    public static final int CONSTANT_TAG_NAME_AND_TYPE = 12;
    public static final int CONSTANT_TAG_METHOD_HANDLE = 15;
    public static final int CONSTANT_TAG_METHOD_TYPE = 16;
    public static final int CONSTANT_TAG_INVOKE_DYNAMIC = 18;

    private final int tag;

    public ConstantInfo(int tag) {
        this.tag = tag;
    }

    public int getTag() {
        return tag;
    }
}
