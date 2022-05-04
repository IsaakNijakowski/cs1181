import javax.swing.*;
import java.awt.*;

class StartMenu {
    StartMenu(int lastScore) {
        JFrame frame = new JFrame("Helpless Hero");
        frame.setSize(1080, 720);

        JPanel root = new JPanel();
        BorderLayout layout = new BorderLayout();
        root.setLayout(layout);

        JLabel title = new JLabel("Helpless Hero");
        JLabel score = new JLabel("Last Score: "+lastScore);

        // Short tutorial
        JPanel tutorial = new JPanel();
        tutorial.setLayout(new BoxLayout(tutorial, BoxLayout.Y_AXIS));
        JProgressBar healthBar = new JColorBar(Color.red);
        healthBar.setString("Health Bar");
        healthBar.setValue(healthBar.getMaximum());
        JLabel healthText = new JLabel("Displays health. Dying will reset progress. Killing will grant gold and EXP. Gold and Skill Points from EXP can be used to upgrade in a shop.");
        JProgressBar speedBar = new JColorBar(Color.green);
        speedBar.setString("Speed Bar");
        speedBar.setValue(speedBar.getMaximum());
        JLabel speedText = new JLabel("Displays speed. Speed will automatically increase. When speed is full, you will attack. The enemy also has their own speed bar.");
        JProgressBar expBar = new JColorBar(Color.blue);
        expBar.setString("EXP Bar");
        expBar.setValue(expBar.getMaximum());
        JLabel expText = new JLabel("Displays experience. When bar is full it will grant a skill point which can be used in the skill shop to boost your character.");
        JProgressBar progressBar = new JColorBar(Color.orange);
        progressBar.setString("Progress Bar");
        progressBar.setValue(progressBar.getMaximum());
        JLabel progressText = new JLabel("Displays progress through a stage. Defeating an enemy increases progress and once the bar is full you will continue to the next stage of enemies.");
        JLabel blank = new JLabel(" ");
        JLabel ready = new JLabel("Did you read all the text? Click the button on the right to begin your journey!");

        tutorial.add(healthBar);
        tutorial.add(healthText);
        tutorial.add(speedBar);
        tutorial.add(speedText);
        tutorial.add(expBar);
        tutorial.add(expText);
        tutorial.add(progressBar);
        tutorial.add(progressText);
        tutorial.add(blank);
        tutorial.add(ready);

        JButton play = new JButton("Play Game");
        JLabel creator = new JLabel("Made by Isaak Nijakowski");

        play.addActionListener(e -> {
            new GameRuntime();
            frame.dispose();
        });

        root.add(title, BorderLayout.PAGE_START);
        root.add(score, BorderLayout.LINE_START);
        root.add(tutorial, BorderLayout.CENTER);
        root.add(play, BorderLayout.LINE_END);
        root.add(creator, BorderLayout.PAGE_END);

        frame.getContentPane().add(root);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        new StartMenu(0);
    }
}