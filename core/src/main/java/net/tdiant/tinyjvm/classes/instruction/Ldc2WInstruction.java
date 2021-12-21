package net.tdiant.tinyjvm.classes.instruction;

public class Ldc2WInstruction extends Instruction {

    private final long a;
    private final double b;

    public Ldc2WInstruction(long x) {
        this.a = x;
        this.b = 0;
    }

    public Ldc2WInstruction(double x) {
        this.b = x;
        this.a = 0;
    }

    @Override
    public int delta() {
        return 3;
    }

    public long getLong() {
        return a;
    }

    public double getDouble() {
        return b;
    }
}
