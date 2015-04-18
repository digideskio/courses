import java.util.Arrays;

public class MergeSort {
  
    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> void sort(T[] a) {
        T[] tmp = (T[]) new Comparable[a.length];
        for (int size = 1; size < a.length; size *= 2) {
            for (int start = 0; start < a.length - size; start += size * 2) {
                merge(a, tmp, start, start + size - 1,
                		Math.min(start + size * 2 - 1, a.length - 1));
            }
        }
    }
 
    private static <T extends Comparable<T>>
    		void merge(T[] a, T[] tmp, int aCtr, int bCtr, int rightEnd) {
        int leftEnd = bCtr - 1;
        int tmpPos = aCtr;
        int	numElements = rightEnd - aCtr + 1;
        while(aCtr <= leftEnd && bCtr <= rightEnd) {
            if(a[aCtr].compareTo(a[bCtr])<= 0)
                tmp[tmpPos++] = a[aCtr++];
            else
                tmp[tmpPos++] = a[bCtr++];
        }
        while(aCtr <= leftEnd) {
            tmp[tmpPos++] = a[aCtr++];
        }
        while(bCtr <= rightEnd)
            tmp[tmpPos++] = a[bCtr++];
        for(int i = 0; i < numElements; i++, rightEnd--)
            a[rightEnd] = tmp[rightEnd];
    }
 
    public static void main(String[] args) {
        Integer[] arr = new Integer[10];
        arr[0] = 5;
        arr[1] = 12;
        arr[2] = 15;
        arr[3] = 25;
        arr[4] = 28;
        arr[5] = 2;
        arr[6] = 7;
        arr[7] = 10;
        arr[8] = 17;
        arr[9] = 24;
        System.out.println(Arrays.toString(arr));
        MergeSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}