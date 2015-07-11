import models.Card;
import models.Hand;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by swati on 11/7/15.
 */
public class Poker {

    public static void main(String[] args){
        List<Hand> player1 = new ArrayList<Hand>();
        List<Hand> player2 = new ArrayList<Hand>();
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("resources/p054_poker.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scanner.hasNext()){
            String lineR = scanner.nextLine();
            String line = lineR.replaceAll("\\s+","");
            Card[] hand1 = new Card[5];
            Card[] hand2 = new Card[5];
            for(int i = 0;i<10;i=i+2){
                hand1[i/2] = new Card(line.substring(i,i+1),line.substring(i+1,i+2));
            }
            for(int i =10;i<line.length();i=i+2){
                hand2[i/2-5] = new Card(line.substring(i,i+1),line.substring(i+1,i+2));
            }
            sort(hand1);sort(hand2);
            player1.add(new Hand(hand1));
            player2.add(new Hand(hand2));
        }
        int numPlayerOneWins = 0 ;
        for(int i = 0 ; i <player1.size();i++){
            Hand hand1 = player1.get(i);
            Card[] h = hand1.getHand();
            Hand hand2 = player2.get(i);
            if(hand1.determineResult(hand2))numPlayerOneWins+=1;
        }
        System.out.println(numPlayerOneWins);
    }
    private static void sort(Card[] hand){
        for(int i = 0 ;i <hand.length;i++){
            boolean swapped = false;
            for(int j =0;j<hand.length-i-1;j++){
                if(Card.numericValue(hand[j + 1].getValue())<Card.numericValue(hand[j].getValue())){
                    Card tmp = hand[j];            System.out.println();

                    hand[j] = hand[j+1];
                    hand[j+1]=tmp;
                    swapped = true;
                }
            }
            if(!swapped)break;
        }
    }

}
