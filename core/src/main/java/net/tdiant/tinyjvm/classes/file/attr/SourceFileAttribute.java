package net.tdiant.tinyjvm.classes.file.attr;

//SourceFile_attribute {
//    u2 attribute_name_index;
//    u4 attribute_length;
//    u2 sourcefile_index;
//}

public class SourceFileAttribute extends Attribute {

    public final String fileName;

    public SourceFileAttribute(String fileName) {
        this.fileName = fileName;
    }

}
