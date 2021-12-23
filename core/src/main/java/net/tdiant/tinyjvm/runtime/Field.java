package net.tdiant.tinyjvm.runtime;

import java.util.HashMap;

/**
 * 类字段
 */
public class Field extends BaseNametag {

    private UnionSlot val;

    public Field(int accessFlags, String name, String descriptor) {
        super(accessFlags, name, descriptor);
    }

    public Field(int accessFlags, String name, String descriptor, UnionSlot val) {
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
                val = UnionSlot.of(0);
                break;
            case "L":
            case "J":
                val = UnionSlot.of(0L);
                break;
            case "F":
                val = UnionSlot.of(0F);
                break;
            case "D":
                val = UnionSlot.of(0.0);
                break;
            default:
                val = UnionSlot.of((Instance) null);
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
                frame.getOperandStack().pushInt(val.getInt());
                break;
            case "L":
            case "J":
                frame.getOperandStack().pushLong(val.getLong());
                break;
            case "F":
                frame.getOperandStack().pushFloat(val.getFloat());
                break;
            case "D":
                frame.getOperandStack().pushDouble(val.getDouble());
                break;
            default:
                frame.getOperandStack().pushRef(val.getRef());
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
                val.setInt(frame.getOperandStack().popInt());
                break;
            case "L":
            case "J":
                val.setLong(frame.getOperandStack().popLong());
                break;
            case "F":
                val.setFloat(frame.getOperandStack().popFloat());
                break;
            case "D":
                val.setDouble(frame.getOperandStack().popDouble());
                break;
            default:
                val.setRef(frame.getOperandStack().popRef());
                break;
        }
    }

    public UnionSlot getVal() {
        return val;
    }

    public void setVal(UnionSlot val) {
        this.val = val;
    }

}
