import java.io.*;
import java.util.Scanner;

/**
 * Created by swati on 9/7/15.
 */
public class MaximumPathSum1 {

    private long maxSum(int totalRows , String file){
        int[][] array = generateArray(totalRows,file);
        return calculateMaxSum(array);
    }

    /*
    Calculate the maximum path sum for a 2-D array as equilateral triangle
     */
    private long calculateMaxSum(int[][] array){
        for (int m = array.length-1 ; m>0;m--){
            for(int n = 0; n<m;n++){
                int bottom = Math.max(array[m][n],array[m][n+1]);
                array[m - 1][n] += bottom;
            }
        }
        return array[0][0];
    }

    /*
    Generate a 2D array from a txt file
     */
    private int[][] generateArray(int totalRows , String file){
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int[][] array = new int[totalRows][totalRows];
        int i =0;
        int j = 0;
        while (scanner.hasNextInt()) {
            array[i][j] = scanner.nextInt();
            if (i == j) {
                i++;
                j = 0;
            } else {
                j++;
            }
        }
        return array;
    }
    public static void main(String args[]){
        int totalRows = 100;
        String file = "resources/p067_triangle.txt";
        MaximumPathSum1 maximumPathSum1 = new MaximumPathSum1();
        long maxSum = maximumPathSum1.maxSum(totalRows,file);
        System.out.println("Max Path Sum Problem 67: " + maxSum );
        file = "resources/p018_triangle.txt";
        totalRows = 15;
        System.out.println("Max Path Sum Problem 18: " + maximumPathSum1.maxSum(totalRows,file) );
    }

}
