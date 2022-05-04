import javax.swing.*;
import java.awt.*;

class EventHandling {

    private int timesClicked = 0;

    public EventHandling() {

        JFrame frame = new JFrame();

        JPanel root = new JPanel();
        JLabel label = new JLabel("0");
        ImageIcon image = new ImageIcon("cookie.png");

        JButton button = new JButton(image);
        //button.addActionListener(new MyActionListener());

        /*button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Clicked");
            }
        });*/

        button.addActionListener(e -> {
            System.out.println("Clicked");
            timesClicked++;
            label.setText("" + timesClicked);
        });

        //button.setPreferredSize(new Dimension(40, 40));
        
        root.add(button);
        root.add(label);
        frame.getContentPane().add(root);

        frame.setSize(500, 300);
        frame.setVisible(true);
        

    }

    public static void main(String[] args) {
        EventHandling test = new EventHandling();
    }
}