package net.tdiant.tinyjvm.classes.file;

import net.tdiant.tinyjvm.classes.file.attr.Attribute;

import java.util.List;

public class ClassFile {

    private int classFileTag;

    private int minorVer; // 附版本号
    private int majorVer; // 主版本号

    private int constantPoolSize; // 常量池大小
    private ConstantPool constantPool; // 常量池

    private int accessFlag; // 类访问标志
    private int thisClass;// 类索引, 存储当前类名或当前接口名
    private int superClass; // 父类索引, 存储父类名

    private int interfaceCnt; // 接口数量
    private List<Interface> interfaces; // 接口对象列表

    private int fieldCnt; // 类字段数量
    private List<FieldInfo> fields; // 类字段列表

    private int methodCnt; // 方法数量
    private List<MethodInfo> methods; // 方法列表

    private int attributeCnt; // 类属性数量
    private List<Attribute> attributes; // 类属性列表

    public ClassFile(int classFileTag, int minorVer, int majorVer, int constantPoolSize, ConstantPool constantPool, int accessFlag, int thisClass, int superClass, int interfaceCnt, List<Interface> interfaces, int fieldCnt, List<FieldInfo> fields, int methodCnt, List<MethodInfo> methods, int attributeCnt, List<Attribute> attributes) {
        this.classFileTag = classFileTag;
        this.minorVer = minorVer;
        this.majorVer = majorVer;
        this.constantPoolSize = constantPoolSize;
        this.constantPool = constantPool;
        this.accessFlag = accessFlag;
        this.thisClass = thisClass;
        this.superClass = superClass;
        this.interfaceCnt = interfaceCnt;
        this.interfaces = interfaces;
        this.fieldCnt = fieldCnt;
        this.fields = fields;
        this.methodCnt = methodCnt;
        this.methods = methods;
        this.attributeCnt = attributeCnt;
        this.attributes = attributes;
    }
}
