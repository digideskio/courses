import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
 
public class SlowMedian implements MovingMedian{
 
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
        Arrays.sort(numbers);
        return numbers[mid];
    }
    
    // The printMovingMedians() method was one that I added for convenience,
    // and it prints all of the values in the medians array, line by line.
    public void printMovingMedians(double[] medians) {
        for(int i = 0; i < medians.length; i++)
            System.out.println(medians[i]);
    }
    
    public static void main(String[] args) {
        SlowMedian test = new SlowMedian();
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

// The worst-case running time of the program is O(N klogk).