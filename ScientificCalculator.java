import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScientificCalculator extends JFrame {
    private JTextField textField;
    private double currentValue;
    private String currentOperator;

    public ScientificCalculator() {
        setTitle("Scientific Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setResizable(false);
        setLocationRelativeTo(null);

        textField = new JTextField();
        textField.setPreferredSize(new Dimension(280, 40));
        textField.setFont(new Font("Arial", Font.PLAIN, 20));
        add(textField, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4, 5, 5));

        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "sin", "cos", "tan", "sqrt"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.PLAIN, 16));
            button.addActionListener(new ButtonClickListener());
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String buttonLabel = event.getActionCommand();

            if (buttonLabel.equals("=")) {
                calculateResult();
            } else if (buttonLabel.equals("sin")) {
                calculateTrigFunction("sin");
            } else if (buttonLabel.equals("cos")) {
                calculateTrigFunction("cos");
            } else if (buttonLabel.equals("tan")) {
                calculateTrigFunction("tan");
            } else if (buttonLabel.equals("sqrt")) {
                calculateSquareRoot();
            } else {
                textField.setText(textField.getText() + buttonLabel);
            }
        }

        private void calculateResult() {
            String expression = textField.getText();
            if (!expression.isEmpty() && currentOperator != null) {
                double secondValue = Double.parseDouble(expression);
                double result = 0.0;
                switch (currentOperator) {
                    case "+":
                        result = currentValue + secondValue;
                        break;
                    case "-":
                        result = currentValue - secondValue;
                        break;
                    case "*":
                        result = currentValue * secondValue;
                        break;
                    case "/":
                        result = currentValue / secondValue;
                        break;
                }
                textField.setText(Double.toString(result));
            }
        }

        private void calculateTrigFunction(String function) {
            String expression = textField.getText();
            if (!expression.isEmpty()) {
                double value = Double.parseDouble(expression);
                double result = 0.0;
                switch (function) {
                    case "sin":
                        result = Math.sin(Math.toRadians(value));
                        break;
                    case "cos":
                        result = Math.cos(Math.toRadians(value));
                        break;
                    case "tan":
                        result = Math.tan(Math.toRadians(value));
                        break;
                }
                textField.setText(Double.toString(result));
            }
        }

        private void calculateSquareRoot() {
            String expression = textField.getText();
            if (!expression.isEmpty()) {
                double value = Double.parseDouble(expression);
                double result = Math.sqrt(value);
                textField.setText(Double.toString(result));
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ScientificCalculator();
        });
    }
}
