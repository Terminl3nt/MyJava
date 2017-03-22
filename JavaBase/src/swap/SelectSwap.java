package swap;

/**
 * Created by Administrator on 2017/3/22.
 * 选择排序法：如果数组有重复值，应使用选择排序
 * 原理：每一趟从排序的数据元素中显出最小（或者最大值），谁许放在已排好序的数列的最后，直到全部待排序的数据元素排完
 * 从数组中挑选最大值并放在数组最后，而遇到重复的相等值不会做任何处理，所以如果程序允许数组有
 * 重复值的情况，建议使用选择排序法，因为数据交换次数较少，相对速度也会略微提升，取决于数组中重复值的数量
 */
public class SelectSwap {
    public static void main(String[] args) {
        int[] ints = new int[]{3,23,43,3,3,21,21,2,11,2,33,123,11};
        int index;
        for (int i = 0; i < ints.length-1; i++) {
            index = i;
            for (int j = i+1; j < ints.length; j++) {
                if (ints[j] < ints[index]){
                    index = j;
                }
            }
            int temp = ints[i];
            ints[i] = ints[index];
            ints[index] = temp;
        }

        for (int anInt : ints) {
            System.out.print(anInt + "\t");
        }
    }
}
