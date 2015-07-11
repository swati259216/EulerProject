package models;

/**
 * Created by swati on 10/7/15.
 */
public class Card {

        private String suit;
        private String value;

        public String getSuit() {
            return suit;
        }

        public void setSuit(String suit) {
            this.suit = suit;
        }

        public String getValue() {
            return value;
        }

    public Card(String value, String suit) {
        this.suit = suit;
        this.value = value;
    }

    public Card() {
    }

    public void setValue(String value) {

            this.value = value;
        }

    public static int numericValue(String value){
        if(isNumeric(value)){
            return Integer.parseInt(value);
        }
        else if("T".equals(value)){
            return 10;
        }
        else if("J".equals(value)){
            return 11;
        }
        else if ("Q".equals(value)){
            return 12;
        }
        else if("K".equals(value)){
            return 13;
        }
        else return 14;
    }

    public static boolean isNumeric(String str)
    {
        try
        {
            Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }


    @Override
    public String toString(){
        return suit + ":" + value;
    }





}
