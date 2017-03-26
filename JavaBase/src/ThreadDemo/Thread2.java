package ThreadDemo;

/**
 * Created by Administrator on 2017/3/26.
 * 实现多线程第一种：扩展Thread类，重写run方法
 */
public class Thread2 extends Thread {
    private String name;

    public Thread2(String name) {

        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(name + "运行: " + i);
            try {
                sleep((int)Math.random()*10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
