package jia.begins.exercises.basics.maria.raleva;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by IntelliJ IDEA.
 * User: maria
 * Date: 12/12/10
 * Time: 11:57 AM
 * Calculator
 */
public class Exercise8 extends JFrame {
    private char operation;
    private String firstValue = "0";
    private String secondValue = "0";
    //TRUE when is pressed button equal
    //FALSE when entered first number in the txtResult field
    //needed for start over typing numbers in the txtResult
    private boolean isPressedEqual = false;

    private final String DBZ = "Dividing by zero";
    private final String UF = "Unknown Format";

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Exercise8();
            }
        });
    }

    Exercise8() {


        //Create JFrame frame
        JFrame frame = new JFrame("Calculator");
        frame.setSize(300, 330);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        //Create  main panel,set Layout and add it to the frame
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        frame.add(panel);

        //Create TextField for the Result
        final JTextField txtResult = new JTextField("0");
        txtResult.setEnabled(false);
        //Set Font and alignment of the TextField
        txtResult.setFont(new Font("Verdana", Font.CENTER_BASELINE, 30));
        txtResult.setHorizontalAlignment(JTextField.CENTER);
        //Add the field to the main panel
        panel.add(txtResult, BorderLayout.NORTH);

        //Create new panel for the number buttons
        JPanel panelNumbers = new JPanel();
        //Add this panel to main panel
        panel.add(panelNumbers, BorderLayout.CENTER);
        //Create Grid Layout
        GridLayout gridDown;
        gridDown = new GridLayout(4, 3, 2, 2);
        //Set this layout to panelNumber
        panelNumbers.setLayout(gridDown);

        //Create array with all operation
        //"+", "-", "/", "*"
        //Set them not focusable
        final JButton arrOButtons[] = new JButton[4];
        arrOButtons[0] = new JButton("+");
        arrOButtons[0].setFocusable(false);
        arrOButtons[1] = new JButton("-");
        arrOButtons[1].setFocusable(false);
        arrOButtons[2] = new JButton("*");
        arrOButtons[2].setFocusable(false);
        arrOButtons[3] = new JButton("/");
        arrOButtons[3].setFocusable(false);

        //Create arr with all number buttons 0 to 9
        final JButton arrNButtons[] = new JButton[10];
        for (int i = 0; i < arrNButtons.length; ++i) {
            arrNButtons[i] = new JButton("" + i);
            arrNButtons[i].setFocusable(false);
            arrNButtons[i].setFont(new Font("Verdana", Font.CENTER_BASELINE, 20));
        }

        //Create "." - floating point
        final JButton dot = new JButton(".");
        dot.setFocusable(false);

        //Create "=" button
        JButton equal = new JButton("=");
        equal.setFocusable(false);

        //Add all number, operation, "." and "=" buttons to the panelNumbers
        panelNumbers.add(arrNButtons[7]);
        panelNumbers.add(arrNButtons[8]);
        panelNumbers.add(arrNButtons[9]);
        panelNumbers.add(arrOButtons[3]);
        panelNumbers.add(arrNButtons[4]);
        panelNumbers.add(arrNButtons[5]);
        panelNumbers.add(arrNButtons[6]);
        panelNumbers.add(arrOButtons[2]);
        panelNumbers.add(arrNButtons[1]);
        panelNumbers.add(arrNButtons[2]);
        panelNumbers.add(arrNButtons[3]);
        panelNumbers.add(arrOButtons[1]);
        panelNumbers.add(arrNButtons[0]);
        panelNumbers.add(dot);
        panelNumbers.add(equal);
        panelNumbers.add(arrOButtons[0]);


        //Action Listener for the numbers buttons
        //Insert number in the text field
        //First zero is replaced with ""
        for (int i = 0; i < arrNButtons.length; ++i) {
            final int index = i;
            arrNButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    //(new BigDecimal(txtResult.getText()).equals("0"))
                    if ((txtResult.getText().equals("0") || secondValue.equals("0") || isPressedEqual)) {
                        txtResult.setText("");
                        txtResult.setText(arrNButtons[index].getText());//arrNButtons[index].setText(txtResult.getText());
                        secondValue = "";
                        isPressedEqual = false;

                    } else {

                        txtResult.setText(txtResult.getText() + arrNButtons[index].getText());
                    }
                    //Enable all Operation Buttons to been pushed
                    OButtonsEnabled(true, arrOButtons);
                }
            });
        }

        //ActionListener for adding floating point to the text field
        dot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                txtResult.setText(txtResult.getText() + dot.getText());
            }
        });

        //ActionListener for all operation buttons
        //Set "operation"  depending on witch button is pushed (-,+,/,*)
        //Also calculate the result if first and second values are set
        for (int i = 0; i < arrOButtons.length; ++i) {
            final int index = i;
            arrOButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {

                    //Disable all Operation Buttons to been pushed
                    OButtonsEnabled(false, arrOButtons);
                    if (firstValue.equals("0")) {
                        System.out.println(firstValue);
                        //First time using of operation button
                        //or after pressed "=" + "number/s" then "operation"
                        //or after "=" then "operation"
                        operation = arrOButtons[index].getText().charAt(0);
                        firstValue = txtResult.getText();
                        txtResult.setText("0");
                    } else { //When performed "operation" + "number/s" + "operation"...
                        secondValue = txtResult.getText();
                        txtResult.setText(calcResult());
                        firstValue = txtResult.getText();
                        if (!(firstValue.equals(DBZ) || firstValue.equals(UF))) {
                            secondValue = "0";
                        }
                        operation = arrOButtons[index].getText().charAt(0);

                    }
                }
            });
        }

        //Action Listener fot "equal" button
        //When button "=" pushed calculate the result
        equal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (firstValue.equals("0")) { //many times pushed only button "="
                    firstValue = txtResult.getText();
                    txtResult.setText(calcResult());
                    firstValue = "0";
                    isPressedEqual = true;
                } else {
                    secondValue = txtResult.getText();
                    txtResult.setText(calcResult());
                    firstValue = "0";
                    isPressedEqual = true;
                }
                //Enable all Operation Buttons to been pushed
                OButtonsEnabled(true, arrOButtons);
            }
        });
    }

    private void OButtonsEnabled(boolean enabled, JButton[] arrOButtons) {
        for (int j = 0; j < arrOButtons.length; ++j) {
            arrOButtons[j].setEnabled(enabled);
        }
    }

    //Calculate the result depending on the value of "operation"
    private String calcResult() {
        try {
            BigDecimal first = new BigDecimal(firstValue);

            BigDecimal second = new BigDecimal(secondValue);
            BigDecimal result = new BigDecimal(0);
            switch (operation) {

                case '+':
                    result = first.add(second);
                    break;
                case '-':
                    result = first.subtract(second);
                    break;
                case '*':
                    result = first.multiply(second);
                    break;
                case '/':
                    result = first.divide(second, 9, RoundingMode.HALF_UP);
                    break;
            }

            if (("" + result).equals("0E-9")) {
                firstValue = "0";
                return "0";
            }
            String noTrailingZeros = trimTrailingZeros("" + result);
            firstValue = noTrailingZeros;
            return noTrailingZeros;
        } catch (NumberFormatException nFE) {
            firstValue = "0";
            secondValue = "0";
            return UF;//"Unknown format"
        } catch (ArithmeticException aE) {
            firstValue = "0";
            secondValue = "0";
            return DBZ;//"Dividing by zero!";
        }
    } //end calcResult

    // trim all trailing zeros
    private static String trimTrailingZeros(String number) {
        if (!number.contains(".")) {
            return number;
        }

        number = number.replaceAll("0*$", "");
        if (number.charAt(number.length() - 1) == '.') {
            number = number.substring(0, number.length() - 1);
        }
        return number;
    }

}
