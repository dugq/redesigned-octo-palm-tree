package testBean;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import springBean.TestIoc;

/**
 * Created by duq on 2017/5/31.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:conf/applicationContext.xml")
public class TestIoc1 extends AbstractJUnit4SpringContextTests {
    @Autowired
    private TestIoc testIoc;
    @Test
    public void test(){
        BeanFactory applicationContext1 = applicationContext;
        System.out.println(applicationContext1.getClass().toString());
        testIoc.test();
    }
}
