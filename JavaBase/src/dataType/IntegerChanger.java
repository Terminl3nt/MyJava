package dataType;

import java.lang.reflect.Field;

/**
 * Created by Administrator on 2017/3/25.
 * 涉及到的知识点:
 * 1.java 的参数传递都是值传递
 * 2.Integer的内部实现(value,缓存,等等)
 * 3.反射操作(可访问性)
 * 4.自动装箱和拆箱
 */
public class IntegerChanger {
    public static void main(String[] args) {
        Integer a = 1, b = 2;
        System.out.println("before swap a = " + a + ",b = " + b);
        swap(a, b);
        System.out.println("after swap a = " + a + ",b = " + b);
        Integer c = 1;
        System.out.println("(警告：Integer缓存被修改，代码里：Integer c = 1)，实际c=" + c);
    }

    private static void swap(Integer a, Integer b) {
        try {
            Field f = Integer.class.getDeclaredField("value");
            f.setAccessible(true);//反射访问控制变量
            int tmp = a;
            f.setInt(a,b);
            f.setInt(b,tmp);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
