/*
 * Isaak Nijakowski
 * Practice Problem 5
 * CS1181L-06
 * 2/18/2022
 */

import javax.swing.*;

public class CheckDivider {
    
    CheckDivider() {

        JFrame frame = new JFrame("Check Divider");
        JPanel root = new JPanel();
        BoxLayout layout = new BoxLayout(root, BoxLayout.Y_AXIS);
        root.setLayout(layout);

        JLabel totalText = new JLabel("Total:");
        JTextField totalField = new JTextField();
        JLabel tipText = new JLabel("Tip Percentage:");
        JTextField tipField = new JTextField();
        JLabel peopleText = new JLabel("Number of People:");
        JTextField peopleField = new JTextField();
        JLabel payText = new JLabel("Each person pays:");
        JLabel result = new JLabel("$0.00");
        JButton calculate = new JButton("Calculate");
        
        root.add(totalText);
        root.add(totalField);
        root.add(tipText);
        root.add(tipField);
        root.add(peopleText);
        root.add(peopleField);
        root.add(payText);
        root.add(result);
        root.add(calculate);

        calculate.addActionListener(e -> {
            double total = Double.parseDouble(totalField.getText());
            double tip = Double.parseDouble(tipField.getText());
            double people = Double.parseDouble(peopleField.getText());
            double payAmount = (total*(1.0+(tip/100.0)))/people;
            result.setText(String.format("$%.2f", payAmount));
        });

        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(root);
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        new CheckDivider();
    }
}