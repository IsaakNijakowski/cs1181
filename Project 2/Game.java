import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class Game {
    // Game 
    protected Monster opponent;
    protected Player user;

    // Timer
    protected Long startTime = System.currentTimeMillis();
    protected Long currentTime;

    // Game stages
    protected ArrayList<Stage> stages = new ArrayList<>();
    protected Stage level1;
    protected Stage level2;
    protected Stage level3;
    protected Stage Boss;
    protected Stage currentStage;

    // UI frame & root
    protected JFrame frame;
    protected JPanel root;
    
    // Layouts
    protected BorderLayout layout;
    protected BoxLayout boxLayout;

    // Title Panel
    protected JPanel title;
    protected JLabel clock;
    protected JLabel scoreText;
    protected JLabel stageText;

    // Skill Shop Panel
    protected JPanel skillShop;
    protected JLabel expPointText;
    protected JLabel expPointValue;
    protected ShopPanel moneyBoost;
    protected ShopPanel experienceBoost;

    // Player Panel
    protected JPanel player;
    protected JLabel playerIcon;
    protected JProgressBar healthBar;
    protected JProgressBar speedBar;
    protected JProgressBar expBar;

    // Enemy Panel
    protected JPanel enemy;
    protected JLabel enemyIcon;
    protected JProgressBar enemyHealthBar;
    protected JProgressBar enemySpeedBar;
    protected JProgressBar stageProgress;

    // Shop Panel
    protected JPanel shop;
    protected JLabel goldPointText;
    protected JLabel goldPointValue;
    protected ShopPanel attackUp;
    protected ShopPanel healthUp;
    protected ShopPanel armorUp;
    protected ShopPanel attackSpeedUp;


    Game() {
        user = new Player(100, 10, 0, 100);
        
        // -----------------
        // MONSTERS & STAGES
        // -----------------
        // stage 1
        level1 = new Stage(10);
        level1.add(new Monster("Baddie", 50, 4, 0, 50, 25));
        level1.add(new Monster("Shotstop", 20, 1, 0, 5, 20));
        level1.add(new Monster("Big Man", 85, 40, 0, 700, 30));
        // stage 2
        level2 = new Stage(8);
        level2.add(new Monster("Undertaker", 100, 10, 1, 50, 50));
        level2.add(new Monster("Backstab McGee", 50, 3, 5, 5, 40));
        level2.add(new Monster("Flaming Lava", 200, 5, 0, 25, 75));
        // stage 3
        level3 = new Stage(5);
        level3.add(new Monster("Suspicous Knight", 200, 50, 5, 50, 150));
        level3.add(new Monster("Mountain of Gold", 500, 200, 10, 1250, 200));
        level3.add(new Monster("Army of Goblins", 150, 10, 0, 7, 100));
        // final boss
        Boss = new Stage(1);
        Boss.add(new Monster("Augelenza Lord of Darkness", 750, 100, 15, 100, 0));
        Boss.add(new Monster("Treasure Chest", 1000, 0, 0, 50, 300));
        // adders
        stages.add(level1);
        stages.add(level2);
        stages.add(level3);
        stages.add(Boss);
        currentStage = stages.get(0);
        opponent = currentStage.getNewMonster();

        // ---------------------
        // WINDOW INITIALIZATION
        // ---------------------
        frame = new JFrame("Helpless Hero");
        frame.setSize(1080, 720);

        root = new JPanel();
        layout = new BorderLayout();
        root.setLayout(layout);
        
        // -----------
        // TITLE PANEL
        // -----------
        title = new JPanel();
        // Text
        clock = new JLabel("0:00");
        scoreText = new JLabel("Score: 00000");
        stageText = new JLabel("Stage: 1/3");
        // Layouts
        title.setLayout(boxLayout = new BoxLayout(title, BoxLayout.Y_AXIS));
        // Adders
        title.add(clock);
        title.add(scoreText);
        title.add(stageText);
        root.add(title, BorderLayout.PAGE_START);
        
        // ----------------
        // SKILL SHOP PANEL
        // ----------------
        skillShop = new JPanel();
        // Text
        expPointText = new JLabel("Skill Points:");
        expPointValue = new JLabel();
        expPointValue.setForeground(Color.blue);
        expPointValue.setFont(expPointValue.getFont().deriveFont((float) 30));
        // Buttons & Bars
        moneyBoost = new ShopPanel("Money Boost", "Increases the amount of gold dropped by enemies", false, 1, 2, 11, user);
        experienceBoost = new ShopPanel("EXP Boost", "Increase the experiences gained from defeating enemies", false, 1, 2, 12, user);
        // Layouts
        skillShop.setLayout(boxLayout = new BoxLayout(skillShop, BoxLayout.Y_AXIS));
        moneyBoost.setLayout(boxLayout = new BoxLayout(moneyBoost, BoxLayout.Y_AXIS));
        experienceBoost.setLayout(boxLayout = new BoxLayout(experienceBoost, BoxLayout.Y_AXIS));
        // Adders
        skillShop.add(expPointText);
        skillShop.add(expPointValue);
        skillShop.add(moneyBoost);
        skillShop.add(experienceBoost);
        root.add(skillShop, BorderLayout.LINE_START);

        // -------------
        // PLAYER LAYOUT
        // -------------
        player = new JPanel();
        // Text
        playerIcon = new JLabel("Player Icon");
        // Buttons & Bars
        healthBar = new JColorBar(Color.red);
        speedBar = new JColorBar(Color.green);
        expBar = new JColorBar(Color.blue);
        // Layouts
        player.setLayout(boxLayout = new BoxLayout(player, BoxLayout.Y_AXIS));
        // Adders
        player.add(playerIcon);
        player.add(healthBar);
        player.add(speedBar);
        player.add(expBar);
        root.add(player, BorderLayout.CENTER);

        // ------------
        // ENEMY LAYOUT
        // ------------
        enemy = new JPanel();
        // Text
        enemyIcon = new JLabel("Enemy Icon");
        // Buttons & Bars
        enemyHealthBar = new JColorBar(Color.red);
        enemySpeedBar = new JColorBar(Color.green);
        stageProgress = new JColorBar(Color.orange);
        // Layouts
        enemy.setLayout(boxLayout = new BoxLayout(enemy, BoxLayout.Y_AXIS));
        // Adders
        enemy.add(enemyIcon);
        enemy.add(enemyHealthBar);
        enemy.add(enemySpeedBar);
        enemy.add(stageProgress);
        root.add(enemy, BorderLayout.LINE_END);

        // ----------
        // SHOP PANEL
        // ----------
        shop = new JPanel();
        // Text
        goldPointText = new JLabel("Gold:");
        goldPointValue = new JLabel();
        goldPointValue.setForeground(new Color(125, 115, 0));
        goldPointValue.setFont(goldPointValue.getFont().deriveFont((float) 30));
        // Buttons & Bars
        attackUp = new ShopPanel("Attack Up", "Increases damage dealt", true, 110, 1.2, 1, user);
        healthUp = new ShopPanel("Health Up", "Increases your maximum health", true, 80, 1.5, 2, user);
        armorUp = new ShopPanel("Armor Up", "Decreases the amount of damage taken from an attack", true, 250, 1.2, 3, user);
        attackSpeedUp = new ShopPanel("Speed Up", "Decreses the amount of time between attacks", true, 200, 1.1, 4, user);
        // Layouts
        shop.setLayout(boxLayout = new BoxLayout(shop, BoxLayout.X_AXIS));
        attackUp.setLayout(boxLayout = new BoxLayout(attackUp, BoxLayout.Y_AXIS));
        healthUp.setLayout(boxLayout = new BoxLayout(healthUp, BoxLayout.Y_AXIS));
        armorUp.setLayout(boxLayout = new BoxLayout(armorUp, BoxLayout.Y_AXIS));
        attackSpeedUp.setLayout(boxLayout = new BoxLayout(attackSpeedUp, BoxLayout.Y_AXIS));
        // Adders
        shop.add(goldPointText);
        shop.add(goldPointValue);
        shop.add(attackUp);
        shop.add(healthUp);
        shop.add(armorUp);
        shop.add(attackSpeedUp);
        root.add(shop, BorderLayout.PAGE_END);

        frame.getContentPane().add(root);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
