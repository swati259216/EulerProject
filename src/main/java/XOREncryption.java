import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by swati on 9/7/15.
 */
public class XOREncryption {


    private void generateArray(){
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("resources/p059_cipher.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String text = scanner.nextLine();
        String[] array = text.split(",");
        int[] cArray = new int[array.length];
        for(int i = 0;i<array.length;i++){
            cArray[i] = Integer.parseInt(array[i]);
        }
        int p1 = getPasswordChar(cArray,0);
        int p2 = getPasswordChar(cArray,1);
        int p3 = getPasswordChar(cArray,2);
        int sum = 0;
        for(int i =0;i<cArray.length;i=i+3){
            cArray[i]=cArray[i]^p1;
            if(i!=1200) {
                cArray[i+1]= cArray[i + 1] ^ p2;
            }
            if(i!=1200 && i!=1999)
                cArray[i+2]= cArray[i + 2] ^ p3;
        }

        for (int i = 0 ;i <cArray.length;i++){
           sum+=cArray[i];
           System.out.print(Character.toString((char)cArray[i]));

        }
        System.out.println("\nSum : " +  sum);


    }

    private int getPasswordChar(int[] cArray , int startIndex){
        int p1 = 97;
        for(int i =97 ;i<=122;i++){
            boolean isCorrect = true;
                for (int j = startIndex ; j < cArray.length;j=j+3) {
                    int aThis =i^cArray[j];
                    if (!(aThis >= 97 && aThis <= 122)&&!(aThis >= 65 && aThis <= 190)&&!(aThis >= 48 && aThis <= 57)&&!(aThis==32||aThis==33||aThis==34||aThis==38||aThis==39||aThis==44||aThis==46||aThis==59||aThis==63||aThis==40||aThis==41)) {
                        isCorrect = false;
                        break;
                    }
                }
            if(isCorrect){
                    p1 = i;
                    break;
                }

            }

        return p1;
    }
    public static void main(String args[]){
        XOREncryption xorEncryption = new XOREncryption();
        xorEncryption.generateArray();
    }

}
