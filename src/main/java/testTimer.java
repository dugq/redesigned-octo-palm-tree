import org.junit.Test;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by duq on 2017/5/23.
 */
public class testTimer {
    @Test
    public void test() throws InterruptedException {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("i don't know !!!");
            }
        };
        Timer timer =  new Timer();
        timer.schedule(timerTask,1000);
        System.out.println("i'm sleeping");
        Thread.sleep(2000);
        System.out.println("wake up!");


    }
}
