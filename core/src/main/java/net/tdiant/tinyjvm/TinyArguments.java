package net.tdiant.tinyjvm;

public class TinyArguments {

    private final String[] args;


    private boolean version; // -version

    public TinyArguments(String[] args) {
        this.args = args;
    }

    private void loadArguments() {

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
            }
        }


    }

}
