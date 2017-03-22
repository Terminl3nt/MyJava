package swap;

/**
 * Created by Administrator on 2017/3/21.
 */
public class Maopao {
    public static void main(String[] args) {
        int[] ints = new int[]{23,34,45,56,34,3,4,5,6,};
        for (int i = 1; i < ints.length; i++) {
            for (int j = 0; j < ints.length-i; j++) {
                if (ints[j] > ints[j+1]){
                    int temp = ints[j+1];
                    ints[j+1] = ints[j];
                    ints[j] = temp;
                }
            }
        }
        for (int anInt : ints) {
            System.out.print(anInt + "\t");
        }
    }
}
