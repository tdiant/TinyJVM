package net.tdiant.tinyjvm.runtime;

public class Field {

    public String name;
    public String descriptor;

    public int accessFlag;

    public Field(String name, String descriptor, int accessFlag) {
        this.name = name;
        this.descriptor = descriptor;
        this.accessFlag = accessFlag;
    }


}
