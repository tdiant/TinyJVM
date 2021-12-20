package net.tdiant.tinyjvm.runtime;

import java.util.ArrayList;
import java.util.List;

public class Instance {

    private final List<Field> fields;
    private final Clazz clazz;

    public Instance(Clazz clazz) {
        this.clazz = clazz;
        fields = new ArrayList<>();

    }

    public Instance(List<Field> fields, Clazz clazz) {
        this.fields = fields;
        this.clazz = clazz;
    }

    public List<Field> getFields() {
        return fields;
    }

    public Clazz getClazz() {
        return clazz;
    }
}
