import javax.swing.Timer;
public class GameRuntime extends Game {
    private Timer timer;
    private int totalTime = 900;
    private int minutes;
    private int seconds;
    private int currentTimeSeconds;
    private int score;
    GameRuntime() {
        timer = new Timer(1, e -> {
            user.speedPlus();
            opponent.speedPlus();

            // Clock setup
            currentTime = System.currentTimeMillis();
            currentTimeSeconds = (int) (startTime / 1000) - (int) (currentTime / 1000);
            minutes = (currentTimeSeconds + totalTime) / 60;
            seconds = (currentTimeSeconds + totalTime) % 60;
            clock.setText("Time Left: "+minutes+":"+seconds);

            // Score Setup
            score = 45000+50*currentTimeSeconds;
            scoreText.setText("Score: "+score);

            // Stage Text Setup
            if (stages.indexOf(currentStage) < 3) {
                stageText.setText("Stage: "+(stages.indexOf(currentStage)+1)+"/3");
            } else {
                stageText.setText("Stage: Final Boss");
            }
            
            playerIcon.setText("Player Level "+user.getLevel());
            enemyIcon.setText(opponent.getName());
            
            // Shop Bar Updater
            // Skillshop
            expPointValue.setText(""+user.getSkillPoints());
            moneyBoost.updateShopBar();
            experienceBoost.updateShopBar();
            // Goldshop
            goldPointValue.setText(""+user.getGold());
            attackUp.updateShopBar();
            healthUp.updateShopBar();
            armorUp.updateShopBar();
            attackSpeedUp.updateShopBar();

            // Player Health Bar Updater
            healthBar.setMaximum(user.getMaxHealth());
            healthBar.setValue(user.getHealth());
            healthBar.setString(user.getHealth()+"/"+user.getMaxHealth());
    
            // Player Speed Bar Updater
            speedBar.setMaximum(user.getAttackSpeed());
            speedBar.setValue(user.getSpeed());
            speedBar.setString(user.getSpeed()+"/"+user.getAttackSpeed());
            
            // Player Exp Bar Updater
            expBar.setMaximum(user.getMaxExp());
            expBar.setValue(user.getExp());
            expBar.setString(user.getExp()+"/"+user.getMaxExp());
    
            // Enemy Health Bar Updater
            enemyHealthBar.setMaximum(opponent.getMaxHealth());
            enemyHealthBar.setValue(opponent.getHealth());
            enemyHealthBar.setString(opponent.getHealth()+"/"+opponent.getMaxHealth());
            
            // Enemy Speed Bar Updater
            enemySpeedBar.setMaximum(opponent.getAttackSpeed());
            enemySpeedBar.setValue(opponent.getSpeed());
            enemySpeedBar.setString(opponent.getSpeed()+"/"+opponent.getAttackSpeed());
    
            // Progress Bar Updater
            stageProgress.setMaximum(currentStage.getCompletionAmount());
            stageProgress.setValue(currentStage.getProgress());
            stageProgress.setString(currentStage.getProgress()+"/"+currentStage.getCompletionAmount());
    
            if (user.getSpeed()>=user.getAttackSpeed()) {
                user.attack(opponent);
                user.resetSpeed();
            }
            if (user.getHealth() <= 0) {
                user.setGold(user.getGold()+10);
                opponent = currentStage.getNewMonster();
                currentStage.progressReset();
                user.setHealth(user.getMaxHealth());
            }
            if (opponent.getHealth() <= 0) {
                user.obtainLoot(opponent.getLoot());
                if (opponent.getName() != "Treasure Chest") {
                    currentStage.progressUp();
                }
                opponent = currentStage.getNewMonster();
                
            }
            if (opponent.getSpeed()>=opponent.getAttackSpeed()) {
                opponent.attack(user);
                opponent.resetSpeed();
            }
            if (user.getExp() >= user.getMaxExp()) {
                user.levelUp();
            }
            if (currentStage.getProgress() == currentStage.getCompletionAmount()) {
                if (currentStage == Boss) {
                    timer.stop();
                    endGame();
                } else {
                    currentStage = stages.get(stages.indexOf(currentStage)+1);
                }
                opponent = currentStage.getNewMonster();
            }
            if (score <= 0) {
                timer.stop();
                endGame();
            }
        });
        timer.setRepeats(true);
        timer.start();
    }
    public void endGame() {
        frame.dispose();
        new StartMenu(score);
    }
}
