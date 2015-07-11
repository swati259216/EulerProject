package models;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by swati on 11/7/15.
 */
public class Hand {
    private Card[] hand ;

    public Hand(Card[] hand) {
        this.hand = hand;
    }

    public Card[] getHand() {
        return hand;
    }

    public void setHand(Card[] hand) {
        this.hand = hand;
    }

    public  int rank(){
        boolean isSameSuit = sameSuit(hand);
        boolean hvConsecValues = true;
        String valueFirst = hand[0].getValue();
        for(int i = 1;i <hand.length;i++){
            valueFirst = nextCard(valueFirst);
            System.out.println();
            if(!valueFirst.equals(hand[i].getValue()))hvConsecValues = false;
        }
        if(isSameSuit && hvConsecValues) {
            if(hand[0].getValue().equals("10"))return 1;
            else return 2;
        }
        Map<String,Integer> countMap = formCountMap(hand);
        if(countMap.containsValue(4))return 3;
        if(countMap.containsValue(3)&&countMap.containsValue(2))return 4;
        if(isSameSuit)return 5;
        if(hvConsecValues) return 6;
        if(countMap.containsValue(3))return 7;
        if(countMap.size()==3&&countMap.containsValue(2)){
           return 8;
        }
        if(countMap.containsValue(2))return 9;
        return 10;
    }

    private String nextCard(String value){
        if(Card.isNumeric(value)){
            if("9".equals(value))return "T";
            return String.valueOf(Integer.parseInt(value)+1);
        }
        else if("T".equals(value)){
            return "J";
        }
        else if("J".equals(value)){
            return "Q";
        }
        else if ("Q".equals(value)){
            return "K";
        }
        else {
            return "A";
        }
    }

    private boolean sameSuit(Card[] hand){
        boolean isSameSuit = true;
        String firstSuit = hand[0].getSuit();
        for(int i = 1;i <hand.length;i++){
            if(!hand[i].getSuit().equals(firstSuit)){
                isSameSuit = false;
            }
        }
        return isSameSuit;
    }

    public boolean determineResult(Hand hand2){

        int rank1 = rank();
        int rank2 = hand2.rank();
      //  System.out.println(rank1 + "," + rank2);
        if(rank1<rank2)return true;
        else if (rank1>rank2) return false;
        else {
           if(rank1==2 || rank1==6 )return tieRank2OR6(hand2);
           else if(rank1==5 || rank1==10){
              return tieRank5OR10(hand2);
           }
           Map<String,Integer> countMap = formCountMap(hand);
           Map<String,Integer> countMap1 = formCountMap(hand2.getHand());
           if(rank1==3)
           {
               if(getHighCard(4,countMap)>getHighCard(4,countMap1))return true;
               else return false;
           }
            if(rank1==4 || rank1 == 7)
            {
                if(getHighCard(3,countMap)>getHighCard(3,countMap1))return true;
                else return false;
            }
            if(rank1==9)
            {
                if(getHighCard(2,countMap)>getHighCard(2,countMap1))return true;
                else if(getHighCard(2,countMap)<getHighCard(2,countMap1)) return false;
                else return tieRank5OR10(hand2);
            }
            if(rank1==8){
                return tieRank8(countMap,countMap1);
            }
        }
        return false;
    }

    public boolean tieRank2OR6(Hand hand2){
        if(Card.numericValue(hand[4].getValue())>Card.numericValue(hand2.getHand()[4].getValue()))return true;
        else return false;
    }

    public boolean tieRank5OR10(Hand hand2){
        for(int i = hand.length-1; i >0 ; i-- ){
            if(Card.numericValue(hand[i].getValue())>Card.numericValue(hand2.getHand()[i].getValue()))return true;
            if(Card.numericValue(hand[i].getValue())<Card.numericValue(hand2.getHand()[i].getValue()))return false;
        }
        return false;
    }
    private Map<String,Integer> formCountMap(Card[] hand){
        Map<String,Integer> countMap = new HashMap<String,Integer>();
        for(int i = 0;i <hand.length;i++){
            String value = hand[i].getValue();
            if(countMap.containsKey(value)){
                countMap.put(value,countMap.get(value)+1);
            }
            else{
                countMap.put(value,1);
            }
        }
        return countMap;
    }

    private boolean tieRank8(Map<String,Integer> countMap1,Map<String,Integer> countMap2){
        int a = 0 ; int b = 0 ;int c = 0 ;
        for(Map.Entry<String,Integer> entry : countMap1.entrySet()){
            if(entry.getValue()==2 && a == 0){
                a = Card.numericValue(entry.getKey());
            }
            else if(entry.getValue()==2) b = Card.numericValue(entry.getKey());
            else if(entry.getValue()==1) c = Card.numericValue(entry.getKey());
        }
        int a2 = 0 ; int b2 = 0 ;int c2 = 0 ;
        for(Map.Entry<String,Integer> entry : countMap2.entrySet()){
            if(entry.getValue()==2 && a2 == 0){
                a2 = Card.numericValue(entry.getKey());
            }
            else if(entry.getValue()==2) b2 = Card.numericValue(entry.getKey());
            else if(entry.getValue()==1) c2 = Card.numericValue(entry.getKey());
        }
        if(Math.max(a,b)>Math.max(a2,b2))return true;
        else if(Math.max(a,b)<Math.max(a2,b2))return false;
        else if(Math.min(a,b)>Math.min(a2,b2))return true;
        else if(Math.min(a,b)<Math.min(a2,b2))return true;
        else return c>c2;
    }
    private int getHighCard(int n , Map<String,Integer> countMap){
        for(Map.Entry<String,Integer> entry : countMap.entrySet()){
            if(entry.getValue()==n){
                return Card.numericValue(entry.getKey());
            }
        }
        return 0;
    }

    @Override
    public String toString(){
        String s = null;
        for(int i = 0 ;i <hand.length;i++){
            s = s + hand[i];
        }
        return s;
    }

}
