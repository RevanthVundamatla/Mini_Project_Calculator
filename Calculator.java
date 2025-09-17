import java.awt.*;
import java.awt.event.*;

public class Calculator extends Frame implements ActionListener {

    // Components
    TextField inputField;
    Button[] numberButtons = new Button[10];
    Button addButton, subButton, mulButton, divButton, equButton, clrButton, perButton;
    Button sqrButton, recipButton, sqrtButton; // New buttons
    Panel panel;

    // Variables for calculation
    double num1 = 0, num2 = 0, result = 0;
    char operator;

    Calculator() {
        // Frame settings
        setTitle("Calculator");
        setSize(420, 580);
        setLayout(null);
        setBackground(new Color(20, 20, 20));
        setVisible(true);

        // Input field
        inputField = new TextField();
        inputField.setBounds(30, 50, 360, 60);
        inputField.setEditable(false);
        inputField.setBackground(new Color(20, 20, 20));
        inputField.setForeground(Color.WHITE);
        inputField.setFont(new Font("Segoe UI", Font.BOLD, 24));
        add(inputField);

        // Buttons
        addButton = new Button("+");
        subButton = new Button("-");
        mulButton = new Button("×");
        divButton = new Button("÷");
        equButton = new Button("=");
        clrButton = new Button("C");
        perButton = new Button("%");
        sqrButton = new Button("x²");
        recipButton = new Button("1/x");
        sqrtButton = new Button("√");

        Button[] functionButtons = {
            addButton, subButton, mulButton, divButton,
            equButton, clrButton, perButton, sqrButton, recipButton, sqrtButton
        };

        for (Button b : functionButtons) {
            b.addActionListener(this);
            b.setBackground(new Color(45, 45, 45));
            b.setForeground(Color.WHITE);
            b.setFont(new Font("Segoe UI", Font.BOLD, 18));
        }

        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new Button(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setBackground(new Color(30, 30, 30));
            numberButtons[i].setForeground(Color.WHITE);
            numberButtons[i].setFont(new Font("Segoe UI", Font.BOLD, 20));
        }

        // Special operator colors
        addButton.setBackground(new Color(255, 140, 0));
        equButton.setBackground(new Color(0, 120, 215));
        clrButton.setBackground(new Color(200, 0, 0));

        // Panel for buttons (6 rows now)
        panel = new Panel();
        panel.setBounds(30, 130, 360, 400);
        panel.setLayout(new GridLayout(6, 4, 10, 10));

        // First row → new functions
        panel.add(sqrButton);
        panel.add(recipButton);
        panel.add(sqrtButton);
        panel.add(divButton);

        // Second row
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);

        // Third row
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);

        // Fourth row
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);

        // Fifth row
        panel.add(numberButtons[0]);
        panel.add(perButton);
        panel.add(clrButton);
        panel.add(equButton);

        add(panel);

        // Window closing event
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });
    }



    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                inputField.setText(inputField.getText().concat(String.valueOf(i)));
            }
        }

        if (e.getSource() == addButton) {
            num1 = Double.parseDouble(inputField.getText());
            operator = '+';
            inputField.setText("");
        }
        if (e.getSource() == subButton) {
            num1 = Double.parseDouble(inputField.getText());
            operator = '-';
            inputField.setText("");
        }
        if (e.getSource() == mulButton) {
            num1 = Double.parseDouble(inputField.getText());
            operator = '*';
            inputField.setText("");
        }
        if (e.getSource() == divButton) {
            num1 = Double.parseDouble(inputField.getText());
            operator = '/';
            inputField.setText("");
        }
        if (e.getSource() == perButton) {
            num1 = Double.parseDouble(inputField.getText());
            operator = '%';
            inputField.setText("");
        }

        //  functions
        if (e.getSource() == sqrButton) {
            num1 = Double.parseDouble(inputField.getText());
            result = num1 * num1;
            inputField.setText(String.valueOf(result));
        }
        if (e.getSource() == recipButton) {
            num1 = Double.parseDouble(inputField.getText());
            if (num1 != 0) {
                result = 1 / num1;
                inputField.setText(String.valueOf(result));
            } else {
                inputField.setText("Error");
            }
        }
        if (e.getSource() == sqrtButton) {
            num1 = Double.parseDouble(inputField.getText());
            if (num1 >= 0) {
                result = Math.sqrt(num1);
                inputField.setText(String.valueOf(result));
            } else {
                inputField.setText("Error");
            }
        }

        if (e.getSource() == equButton) {
            num2 = Double.parseDouble(inputField.getText());

            switch (operator) {
                case '+' -> result = num1 + num2;
                case '-' -> result = num1 - num2;
                case '*' -> result = num1 * num2;
                case '/' -> {
                    if (num2 != 0){ 
                        result = num1 / num2;
                    } else { 
                        inputField.setText("Error");
                        return; 
                    }
                }
                case '%' -> result = num1 % num2;
            }
            inputField.setText(String.valueOf(result));
            num1 = result;
        }

        if (e.getSource() == clrButton) {
            inputField.setText("");
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
