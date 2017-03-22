package dataType;

/**
 * Created by Administrator on 2017/3/22.
 * 装箱和拆箱
 */
public class Boxing {
    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;
        System.out.println(c == d );//true
        System.out.println(e == f);//false Integer 的范围是-128~127之间，在这个范围之外不存入缓存
        System.out.println(c == (a+b));//true
        System.out.println(c.equals(g));//false
        System.out.println((a+b) == c);//true
        System.out.println(g.equals((a+b)));//false 不知道为什么？
    }
}
