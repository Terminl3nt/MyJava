package dataType;

/**
 * Created by Administrator on 2017/3/24.
 * 字符串池
 * JVM中存在着一个字符串池，其中保存着很多String对象
 * 并且可以被共享使用，因此它提高了效率
 * 由于String类型是final的，它的值经创建就不可改变
 * 字符串池由String类维护，我们可以调用intern()方法来访问字符串池
 */
public class StringDataType {
    public static void main(String[] args) {
        //在字符串池创建了一个对象
        String s1 = "abc";
        //字符串pool已经存在对象“abc”共享，所以创建了0个对象，累计创建了1个对象
        String s2 = "abc";
        System.out.println("s1==s2:" + (s1 == s2)); //true

        //===============================================
        //关于new String
        //创建了两个对象，一个存放在字符串池中，一个存在堆内存中
        //还有一个对象引用 s3存放在栈中
        String s3 = new String("abc");
        //字符串池中已经存在“abc”对象，所以只在堆中创建一个对象
        String s4 = new String("abc");
        System.out.println("s3 == s4:" +(s3 == s4));//false s3 和s4栈区的地址值不同，指向堆区的不同地址
        System.out.println("s3.equals(s4):" + s3.equals(s4));//true s3和s4值相同
        System.out.println("s1 == s3:" + (s1 == s3)); //false 存放的地区多不同，一个栈，一个堆
        System.out.println("s1.equals(s3):" + s1.equals(s3)); //true 值相同
        //===========================
        /**
         * 由于常量的值在编译的时候就被确定了
         * 在这里“ab”和“cd”都是常量，因此变量str3的值在编译时就可以确定
         * 这行代码编译后的效果等同于String str3 = "abcd"
         */
        String str1 = "ab" + "cd";
        String str11 = "abcd";
        System.out.println("str1 == str11:" + (str1 == str11)); // true
        //=======================================
        /**
         * 局部变量str2，str3存储的时存储两个拘留字符串对象（intern字符串对象）的地址
         * 第三行代码原理(str2 + str3)
         * 运行期JVM首先会在堆中创建要给StringBuilder类
         * 同时用str2指向的拘留字符串对象完成初始化
         * 然后调用append方法完成堆str3所指向的拘留字符串的合并
         * 接着调用StringBuilder的toString()方法在堆中创建一个String对象
         * 最后将刚生成的String对象的堆地址存放在局部变量str4中
         * 而str5存储的时字符串池中“abcd”所对应的拘留字符串对象的地址
         * str4与str5地址就不一样了
         * 内存中实际上有5个字符串对象
         * 三个拘留字符串对象，一个String对象，一个StringBuilder对象
         */
        String str2 = "ab"; //一个对象
        String str3 = "cd"; //一个对象
        String str4 = str2 + str3;
        String str5 = "abcd";
        System.out.println("str4 == str5:" + (str4 == str5));//false
        //==========================================
        /**
         * Java编译器对String + 基本类型/常量 是当成常量表达式直接求值来优化的
         * 运行期间两个String相加，会产生心得对象，存储在堆中
         */
        String str6 = "b";
        String str7 = "a" + str6;
        String str67 = "ab";
        System.out.println("str7 == str67:" + (str7 == str67)); //false
        //str8为常量变量，编译期会被优化
        final String str8 = "b";
        String str9  = "a" + str8;
        String str89 = "ab";
        System.out.println("str9 == str89:" + (str9 == str89)); //true
    }
}
