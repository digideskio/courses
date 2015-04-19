import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
 
public class FastMedian implements MovingMedian{
 
    private double[] prices;
 
    // The method readFile() first reads all of the lines into an ArrayList
    // as doubles (to get the size), then transfers the doubles to an
    // array (the static variable prices).
    @Override
    public void readFile(String filename) throws IOException {
        String line = null;
        ArrayList<Double> data = new ArrayList<Double>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            while ((line = br.readLine()) != null) {
                data.add(Double.parseDouble(line));
            }
            br.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        prices = new double[data.size()];
        int i = 0;
        for (Double d : data) {
            prices[i++] = d;
        }
    }
 
    // The method getMovingMedians() returns an array of doubles with all of
    // the moving medians for each window. It iterates over prices, starting
    // at the end of the first window, and for each window, copies a subarray
    // of doubles (number) that stores its values. That copy is passed to
    // the getMedian() method, and the returned moving median is stored in the
    // medians array.
    @Override
    public double[] getMovingMedians(int window) {
        double[] medians = new double[prices.length - (window - 1)];
        for(int i = window - 1; i < prices.length; i++) {
            double[] numbers = Arrays.copyOfRange(prices, i - (window - 1), i + 1);
            medians[i - (window - 1)] = getMedian(numbers, i - (window - 1), i);
        }
        return medians;
    }
 
    // The getMedian() method simply returns the median of each window it
    // receives. With the start and end indexes, it gets the midpoint,
    // then sorts the subarray for the window and returns the number
    // at the midpoint index.
    @Override
    public double getMedian(double[] numbers, int start, int end) {
        int mid = (end - start) / 2;
        quickSelect(numbers, mid);
        return numbers[mid];
    }
 
    // The method quickSelect() implements the Quick Select algorithm to place
    // the desired value (in this case, the median) in the right index.
    private Object quickSelect(double[] arr, int n) {
        int left = 0;
        int right = arr.length - 1;
        while (right >= left) {
            int pivotIndex = partition(arr, left, right);
            if (pivotIndex == n)
                return arr[pivotIndex];
            else if (pivotIndex < n)
                left = pivotIndex + 1;
            else
                right = pivotIndex - 1;
        }
        return null;
    }
 
    // The method partition() gets the pivot index and moves through the array,
    // swapping elements that are less than the pivot value to move them to the
    // left and moving elements that are greater than the pivot value toward
    // the right.
    private int partition(double[] arr, int left, int right) {
        int pivot = medianOfThrees(arr, left, right);
        double pivotVal = arr[pivot];
        swap(arr, pivot, right);
        int ind = left;
        for (int i = left; i < right; i++) {
            if (arr[i] < pivotVal) {
                swap(arr, i, ind);
                ind++;
            }
        }
        swap(arr, right, ind);
        return ind;
    }
 
    // The method medianOfThrees() returns the median between the left, right,
    // and middle values of the array to choose the pivot.
    private int medianOfThrees(double[] arr, int left, int right) {
        int mid = (left + right) / 2;
        if(arr[right] < arr[left])
            swap(arr, left, right);
        if(arr[mid] < arr[left])
            swap(arr, mid, left);
        if(arr[right] < arr[mid])
            swap(arr, right, mid);
        return mid;
    }
 
    // The method swap() simply swaps elements at two different indexes within
    // an array.
    private void swap(double[] arr, int a, int b) {
        if (a != b) {
            double tmp = arr[a];
            arr[a] = arr[b];
            arr[b] = tmp;
        }
    }
 
    // The printMovingMedians() method was one that I added for convenience,
    // and it prints all of the values in the medians array, line by line.
    public void printMovingMedians(double[] medians) {
        for(int i = 0; i < medians.length; i++)
            System.out.println(medians[i]);
    }
 
    public static void main(String[] args) {
        FastMedian test = new FastMedian();
        try {
            test.readFile(args[0]);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        double[] medians = test.getMovingMedians(5);
        test.printMovingMedians(medians);
    }
}

// The best-case running time of the program is O(N k), and the worst-case
// running time of the program is O(N k^2). For small window sizes, it's
// better not to use Quick Select because the implementation of Quick
// Select takes longer in practice than (for instance) the Arrays.sort()
// function, though the advantages we see in the Big-O runtime would
// become more evident with larger window sizes.