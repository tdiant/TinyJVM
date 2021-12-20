package net.tdiant.tinyjvm.runtime;

public class BaseNametag {

    private int accessFlags;
    private String name;
    private String descriptor;

    public BaseNametag(int accessFlags, String name, String descriptor) {
        this.accessFlags = accessFlags;
        this.name = name;
        this.descriptor = descriptor;
    }

    public int getAccessFlags() {
        return accessFlags;
    }

    public void setAccessFlags(int accessFlags) {
        this.accessFlags = accessFlags;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }
}
