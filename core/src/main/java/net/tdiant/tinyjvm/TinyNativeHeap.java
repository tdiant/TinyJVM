package net.tdiant.tinyjvm;

import net.tdiant.tinyjvm.runtime.Clazz;
import net.tdiant.tinyjvm.runtime.NativeMethod;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TinyNativeHeap {

    private final Map<String, NativeMethod> nativeMethods = new HashMap<>();
    private final Map<String, Clazz> classesMap = new HashMap<>();

    public void registerMethod(String key, NativeMethod method) {
        if (nativeMethods.containsKey(key)) return;
        nativeMethods.put(key, method);
    }

    public void registerClass(String key, Clazz clazz) {
        if (classesMap.containsKey(key)) return;
        classesMap.put(key, clazz);
    }

    public void registerEmptyMethod(String key) {
        if (nativeMethods.containsKey(key)) return;
        nativeMethods.put(key, frame -> {
        });
    }

    public NativeMethod getMethod(String key) {
        return nativeMethods.get(key);
    }

    public Clazz getClazz(String key) {
        return classesMap.get(key);
    }

    public Set<NativeMethod> getNativeMethods() {
        return new HashSet<>(nativeMethods.values());
    }

    public Set<Clazz> getClasses() {
        return new HashSet<>(classesMap.values());
    }

}
