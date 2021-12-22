package net.tdiant.tinyjvm.classes.file;

import net.tdiant.tinyjvm.classes.file.constant.*;
import net.tdiant.tinyjvm.classes.instruction.*;
import net.tdiant.tinyjvm.exception.InstructionReaderException;
import net.tdiant.tinyjvm.util.CodeByteArrayInputStream;
import net.tdiant.tinyjvm.util.CodeDataInputStream;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class InstructionReader {

    private final int op;
    private final CodeDataInputStream s;
    private final CodeByteArrayInputStream ba;
    private final ConstantPool pool;

    public InstructionReader(int op, CodeByteArrayInputStream ba, CodeDataInputStream s, ConstantPool pool) {
        this.op = op;
        this.s = s;
        this.ba = ba;
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
                return readLdc();
            case 0x13:
                return readLdcW();
            case 0x14:
                return readLdc2W();
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
                return new IALoadInstruction();
            case 0x2f:
                return new LALoadInstruction();
            case 0x30:
                return new FALoadInstruction();
            case 0x31:
                return new DALoadInstruction();
            case 0x32:
                return new AALoadInstruction();
            case 0x33:
                return new BALoadInstruction();
            case 0x34:
                return new CALoadInstruction();
            case 0x35:
                return new SALoadInstruction();
            case 0x36:
                return new IStoreInstruction(s.readUnsignedByte());
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
                return new IAStoreInstruction();
            case 0x50:
                return new LAStoreInstruction();
            case 0x51:
                return new FAStoreInstruction();
            case 0x52:
                return new DAStoreInstruction();
            case 0x53:
                return new AAStoreInstruction();
            case 0x54:
                return new BAStoreInstruction();
            case 0x55:
                return new CAStoreInstruction();
            case 0x56:
                return new SAStoreInstruction();
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
                return readLookupSwitch();
            case 0xac:
            case 0xae:
            case 0xb0:
                return new IReturnInstruction();
            case 0xad:
            case 0xaf:
                return new LReturnInstruction();
            case 0xb1:
                return new ReturnInstruction();
//            case 0xb2:
//                return readGetterAndPutter(0);
//            case 0xb3:
//                return readGetterAndPutter(1);
//            case 0xb4:
//                return readGetterAndPutter(2);
//            case 0xb5:
//                return readGetterAndPutter(3);
//            case 0xb6:
//                return readGetterAndPutter(4);
//            case 0xb7:
//                return readGetterAndPutter(5);
//            case 0xb8:
//                return readGetterAndPutter(6);
//            case 0xb9:
//                return readGetterAndPutter(7);

            case 0xb2:
                int gsIndex = s.readUnsignedShort();
                return new GetStaticInstruction(
                        pool.getClassNameByFieldDefIdx(gsIndex),
                        pool.getMethodNameByFieldDefIdx(gsIndex),
                        pool.getMethodTypeByFieldDefIdx(gsIndex)
                );
            case 0xb3:
                int psIndex = s.readUnsignedShort();
                return new PutStaticInstruction(
                        pool.getClassNameByFieldDefIdx(psIndex),
                        pool.getMethodNameByFieldDefIdx(psIndex),
                        pool.getMethodTypeByFieldDefIdx(psIndex)
                );
            case 0xb4:
                int gfIndex = s.readUnsignedShort();
                return new GetFieldInstruction(
                        pool.getClassNameByFieldDefIdx(gfIndex),
                        pool.getMethodNameByFieldDefIdx(gfIndex),
                        pool.getMethodTypeByFieldDefIdx(gfIndex)
                );
            case 0xb5:
                int pfIndex = s.readUnsignedShort();
                return new PutFieldInstruction(
                        pool.getClassNameByFieldDefIdx(pfIndex),
                        pool.getMethodNameByFieldDefIdx(pfIndex),
                        pool.getMethodTypeByFieldDefIdx(pfIndex)
                );
            case 0xb6:
                int ivIndex = s.readUnsignedShort();
                return new InvokeVirtualInstruction(
                        pool.getClassNameByMethodDefIdx(ivIndex),
                        pool.getMethodNameByMethodDefIdx(ivIndex),
                        pool.getMethodTypeByMethodDefIdx(ivIndex)
                );
            case 0xb7:
                int isIndex = s.readUnsignedShort();
                return new InvokeSpecialInstruction(
                        pool.getClassNameByMethodDefIdx(isIndex),
                        pool.getMethodNameByMethodDefIdx(isIndex),
                        pool.getMethodTypeByMethodDefIdx(isIndex)
                );
            case 0xb8:
                int mdIdx = s.readUnsignedShort();
                return new InvokeStaticInstruction(
                        pool.getClassNameByMethodDefIdx(mdIdx),
                        pool.getMethodNameByMethodDefIdx(mdIdx),
                        pool.getMethodTypeByMethodDefIdx(mdIdx)
                );
            case 0xb9:
                int iiIdx = s.readUnsignedShort();
                System.out.println(iiIdx);
                return new InvokeInterfaceInstruction(
                        pool.getClassNameByIMethodDefIdx(iiIdx),
                        pool.getMethodNameByIMethodDefIdx(iiIdx),
                        pool.getMethodTypeByIMethodDefIdx(iiIdx),
                        s.readUnsignedByte(),
                        s.readUnsignedByte()
                );

//            case 0xba:
//                return readInvokeDynamic();
            case 0xbb:
                return new NewInstruction(pool.getClassName(s.readUnsignedShort()));
            case 0xbc:
                return new NewArrayInstruction(s.readUnsignedByte());
            case 0xbd:
                return new ANewArrayInstruction(pool.getClassName(s.readUnsignedShort()));
            case 0xbe:
                return new ArrayLengthInstruction();
            case 0xbf:
                return new AThrowInstruction();
            case 0xc1:
                return new InstanceOfInstruction(pool.getClassName(s.readUnsignedShort()));
            case 0xc2:
                return new MonitorEnterInstruction();
            case 0xc3:
                return new MonitorExitInstruction();
            case 0xc4:
                switch (s.readUnsignedByte()) {
                    case 0x15:
                        return new WideInstruction(4, new ILoadInstruction(s.readUnsignedShort()));
                    case 0x17:
                        return new WideInstruction(4, new FLoadInstruction(s.readUnsignedShort()));
                    case 0x19:
                        return new WideInstruction(4, new ALoadInstruction(s.readUnsignedShort()));
                    case 0x16:
                        return new WideInstruction(4, new LLoadInstruction(s.readUnsignedShort()));
                    case 0x18:
                        return new WideInstruction(4, new DLoadInstruction(s.readUnsignedShort()));
                    case 0x36:
                        return new WideInstruction(4, new IStoreInstruction(s.readUnsignedShort()));
                    case 0x38:
                        return new WideInstruction(4, new FStoreInstruction(s.readUnsignedShort()));
                    case 0x3a:
                        return new WideInstruction(4, new AStoreInstruction(s.readUnsignedShort()));
                    case 0x37:
                        return new WideInstruction(4, new LStoreInstruction(s.readUnsignedShort()));
                    case 0x39:
                        return new WideInstruction(4, new DStoreInstruction(s.readUnsignedShort()));
                    case 0x84:
                        return new WideInstruction(4, new IIncInstruction(s.readUnsignedShort(), s.readUnsignedShort()));
                    default:
                        throw new UnsupportedOperationException();
                }
            case 0xc6:
                return new IfNullInstruction(s.readShort());
            case 0xc7:
                return new IfNonNullInstruction(s.readShort());
            case 0xc8:
                return new GotoWInstruction(s.readInt());

            case 0xc0:
                return new TodoInstruction("checkcast", Arrays.asList(
                        s.readUnsignedShort()
                ));

            case 0xa8:
            case 0xa9:
            case 0xc5:
            case 0xc9:
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

    private LdcInstruction readLdc() throws IOException {
        int idx = s.readUnsignedByte();
        ConstantInfo info = pool.get(idx - 1);
        switch (info.getTag()) {
            case ClazzFile.CONSTANT_STRING:
                int strIdx = ((StringConstantInfo) info).getStringIndex();
                String str = ((Utf8ConstantInfo) pool.get(strIdx - 1)).str();
                return new LdcInstruction("Ljava/lang/String", str);
            case ClazzFile.CONSTANT_INTEGER:
                return new LdcInstruction("I", ((IntegerConstantInfo) info).val());
            case ClazzFile.CONSTANT_FLOAT:
                return new LdcInstruction("F", ((FloatConstantInfo) info).val());
            case ClazzFile.CONSTANT_CLASS:
                return new LdcInstruction("L", ((Utf8ConstantInfo) pool.get(((ClassConstantInfo) info).getNameIndex() - 1)).str());
            default:
                throw new InstructionReaderException();
        }
    }

    private LdcWInstruction readLdcW() throws IOException {
        int idx = s.readUnsignedShort();
        ConstantInfo info = pool.get(idx - 1);
        switch (info.getTag()) {
            case ClazzFile.CONSTANT_STRING:
                int strIdx = ((StringConstantInfo) info).getStringIndex();
                String str = ((Utf8ConstantInfo) pool.get(strIdx - 1)).str();
                return new LdcWInstruction("Ljava/lang/String", str);
            case ClazzFile.CONSTANT_INTEGER:
                return new LdcWInstruction("I", ((IntegerConstantInfo) info).val());
            case ClazzFile.CONSTANT_FLOAT:
                return new LdcWInstruction("F", ((FloatConstantInfo) info).val());
            case ClazzFile.CONSTANT_CLASS:
                return new LdcWInstruction("L", info);
            default:
                throw new InstructionReaderException();
        }
    }

    private Ldc2WInstruction readLdc2W() throws IOException {
        int idx = s.readUnsignedShort();
        ConstantInfo info = pool.get(idx - 1);
        switch (info.getTag()) {
            case ClazzFile.CONSTANT_DOUBLE:
                return new Ldc2WInstruction(((DoubleConstantInfo) info).val());
            case ClazzFile.CONSTANT_LONG:
                return new Ldc2WInstruction(((LongConstantInfo) info).val());
            default:
                throw new InstructionReaderException();
        }
    }

    private LookupSwitchInstruction readLookupSwitch() throws IOException {
        int lsOffset = 1;
        int lsPadding = s.readPadding();
        lsOffset += lsPadding;

        int lsDef = s.readInt();
        lsOffset += 4;
        int lsPairsCnt = s.readInt();
        lsOffset += 4;

        int lsPairsLen = lsPairsCnt * 2 * 4;
        Map<Integer, Integer> lsMap = new HashMap<>();
        for (int i = 0; i < lsPairsCnt; i++) {
            lsMap.put(s.readInt(), s.readInt());
        }

        lsOffset += lsPairsLen;
        return new LookupSwitchInstruction(lsOffset, lsDef, lsPairsCnt, lsMap);
    }

    private Instruction readGetterAndPutter(int i) throws IOException {
        int idx = s.readUnsignedShort();
        ClassNameDescriptionUnion union = ClassNameDescriptionUnion.of(idx, pool);
        switch (i) {
            case 0:
                return new GetStaticInstruction(
                        union.className,
                        union.name,
                        union.descriptor
                );
            case 1:
                return new PutStaticInstruction(
                        union.className,
                        union.name,
                        union.descriptor
                );
            case 2:
                return new GetFieldInstruction(
                        union.className,
                        union.name,
                        union.descriptor
                );
            case 3:
                return new PutFieldInstruction(
                        union.className,
                        union.name,
                        union.descriptor
                );
            case 4:
                return new InvokeVirtualInstruction(
                        union.className,
                        union.name,
                        union.descriptor
                );
            case 5:
                return new InvokeSpecialInstruction(
                        union.className,
                        union.name,
                        union.descriptor
                );
            case 6:
                return new InvokeStaticInstruction(
                        union.className,
                        union.name,
                        union.descriptor
                );
            case 7:
                return new InvokeInterfaceInstruction(
                        union.className,
                        union.name,
                        union.descriptor,
                        s.readUnsignedByte(),
                        s.readUnsignedByte()

                );
            default:
                return null;
        }
    }

    public int getOperation() {
        return op;
    }

//    public InvokeDynamicInstruction readInvokeDynamic() throws IOException {
//        int idx = s.readUnsignedShort();
//        InvokeDynamicConstantInfo info = (InvokeDynamicConstantInfo) pool.get(idx - 1);
//
//        NameAndTypeConstantInfo nameAndType = (NameAndTypeConstantInfo) pool.get(info.getNameAndTypeIndex());
//
//        return new InvokeDynamicInstruction(
//                pool.getString(nameAndType.getNameIndex()),
//                pool.getString(nameAndType.getDescriptorIndex()),
//                info.getBootstrapMethodAttrIndex()
//        );
//    }

    public DataInputStream getDataInputStream() {
        return s;
    }

    public ByteArrayInputStream getByteArrayInputStream() {
        return ba;
    }

    public ConstantPool getConstantPool() {
        return pool;
    }

    private static class ClassNameDescriptionUnion {
        public final String className;
        public final String name;
        public final String descriptor;

        public ClassNameDescriptionUnion(String className, String name, String descriptor) {
            this.className = className;
            this.name = name;
            this.descriptor = descriptor;
        }

        public static ClassNameDescriptionUnion of(int idx, ConstantPool pool) {
            System.out.println(idx);
            FieldConstantInfo info = ((FieldConstantInfo) pool.get(idx - 1));
            NameAndTypeConstantInfo nameAndTypeConstantInfo = ((NameAndTypeConstantInfo) pool.get(info.getNameAndTypeIndex()));

            String className = ((Utf8ConstantInfo) pool.get(info.getClassIndex() - 1)).str();
            String name = ((Utf8ConstantInfo) pool.get(nameAndTypeConstantInfo.getNameIndex() - 1)).str();
            String descriptor = ((Utf8ConstantInfo) pool.get(nameAndTypeConstantInfo.getDescriptorIndex() - 1)).str();

            return new ClassNameDescriptionUnion(className, name, descriptor);
        }

    }
}
