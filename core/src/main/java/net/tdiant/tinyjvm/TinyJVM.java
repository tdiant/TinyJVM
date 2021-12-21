package net.tdiant.tinyjvm;

public class TinyJVM {

    public static final VMMain vm = new VMMain();

    private static TinyArguments args;

    public static void main(String[] args) {

        TinyJVM.args = new TinyArguments(args);

        vm.run();

    }

    public static TinyArguments getArguments() {
        return args;
    }

}
