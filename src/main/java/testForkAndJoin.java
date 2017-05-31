import java.util.concurrent.*;

/**
 * Created by duq on 2017/5/22.
 */
public class testForkAndJoin {
    private static class HasResult extends RecursiveTask{
        private int times;
        public HasResult(int times){
            this.times = times;
        }
        private final static int max = 10;

        @Override
        protected Object compute() {
            if(times > max){
                int middle = times / 2;
                HasResult left = new HasResult(middle);
                HasResult right = new HasResult(times-middle);
                ForkJoinTask fork = left.fork();
                ForkJoinTask fork1 = right.fork();
                return  (int) fork.join()+(int)fork1.join();
            }else{
                return doSomthings(times);
            }
        }
    }

    private class NoResult extends RecursiveAction{

        @Override
        protected void compute() {

        }
    }


    public static int getInteger(){
        return 100;
    }

    public static int doSomthings(int i){
        int a  = 0;
        for(int j = 0 ; j < i ; j++){
            a=+ j;
        }
        return a;
    }


    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        HasResult task = new HasResult(getInteger());
        Future future = pool.submit(task);
        try {
            Object o = future.get();
            System.out.println(o.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
