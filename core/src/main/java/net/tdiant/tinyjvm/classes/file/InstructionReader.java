package net.tdiant.tinyjvm.classes.file;

import net.tdiant.tinyjvm.classes.instruction.*;
import net.tdiant.tinyjvm.code.instruction.*;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class InstructionReader {

    private int op;
    private DataInputStream s;
    private ConstantPool pool;


    public InstructionReader(int op, DataInputStream s, ConstantPool pool) throws IOException {

        this.op = op;
        this.s = s;
        this.pool = pool;

    }

    public Instruction read() throws IOException {
        switch (op) {
            case 0x0:
                return new NopInstruction();
            case 0x1:
                return new AconstNullInstruction();
            case 0x2:
                return new IConstM1Instruction();
            case 0x3:
                return new IConst0Instruction();
            case 0x4:
                return new IConst1Instruction();
            case 0x5:
                return new IConst2Instruction();
            case 0x6:
                return new IConst3Instruction();
            case 0x7:
                return new IConst4Instruction();
            case 0x8:
                return new IConst5Instruction();
            case 0x9:
                return new Lconst0Instruction();
            case 0xa:
                return new Lconst1Instruction();
            case 0xb:
                return new FConst0Instruction();
            case 0xc:
                return new FConst1Instruction();
            case 0xd:
                return new FConst2Instruction();
            case 0xe:
                return new DConst0Instruction();
            case 0xf:
                return new DConst1Instruction();
            case 0x10:
                return new BiPushInstruction(s.readByte());
            case 0x11:
                return new SiPushInstruction(s.readShort());
            case 0x12:
                //todo 0x12
            case 0x13:
                //todo 0x13
            case 0x14:
                //todo 0x14
            case 0x15:
                return new ILoadInstruction(s.readUnsignedByte());
            case 0x16:
                return new LLoadInstruction(s.readUnsignedByte());
            case 0x17:
                return new FLoadInstruction(s.readUnsignedByte());
            case 0x18:
                return new DLoadInstruction(s.readUnsignedByte());
            case 0x19:
                return new ALoadInstruction(s.readUnsignedByte());
            case 0x1a:
                return new ILoadInstruction(0);
            case 0x1b:
                return new ILoadInstruction(1);
            case 0x1c:
                return new ILoadInstruction(2);
            case 0x1d:
                return new ILoadInstruction(3);
            case 0x1e:
                return new LLoadInstruction(0);
            case 0x1f:
                return new LLoadInstruction(1);
            case 0x20:
                return new LLoadInstruction(2);
            case 0x21:
                return new LLoadInstruction(3);
            case 0x22:
                return new FLoadInstruction(0);
            case 0x23:
                return new FLoadInstruction(1);
            case 0x24:
                return new FLoadInstruction(2);
            case 0x25:
                return new FLoadInstruction(3);
            case 0x26:
                return new DLoadInstruction(0);
            case 0x27:
                return new DLoadInstruction(1);
            case 0x28:
                return new DLoadInstruction(2);
            case 0x29:
                return new DLoadInstruction(3);
            case 0x2a:
                return new ALoadInstruction(0);
            case 0x2b:
                return new ALoadInstruction(1);
            case 0x2c:
                return new ALoadInstruction(2);
            case 0x2d:
                return new ALoadInstruction(3);
            case 0x2e:
                return new IALoadInstruction();//todo array support
            case 0x2f:
                return new LALoadInstruction();//todo array support
            case 0x30:
                return new FALoadInstruction();//todo array support
            case 0x31:
                return new DALoadInstruction();//todo array support
            case 0x32:
                return new AALoadInstruction();//todo array support
            case 0x33:
                return new BALoadInstruction();//todo array support
            case 0x34:
                return new CALoadInstruction();//todo array support
            case 0x35:
                return new SALoadIsntruction();//todo array support
            case 0x36:
                return new IStoreInstruction(s.readUnsignedShort());
            case 0x37:
                return new LStoreInstruction(s.readUnsignedByte());
            case 0x38:
                return new FStoreInstruction(s.readUnsignedByte());
            case 0x39:
                return new DStoreInstruction(s.readUnsignedByte());
            case 0x3a:
                return new AStoreInstruction(s.readUnsignedByte());
            case 0x3b:
                return new IStoreInstruction(0);
            case 0x3c:
                return new IStoreInstruction(1);
            case 0x3d:
                return new IStoreInstruction(2);
            case 0x3e:
                return new IStoreInstruction(3);
            case 0x3f:
                return new LStoreInstruction(0);
            case 0x40:
                return new LStoreInstruction(1);
            case 0x41:
                return new LStoreInstruction(2);
            case 0x42:
                return new LStoreInstruction(3);
            case 0x43:
                return new FStoreInstruction(0);
            case 0x44:
                return new FStoreInstruction(1);
            case 0x45:
                return new FStoreInstruction(2);
            case 0x46:
                return new FStoreInstruction(3);
            case 0x47:
                return new DStoreInstruction(0);
            case 0x48:
                return new DStoreInstruction(1);
            case 0x49:
                return new DStoreInstruction(2);
            case 0x4a:
                return new DStoreInstruction(3);
            case 0x4b:
                return new AStoreInstruction(0);
            case 0x4c:
                return new AStoreInstruction(1);
            case 0x4d:
                return new AStoreInstruction(2);
            case 0x4e:
                return new AStoreInstruction(3);
            case 0x4f:
                return new IAStoreInstruction();//todo array support
            case 0x50:
                return new LAStoreInstruction();//todo array support
            case 0x51:
                return new FAStoreInstruction();//todo array support
            case 0x52:
                return new DAStoreInstruction();//todo array support
            case 0x53:
                return new AAStoreInstruction();//todo array support
            case 0x54:
                return new BAStoreInstruction();//todo array support
            case 0x55:
                return new CAStoreInstruction();//todo array support
            case 0x56:
                return new SAStoreInstruction();//todo array support
            case 0x57:
                return new PopInstruction();
            case 0x58:
                return new Pop2Instruction();
            case 0x59:
                return new DupInstruction();
            case 0x5a:
                return new DupX1Instruction();
            case 0x5b:
                return new DupX2Instruction();
            case 0x5c:
                return new Dup2Instruction();
            case 0x5d:
                return new Dup2X1Instruction();
            case 0x5e:
                return new Dup2X2Instruction();
            case 0x5f:
                return new SwapInstruction();
            case 0x60:
                return new IAddInstruction();
            case 0x61:
                return new LAddInstruction();
            case 0x62:
                return new FAddInstruction();
            case 0x63:
                return new DAddInstruction();
            case 0x64:
                return new ISubInstruction();
            case 0x65:
                return new LSubInstruction();
            case 0x66:
                return new FSubInstruction();
            case 0x67:
                return new DSubInstruction();
            case 0x68:
                return new IMulInstruction();
            case 0x69:
                return new LMulInstruction();
            case 0x6a:
                return new FMulInstruction();
            case 0x6b:
                return new DMulInstruction();
            case 0x6c:
                return new IDivInstruction();
            case 0x6d:
                return new LDivInstruction();
            case 0x6e:
                return new FDivInstruction();
            case 0x6f:
                return new DDivInstruction();
            case 0x70:
                return new IRemInstruction();
            case 0x71:
                return new LRemInstruction();
            case 0x72:
                return new FRemInstruction();
            case 0x73:
                return new DRemInstruction();
            case 0x74:
                return new INegInstruction();
            case 0x75:
                return new LNegInstruction();
            case 0x76:
                return new FNegInstruction();
            case 0x77:
                return new DNegInstruction();
            case 0x78:
                return new IShlInstruction();
            case 0x79:
                return new LShlInstruction();
            case 0x7a:
                return new IShrInstruction();
            case 0x7b:
                return new LShrInstruction();
            case 0x7c:
                return new IUShrInstruction();
            case 0x7d:
                return new LUShrInstruction();
            case 0x7e:
                return new IAndInstruction();
            case 0x7f:
                return new LAndInstruction();
            case 0x80:
                return new IOrInstruction();
            case 0x81:
                return new LOrInstruction();
            case 0x82:
                return new IXOrInstruction();
            case 0x83:
                return new LXOrInstruction();
            case 0x84:
                return new IIncInstruction(s.readUnsignedByte(), s.readByte());
            case 0x85:
                return new I2lInstruction();
            case 0x86:
                return new I2fInstruction();
            case 0x87:
                return new I2dInstruction();
            case 0x88:
                return new L2iInstruction();
            case 0x89:
                return new L2fInstruction();
            case 0x8a:
                return new L2dInstruction();
            case 0x8b:
                return new F2iInstruction();
            case 0x8c:
                return new F2lInstruction();
            case 0x8d:
                return new F2dInstruction();
            case 0x8e:
                return new D2iInstruction();
            case 0x8f:
                return new D2lInstruction();
            case 0x90:
                return new D2fInstruction();
            case 0x91:
                return new I2bInstruction();
            case 0x92:
                return new I2cInstruction();
            case 0x93:
                return new I2sInstruction();
            case 0x94:
                return new LCmpInstruction();
            case 0x95:
                return new FCmpLInstruction();
            case 0x96:
                return new FCmpGInstruction();
            case 0x97:
                return new DCmpLInstruction();
            case 0x98:
                return new DCmpGInstruction();
            case 0x99:
                return new IfEqInstruction(s.readShort());
            case 0x9a:
                return new IfNeInstruction(s.readShort());
            case 0x9b:
                return new IfLtInstruction(s.readShort());
            case 0x9c:
                return new IfGeInstruction(s.readShort());
            case 0x9d:
                return new IfGtInstruction(s.readShort());
            case 0x9e:
                return new IfLeInstruction(s.readShort());
            case 0x9f:
                return new IfICmpEqInstruction(s.readShort());

            case 0xa0:
                return new IfICmpNeInstruction(s.readShort());
            case 0xa1:
                return new IfICmpLtInstruction(s.readShort());
            case 0xa2:
                return new IfICmpGeInstruction(s.readShort());
            case 0xa3:
                return new IfICmpGtInstruction(s.readShort());
            case 0xa4:
                return new IfICmpLeInstruction(s.readShort());
            case 0xa5:
                return new IfACmpEqInstruction(s.readShort());
            case 0xa6:
                return new IfACmpNeInstruction(s.readShort());
            case 0xa7:
                return new GotoInstruction(s.readShort());
            case 0xaa:
                return readTableSwitch();
            case 0xab:
                //todo 0xab
            case 0xac:
                return new IReturnInstruction();
            case 0xad:
                return new LReturnInstruction();
            case 0xae:
                return new FReturnInstruction();
            case 0xaf:
                return new DReturnInstruction();
            case 0xb0:
                return new AReturnInstruction();
            case 0xb1:
                return new ReturnINstruction();
            case 0xb2:


            case 0xa8:
            case 0xa9:
            default:
                throw new UnsupportedOperationException("" + op);

        }

    }

    private TableSwitchInstruction readTableSwitch() throws IOException {
        int a = s.readInt();
        int def = s.readInt();
        int low = s.readInt(), high = s.readInt();
        Map<Integer, Integer> mp = new HashMap<>();
        for (int i = low; i <= high; i++)
            mp.put(i, s.readInt());
        return new TableSwitchInstruction(
                1 + a + (high - low + 1) * 4,
                def, high, low, mp
        );
    }

}
