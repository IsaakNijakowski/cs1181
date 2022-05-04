/*
 * Isaak Nijakowski
 * CS1181L-6
 * Practice Problem 6
 * 2/25/2022
 */
import javax.swing.*;
class GUI {

    public GUI() {
        // Create Frame!
        JFrame frame = new JFrame("Text Decorator");

        // Set up and layout root panel
        JPanel root = new JPanel();
        BoxLayout layout = new BoxLayout(root, BoxLayout.Y_AXIS);
        root.setLayout(layout);

        // Text Area formatting and code
        JTextArea text = new JTextArea("Type here...");

        // Slider formatting and code
        JPanel sliderZone = new JPanel();
        JLabel sliderText = new JLabel("Font Size:");
        JSlider slider = new JSlider(0, 100, 12); //Text default size is 12 units
        slider.setMajorTickSpacing(25);
        slider.setMinorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        // Sets font size to slider value
        slider.addChangeListener(e -> {
            text.setFont(text.getFont().deriveFont((float) slider.getValue()));
        });

        // Adds slider panel to root panel
        sliderZone.add(sliderText);
        sliderZone.add(slider);

        // Adds all components to root panel
        root.add(text);
        root.add(sliderZone);

        // Set up and display frame
        frame.getContentPane().add(root);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setVisible(true);

    }
    public static void main(String[] args) {
        new GUI();
    }
}