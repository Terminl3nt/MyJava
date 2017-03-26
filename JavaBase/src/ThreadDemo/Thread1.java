package ThreadDemo;

/**
 * Created by Administrator on 2017/3/26.
 * synchronized, wait, notify结合:典型场景生产者消费者问题
 */
public class Thread1 {
    /**
     * 生产者生产出来的产品交给店员
     */
    private int product = 51;
    private int MAX_PRODUCT = 10;
    public synchronized void produce(){
        if (this.product >= MAX_PRODUCT){
            try {
                System.out.println("产品已经满了，请稍候生产");
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return;
        }
        this.product++;
        System.out.println("生产者生产第" + this.product + "个商品");
        notifyAll(); //通知等待的消费者可以取出产品了
    }
    /**
     * 消费者从店员取产品
     */
    public synchronized void consume(){
        if (this.product <= MAX_PRODUCT){
            try {
                System.out.println("缺货，稍后再取");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return;
        }
        System.out.println("消费者取走了第" + this.product + "个商品");
        this.product--;
        notifyAll(); //通知等待去的生产者可以生产产品了
    }
}
