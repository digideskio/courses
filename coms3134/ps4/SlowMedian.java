import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
 
public class SlowMedian implements MovingMedian{
 
    private double[] prices;
 
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
 
    @Override
    public double[] getMovingMedians(int window) {
        double[] medians = new double[prices.length - (window - 1)];
        for(int i = window - 1; i < prices.length; i++) {
            double[] numbers = Arrays.copyOfRange(prices, i - (window - 1), i + 1);
            medians[i - (window - 1)] = getMedian(numbers, i - (window - 1), i);
        }
        return medians;
    }
 
    @Override
    public double getMedian(double[] numbers, int start, int end) {
        int mid = (end - start) / 2;
        Arrays.sort(numbers);
        return numbers[mid];
    }
    
    public void printMovingMedians(double[] medians) {
        for(int i = 0; i < medians.length; i++)
            System.out.println(medians[i]);
    }
    
    public static void main(String[] args) {
        SlowMedian test = new SlowMedian();
        test.prices = new double[8000];
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