package jia.begins.exercises.basics.maria.raleva;

/**
 * @author <a href="mailto:maria.raleva@gmail.com">Maria Raleva</a>
 * The program is returning the sum of all numbers that can be devided by two given numbers
 */
public class Exercise3 {
     public static void main(String[] args){
         //Sum of all numbers between 1 and 1000 that are divided by 3 OR 5 with no rest
         sumOfDividedNumbersOR(3, 5, 1000);
         //Sum of all numbers between 1 and 1000 that are divided by 3 AND 5 with no rest
         sumOfDividedNumbersAND(3, 5, 1000);

     }
      /**
     * Prints the sum of all numbers between 1 and maxNumber that can be divided by firstNumber AND secondNumber
     * @param firstNumber   first divisor
     * @param secondNumber     second divisor
     * @param maxNumber        max number to be divided
     */
    private static void sumOfDividedNumbersAND(int firstNumber, int secondNumber,int  maxNumber) {
        //The smallest number that can be divided by firstNumber AND secondNumber
        int nextNumber = firstNumber*secondNumber;
        int sumDivNumbers=nextNumber;
        //Count how many time to iterate
        int iterEnd = maxNumber/nextNumber;
        for(int i=2; i<=iterEnd; i++){
            sumDivNumbers+=nextNumber*i;
        }
        System.out.printf("Sum of all numbers between 1 and %d that can be divided by %d and %d is %d:%n",maxNumber, firstNumber, secondNumber, sumDivNumbers);
    }

    /**
     * Prints the sum of all numbers between 1 and maxNumber that can be divided by firstNumber OR secondNumber
     * @param firstNumber   first divisor
     * @param secondNumber     second divisor
     * @param maxNumber        max number to be divided
     */
    private static void sumOfDividedNumbersOR(int firstNumber,int secondNumber,int maxNumber) {
        //Sum of all numbers that are divided by firstNumber or secondNumber with no rest
        int sumDivNumbers = 0;
        for (int i = 3; i < maxNumber; ++i) {
            if (i % firstNumber == 0 || i % secondNumber == 0) {
                sumDivNumbers += i;
            }
        }
        System.out.printf("Sum of all numbers between 1 and %d that can be divided by %d or %d is %d:%n",maxNumber, firstNumber, secondNumber, sumDivNumbers);
    }
}