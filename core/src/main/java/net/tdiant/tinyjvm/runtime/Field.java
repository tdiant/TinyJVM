package net.tdiant.tinyjvm.runtime;

/**
 * 类字段
 */
public class Field extends BaseNametag {

    private Slot val;

    public Field(int accessFlags, String name, String descriptor) {
        super(accessFlags, name, descriptor);
    }

    public Field(int accessFlags, String name, String descriptor, Slot val) {
        this(accessFlags, name, descriptor);
        this.val = val;
    }

    /**
     * 判断该字段是否为静态字段
     */
    public boolean isStatic() {
        return (this.getAccessFlags() & 0x0008) != 0;
    }

    /**
     * 初始化
     */
    public void init() {
        switch (this.getDescriptor()) {
            case "Z":
            case "C":
            case "B":
            case "S":
            case "I":
                val = new Slot(0);
                break;
            case "L":
            case "J":
                val = new Slot(0L);
                break;
            case "F":
                val = new Slot(0F);
                break;
            case "D":
                val = new Slot(0.0);
                break;
            default:
                val = new Slot((Instance) null);
                break;
        }
    }

    /**
     * 将字段的值推到栈中
     */
    public void pushStack(Frame frame) {
        switch (this.getDescriptor()) {
            case "Z":
            case "C":
            case "B":
            case "S":
            case "I":
                frame.getOperandStack().push(new Slot(val.getInt()));
                break;
            case "L":
            case "J":
                frame.getOperandStack().push(new Slot(val.getLong()));
                break;
            case "F":
                frame.getOperandStack().push(new Slot(val.getFloat()));
                break;
            case "D":
                frame.getOperandStack().push(new Slot(val.getDouble()));
                break;
            default:
                frame.getOperandStack().push(new Slot(val.getInstance()));
                break;
        }
    }

    /**
     * 将字段的值设置为栈顶
     */
    public void set(Frame frame) {
        switch (this.getDescriptor()) {
            case "Z":
            case "C":
            case "B":
            case "S":
            case "I":
                val.set(frame.getOperandStack().pop().getInt());
                break;
            case "L":
            case "J":
                val.set(frame.getOperandStack().pop().getLong());
                break;
            case "F":
                val.set(frame.getOperandStack().pop().getFloat());
                break;
            case "D":
                val.set(frame.getOperandStack().pop().getDouble());
                break;
            default:
                val.set(frame.getOperandStack().pop().getInstance());
                break;
        }
    }

    public Slot getVal() {
        return val;
    }

    public void setVal(Slot val) {
        this.val = val;
    }

}
