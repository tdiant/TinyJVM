package net.tdiant.tinyjvm;

import java.util.ArrayList;
import java.util.List;

public class TinyArguments {

    private final String[] args;

    private int maxThreadSize = 2048; // 调用栈大小

    private String mainClass = null;
    private final List<String> classes = new ArrayList<>();

    public TinyArguments(String[] args) {
        this.args = args;
    }

    public void loadArguments() {

        int i = 0;
        while (i < args.length) {
            switch (args[i]) {
                case "-version":
                    System.out.println("Java version: \"0.0.1_tinyjvm_beta1\"");
                    System.out.println("Core based on tinyjvm SNAPSHOT 1 _ by tdiant");
                    break;
                case "-help":
                    System.out.println("Welcome to TinyJVM 0.0.1_beta");
                    System.out.println("Powered by tdiant.");
                    System.out.println(" - help : show helps");
                    System.out.println(" - version : show version");
                    break;
                case "-classes":
                    while (i + 1 < args.length && !args[i + 1].startsWith("-"))
                        classes.add(args[++i]);
                    break;
                case "-main":
                    if (i + 1 < args.length && !args[i + 1].startsWith("-"))
                        mainClass = args[++i];
                    break;
            }
            i += 1;
        }

    }

    public String[] getArgs() {
        return args;
    }

    public List<String> getClasses() {
        return classes;
    }

    public String getMainClass() {
        return mainClass;
    }

    public int getMaxThreadSize() {
        return maxThreadSize;
    }

}
