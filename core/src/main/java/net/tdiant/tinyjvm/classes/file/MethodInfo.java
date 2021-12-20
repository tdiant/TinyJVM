package net.tdiant.tinyjvm.classes.file;

import net.tdiant.tinyjvm.classes.file.attr.Attribute;

import java.util.List;

public class MethodInfo {

    private final int accessFlags;
    private final String name;
    private final String descriptor;
    private final List<Attribute> attributes;

    public MethodInfo(int accessFlags, String name, String descriptor, List<Attribute> attributes) {
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
