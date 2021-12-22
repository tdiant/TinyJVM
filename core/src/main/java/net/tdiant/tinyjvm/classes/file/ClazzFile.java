package net.tdiant.tinyjvm.classes.file;

import net.tdiant.tinyjvm.classes.file.attr.Attribute;
import net.tdiant.tinyjvm.classes.file.attr.BootstrapMethodsAttribute;
import net.tdiant.tinyjvm.classes.file.constant.ConstantInfo;
import net.tdiant.tinyjvm.classes.loader.ClazzSource;

import java.util.List;

public class ClazzFile {

    public static final int CLASS_LOADED = 1;
    public static final int CLASS_LINKED = 2;
    public static final int CLASS_INIT_NOW = 3;
    public static final int CLASS_INIT_FINISH = 4;

    public static final int ACCESS_STATIC = 0x0008;
    public static final int ACCESS_NATIVE = 0x0100;
    public static final int ACCESS_INTERFACE = 0x0200;
    public static final int ACCESS_ABSTRACT = 0x0400;

    public static final int FAKE_FRAME = 1;
    public static final int FAKE_FRAME_END = 2;

    public static final int CONSTANT_CLASS = 7;
    public static final int CONSTANT_FIELD_REF = 9;
    public static final int CONSTANT_METHOD_REF = 10;
    public static final int CONSTANT_INTERFACE_METHOD_REF = 11;
    public static final int CONSTANT_STRING = 8;
    public static final int CONSTANT_INTEGER = 3;
    public static final int CONSTANT_FLOAT = 4;
    public static final int CONSTANT_LONG = 5;
    public static final int CONSTANT_DOUBLE = 6;
    public static final int CONSTANT_NAME_AND_TAG = 12;
    public static final int CONSTANT_UTF8 = 1;
    public static final int CONSTANT_METHOD_HANDLE = 15;
    public static final int CONSTANT_METHOD_TYPE = 16;
    public static final int CONSTANT_INVOKE_DYNAMIC = 18;

    public ConstantInfo cpInfo;

    private int classFileTag;

    private int minorVer; // 附版本号
    private int majorVer; // 主版本号

    private int constantPoolSize; // 常量池大小
    private ConstantPool constantPool; // 常量池

    private int accessFlags; // 类访问标志
    private int thisClass;// 类索引, 存储当前类名或当前接口名
    private int superClass; // 父类索引, 存储父类名

    private int interfaceCnt; // 接口数量
    private List<InterfaceInfo> interfaceInfos; // 接口对象列表

    private int fieldCnt; // 类字段数量
    private List<FieldInfo> fields; // 类字段列表

    private int methodCnt; // 方法数量
    private List<MethodInfo> methods; // 方法列表

    private int attributeCnt; // 类属性数量
    private List<Attribute> attributes; // 类属性列表

    private String source = "<runtime>";

    public ClazzFile(int classFileTag, int minorVer, int majorVer, int constantPoolSize, ConstantPool constantPool, int accessFlags, int thisClass, int superClass, int interfaceCnt, List<InterfaceInfo> interfaceInfos, int fieldCnt, List<FieldInfo> fields, int methodCnt, List<MethodInfo> methods, int attributeCnt, List<Attribute> attributes) {
        this.classFileTag = classFileTag;
        this.minorVer = minorVer;
        this.majorVer = majorVer;
        this.constantPoolSize = constantPoolSize;
        this.constantPool = constantPool;
        this.accessFlags = accessFlags;
        this.thisClass = thisClass;
        this.superClass = superClass;
        this.interfaceCnt = interfaceCnt;
        this.interfaceInfos = interfaceInfos;
        this.fieldCnt = fieldCnt;
        this.fields = fields;
        this.methodCnt = methodCnt;
        this.methods = methods;
        this.attributeCnt = attributeCnt;
        this.attributes = attributes;
    }

    public BootstrapMethodsAttribute getBootstrapMethods() {
        for (Attribute attribute : attributes) {
            if (attribute instanceof BootstrapMethodsAttribute)
                return (BootstrapMethodsAttribute) attribute;
        }
        return null;
    }

    public ConstantInfo getConstantPoolInfo() {
        return cpInfo;
    }

    public void setConstantPoolInfo(ConstantInfo cpInfo) {
        this.cpInfo = cpInfo;
    }

    public int getClassFileTag() {
        return classFileTag;
    }

    public void setClassFileTag(int classFileTag) {
        this.classFileTag = classFileTag;
    }

    public int getMinorVer() {
        return minorVer;
    }

    public void setMinorVer(int minorVer) {
        this.minorVer = minorVer;
    }

    public int getMajorVer() {
        return majorVer;
    }

    public void setMajorVer(int majorVer) {
        this.majorVer = majorVer;
    }

    public int getConstantPoolSize() {
        return constantPoolSize;
    }

    public void setConstantPoolSize(int constantPoolSize) {
        this.constantPoolSize = constantPoolSize;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public void setConstantPool(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    public int getAccessFlags() {
        return accessFlags;
    }

    public void setAccessFlags(int accessFlags) {
        this.accessFlags = accessFlags;
    }

    public int getThisClass() {
        return thisClass;
    }

    public void setThisClass(int thisClass) {
        this.thisClass = thisClass;
    }

    public int getSuperClass() {
        return superClass;
    }

    public void setSuperClass(int superClass) {
        this.superClass = superClass;
    }

    public int getInterfaceCnt() {
        return interfaceCnt;
    }

    public void setInterfaceCnt(int interfaceCnt) {
        this.interfaceCnt = interfaceCnt;
    }

    public List<InterfaceInfo> getInterfaces() {
        return interfaceInfos;
    }

    public void setInterfaces(List<InterfaceInfo> interfaceInfos) {
        this.interfaceInfos = interfaceInfos;
    }

    public int getFieldCnt() {
        return fieldCnt;
    }

    public void setFieldCnt(int fieldCnt) {
        this.fieldCnt = fieldCnt;
    }

    public List<FieldInfo> getFields() {
        return fields;
    }

    public void setFields(List<FieldInfo> fields) {
        this.fields = fields;
    }

    public int getMethodCnt() {
        return methodCnt;
    }

    public void setMethodCnt(int methodCnt) {
        this.methodCnt = methodCnt;
    }

    public List<MethodInfo> getMethods() {
        return methods;
    }

    public void setMethods(List<MethodInfo> methods) {
        this.methods = methods;
    }

    public int getAttributeCnt() {
        return attributeCnt;
    }

    public void setAttributeCnt(int attributeCnt) {
        this.attributeCnt = attributeCnt;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
