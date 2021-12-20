package net.tdiant.tinyjvm.classes.file.attr;

public class BootstrapMethodsAttribute extends Attribute {

    private final BootstrapMethod[] methods;

    public BootstrapMethodsAttribute(BootstrapMethod[] methods) {
        this.methods = methods;
    }

    public BootstrapMethod[] getMethods() {
        return methods;
    }

    public static class BootstrapMethod {
        private final int refIdx;
        private final int[] refArgs;

        public BootstrapMethod(int refIdx, int[] refArgs) {
            this.refIdx = refIdx;
            this.refArgs = refArgs;
        }

        public int getRefIdx() {
            return refIdx;
        }

        public int[] getRefArgs() {
            return refArgs;
        }
    }

}
