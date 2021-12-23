package net.tdiant.tinyjvm.classes.file;

import net.tdiant.tinyjvm.classes.file.attr.*;
import net.tdiant.tinyjvm.classes.file.constant.*;
import net.tdiant.tinyjvm.classes.instruction.Instruction;
import net.tdiant.tinyjvm.exception.ClassFileReaderException;
import net.tdiant.tinyjvm.util.CodeByteArrayInputStream;
import net.tdiant.tinyjvm.util.CodeDataInputStream;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClazzFileReader {

    private final File file;
    private final DataInputStream in;

    /**
     * 类文件读取器
     *
     * @param file 类文件
     */
    public ClazzFileReader(File file) throws IOException {
        this.file = file;
        this.in = new DataInputStream(new FileInputStream(file));
    }

    /**
     * 类文件读取器
     *
     * @param in 输入流
     */
    public ClazzFileReader(DataInputStream in) throws IOException {
        this.in = in;
        this.file = null;
    }

    /**
     * 读取Class文件
     *
     * @return 类对象
     */
    public ClazzFile read() throws IOException {

        int classFileTag = in.readInt(); // Class文件标记

        int minorVer = in.readUnsignedShort(); // 附版本号
        int majorVer = in.readUnsignedShort(); // 主版本号

        int constantPoolSize = in.readUnsignedShort(); // 常量池大小
        ConstantPool constantPool = readConstantPool(constantPoolSize - 1); // 常量池

        int accessFlag = in.readUnsignedShort(); // 类访问标志
        int thisClass = in.readUnsignedShort(); // 类索引, 存储当前类名或当前接口名
        int superClass = in.readUnsignedShort(); // 父类索引, 存储父类名

        int interfaceCnt = in.readUnsignedShort(); // 接口数量
        List<InterfaceInfo> interfaceInfos = readInterfaces(interfaceCnt, constantPool); // 接口对象列表

        int fieldCnt = in.readUnsignedShort(); // 类字段数量
        List<FieldInfo> fields = readFields(fieldCnt, constantPool); // 类字段列表

        System.out.println(fields);

        int methodCnt = in.readUnsignedShort(); // 方法数量
        List<MethodInfo> methods = readMethods(methodCnt, constantPool); // 方法列表

        int attributeCnt = in.readUnsignedShort(); // 类属性数量
        List<Attribute> attributes = readAttributes(attributeCnt, constantPool); // 类属性列表

        in.close();

        return new ClazzFile(
                classFileTag,
                minorVer,
                majorVer,
                constantPoolSize,
                constantPool,
                accessFlag,
                thisClass,
                superClass,
                interfaceCnt,
                interfaceInfos,
                fieldCnt,
                fields,
                methodCnt,
                methods,
                attributeCnt,
                attributes
        );

    }

    // 读取常量池
    private ConstantPool readConstantPool(int size) throws IOException {
        ConstantPool pool = new ConstantPool(size);
        for (int i = 1; i <= size; i++) {

            int tag = in.readUnsignedByte();
            ConstantInfo con = null;

            switch (tag) {
                case ClazzFile.CONSTANT_CLASS:
                    con = new ClassConstantInfo(tag, in.readUnsignedShort());
                    break;
                case ClazzFile.CONSTANT_FIELD_REF:
                    con = new FieldConstantInfo(tag, in.readUnsignedShort(), in.readUnsignedShort());
                    break;
                case ClazzFile.CONSTANT_METHOD_REF:
                    con = new MethodConstantInfo(tag, in.readUnsignedShort(), in.readUnsignedShort());
                    break;
                case ClazzFile.CONSTANT_INTERFACE_METHOD_REF:
                    con = new InterfaceMethodConstantInfo(tag, in.readUnsignedShort(), in.readUnsignedShort());
                    break;
                case ClazzFile.CONSTANT_STRING:
                    con = new StringConstantInfo(tag, in.readUnsignedShort());
                    break;
                case ClazzFile.CONSTANT_INTEGER:
                    con = new IntegerConstantInfo(tag, in.readInt());
                    break;
                case ClazzFile.CONSTANT_FLOAT:
                    con = new FloatConstantInfo(tag, in.readFloat());
                    break;
                case ClazzFile.CONSTANT_LONG:
                    con = new LongConstantInfo(tag, in.readLong());
                    break;
                case ClazzFile.CONSTANT_DOUBLE:
                    con = new DoubleConstantInfo(tag, in.readDouble());
                    break;
                case ClazzFile.CONSTANT_NAME_AND_TAG:
                    con = new NameAndTypeConstantInfo(tag, in.readUnsignedShort(), in.readUnsignedShort());
                    break;
                case ClazzFile.CONSTANT_UTF8:
                    con = readUtf8ConstantInfo();
                    break;
                case ClazzFile.CONSTANT_METHOD_HANDLE:
                    con = new MethodHandleConstantInfo(tag, in.readUnsignedByte(), in.readUnsignedShort());
                    break;
                case ClazzFile.CONSTANT_METHOD_TYPE:
                    con = new MethodTypeConstantInfo(tag, in.readUnsignedShort());
                    break;
                case ClazzFile.CONSTANT_INVOKE_DYNAMIC:
                    con = new InvokeDynamicConstantInfo(tag, in.readUnsignedShort(), in.readUnsignedShort());
                    break;
            }

            if (con == null)
                throw new ClassFileReaderException("constant_pool_reader", "Invalid Constant Tag: " + tag);

            pool.push(con);

            // 双精度需要占双位置
            if (tag == ClazzFile.CONSTANT_DOUBLE || tag == ClazzFile.CONSTANT_LONG) {
                pool.push(new EmptyConstantInfo());
                i++;
            }
        }

        return pool;
    }

    private Utf8ConstantInfo readUtf8ConstantInfo() throws IOException {
        int len = in.readUnsignedShort();

        byte[] bytes = new byte[len];
        for (int i = 0; i < len; i++)
            bytes[i] = in.readByte();

        return new Utf8ConstantInfo(ClazzFile.CONSTANT_UTF8, len, bytes);
    }

    // 读取接口
    private List<InterfaceInfo> readInterfaces(int cnt, ConstantPool pool) throws IOException {
        List<InterfaceInfo> interfaceInfos = new ArrayList<>();

        for (int i = 1; i <= cnt; i++) {

            int idx = in.readUnsignedShort();
            String name = pool.getClassName(idx);
            interfaceInfos.add(new InterfaceInfo(name));
        }

        return interfaceInfos;
    }

    // 读取类字段
    private List<FieldInfo> readFields(int cnt, ConstantPool pool) throws IOException {
        List<FieldInfo> fields = new ArrayList<>();

        for (int i = 1; i <= cnt; i++) {

            int accessFlag = in.readUnsignedShort();
            int nameIdx = in.readUnsignedShort();
            int descriptorIdx = in.readUnsignedShort();
            int attributeCnt = in.readUnsignedShort();

            List<Attribute> attributes = readAttributes(attributeCnt, pool);

            ConstantInfo info = pool.get(nameIdx - 1);
            String name = ((Utf8ConstantInfo) info).str();

            String descriptor = ((Utf8ConstantInfo) pool.get(descriptorIdx - 1)).str();

            FieldInfo fieldInfo = new FieldInfo(accessFlag, name, descriptor, attributes);
            fields.add(fieldInfo);

        }

        return fields;
    }

    // 读取类方法
    private List<MethodInfo> readMethods(int cnt, ConstantPool pool) throws IOException {
        List<MethodInfo> infos = new ArrayList<>();

        for (int i = 0; i < cnt; i++) {

            int accessFlag = in.readUnsignedShort();
            int nameIdx = in.readUnsignedShort();
            int descriptorIdx = in.readUnsignedShort();
            int attributesCount = in.readUnsignedShort();

            String name = ((Utf8ConstantInfo) pool.get(nameIdx - 1)).str();
            String descriptor = ((Utf8ConstantInfo) pool.get(descriptorIdx - 1)).str();

            System.out.println("loading code:: " + name + "::" + nameIdx + "::" + accessFlag);

            List<Attribute> attrs = readAttributes(attributesCount, pool);

            infos.add(new MethodInfo(
                    accessFlag,
                    name,
                    descriptor,
                    attrs
            ));
        }
        return infos;
    }

    // 读取类属性
    private List<Attribute> readAttributes(int cnt, ConstantPool pool) throws IOException {

        List<Attribute> attributes = new ArrayList<>();

        for (int i = 1; i <= cnt; i++) {

            int attrNameIdx = in.readUnsignedShort();
            String attrName = ((Utf8ConstantInfo) pool.get(attrNameIdx - 1)).str();
            int attrLen = in.readInt();

            System.out.println("  - arr name: " + attrName + " :: len: " + attrLen);

            Attribute attr = null;

            switch (attrName) {
                case Attribute.SourceFile:
                    String srcFile = ((Utf8ConstantInfo) pool.get(in.readUnsignedShort() - 1)).str();
                    attr = new SourceFileAttribute(srcFile);
                    break;
                case Attribute.Code:
                    int maxStackSize = in.readUnsignedShort();
                    int maxLocals = in.readUnsignedShort();
                    int codeLen = in.readInt();

                    System.out.println("  -==-:: " + maxStackSize + "::" + maxLocals + "::" + codeLen);

                    byte[] codeBytes = new byte[codeLen];
                    for (int j = 0; j < codeLen; j++)
                        codeBytes[j] = in.readByte();

                    List<Instruction> instructions = readCodeByByte(codeBytes, pool);

                    int exTableLength = in.readUnsignedShort(); //异常数量
                    ExceptionInfo[] exceptionInfos = new ExceptionInfo[exTableLength];
                    for (int j = 0; j < exTableLength; j++) {
                        int etsp = in.readUnsignedShort();
                        int etep = in.readUnsignedShort();
                        int ethp = in.readUnsignedShort();
                        int ctIdx = in.readUnsignedShort();

                        String etClassname = null;
                        if (ctIdx != 0)
                            etClassname = pool.getClassName(ctIdx);

                        ExceptionInfo e = new ExceptionInfo(etsp, etep, ethp, etClassname);
                        exceptionInfos[j] = e;
                    }

                    int attrCnt = in.readUnsignedShort();
                    List<Attribute> attrs = readAttributes(attrCnt, pool);

                    attr = new CodeAttribute(maxStackSize, maxLocals, instructions, Arrays.asList(exceptionInfos), attrs);

                    break;
                case Attribute.LineNumberTable:
                    int lnLen = in.readUnsignedShort();
                    LineNumTableAttribute.Line[] lines = new LineNumTableAttribute.Line[lnLen];
                    for (int j = 0; j < lnLen; j++)
                        lines[j] = new LineNumTableAttribute.Line(
                                in.readUnsignedShort(),
                                in.readUnsignedShort()
                        );

                    attr = new LineNumTableAttribute(lines);

                    break;
                case Attribute.BootstrapMethods:
                    int bLen = in.readUnsignedShort();
                    BootstrapMethodsAttribute.BootstrapMethod[] methods = new BootstrapMethodsAttribute.BootstrapMethod[bLen];
                    for (int j = 0; j < bLen; j++) {
                        int bsmr = in.readUnsignedShort();
                        int nbma = in.readUnsignedShort();
                        int[] args = new int[nbma];
                        for (int i2 = 0; i2 < nbma; i2++)
                            args[i2] = in.readUnsignedShort();

                        methods[j] = new BootstrapMethodsAttribute.BootstrapMethod(bsmr, args);
                    }

                    attr = new BootstrapMethodsAttribute(methods);
                    break;
                default:
                    //skit
                    for (int j = 0; j < attrLen; j++)
                        in.readByte();
                    break;
            }

            attributes.add(attr);

        }

        return attributes;

    }

    public List<Instruction> readCodeByByte(byte[] bytes, ConstantPool pool) throws IOException {

        System.out.println(Arrays.toString(bytes));

        List<Instruction> list = new ArrayList<>();

        CodeByteArrayInputStream ba = new CodeByteArrayInputStream(bytes);
        CodeDataInputStream s = new CodeDataInputStream(ba);
        while (s.available() > 0) {
            int op = s.readUnsignedByte(); // 操作
            System.out.println(" ===::" + op);

            Instruction ins = new InstructionReader(op, ba, s, pool).read();
            System.out.println("inst::" + ins.getClass().getName() + "::" + ins);
            if (ins == null) {
                throw new ClassFileReaderException("ByteCodeReader", "Cannot transform instruction operation " + op + ".");
            }
            list.add(ins);

        }

        return list;
    }


    public File getFile() {
        return file;
    }
}
