import org.junit.Test;

import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by duq on 2017/6/2.
 */
public class TestThreads {
    volatile int flag = 0;
    @Test
    public void test(){
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(;;){
                    if(flag%4==0){
                        System.out.println("A");
                        flag++;
                        if(flag>40000){
                            break;
                        }
                    }
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(;;){
                    if(flag%4==1){
                        System.out.println("B");
                        flag++;
                        if(flag>40000){
                            break;
                        }
                    }
                }
            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(;;){
                    if(flag%4==2){
                        System.out.println("C");
                        flag++;
                        if(flag>40000){
                            break;
                        }
                    }
                }
            }
        });
        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(;;){
                    if(flag%4==3){
                        System.out.println("D");
                        flag++;
                        if(flag>40000){
                            break;
                        }
                    }
                }
            }
        });

        Date start = new Date();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Date end = new Date();
        System.out.println(end.getTime()-start.getTime());
    }

    public synchronized void say(String str){
        System.out.println(str);
    }
    final Lock l = new ReentrantLock();
    @Test
    public void test2(){

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(;;){
                    l.lock();
                    if(flag%4==0){
                        System.out.println("A");
                        flag++;
                        if(flag>40000){
                            l.unlock();
                            break;
                        }
                    }
                    l.unlock();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(;;){
                    l.lock();
                    if(flag%4==1){
                        System.out.println("B");
                        flag++;
                        if(flag>40000){
                            l.unlock();
                            break;
                        }
                    }
                    l.unlock();
                }
            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(;;){
                    l.lock();
                    if(flag%4==2){
                        System.out.println("C");
                        flag++;
                        if(flag>40000){
                            l.unlock();
                            break;
                        }
                    }
                    l.unlock();
                }
            }
        });
        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(;;){
                    l.lock();
                    if(flag%4==3){
                        System.out.println("D");
                        flag++;
                        if(flag>40000){
                            l.unlock();
                            break;
                        }
                    }
                    l.unlock();
                }
            }
        });
        Date start = new Date();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Date end = new Date();
        System.out.println(end.getTime()-start.getTime());

    }


}
