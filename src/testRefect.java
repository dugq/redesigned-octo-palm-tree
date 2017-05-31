import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Test;
import sun.reflect.misc.FieldUtil;

import java.lang.reflect.Field;

/**
 * Created by duq on 2017/5/22.
 */
public class testRefect {

    @Test
    public void testRect(){
        Obj4Test test = new Obj4Test();
        try {
            Field a = test.getClass().getDeclaredField("a");
            a.setAccessible(true);
            System.out.println( a.get(test).toString());
            System.out.println(FieldUtils.readDeclaredField(test, "a",true).toString());
            System.out.println(FieldUtils.readDeclaredField(test, "b",true).toString());
            System.out.println(FieldUtils.readDeclaredField(test, "c",true).toString());
            System.out.println(FieldUtils.readDeclaredField(test, "aa",true).toString());

            FieldUtils.writeDeclaredField(test,"a",2,true);
            FieldUtils.writeDeclaredField(test,"b",3,true);
            FieldUtils.writeDeclaredField(test,"c",4,true);
            FieldUtils.writeDeclaredField(test,"aa","1231232",true);

            ClassLoader classLoader = Obj4Test.class.getClassLoader();


            System.out.println(FieldUtils.readDeclaredField(test, "a",true).toString());
            System.out.println(FieldUtils.readDeclaredField(test, "b",true).toString());
            System.out.println(FieldUtils.readDeclaredField(test, "c",true).toString());
            System.out.println(FieldUtils.readDeclaredField(test, "aa",true).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
