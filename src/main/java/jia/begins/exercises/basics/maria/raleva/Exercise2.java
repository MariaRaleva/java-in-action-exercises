package jia.begins.exercises.basics.maria.raleva;

/**
 * Created by IntelliJ IDEA.
 * User: maria
 * Date: Nov 15, 2010
 * Time: 10:49:42 PM
 * Counts the sum of first n(maxNatural) natural numbers
 */
public class Exercise2 {
    public static void main(String[] args){
        sumOfNaturalNumbers(1000);
    }

    /***
     *
     * @param maxNatural -max natural number
     * The sum of first maxNatural natural numbers
     */
    private static void sumOfNaturalNumbers(int maxNatural) {
        //The sum of first 1000 natural numbers example
        //1+1000=1001
        //2+999=1001
        //....
        //500+501=1001
        //500 times we have sum of 1001, i.e 500*10001
        int minNatural=1;
        int sum;
        if(maxNatural % 2 == 0){
            sum = (maxNatural + minNatural) * (maxNatural /2);
        }else if(maxNatural == 1 || maxNatural == 0){
            sum = maxNatural;
        }else{
            sum = (maxNatural +minNatural)*(maxNatural /2) + maxNatural /2 +1;
        }
        System.out.printf("Sum of first %d natural numbers is %d\n:", maxNatural, sum);
    }
}
