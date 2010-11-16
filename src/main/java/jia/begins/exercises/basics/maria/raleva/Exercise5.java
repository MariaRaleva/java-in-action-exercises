package jia.begins.exercises.basics.maria.raleva;

/**
 * Created by IntelliJ IDEA.
 * User: maria
 * Date: Nov 16, 2010
 * Time: 10:21:11 AM
 * Prints the sum of all letters that are forming the numbers from 1 to 1000
 */
public class Exercise5 {
    public static void main(String[] args) {
        int number;
        //one thousand - 11 characters
        int sum = 11;//1000 need to be included
        int units;
        //sum all letters for numbers from 1 to 1000
        for (int i = 1; i < 1000; i++) {
            number = i;
            // Numbers with units and tens bigger then 20
            units = number % 100;
            if (units>=20){
                int iter = 0;
                while (number > 0) {
                    ++iter;
                    units = number % 10;
                    number = number / 10;

                    if (iter == 1) {   //get the number of letters from the units
                        sum = addNumberOfLetters(units, sum);
                    } else if (iter == 2) { //get the number of letters from the tens
                        sum = addNumberOfLettersForTens(units, sum);
                    } else { //get the number of letters from the hundreds 
                        //hundred - 7 characters
                        sum += 7;
                       sum =  addNumberOfLetters(units, sum);
                    }//end if
                } //end while
            } else {
                //special case for
                //the numbers between 1 and 20
                sum = addNumberOfLetters(units, sum);
                units = number/100;
                if(units != 0 ){
                    ///if there is hundreds
                    sum += 7;  //hundred - 7 characters
                    sum = addNumberOfLetters(units,sum);
                }
            }
        }//end for

        System.out.println(sum);
    }

    private static int addNumberOfLettersForTens(int number, int sum) {
        //tens
        switch (number) {
            //numbers with 6 letters
            case 2: //Twenty
            case 3: //Thirty
            case 8: //Eighty
            case 9: //Ninety
                sum += 6;
                break;
            //numbers with 5 letters
            case 4: //Forty
            case 5: //Fifty
            case 6: //Sixty
                sum += 5;
                break;
            //numbers with 7 letters
            case 7:
                sum += 7; //Seventy
                break;

        }
        return sum;
    }

    private static int addNumberOfLetters(int number, int sum) {
        switch (number) {
            //numbers with 3 letters
            case 1://one
            case 2://two
            case 6://six
            case 10://ten
                sum += 3;
                break;
            //numbers with 5 letters
            case 3:
            case 7:
            case 8:
                sum += 5;
                break;
            //numbers with 4 letters
            case 4:
            case 5:
            case 9:
                sum += 4;
                break;
            //numbers with 6 letters
            case 11:
            case 12:
                sum += 6;
                break;
            //numbers with 8 letters
            case 13:
            case 14:
            case 18:
            case 19:
                sum += 8;
                break;
            //numbers with 7 letters
            case 15:
            case 16:
                sum += 7;
                break;
            //numbers with 9 letters
            case 17:
                sum += 9;
                break;
        }
        return sum;
    }

}
