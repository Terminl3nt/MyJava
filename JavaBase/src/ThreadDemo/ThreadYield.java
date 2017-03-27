package ThreadDemo;

/**
 * Created by Administrator on 2017/3/27.
 * yield()应该做的是让当前运行线程回到可运行状态，以允许具有相同优先级的其他线程获得运行机会。
 * 因此，使用yield()的目的是让相同优先级的线程之间能适当的轮转执行。
 * 但是，实际中无法保证yield()达到让步目的，因为让步的线程还有可能被线程调度程序再次选中。
 *  结果：
 * 第一种情况：李四（线程）当执行到30时会CPU时间让掉，这时张三（线程）抢到CPU时间并执行。

    第二种情况：李四（线程）当执行到30时会CPU时间让掉，这时李四（线程）抢到CPU时间并执行。
 */
public class ThreadYield extends Thread {
    private String name;

    public ThreadYield(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i <=50; i++) {
            System.out.println("" + this.getName() + i);
            //当i=30时，该线程就会把cpu时间让掉，让其他线程或者自己的线程执行（谁先抢到谁执行）
            if (i == 30){
                ThreadYield.yield();
            }
        }
    }
}
