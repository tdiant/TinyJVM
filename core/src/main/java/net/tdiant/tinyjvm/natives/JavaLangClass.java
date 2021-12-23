package net.tdiant.tinyjvm.natives;

import net.tdiant.tinyjvm.TinyJVM;
import net.tdiant.tinyjvm.runtime.*;
import net.tdiant.tinyjvm.util.RuntimeUtils;

import java.util.List;

public class JavaLangClass {
    public static void registerNatives() {

        TinyJVM.vm.getHeap().registerEmptyMethod("java/lang/Class_registerNatives_()V");
        TinyJVM.vm.getHeap().registerEmptyMethod("java/lang/Class_isInstance_(Ljava/lang/Object;)Z");
        TinyJVM.vm.getHeap().registerEmptyMethod("java/lang/Class_isAssignableFrom_(Ljava/lang/Class;)Z");
        TinyJVM.vm.getHeap().registerEmptyMethod("java/lang/Class_getInterfaces0_()[Ljava/lang/Class;");
        TinyJVM.vm.getHeap().registerEmptyMethod("java/lang/Class_getModifiers_()I");
        TinyJVM.vm.getHeap().registerEmptyMethod("java/lang/Class_getSigners_()[Ljava/lang/Object;");
        TinyJVM.vm.getHeap().registerEmptyMethod("java/lang/Class_setSigners_([Ljava/lang/Object;)V");
        TinyJVM.vm.getHeap().registerEmptyMethod("java/lang/Class_getEnclosingMethod0_()[Ljava/lang/Object;");
        TinyJVM.vm.getHeap().registerEmptyMethod("java/lang/Class_getDeclaringClass0_()Ljava/lang/Class;");
        TinyJVM.vm.getHeap().registerEmptyMethod("java/lang/Class_getProtectionDomain0_()Ljava/security/ProtectionDomain;");
        TinyJVM.vm.getHeap().registerEmptyMethod("java/lang/Class_getGenericSignature0_()Ljava/lang/String;");
        TinyJVM.vm.getHeap().registerEmptyMethod("java/lang/Class_getRawAnnotations_()[B");
        TinyJVM.vm.getHeap().registerEmptyMethod("java/lang/Class_getRawTypeAnnotations_()[B");
        TinyJVM.vm.getHeap().registerEmptyMethod("java/lang/Class_getConstantPool_()Lsun/reflect/ConstantPool;");
        TinyJVM.vm.getHeap().registerEmptyMethod("java/lang/Class_getDeclaredFields0_(Z)[Ljava/lang/reflect/Field;");
        TinyJVM.vm.getHeap().registerEmptyMethod("java/lang/Class_getDeclaredMethods0_(Z)[Ljava/lang/reflect/Method;");
        TinyJVM.vm.getHeap().registerEmptyMethod("java/lang/Class_getDeclaredConstructors0_(Z)[Ljava/lang/reflect/Constructor;");
        TinyJVM.vm.getHeap().registerEmptyMethod("java/lang/Class_getDeclaredClasses0_()[Ljava/lang/Class;");
        TinyJVM.vm.getHeap().registerEmptyMethod("java/lang/Class_desiredAssertionStatus0_(Ljava/lang/Class;)Z");

        TinyJVM.vm.getHeap().registerMethod("java/lang/Class_isInterface_()Z", frame -> {
            Clazz cls = frame.getOperandStack().popRef().getMetaClass();
            frame.getOperandStack().pushInt(cls.isInterface() ? 1 : 0);
        });
        TinyJVM.vm.getHeap().registerMethod("java/lang/Class_isArray_()Z", frame -> {
            Clazz metaClass = frame.getOperandStack().popRef().getMetaClass();
            frame.getOperandStack().pushInt(metaClass.getName().startsWith("[") ? 1 : 0);
        });
        TinyJVM.vm.getHeap().registerMethod("java/lang/Class_isPrimitive_()Z", frame -> {
            Clazz cls = frame.getOperandStack().popRef().getMetaClass();
            frame.getOperandStack().pushInt(cls.isPrimitive() ? 1 : 0);
        });
        TinyJVM.vm.getHeap().registerMethod("java/lang/Class_getSuperclass_()Ljava/lang/Class;", frame -> {
            Clazz superClass = frame.getOperandStack().popRef().getMetaClass().getSuperClass();
            if (superClass == null) {
                frame.getOperandStack().pushRef((Instance) null);
                return;
            }
            frame.getOperandStack().pushRef(superClass.getRuntimeClass());
        });

        TinyJVM.vm.getHeap().registerMethod("java/lang/Class_getName0_()Ljava/lang/String;", frame -> {
            Instance obj = frame.getOperandStack().popRef();
            String name = obj.getMetaClass().getName();
            Clazz strClazz = TinyJVM.vm.getHeap().getClazz("java/lang/String");
            Instance nameObj = strClazz.newInstance();

            char[] chars = name.replace('/', '.').toCharArray();
            PrimitiveArray array = PrimitiveArray.charArray(chars.length);
            for (int i = 0; i < chars.length; i++)
                array.ints[i] = chars[i];

            nameObj.setField("value", "[C", UnionSlot.of(array));
            frame.getOperandStack().pushRef(nameObj);
        });
        TinyJVM.vm.getHeap().registerMethod(
                "java/lang/Class_forName0_(Ljava/lang/String;ZLjava/lang/ClassLoader;Ljava/lang/Class;)Ljava/lang/Class;",
                frame -> {
                    frame.getOperandStack().popRef();
                    frame.getOperandStack().popRef();
                    int init = frame.getOperandStack().popInt();
                    Instance name = frame.getOperandStack().popRef();
                    String clsName = RuntimeUtils.obj2Str(name).replace('.', '/');
                    Clazz clazz = TinyJVM.vm.getHeap().getClazz(clsName);
                    if (clazz == null)
                        clazz = frame.getMethod().getClazz().getClazzLoader().loadClazz(clsName);

                    if (clazz == null)
                        throw new IllegalStateException("class not found " + clsName);

                    frame.getOperandStack().push(new Slot(clazz.getRuntimeClass()));

                    if (init == 1 && clazz.getStat() <= 0) {
                        Method cinit = clazz.getClinitMethod();
                        if (cinit == null) {
                            throw new IllegalStateException();
                        }

                        clazz.setStat(1);
                        TinyJVM.vm.execute(cinit);
                        clazz.setStat(2);
                    }
                });


        TinyJVM.vm.getHeap().registerMethod("java/lang/Class_getComponentType_()Ljava/lang/Class;", frame -> {
            Clazz cls = frame.getOperandStack().popRef().getMetaClass();
            if (cls.getName().startsWith("[")) {
                String name = cls.getName().substring(1);
                //todo support another types
                if ("C".equals(name)) {
                    Clazz ccls = TinyJVM.vm.getHeap().getClazz("java/lang/Character");
                    Instance runtimeClass = ccls.getRuntimeClass();
                    frame.getOperandStack().push(new Slot(runtimeClass));
                } else {
                    throw new UnsupportedOperationException();
                }
            }
        });


        TinyJVM.vm.getHeap().registerMethod("java/lang/Class_getPrimitiveClass_(Ljava/lang/String;)Ljava/lang/Class;",
                (frame) -> {
                    final Instance instance = frame.getOperandStack().popRef();
                    final String val = RuntimeUtils.obj2Str(instance);
                    Clazz cls = TinyJVM.vm.getHeap().getClazz(val);
                    frame.getOperandStack().push(new Slot(cls.getRuntimeClass()));
                });
        TinyJVM.vm.getHeap().registerMethod("java/lang/Class_desiredAssertionStatus_()Z", frame -> {
            Object xx = frame.getOperandStack().popRef();
            frame.getOperandStack().push(new Slot(1));
        });

        TinyJVM.vm.getHeap().registerMethod("java/lang/Class_getSimpleName_()Ljava/lang/String;", frame -> {
            Clazz cls = frame.getOperandStack().popRef().getMetaClass();
            int lidx = cls.getName().lastIndexOf("/");
            int idx = 0;
            if (lidx > 0) {
                idx = lidx + 1;
            }
            String sn = cls.getName().substring(idx);
            Instance obj = RuntimeUtils.str2Obj(sn, frame.getMethod().getClazz().getClazzLoader());
            frame.getOperandStack().push(new Slot(obj));
        });

        TinyJVM.vm.getHeap().registerMethod("java/lang/Class_getCanonicalName_()Ljava/lang/String;", frame -> {
            Clazz cls = frame.getOperandStack().popRef().getMetaClass();
            String sn = cls.getName().replace('/', '.');
            Instance obj = RuntimeUtils.str2Obj(sn, frame.getMethod().getClazz().getClazzLoader());
            frame.getOperandStack().push(new Slot(obj));
        });

        TinyJVM.vm.getHeap().registerMethod("java/lang/Class_getInterfaces_()[Ljava/lang/Class;", frame -> {
            Instance thisObj = frame.getOperandStack().popRef();
            Clazz cls = (thisObj).getMetaClass();
            if (!cls.getInterfaceNames().isEmpty() && cls.getInterfaces().isEmpty()) {
                frame.getOperandStack().push(new Slot(thisObj));
                cls.interfaceInit(frame);
                return;
            }
            List<Clazz> interfaces = cls.getInterfaces();
            String name = "[Ljava/lang/Class;";
            Clazz clazz = TinyJVM.vm.getHeap().getClazz(name);
            if (clazz == null) {
                clazz = new Clazz(1, name, frame.getMethod().getClazz().getClazzLoader(), null);
                clazz.setSuperClass(TinyJVM.vm.getHeap().getClazz("java/lang/Object"));
                clazz.setStat(2);
                TinyJVM.vm.getHeap().registerClass(name, clazz);
            }
            Instance[] objs = new Instance[interfaces.size()];

            for (int i = 0; i < interfaces.size(); i++)
                objs[i] = interfaces.get(i).getRuntimeClass();

            InstanceArrayInstance instanceArray = new InstanceArrayInstance(clazz, objs);
            frame.getOperandStack().push(new Slot(instanceArray));
        });

        TinyJVM.vm.getHeap().registerMethod("java/lang/Class_newInstance_()Ljava/lang/Object;", frame -> {
            Clazz cls = frame.getOperandStack().popRef().getMetaClass();
            Instance ins = cls.newInstance();
            frame.getOperandStack().push(new Slot(ins));
        });

//        TinyJVM.vm.getHeap().registerMethod(
//                "java/lang/Class_getDeclaredField_(Ljava/lang/String;)Ljava/lang/reflect/Field;", frame -> {
//                    Instance nameObj = (Instance) frame.getOperandStack().popRef();
//                    Instance thisObj = (Instance) frame.getOperandStack().popRef();
//                    String name = RuntimeUtils.obj2Str(nameObj);
//                    Field field = thisObj.getMetaClass().getField(name);
//                    frame.getOperandStack().push(new Slot(field.));
//                });
    }
}
