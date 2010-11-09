package jia.begins.examples.basics.maria.raleva;

import java.io.PrintWriter;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Scanner;
import java.io.*  ;

/**
 * @author <a href="mailto:maria.raleva@gmail.com">Maria Raleva</a>
 * The program is inserting data from the Console into a File JACMEEmployeeFile
 * First Name, Last Name, Age, Position and Salary about the employee working in JACME company
 * The information about the new employee is append in the end of the file
 * MORE:
 * The total number of records is append in the begining of the file
 *
 */
public class Exercise1 {
    public static void main(String[] args) throws IOException, ParseException {
        String fileName = "JACMEEmployeeFile.txt";

        //Check if file already exist
        boolean exists = (new File(fileName)).exists();

        //Create new file JACME or use the existing one
        File JACME = new  File(fileName);

        //append = true - append data
        //append = false - truncate the file to zero length
        Boolean append = true;
        PrintWriter pw = new PrintWriter(new FileWriter(JACME, append));

        //If the file exist -get number of employees
        //else write first line into the file
        int total = 0;
        if(exists){
            //get the number of all employees in the file
            total = returnTotalEmployees(fileName);//.intValue();
        } else{
            pw.println("0    Employees Total \n");
            pw.println("No    First Name      Last Name            Age   Position                       Salary ");
            pw.println("---------------------------------------------------------------------------------------");
        }

        total = writeEmployees(pw, total);
        //Write total number of inserted Employees in the beginning of the file
        setNumberOfTotalEmployees(Integer.toString(total),fileName);
    }

    /**
     *  Write Employee Info into the file
     * @param pw   PrintWriter
     * @param total  : int total of employees
     * @return  total : int new total
     */
    private static int writeEmployees(PrintWriter pw, int total) {
        Scanner in = new Scanner(System.in);
        System.out.print("How many employees you want to insert? ");
        Integer numberOfEmployees = in.nextInt();

        for (int i=0; i<numberOfEmployees; ++i)  {
            System.out.printf("Inserting the information of the %d employee :%n",i+1);

            System.out.print("Insert the first name of the employee:");
            String firstName =  in.next();

            System.out.print("Insert the last name of the employee:");
            String lastName =  in.next();

            System.out.print("Insert age of the employee:");
            String age =  in.next();

            System.out.print("Insert position of the employee:");
            //Can be entered hole sentence, not only one word
            //Example1: Developer
            //Example2: Engineer Computer Software
            in.nextLine();
            String position =  in.nextLine();

            System.out.print("Insert salary of the employee:");
            Double salary =  in.nextDouble();
            ///Write the information into the file
            pw.printf("%1$-5d %2$-15s %3$-20s %4$-5s %5$-30s %6$-10.2f%n",++total,firstName,lastName,age,position,salary);

            pw.flush();
        }
        pw.close();
        in.close();
        return total;
    }

    /**
     * @param fileName  String
     * @return int total employees added in the file
     * Extract the number(total) from the first line from the file
     * @throws IOException    ..
     * @throws ParseException ..
     */

    private static int returnTotalEmployees(String fileName) throws IOException, ParseException {
        BufferedReader input = new BufferedReader(new FileReader(fileName));
        String firstLine = input.readLine();//read the first line form the file
        input.close();
        NumberFormat nf = NumberFormat.getInstance();
        Number totals = nf.parse(firstLine);  //parse String to Number
        return totals.intValue(); //parse Number to int
    }

    /**
     * @param fileName  String
     * @param total  String
     * Change the old total of employees with the new total
     * by overwriting the beginning of the file
     * @throws IOException      ..
     */
    private static void setNumberOfTotalEmployees(String total, String fileName) throws IOException {
        RandomAccessFile file = new RandomAccessFile(fileName, "rw");
        file.seek(0);
        file.write(total.getBytes());
        file.close();
    }
}
