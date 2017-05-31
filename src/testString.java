import org.junit.Test;

/**
 * Created by duq on 2017/5/27.
 */
public class testString {
    @Test
    public void test(){
        String A = "ab";
        String B = "abc";
        String C = A+"c";
        String D = "a"+"bc";
        System.out.println(B.equals(C));
        System.out.println(B.equals(D));
        System.out.println(D.equals(C));
    }

}
