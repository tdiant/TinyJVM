package net.tdiant.tinyjvm.runtime;

import net.tdiant.tinyjvm.classes.file.ClazzFile;
import net.tdiant.tinyjvm.classes.file.ConstantPool;
import net.tdiant.tinyjvm.classes.file.attr.BootstrapMethodsAttribute;
import net.tdiant.tinyjvm.classes.loader.ClazzLoader;

import java.util.List;

public class Clazz extends BaseNametag {

    private final String superClassName;
    private final List<String> interfaceNames;
    private final List<Method> methods;
    private final List<Field> fields;
    private final BootstrapMethodsAttribute bootstrapMethods;
    private final ConstantPool constantPool;
    private final ClazzLoader clazzLoader;
    private final ClazzFile clazzFile;

    private Clazz superClass;
    private List<Clazz> interfaces;
    public int stat = 0;

    private Instance runtimeClass;

    public Clazz(int accessFlags, String name, ClazzLoader clazzLoader, ClazzFile clazzFile) {
        super(accessFlags, name, descriptor);
        this.superClassName = superClassName;
        this.interfaceNames = interfaceNames;
        this.methods = methods;
        this.fields = fields;
        this.bootstrapMethods = bootstrapMethods;
        this.constantPool = constantPool;
        this.clazzLoader = clazzLoader;
        this.clazzFile = clazzFile;
        this.superClass = superClass;
        this.interfaces = interfaces;
        this.stat = stat;
        this.runtimeClass = runtimeClass;
    }

    public String getSuperClassName() {
        return superClassName;
    }

    public List<String> getInterfaceNames() {
        return interfaceNames;
    }

    public List<Method> getMethods() {
        return methods;
    }

    public List<Field> getFields() {
        return fields;
    }

    public BootstrapMethodsAttribute getBootstrapMethods() {
        return bootstrapMethods;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public ClazzLoader getClazzLoader() {
        return clazzLoader;
    }

    public ClazzFile getClazzFile() {
        return clazzFile;
    }

    public Clazz getSuperClass() {
        return superClass;
    }

    public void setSuperClass(Clazz superClass) {
        this.superClass = superClass;
    }

    public List<Clazz> getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(List<Clazz> interfaces) {
        this.interfaces = interfaces;
    }

    public int getStat() {
        return stat;
    }

    public void setStat(int stat) {
        this.stat = stat;
    }

    public Instance getRuntimeClass() {
        return runtimeClass;
    }

    public void setRuntimeClass(Instance runtimeClass) {
        this.runtimeClass = runtimeClass;
    }

    public Field getField(String fieldName, String fieldDescriptor) {
    }

    public boolean instanceOf(String clazz) {

    }

    public Method getMethod(String s, String s1) {
        return null;
    }

    public Instance newInstance() {
    }

    public Method getClinitMethod() {
    }

    public void interfaceInit(Frame frame) {
    }
}
