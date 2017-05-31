import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by duq on 2017/4/26.
 */
public class TestMyTestBlock {

    public static void main(String[] args) {
        TestBlock block = new TestBlock(false,2);
        newBlock(block);
    }

    public static void newBlock(final TestBlock block){
        for( int i = 0 ; i < 10; i++){
            threadTest(block, i);
        }
    }

    private static synchronized void  threadTest(final TestBlock block, final int i) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                testM(block, i);
            }
        },"线程"+i);
        thread.start();
    }

    public static void testM(TestBlock block,int thread){
        block.lock();
        ArrayList<Thread> queuedThreads = block.getQueuedThreads();
        System.out.println(thread+"开始啦。。。。。。。。。");
        if(queuedThreads.size()>0){
            System.out.println(queuedThreads.get(queuedThreads.size()-1).getName());
        }
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(thread+"结束啦。。。。。。。。。");
        block.unlock();
        block.lock();
        System.out.println(thread+"嘿嘿嘿。。。。。。。。。");
        block.unlock();
    }
}
