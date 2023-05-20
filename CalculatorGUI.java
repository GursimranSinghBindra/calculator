package calculator;//package calculator;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class Calculator implements ActionListener {
//    JFrame jframe =new JFrame();
//    JFrame frame;
//    JTextField textfield;
//    JButton[] numberButtons = new JButton[10];
//    JButton[] functionButtons = new JButton[8];
//    JButton addbutton, subButton, divButton, mulButton;
//    JButton decButton, equButton, delButton, clrButton;
//    JPanel panel;
//    Font myfont = new Font(Font.SANS_SERIF, Font.BOLD, 20);
//    double num1 = 0, num2 = 0, result = 0;
//    char operator;
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//
//    }
//Calculator(){
//
//    frame =new JFrame("Calculator");
//    frame.setDefaultCloseOperation(3);
//        frame.setSize(500, 700);
//        frame.setLayout(null);
//        textfield =new JTextField();
//        textfield .setBounds(50,25,300,50);
//        textfield.setFont(myfont);
//        textfield.setEditable(false);
//
//    addbutton =new JButton("+");
//    subButton =new JButton("-");
//    mulButton =new JButton("*");
//    divButton =new JButton("/");
//    delButton =new JButton("Delete");
//    equButton =new JButton("=");
//    decButton =new JButton(".");
//    clrButton =new JButton("Clear");
//    functionButtons [0]= addbutton;
//    functionButtons [1]= addbutton;
//    functionButtons [2]= addbutton;
//    functionButtons [3]= addbutton;
//    functionButtons [4]= addbutton;
//    functionButtons [5]= addbutton;
//    functionButtons [6]= addbutton;
//    functionButtons [7]= addbutton;
//    frame.add(textfield);
//    frame .setVisible(true);
//
//
//
//
//
//
//    }
//
//    public static void main(String[] args) {
//    Calculator canc =new Calculator();
//
//
//    }
//
//        }
//
//
//
//

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGUI extends JFrame implements ActionListener {
    private JTextField displayField;
    private double num1, num2;
    private String operator;

    public CalculatorGUI() {

        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        displayField = new JTextField(20);
        displayField.setEditable(false);
        displayField.setHorizontalAlignment(JTextField.RIGHT);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 4, 5, 5));
        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "C", "=", "+"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        setLayout(new FlowLayout());
        add(displayField);
        add(buttonPanel);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (isNumeric(command)) {
            String currentText = displayField.getText();
            displayField.setText(currentText + command);
        } else if (isOperator(command)) {
            num1 = Double.parseDouble(displayField.getText());
            operator = command;
            displayField.setText("");
        } else if (command.equals("=")) {
            num2 = Double.parseDouble(displayField.getText());
            double result = calculateResult(num1, num2, operator);
            displayField.setText(Double.toString(result));
        } else if (command.equals("C")) {
            displayField.setText("");
        }
    }

    private boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    private boolean isOperator(String str) {
        return str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/");
    }

    private double calculateResult(double num1, double num2, String operator) {
        double result = 0;
        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                result = num1 / num2;
                break;
        }
        return result;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculatorGUI calculator = new CalculatorGUI();
            calculator.pack();
            calculator.setVisible(true);
        });
    }
}
