package net.tdiant.tinyjvm;

public class TinyJVM {

    public static final VMMain vm = new VMMain();

    public static void main(String[] args) {

        if (args.length != 0) {
            processArgs(args);
        } else {
            processArgs(new String[]{"help"});
        }

        vm.run();

    }

    private static void processArgs(String[] args) {
        switch (args[0]) {
            default:
            case "help":

            case "version":

        }
    }

}
