package net.tdiant.tinyjvm.classes.loader;

import net.tdiant.tinyjvm.classes.file.ClazzFile;

public interface ClazzSource {

    ClazzFile findClazz(String name);

    String sourcePath();

}
