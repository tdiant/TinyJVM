package net.tdiant.tinyjvm.classes.file;

import net.tdiant.tinyjvm.classes.file.constant.ClassConstantInfo;
import net.tdiant.tinyjvm.classes.file.constant.ConstantInfo;
import net.tdiant.tinyjvm.classes.file.constant.NameAndTypeConstantInfo;
import net.tdiant.tinyjvm.classes.file.constant.Utf8ConstantInfo;

import java.util.ArrayList;
import java.util.List;

public class ConstantPool {

    private int size;
    private List<ConstantInfo> list = new ArrayList<>();

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
        Utf8ConstantInfo utf8 = (Utf8ConstantInfo) list.get(inf.getNameIndex());
        return utf8.str();
    }

    public String getString(int idx) {
        Utf8ConstantInfo info = (Utf8ConstantInfo) list.get(idx - 1);
        return info.str();
    }

}
