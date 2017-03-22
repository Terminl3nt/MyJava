package swap;

/**
 * Created by Administrator on 2017/3/22.
 * 字符数组的反转，将最后一个放到最前端，经历chars.length/2次循环
 */
public class Reverser {
    public static void main(String[] args) {
        char[] chars = new char[]{'a','d','s','r'};
        for (int i = 0; i < chars.length/2; i++) {
            char temp = chars[chars.length-i-1];
            chars[chars.length-i-1] = chars[i];
            chars[i] = temp;
        }
        for (char aChar : chars) {
            System.out.print(aChar + "\t");
        }
    }
}
