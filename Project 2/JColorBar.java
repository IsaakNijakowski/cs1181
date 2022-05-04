import javax.swing.JProgressBar;
import java.awt.Color;

public class JColorBar extends JProgressBar{
    JColorBar(Color color) {
        setStringPainted(true);
        setForeground(color);
    }
}
