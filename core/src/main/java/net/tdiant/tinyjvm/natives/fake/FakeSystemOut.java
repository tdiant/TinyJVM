package net.tdiant.tinyjvm.natives.fake;

import net.tdiant.tinyjvm.TinyJVM;

public class FakeSystemOut {

    public static void fake() {
//        Clazz clazzPrintStream = new Clazz(2, "java/io/PrintStream", "java/lang/Class", new ArrayList<>(), Arrays.asList(
//                new Method(2, "println", "(I)V", 666, 666, new HashMap<>(), new ArrayList<>(), new LineNumTableAttribute(new LineNumTableAttribute.Line[]{}))
//        ), null, null, null, null, null);
//        TinyJVM.vm.getHeap().registerClass("java/io/PrintStream", clazzPrintStream);

        TinyJVM.vm.getHeap().registerMethod("java/io/PrintStream_println_(I)V", frame -> {
            System.out.println(frame.getOperandStack().popInt());
        });
    }

}
