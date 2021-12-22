package net.tdiant.tinyjvm.util;

import net.tdiant.tinyjvm.classes.file.ClazzFile;
import net.tdiant.tinyjvm.classes.loader.ClazzSource;

public class MeowUtils {

    //todo temp clazz source!!!
    public static ClazzSource getTempClazzSource() {
        return new ClazzSource() {
            @Override
            public ClazzFile findClazz(String name) {
                return null;
            }

            @Override
            public String sourcePath() {
                return "<test_dir>";
            }
        };
    }

}
