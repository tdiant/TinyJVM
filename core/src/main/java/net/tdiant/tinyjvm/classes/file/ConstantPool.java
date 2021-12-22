package net.tdiant.tinyjvm.classes.file;

import net.tdiant.tinyjvm.classes.file.constant.*;

import java.util.ArrayList;
import java.util.List;

public class ConstantPool {

    private final int size;
    private final List<ConstantInfo> list = new ArrayList<>();

    public ConstantPool(int size) {
        this.size = size;
    }

    public void push(ConstantInfo info) {
        list.add(info);
    }

    public ConstantInfo get(int idx) {
        return list.get(idx);
    }

    public String getClassName(int classIdx) {
        ClassConstantInfo inf = (ClassConstantInfo) list.get(classIdx - 1);
        Utf8ConstantInfo utf8 = (Utf8ConstantInfo) list.get(inf.getNameIndex() - 1);
        return utf8.str();
    }

    public String getString(int idx) {
        Utf8ConstantInfo info = (Utf8ConstantInfo) list.get(idx - 1);
        return info.str();
    }

    public String getNameByNameAndTypeIdx(int natIdx) {
        int nameIndex = ((NameAndTypeConstantInfo) this.get(natIdx - 1)).getNameIndex();
        return getString(nameIndex);
    }

    public String getClassNameByFieldDefIdx(int idx) {
        FieldConstantInfo info = (FieldConstantInfo) this.get(idx - 1);
        return getClassName(info.getClassIndex());
    }

    public String getTypeByNameAndTypeIdx(int natIdx) {
        int idx = ((NameAndTypeConstantInfo) this.get(natIdx - 1)).getDescriptorIndex();
        return getString(idx);
    }

    public String getMethodNameByFieldDefIdx(int idx) {
        FieldConstantInfo info = (FieldConstantInfo) this.get(idx - 1);
        int nameIndex = ((NameAndTypeConstantInfo) this.get(info.getNameAndTypeIndex() - 1)).getNameIndex();
        return getString(nameIndex);
    }

    public String getMethodTypeByFieldDefIdx(int idx) {
        FieldConstantInfo info = (FieldConstantInfo) this.get(idx - 1);
        int descriptorIdx = ((NameAndTypeConstantInfo) this.get(info.getNameAndTypeIndex() - 1)).getDescriptorIndex();
        return getString(descriptorIdx);
    }

    public String getClassNameByMethodDefIdx(int mdIdx) {
        System.out.println(mdIdx);
        MethodConstantInfo info = (MethodConstantInfo) this.get(mdIdx - 1);
        return getClassName(info.getClassIndex());
    }

    public String getMethodNameByMethodDefIdx(int mdIdx) {
        MethodConstantInfo info = (MethodConstantInfo) this.get(mdIdx - 1);
        return getNameByNameAndTypeIdx(info.getNameAndTypeIndex());
    }

    public String getMethodTypeByMethodDefIdx(int mdIdx) {
        MethodConstantInfo info = (MethodConstantInfo) this.get(mdIdx - 1);
        return getTypeByNameAndTypeIdx(info.getNameAndTypeIndex());
    }

    public String getClassNameByIMethodDefIdx(int mdIdx) {
        InterfaceMethodConstantInfo info = (InterfaceMethodConstantInfo) this.get(mdIdx - 1);
        return getClassName(info.getClassIndex());
    }

    public String getMethodNameByIMethodDefIdx(int mdIdx) {
        InterfaceMethodConstantInfo info = (InterfaceMethodConstantInfo) this.get(mdIdx - 1);
        return getNameByNameAndTypeIdx(info.getNameAndTypeIndex());
    }

    public String getMethodTypeByIMethodDefIdx(int mdIdx) {
        InterfaceMethodConstantInfo info = (InterfaceMethodConstantInfo) this.get(mdIdx - 1);
        return getTypeByNameAndTypeIdx(info.getNameAndTypeIndex());
    }

}
