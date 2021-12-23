package net.tdiant.tinyjvm.runtime;

import net.tdiant.tinyjvm.TinyJVM;
import net.tdiant.tinyjvm.classes.file.ClazzFile;
import net.tdiant.tinyjvm.classes.file.ConstantPool;
import net.tdiant.tinyjvm.classes.file.attr.BootstrapMethodsAttribute;
import net.tdiant.tinyjvm.classes.loader.ClazzLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 类实例
 */
public class Clazz extends BaseNametag {

    private final String superClassName;
    private final List<String> interfaceNames;
    private final List<Method> methods;
    private final List<Field> fields;
    private final BootstrapMethodsAttribute bootstrapMethods;
    private final ConstantPool constantPool;
    private final ClazzLoader clazzLoader;
    private final ClazzFile clazzFile;
    public int stat = 0;
    private Clazz superClass;
    private List<Clazz> interfaces;
    private Instance runtimeClass;

    public Clazz(int accessFlags, String name, String superClassName, List<String> interfaceNames, List<Method> methods, List<Field> fields, BootstrapMethodsAttribute bootstrapMethods, ConstantPool constantPool, ClazzLoader classLoader, ClazzFile classFile) {
        super(accessFlags, name, "");
        this.superClassName = superClassName;
        this.interfaceNames = interfaceNames;
        this.clazzFile = classFile;
        this.interfaces = new ArrayList<>();
        this.methods = methods;
        this.fields = fields;
        this.bootstrapMethods = bootstrapMethods;
        this.constantPool = constantPool;
        this.clazzLoader = classLoader;
        methods.forEach(it -> it.setClazz(this));
    }

    public Clazz(int accessFlags, String name, ClazzLoader classLoader, ClazzFile clazzFile) {
        super(accessFlags, name, "");
        this.superClassName = "java/lang/Object";
        this.interfaceNames = new ArrayList<>();
        this.interfaces = new ArrayList<>();
        this.bootstrapMethods = null;
        this.constantPool = null;
        this.clazzLoader = classLoader;
        this.clazzFile = clazzFile;
        this.methods = new ArrayList<>();
        this.fields = new ArrayList<>();
        this.stat = 2;
    }

    public Clazz(int accessFlags, String name, ClazzLoader classLoader) {
        this(accessFlags, name, classLoader, null);
    }

    /**
     * 获取类方法
     */
    public Method getMethod(String name, String descriptor) {
        return this.getMethod(name, descriptor, true);
    }

    /**
     * 获取类方法
     *
     * @param noAllDeclare 是否搜寻全部定义的方法，如果为false，将不下溯父类和接口实现
     */
    public Method getMethod(String name, String descriptor, boolean noAllDeclare) {
        for (Method m : methods) {
            if (Objects.equals(m.getName(), name) && Objects.equals(m.getDescriptor(), descriptor))
                return m;
        }
//        return null;
        if (!noAllDeclare) return null;

        for (Clazz inter : this.interfaces) {
            Method method = inter.getMethod(name, descriptor);
            if (method != null)
                return method;
        }

        if (this.superClass == null)
            return null;

        return this.superClass.getMethod(name, descriptor);
    }

    /**
     * 获取类的初始化构造方法
     */
    public Method getClinitMethod() {
        return this.getMethod("<clinit>", "()V");
    }

    /**
     * 获取类的main方法
     */
    public Method getMainMethod() {
        return this.getMethod("main", "([Ljava/lang/String;)V", false);
    }

    /**
     * 获取类字段
     */
    public Field getField(String name, String descriptor) {
        for (Field field : fields) {
            if (Objects.equals(field.getName(), name) && Objects
                    .equals(field.getDescriptor(), descriptor))
                return field;
        }
        return null;
    }

    /**
     * 获取类字段
     */
    public Field getField(String name) {
        for (Field field : fields) {
            if (Objects.equals(field.getName(), name))
                return field;
        }
        return null;
    }

    /**
     * 创建类实例
     */
    public Instance newInstance() {
        List<Field> newFields = new ArrayList<>();
        for (Field field : fields)
            newFields.add(this.map(field));
        Instance ins = new Instance(newFields, this);
        if (this.superClass != null) {
            ins.setSuperInstance(this.superClass.newInstance());
        }
        return ins;
    }

    public boolean instanceOf(String clazz) {
        if (this.getName().equals(clazz))
            return true;

        if (this.superClass != null)
            return this.superClass.instanceOf(clazz);

        for (String interfaceName : this.interfaceNames) {
            if (Objects.equals(interfaceName, clazz))
                return true;
        }

        return false;
    }

    public boolean isInterface() {
        return (getAccessFlags() & 0x0200) != 0;
    }

    public boolean isPrimitive() {
        return (getAccessFlags() & 0x1000) != 0;
    }

    public void interfaceInit(Frame frame) {
        List<Clazz> interfaces = new ArrayList<>();
        for (String interfaceName : this.interfaceNames) {
            Clazz tmp = TinyJVM.vm.getHeap().getClazz(interfaceName);

            if (tmp == null)
                tmp = frame.getMethod().getClazz().getClazzLoader().loadClazz(interfaceName);

            interfaces.add(tmp);
            tmp.interfaceInit(frame);

            if (tmp.getStat() <= 0) {
                Method cinit = tmp.getClinitMethod();

                if (cinit == null)
                    throw new IllegalStateException();

                tmp.setStat(1);
                TinyJVM.vm.execute(cinit);
                tmp.setStat(2);
            }
        }
        this.setInterfaces(interfaces);
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

    private Field map(Field source) {
        if (source.isStatic())
            return source;
        final Field field = new Field(source.getAccessFlags(), source.getName(), source.getDescriptor());
        field.init();
        return field;
    }

    @Override
    public String toString() {
        return "Clazz{" +
                "name=" + this.getName() +
                ", descriptor=" + this.getDescriptor() +
                ", superClassName='" + superClassName + '\'' +
                ", interfaceNames=" + interfaceNames +
//                ", methods=" + methods +
                ", fields=" + fields +
                ", bootstrapMethods=" + bootstrapMethods +
                ", constantPool=" + constantPool +
                ", clazzLoader=" + clazzLoader +
                ", clazzFile=" + clazzFile +
                ", stat=" + stat +
                ", superClass=" + superClass +
                ", interfaces=" + interfaces +
                ", runtimeClass=" + runtimeClass +
                '}';
    }
}
