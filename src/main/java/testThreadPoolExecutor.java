import org.junit.Test;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by duq on 2017/5/23.
 */
public class testThreadPoolExecutor {
    @Test
    public void test(){
        ThreadPoolExecutor pool = new ThreadPoolExecutor(1,1,0l, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<Runnable>(){});
        pool.execute(new Runnable() {
            @Override
            public void run() {

            }
        });
    }
}
