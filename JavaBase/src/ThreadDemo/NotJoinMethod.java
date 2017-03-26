package ThreadDemo;

/**
 * Created by Administrator on 2017/3/26.
 */
public class NotJoinMethod {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + "主线程运行开始");
        JoinMethod jm1 = new JoinMethod("A");
        JoinMethod jm2 = new JoinMethod("B");
        jm1.start();
        jm2.start();
        System.out.println(Thread.currentThread().getName() + "主线程运行结束");
    }
}
