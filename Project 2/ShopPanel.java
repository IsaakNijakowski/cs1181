import javax.swing.*;
import java.awt.Color;
public class ShopPanel extends JPanel {

    private JLabel title = new JLabel();
    private JLabel description = new JLabel();
    private JButton icon = new JButton();
    private int cost;
    private double costScale;
    private boolean goldCurrency;
    private int upgradeIndex;
    private Player player;

    private JProgressBar shopBar;

    ShopPanel(String title, String description, boolean goldCurrency, int cost, double costScale, int upgradeIndex, Player player) {
        this.player = player;
        this.cost = cost;
        this.costScale = costScale;
        this.goldCurrency = goldCurrency;
        this.upgradeIndex = upgradeIndex;
        this.title.setText(title);
        this.title.setFont(this.title.getFont().deriveFont((float) 15));
        this.description.setText(description);
        if (goldCurrency) {
            icon.setIcon(new ImageIcon("./goldArrow.png"));
            shopBar = new JColorBar(new Color(180, 125, 0));
        } else {
            icon.setIcon(new ImageIcon("./blueArrow.png"));
            shopBar = new JColorBar(Color.blue);
        }
        shopBar.setMaximum(cost);
        shopBar.setString("0/"+cost);
        this.add(this.title);
        this.add(this.icon);
        this.add(this.shopBar);
        this.add(this.description);
        this.setBorder(BorderFactory.createLineBorder(Color.black));

        icon.addActionListener(e -> {
            if (goldCurrency) {
                if (this.player.getGold() >= this.cost) {
                    this.player.setGold(this.player.getGold() - this.cost);
                    this.cost = (int) (this.cost*this.costScale);
                    switch (this.upgradeIndex) {
                        case 1:
                        this.player.setDamage(this.player.getDamage() + 5);
                        break;
                        case 2:
                        this.player.setMaxHealth(this.player.getMaxHealth() + 20);
                        break;
                        case 3:
                        this.player.setArmor(this.player.getArmor() + 1);
                        break;
                        case 4:
                        this.player.setAttackSpeed((int)(this.player.getAttackSpeed()*0.9));
                        break;
                        default:
                        System.err.println("Invalid upgrade case for gold based upgrades");
                        break;
                    }
                }
            } else {
                if (this.player.getSkillPoints() >= this.cost) {
                    this.player.setSkillPoints(this.player.getSkillPoints() - this.cost);
                    this.cost = (int) (this.cost*this.costScale);
                    switch (this.upgradeIndex) {
                        case 11:
                        this.player.setGoldMultiplier(this.player.getGoldMultiplier() + 0.25);
                        break;
                        case 12:
                        this.player.setExpMultiplier(this.player.getExpMultiplier() + 0.5);
                        break;
                        default:
                        System.err.println("Invalid upgrade caase for exp based upgrdes");
                    }
                }
            }
        });
    }

    public void updateShopBar() {
        if (goldCurrency) {
            shopBar.setValue(player.getGold());
            shopBar.setString(player.getGold()+"/"+cost);
        } else {
            shopBar.setValue(player.getSkillPoints());
            shopBar.setString(player.getSkillPoints()+"/"+cost);
        }
        shopBar.setMaximum(cost);
    }
}
