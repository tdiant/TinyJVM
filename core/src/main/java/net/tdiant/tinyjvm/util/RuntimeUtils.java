package net.tdiant.tinyjvm.util;

import net.tdiant.tinyjvm.TinyJVM;
import net.tdiant.tinyjvm.classes.file.ClazzFile;
import net.tdiant.tinyjvm.classes.loader.ClazzLoader;
import net.tdiant.tinyjvm.runtime.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RuntimeUtils {

    public static final List<Character> METHOD_DESCRIPTOR_BASE = Arrays.asList('V', 'Z', 'B', 'C', 'S', 'I', 'J', 'F', 'D');

    public static void clinit(Clazz clazz) {
        if (clazz.stat >= ClazzFile.CLASS_INIT_NOW) return;

        if (clazz.getSuperClass() != null)
            clinit(clazz.getSuperClass());

        final Method clinitMethod = clazz.getClinitMethod();
        if (clinitMethod == null) {
            clazz.stat = ClazzFile.CLASS_INIT_FINISH;
            return;
        }

        NativeMethod nm = TinyJVM.vm.getHeap().getMethod(clinitMethod.nativeMethodKey());
        if (nm != null) {
            clazz.stat = ClazzFile.CLASS_INIT_NOW;
            nm.invoke(TinyJVM.vm.getMainThread().now());
            clazz.stat = ClazzFile.CLASS_INIT_FINISH;
            return;
        }

        clazz.stat = ClazzFile.CLASS_INIT_NOW;
        Frame newFrame = new Frame(clinitMethod);
        TinyJVM.vm.execute(newFrame);
        clazz.stat = ClazzFile.CLASS_INIT_FINISH;
    }

    public static Instance str2Obj(String str, ClazzLoader clazzLoader) {
        Clazz cls = TinyJVM.vm.getHeap().getClazz("java/lang/String");
        Instance object = cls.newInstance();
        Field field = object.getField("value", "[C");
        char[] chars = str.toCharArray();
        final PrimitiveArray arr = PrimitiveArray.charArray(chars.length);
        for (int i = 0; i < chars.length; i++) {
            arr.ints[i] = chars[i];
        }

        field.setVal(UnionSlot.of(arr));
        return object;
    }

    public static void invokeMethod(Method method) {
        NativeMethod nmb = TinyJVM.vm.getHeap().getMethod(method.nativeMethodKey());
        if (nmb != null) {
            nmb.invoke(TinyJVM.vm.getMainThread().now());
            return;
        }

        if (method.isNative()) {
            String key = method.nativeMethodKey();
            System.out.println(key);
            NativeMethod nm = TinyJVM.vm.getHeap().getMethod(key);

            System.out.println(TinyJVM.vm.getHeap().getNativeMethodNames());

            if (nm == null)
                throw new IllegalStateException("not found native method: " + key);

            nm.invoke(TinyJVM.vm.getMainThread().now());
        } else {
            Frame newFrame = new Frame(method);
            Frame old = TinyJVM.vm.getMainThread().now();

            final int slots = method.getArgSlotSize();
            for (int i = slots - 1; i >= 0; i--)
                newFrame.getLocalVars().set(i, old.getOperandStack().pop());

            TinyJVM.vm.getMainThread().push(newFrame);
        }
    }

    public static List<String> parseMethodDescriptor(String descriptor) {
        if (descriptor.startsWith("()"))
            return new ArrayList<>();

        descriptor = descriptor.substring(descriptor.indexOf("(") + 1, descriptor.indexOf(")"));

        List<String> rets = new ArrayList<>();
        for (int i = 0; i < descriptor.length(); i++) {
            if (METHOD_DESCRIPTOR_BASE.contains(descriptor.charAt(i))) {
                rets.add(String.valueOf(descriptor.charAt(i)));
                continue;
            }

            if (descriptor.charAt(i) == '[') {
                int temp = i;
                i++;
                while (descriptor.charAt(i) == '[') {
                    i++;
                }
                if (METHOD_DESCRIPTOR_BASE.contains(descriptor.charAt(i))) {
                    rets.add(descriptor.substring(temp, i + 1));
                    continue;
                }
                int idx = descriptor.indexOf(';', i);
                rets.add(descriptor.substring(temp, idx));
                i = idx;
                continue;
            }

            if (descriptor.charAt(i) == 'L') {
                int idx = descriptor.indexOf(';', i);
                rets.add(descriptor.substring(i, idx));
                i = idx;
            }
        }
        return rets;
    }

    public static String obj2Str(Instance nameObj) {
        if (!nameObj.getClazz().getName().equals("java/lang/String"))
            throw new IllegalStateException();

        PrimitiveArray pa = ((PrimitiveArray) nameObj.getField("value", "[C").getVal().getRef());
        char[] chars = new char[pa.getLength()];
        for (int i = 0; i < pa.getLength(); i++)
            chars[i] = (char) pa.ints[i];

        return new String(chars);
    }

    public static String nativeMethodKey(String clazzName, String methodName, String methodDescriptor) {
        return clazzName + "_" + methodName + "_" + methodDescriptor;
    }

    public static void doReturn(int slotSize) {
        Frame old = TinyJVM.vm.getMainThread().pop();

        if (old.getStat() == ClazzFile.FAKE_FRAME)
            old.setStat(ClazzFile.FAKE_FRAME_END);

        if (slotSize == 0)
            return;

        Frame now = TinyJVM.vm.getMainThread().now();
        if (slotSize == 1) {
            now.getOperandStack().push(old.getOperandStack().pop());
            return;
        }

        if (slotSize == 2) {
            Slot v2 = old.getOperandStack().pop();
            Slot v1 = old.getOperandStack().pop();
            now.getOperandStack().push(v1);
            now.getOperandStack().push(v2);
            return;
        }

        throw new IllegalStateException();
    }

}
