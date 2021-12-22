package net.tdiant.tinyjvm.classes.loader;

import net.tdiant.tinyjvm.classes.file.ClazzFile;
import net.tdiant.tinyjvm.classes.file.ClazzFileReader;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class JarClazzSource implements ClazzSource {

    private final String path;

    public JarClazzSource(String path) {
        this.path = path;
    }

    @Override
    public ClazzFile findClazz(String name) {
        ZipFile zip;
        try {
            zip = new ZipFile(path);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        ZipEntry entry = zip.getEntry(name + ".class");
        if (entry == null)
            return null;

        try {

            ClazzFile clzFile = new ClazzFileReader(new DataInputStream(
                    zip.getInputStream(entry))).read();
            clzFile.setSource(this.path);

            return clzFile;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String sourcePath() {
        return path;
    }

}
