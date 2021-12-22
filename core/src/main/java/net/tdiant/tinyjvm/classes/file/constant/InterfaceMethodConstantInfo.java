package net.tdiant.tinyjvm.classes.file.constant;

//CONSTANT_InterfaceMethodref_info {
//    u1 tag;
//    u2 class_index;
//    u2 name_and_type_index;
//}

public class InterfaceMethodConstantInfo extends MethodConstantInfo {

    public InterfaceMethodConstantInfo(int tag, int classIndex, int nameAndTypeIndex) {
        super(tag, classIndex, nameAndTypeIndex);
    }
}
