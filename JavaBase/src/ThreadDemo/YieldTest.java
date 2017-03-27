package ThreadDemo;

/**
 * Created by Administrator on 2017/3/27.
 * 结论：yield()从未导致线程转到等待/睡眠/阻塞状态。
 * 在大多数情况下，yield()将导致线程从运行状态转到可运行状态，但有可能没有效果。
 */
public class YieldTest {
    public static void main(String[] args) {
        ThreadYield ty1 = new ThreadYield("张三");
        ThreadYield ty2 = new ThreadYield("李四");
        ty1.start();
        ty2.start();
    }
}
