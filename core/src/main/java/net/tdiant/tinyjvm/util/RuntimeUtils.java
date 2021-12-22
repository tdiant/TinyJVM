package net.tdiant.tinyjvm.util;

import net.tdiant.tinyjvm.classes.file.constant.ConstantInfo;
import net.tdiant.tinyjvm.classes.loader.ClazzLoader;
import net.tdiant.tinyjvm.runtime.Clazz;
import net.tdiant.tinyjvm.runtime.Instance;
import net.tdiant.tinyjvm.runtime.Method;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RuntimeUtils {

    public static final List<Character> METHOD_DESCRIPTOR_BASE = Arrays.asList('V', 'Z', 'B', 'C', 'S', 'I', 'J', 'F', 'D');


    public static void clinit(Clazz aClass) {
    }

    public static Instance str2Obj(String s, ClazzLoader clazzLoader) {
    }

    public static void invokeMethod(Method method) {
    }

    public static String genNativeMethodKey(String clazzName, String methodName, String descriptor) {
    }

    public static String genNativeMethodKey(Method implMethod) {
    }

    public static String getClassName(ConstantInfo info, int idx) {

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
}
