package net.tdiant.tinyjvm.classes.loader;

import net.tdiant.tinyjvm.runtime.Clazz;

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

    }

}
