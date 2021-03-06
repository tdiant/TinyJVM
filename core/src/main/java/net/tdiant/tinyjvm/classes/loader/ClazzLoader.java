package net.tdiant.tinyjvm.classes.loader;

import net.tdiant.tinyjvm.TinyJVM;
import net.tdiant.tinyjvm.classes.file.ClazzFile;
import net.tdiant.tinyjvm.classes.file.FieldInfo;
import net.tdiant.tinyjvm.classes.file.InterfaceInfo;
import net.tdiant.tinyjvm.classes.file.MethodInfo;
import net.tdiant.tinyjvm.classes.file.attr.BootstrapMethodsAttribute;
import net.tdiant.tinyjvm.classes.file.attr.CodeAttribute;
import net.tdiant.tinyjvm.runtime.*;

import java.util.ArrayList;
import java.util.List;

public class ClazzLoader {

    private final String name;
    private final ClazzSource source;

    public ClazzLoader(String name, ClazzSource source) {
        this.name = name;
        this.source = source;

    }

    public ClazzLoader(ClazzSource source) {
        this("runtime_default", source);
    }

    public Clazz loadClazz(String name) {

        if (name.contains("java/lang/Thread"))
            return null;

        Clazz cls = TinyJVM.vm.getHeap().getClazz(name);
        if (cls != null)
            return cls;
        cls = doLoadClass(name);
        nativeClazzRegister(cls);
        return cls;
    }

    public void loadPrimitiveClass(String name) {
        Clazz cls = TinyJVM.vm.getHeap().getClazz(name);
        if (cls != null)
            return;
        cls = new Clazz(1, name, this);
        Instance metaCls = TinyJVM.vm.getHeap().getClazz("java/lang/Class").newInstance();
        cls.setRuntimeClass(metaCls);
        metaCls.setMetaClass(cls);

        nativeClazzRegister(cls);
    }

    private void nativeClazzRegister(Clazz clazz) {
        TinyJVM.vm.getHeap().registerClass(clazz.getName(), clazz);
        for (Method m : clazz.getMethods()) {
            if (!m.isNative()) continue;
            String key = m.nativeMethodKey();
            NativeMethod nm = TinyJVM.vm.getHeap().getMethod(key);
            if (nm == null) {
                System.err.println("The method " + key + " not found.");
            }
        }
    }

    public Clazz doLoadClass(String name) {
        ClazzFile clazz = source.findClazz(name);
        Clazz cls = doLoadClass(name, clazz);

        if (cls.getSuperClassName() != null)
            cls.setSuperClass(this.loadClazz(cls.getSuperClassName()));

        if (TinyJVM.vm.getHeap().getClazz("java/lang/Class") != null) {
            Instance rcs = TinyJVM.vm.getHeap().getClazz("java/lang/Class").newInstance();
            cls.setRuntimeClass(rcs);
            rcs.setMetaClass(cls);
        }

        return cls;
    }

    public Clazz doLoadClass(String name, ClazzFile clzFile) {
        List<Method> methods = new ArrayList<>();
        for (MethodInfo methodInfo : clzFile.getMethods())
            methods.add(this.map(methodInfo));

        List<Field> fields = new ArrayList<>();
        for (FieldInfo fieldInfo : clzFile.getFields())
            fields.add(this.map(fieldInfo));

        for (Field it : fields) {
            if (it.isStatic())
                it.init();
        }

        int scIdx = clzFile.getSuperClass();
        String superClassName = null;
        if (scIdx != 0) {
            superClassName = clzFile.getConstantPool().getClassName(scIdx);
//            int nameIdx = ((ClassConstantInfo) clzFile.getConstantPool().get(scIdx)).getNameIndex();
//            superClassName = ((Utf8ConstantInfo) clzFile.getConstantPool().get(nameIdx - 1)).str();
        }

        List<String> interfaceNames = new ArrayList<>();
        if (clzFile.getInterfaces().size() != 0) {
            for (InterfaceInfo i : clzFile.getInterfaces())
                interfaceNames.add(i.getName());
        }

        BootstrapMethodsAttribute bootstrapMethods = clzFile.getBootstrapMethods();

        return new Clazz(clzFile.getAccessFlags(), name, superClassName, interfaceNames, methods, fields,
                bootstrapMethods, clzFile.getConstantPool(), this, clzFile);
    }

    public Method map(MethodInfo cfMethodInfo) {
        CodeAttribute code = cfMethodInfo.getCode();
        if (code == null) {
            return new Method(cfMethodInfo.getAccessFlags(), cfMethodInfo.getName(), cfMethodInfo.getDescriptor(), 0, 0,
                    null, null, cfMethodInfo.getLineNumber());
        }
        return new Method(cfMethodInfo.getAccessFlags(), cfMethodInfo.getName(), cfMethodInfo.getDescriptor(),
                code.getMaxStacks(), code.getMaxLocals(), code.getInstructions(), code.getExceptionTable(),
                cfMethodInfo.getLineNumber());
    }

    public Field map(FieldInfo fieldInfo) {
        return new Field(fieldInfo.getAccessFlags(), fieldInfo.getName(), fieldInfo.getDescriptor());
    }

    public String getName() {
        return name;
    }

    public ClazzSource getSource() {
        return source;
    }
}
