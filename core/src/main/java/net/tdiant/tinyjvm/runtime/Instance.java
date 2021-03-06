package net.tdiant.tinyjvm.runtime;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 实例对象
 */
public class Instance {

    private final List<Field> fields;
    private final Clazz clazz;

    private Instance superInstance;

    private Clazz metaClass; //class instance only
    private Object extra; //native only

    public Instance(Clazz clazz) {
        this.clazz = clazz;
        fields = new ArrayList<>();
    }

    public Instance(List<Field> fields, Clazz clazz) {
        this.fields = fields;
        this.clazz = clazz;
    }

    public Field getField(String name, String descriptor) {
        for (Field field : fields) {
            if (Objects.equals(field.getName(), name) && Objects.equals(field.getDescriptor(), descriptor))
                return field;
        }

        if (this.getSuperInstance() == null)
            return null;

        return this.superInstance.getField(name, descriptor);
    }

    public void setField(String name, String descriptor, UnionSlot val) {
        Field field = getField(name, descriptor);
        this.setField(field, val);
    }

    public void setField(Field field, UnionSlot val) {
        field.setVal(val);
    }

    public List<Field> getFields() {
        return fields;
    }

    public Clazz getClazz() {
        return clazz;
    }

    public Instance getSuperInstance() {
        return superInstance;
    }

    public void setSuperInstance(Instance superInstance) {
        this.superInstance = superInstance;
    }

    public Clazz getMetaClass() {
        return metaClass;
    }

    public void setMetaClass(Clazz metaClass) {
        this.metaClass = metaClass;
    }

    public Object getExtra() {
        return extra;
    }

    public void setExtra(Object extra) {
        this.extra = extra;
    }

    @Override
    public String toString() {
        return "Instance{" +
//                "fields=" + fields +
                ", clazz=" + clazz.getName() +
                '}';
    }
}
