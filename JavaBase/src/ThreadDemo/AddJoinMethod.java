package ThreadDemo;

/**
 * Created by Administrator on 2017/3/26.
 * 在很多情况下，主线程生成并起动了子线程，如果子线程里要进行大量的耗时的运算，
 * 主线程往往将于子线程之前结束，但是如果主线程处理完其他的事务后，需要用到子线程的处理结果，
 * 也就是主线程需要等待子线程执行完成之后再结束，这个时候就要用到join()方法了。

 */
public class AddJoinMethod {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + "主线程运行开始");
        JoinMethod jm1 = new JoinMethod("A");
        JoinMethod jm2 = new JoinMethod("B");
        jm1.start();
        jm2.start();
        try {
            jm1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            jm2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "主线程运行结束");
    }
}
