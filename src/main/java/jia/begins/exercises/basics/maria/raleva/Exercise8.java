package jia.begins.exercises.basics.maria.raleva;


import com.sun.org.apache.bcel.internal.classfile.InnerClass;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.Rectangle;
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
    char operation;

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
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLayout(new GridBagLayout());

        GridBagConstraints a = new GridBagConstraints();
        a.weightx = 1.0;
        a.weighty = 1.0;
        a.insets = new Insets(10, 10, 10, 10);
        //a.insets = new Insets(10,0,0,0);
        a.fill = GridBagConstraints.BOTH;
        //Create JPanel panel
        JPanel panel = new JPanel();
        frame.add(panel, a);
        //panel.setBackground(Color.BLACK);
        panel.setLayout(new GridBagLayout());
        JPanel panelUp = new JPanel();
        //a.ipady = 20;
        //panelUp.setBackground(Color.GREEN);
        //Border
        panelUp.setBorder(BorderFactory.createLineBorder(Color.black));
        a.weightx = 1.0;
        a.weighty = 0.0;
        a.insets = new Insets(10, 10, 10, 10);
        a.fill = GridBagConstraints.HORIZONTAL;
        a.gridx = 0;
        a.gridy = 0;
        panel.add(panelUp, a);
        JPanel panelDown = new JPanel();
       // panelDown.setBackground(Color.RED);
        // a.ipady = 20;

        a.weightx = 1.0;
        a.weighty = 1.0;
        //a.insets = new Insets(10,0,0,0);
        a.fill = GridBagConstraints.BOTH;
        a.gridx = 0;
        a.gridy = 1;
        panel.add(panelDown, a);
        GridBagLayout gridUp;
        gridUp = new GridBagLayout();
        GridLayout gridDown;
        gridDown = new GridLayout(4, 3, 2, 2);

        panelUp.setLayout(gridUp);
        panelDown.setLayout(gridDown);

        //create arr with all number buttons 0 to 9
        final JButton arrNButtons[] = new JButton[10];
        for (int i = 0; i < arrNButtons.length; ++i) {
            arrNButtons[i] = new JButton("" + i);
            arrNButtons[i].setFocusable(false);

        }
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

        //Add all numbers button to panelDown
        panelDown.add(arrNButtons[1]);
        panelDown.add(arrNButtons[2]);
        panelDown.add(arrNButtons[3]);
        panelDown.add(arrOButtons[0]);
        panelDown.add(arrNButtons[4]);
        panelDown.add(arrNButtons[5]);
        panelDown.add(arrNButtons[6]);
        panelDown.add(arrOButtons[1]);
        panelDown.add(arrNButtons[7]);
        panelDown.add(arrNButtons[8]);
        panelDown.add(arrNButtons[9]);
        panelDown.add(arrOButtons[2]);
        JButton clear = new JButton("Clear");
        clear.setFocusable(false);
        clear.setForeground(Color.RED);
        panelDown.add(clear);
        JButton equal = new JButton("=");
        equal.setFocusable(false);
        panelDown.add(arrNButtons[0]);

        panelDown.add(equal);

        panelDown.add(arrOButtons[3]);

        GridBagConstraints c = new GridBagConstraints();
        final JTextField txtN1 = new JTextField("0");
        txtN1.grabFocus();
        c.insets = new Insets(10, 10, 10, 10);
        c.ipady = 10;
        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        panelUp.add(txtN1, c);

        ///////////
        final JLabel lblOperation = new JLabel("+", JLabel.CENTER);
        c.insets = new Insets(1, 1, 1, 1);
        lblOperation.setForeground(Color.RED);
        //lblOperation.setBorder(BorderFactory.createLineBorder(Color.black));
        //lblOperation.setHorizontalAlignment(1);
        c.weightx = 0.05;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        panelUp.add(lblOperation, c);
        //////
        final JTextField txtN2 = new JTextField("0");
        c.insets = new Insets(10, 10, 10, 10);
        c.ipady = 10;
        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 0;
        panelUp.add(txtN2, c);
        //////////////
        final JTextField txtResult = new JTextField("0.0");
        txtResult.setEnabled(false);
        c.ipady = 20;
        c.gridwidth = 3;
        c.weightx = 0.0;
        c.insets = new Insets(10, 10, 10, 10);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        panelUp.add(txtResult, c);
        ///////////////////////

        //Action Listener for the numbers buttons
        //Insert number in the text field when focused
        //First zero is replaced with ""
        for (int i = 0; i < arrNButtons.length; ++i) {
            final int index = i;
            arrNButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    try {
                        Integer.parseInt(txtN1.getText());
                        if (txtN1.isFocusOwner()) {


                            if (Integer.parseInt(txtN1.getText()) != 0) {
                                txtN1.setText(txtN1.getText() + arrNButtons[index].getText());
                            } else {
                                txtN1.setText("");
                                txtN1.setText(txtN1.getText() + arrNButtons[index].getText());
                            }
                        } else {
                            if (Integer.parseInt(txtN2.getText()) != 0) {
                                txtN2.setText(txtN2.getText() + arrNButtons[index].getText());
                            } else {
                                txtN2.setText("");
                                txtN2.setText(txtN2.getText() + arrNButtons[index].getText());
                            }

                        }
                    } catch (NumberFormatException nFE) {
                        txtResult.setText("Unknown Format");
                    }

                }
            });
        }
        //Set operation
        for (int i = 0; i < arrOButtons.length; ++i) {
            final int index = i;
            arrOButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    operation = arrOButtons[index].getText().charAt(0);
                    txtN2.grabFocus();
                    lblOperation.setText(arrOButtons[index].getText());

                }
            });
        }
        //Calculates the result
        equal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                try {
                    BigDecimal first = new BigDecimal(txtN1.getText());

                    BigDecimal second = new BigDecimal(txtN2.getText());
                    BigDecimal result = new BigDecimal(0);
                    switch (lblOperation.getText().charAt(0)) {

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
                            result =  first.divide(second,9, RoundingMode.HALF_UP);
                            break;
                    }
                    txtResult.setText("" + result);
                    txtN1.setText("0");
                    txtN2.setText("0");
                    txtN1.grabFocus();
                } catch (NumberFormatException nFE) {
                    txtResult.setText("Unknown Format");
                }
            }
        });

        ///Clear all values
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                txtResult.setText("0.0");
                txtN1.setText("0");
                txtN2.setText("0");
                txtN1.grabFocus();
            }
        });
        //grid.addLayoutComponent("Up",gridUp);
        //JPanel panel2 = new JPanel();
    }
}
