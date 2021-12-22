package net.tdiant.tinyjvm.classes.loader;

import net.tdiant.tinyjvm.classes.file.ClazzFile;

import java.util.List;

public class MultipleClazzSource implements ClazzSource {

    private final List<ClazzSource> sources;

    public MultipleClazzSource(List<ClazzSource> sources) {
        this.sources = sources;
    }

    @Override
    public ClazzFile findClazz(String name) {
        for (ClazzSource cs : sources) {
            ClazzFile f = cs.findClazz(name);
            if (f != null)
                return f;
        }

        return null;
    }

    @Override
    public String sourcePath() {
        return null;
    }
}
