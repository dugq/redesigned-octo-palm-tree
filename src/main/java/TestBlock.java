import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by duq on 2017/4/24.
 */
public class TestBlock implements Lock{
    private final boolean isFair;
    private final AbstractQueuedSynchronizer block;
    private final boolean isShare;

    /**
     * 不共享
     * @param isFair
     */
    public TestBlock(boolean isFair){
        this.isFair = isFair;
        this.isShare = false;
        this.block = isFair? new myUnShareFairLock():new myUnShareUnFairLock() ;
    }
    /**
     * 共享
     * @param isFair
     * @param state
     */
    public TestBlock(boolean isFair,int state){
        this.isFair = isFair;
        this.isShare = true;
        this.block  = isFair? new myShareFairLock(state):new myShareUnFairLock(state);
    }
    private class myUnShareUnFairLock extends AbstractQueuedSynchronizer{
        protected boolean tryAcquire(int arg) {
            if(getState()==0 && compareAndSetState(0,1)){
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }
        protected boolean tryRelease(int arg) {
            if(getExclusiveOwnerThread()!=Thread.currentThread()){
                throw new IllegalArgumentException("not owner");
            }
            for(;;){
                if(compareAndSetState(1,0)){
                    setExclusiveOwnerThread(null);
                    return true;
                }
            }
        }
        protected boolean isHeldExclusively()  {
            return getState() == 1 ;
        }

    }
    private class myShareUnFairLock extends AbstractQueuedSynchronizer{
        public myShareUnFairLock(int state){
            setState(state);
        }

        protected int tryAcquireShared(int arg) {
            int old = getState();
            int newState = old - arg;
                if(newState < 0 || compareAndSetState(old,newState)){
                    return newState;
                }
                return -1;
        }
        protected boolean tryReleaseShared(int arg) {
            for(;;){
                int old = getState();
                int newVale = old+arg;
                if(compareAndSetState(old,newVale)){
                    return true;
                }
            }
        }
    }
    private class myShareFairLock extends AbstractQueuedSynchronizer{
        public myShareFairLock(int state){
            setState(state);
        }

        protected int tryAcquireShared(int arg) {
            int old = getState();
            int newState = old - arg;
                if(!hasQueuedPredecessors() && (newState < 0 || compareAndSetState(old,newState))){
                    return newState;
                }
            return -1;
        }
        protected boolean tryReleaseShared(int arg) {
            for(;;){
                int old = getState();
                int newVale = old+arg;
                if(compareAndSetState(old,newVale)){
                    return true;
                }
            }
        }
    }
    private class myUnShareFairLock extends AbstractQueuedSynchronizer{
        protected boolean tryAcquire(int arg) {
            if(!hasQueuedPredecessors() && compareAndSetState(0,1)){
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }
        protected boolean tryRelease(int arg) {
            if(getExclusiveOwnerThread()!=Thread.currentThread()){
                throw new IllegalArgumentException("not owner");
            }
            for(;;){
                if(compareAndSetState(1,0)){
                    setExclusiveOwnerThread(null);
                    return true;
                }
            }
        }
        protected boolean isHeldExclusively()  {
            return getState()==1;
        }
    }

    @Override
    public void lock() {
        if(isShare){
            block.acquireShared(1);
        }else {
            block.acquire(1);
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean tryLock() {
       throw new UnsupportedOperationException();
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        if(isShare){
           return block.tryAcquireSharedNanos(1,unit.toNanos(time));
        }else {
            return block.tryAcquireNanos(1,unit.toNanos(time));
        }
    }

    @Override
    public void unlock() {
        if(isShare){
            block.releaseShared(1);
        }else {
            block.release(1);
        }
    }
    public ArrayList<Thread> getQueuedThreads(){
       return (ArrayList<Thread>) block.getQueuedThreads();
    }

    @Override
    public Condition newCondition() {
        throw new UnsupportedOperationException();
    }
}
