package net.tdiant.tinyjvm;

public class TinyJVM {

    public static final VMMain vm = new VMMain();
    public static TinyArguments args;

    public static void main(String[] args) {

        TinyJVM.args = new TinyArguments(args);
        TinyJVM.args.loadArguments();

        vm.run();

    }

}
