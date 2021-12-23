package net.tdiant.tinyjvm.classes.file.attr;

import net.tdiant.tinyjvm.classes.file.ExceptionInfo;
import net.tdiant.tinyjvm.classes.instruction.Instruction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//  Code_attribute {
//    u2 attribute_name_index;
//    u4 attribute_length;
//    u2 max_stack;
//    u2 max_locals;
//    u4 code_length;
//    u1 code[code_length];
//    u2 exception_table_length;
//    {   u2 start_pc;
//      u2 end_pc;
//      u2 handler_pc;
//      u2 catch_type;
//    } exception_table[exception_table_length];
//    u2 attributes_count;
//    attribute_info attributes[attributes_count];
//  }

public class CodeAttribute extends Attribute {

    private final int maxStacks;
    private final int maxLocals;
    private final List<Instruction> instructions;
    private final List<ExceptionInfo> exceptionTable;
    private final List<Attribute> attributes;

    public CodeAttribute(int maxStacks, int maxLocals, List<Instruction> instructions, List<ExceptionInfo> exceptionTable, List<Attribute> attributes) {
        this.maxStacks = maxStacks;
        this.maxLocals = maxLocals;
        this.instructions = instructions;
        this.exceptionTable = exceptionTable;
        this.attributes = attributes;
    }

    public Map<Integer, Instruction> getInstructions() {
        Map<Integer, Instruction> map = new HashMap<>(instructions.size());
        int pc = 0;
        for (Instruction instruction : instructions) {
            map.put(pc, instruction);
            pc += instruction.delta();
        }
        return map;
    }

    public int getMaxStacks() {
        return maxStacks;
    }

    public int getMaxLocals() {
        return maxLocals;
    }

    public List<ExceptionInfo> getExceptionTable() {
        return exceptionTable;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }
}
