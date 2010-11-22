package jia.begins.exercises.basics.maria.raleva;

/**
 * Created by IntelliJ IDEA.
 * User: maria
 * Date: Nov 18, 2010
 * Time: 11:40:55 AM
 * Class Rational implements add, delete, multiply and subtract methods for a fractions
 */

class Rational {


    private int dividend;
    private int divisor;
    
    //Constructor for integer numbers
    public Rational(int dividend) {
         this(dividend,1);
    }
    public Rational(int dividend, int divisor){

        if (divisor==0){
             throw new ArithmeticException();
        }

        this.dividend = dividend;
        this.divisor = divisor;
        if(divisor < 1){ // -2/-5 = 2/5; 2/-5 = -2/5
            this.dividend =dividend*(-1);
            this.divisor = divisor*(-1);
        }

        if(divisor != 1){
            //reduce to normal form
            //example 3/6=1/2
            normalisation();
        }
        
    }

    public int getDividend() {
        return dividend;
    }

    public int getDivisor() {
        return divisor;
    }


    public Rational add(Rational other){
        return new Rational(dividend*other.divisor + divisor*other.dividend, divisor*other.divisor);
    }
    public Rational sub(Rational other){
        return new Rational(dividend*other.divisor - divisor*other.dividend, divisor*other.divisor);
    }
    public Rational mul(Rational other){
        return new Rational(dividend*other.dividend, divisor*other.divisor);
    }
    public Rational div(Rational other){
        return new Rational(dividend*other.divisor,divisor*other.dividend);
    }

    /**
     * Override the method toString.
     * @return  fraction as a string
     */
    @Override public String toString() {
        String fraction;
        if(divisor!=1){
            fraction = dividend +"/"+ divisor;
        }else { //integer number
            fraction= ""+dividend;
        }
        if(dividend<1){ //negative fraction
            fraction = "("+fraction+")";
        }
        return fraction;
    }

    /**
     * Counts greatest common factor of 2 numbers(dividend and divisor)
     * @return   int greatest common factor
     */
    private int greatestCommonFactor(){
        int a=Math.abs(this.dividend);
        int b=Math.abs(this.divisor);
        //Used Euclidean algorithm
        while(b!=0&&a!=0){
            if (b>a){
                b=b-a;
            }else {
                a=a-b;
            }
        }
        if(a==0){
            return b;
        }
        return a;
    }

    /**
     * If the dividend and the divisor are not coprime
     * they are divided by there greatest common factor
     * Example: For fraction 6/9 the gcf is 3 =>
     * 6 divided by 3 is 2 and 9 divided by 3 is 3 => 2/3
     */
    private void normalisation(){
        int gcf = greatestCommonFactor();  //get the greatest common factor
        if(gcf!=1){ //if greatestCommonFactor == 1 the fraction is in normal form
            dividend = dividend/gcf;
            divisor = divisor/gcf;
        }       
    }

}

public class Exercise6 {
    public static void main(String[] args) {

        Rational f1 = new Rational(5,-15);
        Rational f2 = new Rational(1);
        System.out.println("Result: "+f1+"+"+f2 +"="+f1.add(f2));
    }
}
