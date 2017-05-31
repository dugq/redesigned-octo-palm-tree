import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

import static java.lang.invoke.MethodHandles.lookup;


/**
 * Created by duq on 2017/2/28.1111111111111111
 */
public class test{
    static {
        a = 2;
    }
    private static int a  = 1;

    private int b = 1;




    public static class gradPa{

    }

    @Test
    public void test()
    {

        b = 2;
        System.out.println(b);
//        a = 0 ;
//        a = 2;
//        a = 1;
//        System.out.println(a);

//        new Son("123","123",123).test1();


//        Set<Person> set = new HashSet<Person>();
//        Person p1 = new Person("唐僧","pwd1",25);
//        Person p2 = new Person("孙悟空","pwd2",26);
//        Person p3 = new Person("猪八戒","pwd3",27);
//        set.add(p1);
//        set.add(p2);
//        set.add(p3);
//        System.out.println("总共有:"+set.size()+" 个元素!"); //结果：总共有:3 个元素!
//        p3.setAge(2); //修改p3的年龄,此时p3元素对应的hashcode值发生改变
//
//        set.remove(p3); //此时remove不掉，造成内存泄漏
//
//        set.add(p3); //重新添加，居然添加成功
//        System.out.println("总共有:"+set.size()+" 个元素!"); //结果：总共有:4 个元素!
//        for (Person person : set)
//        {
//            System.out.println(person);
//        }
    }

AtomicReference<ArrayList<String>> atomicReference = new AtomicReference(new ArrayList<>());
    private int length;
    private int times=0;
    private CountDownLatch c = new CountDownLatch(2);
    @Test
    public void String() throws InterruptedException {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0 ; i < 1000; i++){
                    testAdd("1");
                    Thread.yield();
                }
                System.out.println("1.size="+atomicReference.get().size());
                System.out.println("1.times="+times);
                c.countDown();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0 ; i < 1000; i++){
                    testAdd("2");
                    Thread.yield();
                }
                System.out.println("1.size="+atomicReference.get().size());
                System.out.println("1.times="+times);
                c.countDown();
            }
        });
        t.start();
        t2.start();
        c.await();

    }

    private void testAdd(String num) {
        boolean b = true;
        do{
            ArrayList<String> list = atomicReference.get();
            ArrayList<String> newlist = new ArrayList<String>(list);
            newlist.add("123");
            b = atomicReference.compareAndSet(list, newlist);
            if(!b){
                times=times+1;
                System.out.println(times);
            }
        }while (!b);
    }


    public class Person{
        private String name;
        private int age;
        private String psw;

        public Person(String name,String psw,int  age){
            this.age = age;
            this.name = name;
            this.psw = psw;
        }

        public String test(){
            System.out.println("test");
            return "123";
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getPsw() {
            return psw;
        }

        public void setPsw(String psw) {
            this.psw = psw;
        }
    }

    public class Son extends Person{

        public Son(String name, String psw, int age) {
            super(name, psw, age);
        }

        public void test1(){
            try {
                MethodType type = MethodType.methodType(String.class);
                MethodHandle mh = lookup().findSpecial(Person.class, "test", type,getClass());
                mh.invoke(this);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }

   public void test123(){
       try {
           Thread.interrupted();
           TimeUnit.SECONDS.sleep(100);
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
   }



   @Test
    public void testException(){
       if(true){
           throw  new RuntimeException("nimabi");
       }
      System.out.println("123");
   }

   @Test
   public void testThreadLocal(){
        ThreadLocal<Integer> testValue = new ThreadLocal<Integer>(){
            protected Integer initialValue(){
                return 1;
            }
        };
        testValue.set(2);
        int a = testValue.get();
        System.out.println(a);
   }

   @Test
    public void testLock(){
       class testReenX extends AbstractQueuedSynchronizer{

       }

   }

   @Test
    public void testList(){
       System.out.println(List.class.isAssignableFrom(ArrayList.class));
   }
}
