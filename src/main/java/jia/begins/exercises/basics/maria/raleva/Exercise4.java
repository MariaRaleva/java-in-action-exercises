package jia.begins.exercises.basics.maria.raleva;

import java.math.BigInteger;
 /**
 * @author <a href="mailto:maria.raleva@gmail.com">Maria Raleva</a>
 * The program is returning the sum of all numbers that are forming 100 factorial
 */
public class Exercise4 {

     public static void main(String[] args){
         
        sumNumbersOfFactorial100();
     }

    /**
     * Prints the sum of all numbers that are forming the number of 100 factorial
     */
    public static void sumNumbersOfFactorial100() {
        // Count Factorial of 100
        BigInteger factorial = BigInteger.valueOf(1);
        for (int i = 2; i < 101; ++i) {
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }
       // System.out.println("Factorial of 100 (100!):" + factorial);

        //Sum all numbers of 100!
        BigInteger ten = BigInteger.valueOf(10);
        BigInteger firstPart = factorial.divide(ten.pow(23));//only for this case, to skip counting the zeros
        BigInteger sumOfNumbers = BigInteger.valueOf(0);
        while (firstPart.equals(BigInteger.valueOf(0))) {
            sumOfNumbers = sumOfNumbers.add(firstPart.remainder(BigInteger.valueOf(10)));
            firstPart = firstPart.divide(BigInteger.valueOf(10));
        }
        System.out.println("The sum of all numbers of 100 factorial is  " + sumOfNumbers);
    }
}