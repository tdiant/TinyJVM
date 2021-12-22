package net.tdiant.tinyjvm.classes.loader;

import net.tdiant.tinyjvm.classes.file.ClazzFile;
import net.tdiant.tinyjvm.classes.file.ClazzFileReader;

import java.io.File;
import java.util.Objects;

public class DirectoryClazzSource implements ClazzSource {

    private final String path;

    public DirectoryClazzSource(String path) {
        this.path = path;
    }

    @Override
    public ClazzFile findClazz(String name) {

        if (name.contains(File.separator)) {

            //loops
            int i = name.indexOf(File.separator);

            String dir = name.substring(0, i);
            String pth = path + File.separator + dir;

            if (!new File(pth).exists())
                return null;

            return new DirectoryClazzSource(pth).findClazz(name.substring(i + 1));

        } else {

            String[] list = new File(path).list();
            if (list == null) return null;

            for (String s : list) {
                if (Objects.equals(s, name + ".class")) {
                    try {
                        return new ClazzFileReader(new File(path + File.separator + name + ".class")).read();
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            }

            return null;
        }

    }

    @Override
    public String sourcePath() {
        return path;
    }
}
