package swap;

/**
 * Created by Administrator on 2017/3/23.
 * 插入排序：将一个记录插入到有序数列中，使得到的心得数列仍然有序
 * 思想：将n个有序数列放在数组a中，要插入的数列为x，首先确定x插在数组中的位置p，数组中p之后的元素都向后移动
 * 一个位置，空出a(p)，将x放入a(p).这样即刻实现插入后数列仍然有序
 * 个人看法：插入排序就是先在设定的无序数组中，找出一个或者两个有序的在进行遍历判断，将符合判断的数进行向前插入
 */
public class InsertSort {
    public static void main(String[] args) {
        Integer[] ints = new Integer[]{2,34,45,56,2,11,23,45,67,8};
        int tmp;    //定义临时变量
        int j;
        for (int i = 0; i < ints.length; i++) {
            tmp = ints[i];  //保存临时变量
            for (j=i-1;j>=0&&ints[j] > tmp;j--) {
                ints[j+1] = ints[j];    //数组元素交换
            }
            ints[j+1] = tmp;
        }

        for (int i = 0; i <ints.length ; i++) {
            System.out.print(ints[i] + "\t");
        }
    }
}
