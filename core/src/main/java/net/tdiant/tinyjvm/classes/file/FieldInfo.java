package net.tdiant.tinyjvm.classes.file;

//field_info {
//    u2             access_flags;
//    u2             name_index;
//    u2             descriptor_index;
//    u2             attributes_count;
//    attribute_info attributes[attributes_count];
//    }

import net.tdiant.tinyjvm.classes.file.attr.Attribute;

import java.util.List;

public class FieldInfo {

    public final int accessFlags;
    public final String name;
    public final String descriptor;
    public final List<Attribute> attributes;

    public FieldInfo(int accessFlags, String name, String descriptor, List<Attribute> attributes) {
        this.accessFlags = accessFlags;
        this.name = name;
        this.descriptor = descriptor;
        this.attributes = attributes;
    }

    public int getAccessFlags() {
        return accessFlags;
    }

    public String getName() {
        return name;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }
}
