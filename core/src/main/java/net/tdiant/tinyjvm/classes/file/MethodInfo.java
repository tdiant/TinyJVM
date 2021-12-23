package net.tdiant.tinyjvm.classes.file;

import net.tdiant.tinyjvm.classes.file.attr.Attribute;
import net.tdiant.tinyjvm.classes.file.attr.CodeAttribute;
import net.tdiant.tinyjvm.classes.file.attr.LineNumTableAttribute;

import java.util.List;

//method_info {
//    u2             access_flags;
//    u2             name_index;
//    u2             descriptor_index;
//    u2             attributes_count;
//    attribute_info attributes[attributes_count];
//    }
public class MethodInfo {

    public final int accessFlags;
    public final String name;
    public final String descriptor;
    public final List<Attribute> attributes;

    public MethodInfo(int accessFlags, String name, String descriptor, List<Attribute> attributes) {
        this.accessFlags = accessFlags;
        this.name = name;
        this.descriptor = descriptor;
        this.attributes = attributes;
    }

    public CodeAttribute getCode() {
        for (Attribute attribute : attributes) {
            if (attribute instanceof CodeAttribute) {
                return ((CodeAttribute) attribute);
            }
        }
        return null;
    }

    public LineNumTableAttribute getLineNumber() {
        if (this.getCode() == null)
            return null;

        for (Attribute attribute : this.getCode().getAttributes()) {
            if (attribute instanceof LineNumTableAttribute)
                return ((LineNumTableAttribute) attribute);
        }

        return null;
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
