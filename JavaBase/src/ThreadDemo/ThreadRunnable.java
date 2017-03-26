package ThreadDemo;

/**
 * Created by Administrator on 2017/3/26.
 */
public class ThreadRunnable implements Runnable{
    private String name;

    public ThreadRunnable(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(name + "运行： " + i);
            try {
                Thread.sleep((int)Math.random()*10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
