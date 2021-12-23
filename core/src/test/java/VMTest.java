import net.tdiant.tinyjvm.TinyArguments;
import net.tdiant.tinyjvm.TinyJVM;
import net.tdiant.tinyjvm.VMMain;
import net.tdiant.tinyjvm.classes.loader.*;
import net.tdiant.tinyjvm.runtime.Clazz;
import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.InstanceArrayInstance;
import net.tdiant.tinyjvm.runtime.Method;

import java.util.Arrays;

public class VMTest {

    public static final ClazzSource src = new MultipleClazzSource(Arrays.asList(
            new DirectoryClazzSource("C:\\Users\\tdiant\\Desktop\\codetest\\code"),
            new JarClazzSource("C:\\Users\\tdiant\\Desktop\\codetest\\code\\rt.jar"),
            new JarClazzSource("D:\\Java\\jre_1.8.0_281\\lib\\rt.jar")
    ));

    public static void main(String[] args) {

        VMMain vm = new VMMain();

        TinyJVM.args = new TinyArguments(new String[]{});
        TinyJVM.vm = vm;

        vm.run(src);

        System.out.println("===hello world===");

        runHelloWorld();

    }

    public static void runHelloWorld() {

        Clazz cls = new ClazzLoader(src).loadClazz("Program");

        Method m = cls.getMethod("main", "([Ljava/lang/String;)V");

        System.out.println("fuck==================================");
        System.out.println(m.getInstructions());
        System.out.println("fuck==================================");

        Frame f = new Frame(m);
        f.getOperandStack().pushRef(new InstanceArrayInstance(
                TinyJVM.vm.getHeap().getClazz("java/lang/String"),
                new Object[]{}
        ));
        TinyJVM.vm.execute(f);

    }

}
