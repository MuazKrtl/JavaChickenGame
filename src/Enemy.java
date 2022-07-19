import java.util.Random;

public class Enemy{
    private final Random rand = new Random();
    private final Chicken chicken;
    private int randX,randY;
    private int counter = 0,enemySpeed = 30;
    private final Level level;

    public Enemy(Chicken chicken,Level level) {
        this.chicken = chicken;
        this.level = level;
        randY = rand.nextInt(700)+660;
        randX = rand.nextInt(750);
    }

    public int getRandX() {
        return randX;
    }

    public int getRandY() {
        if(level.getLevel() == 1){
            enemySpeed = 30;
        }else if(level.getLevel() == 2){
            enemySpeed = 20;
        }else if(level.getLevel() == 3){
            enemySpeed = 15;
        }else if(level.getLevel() == 4){
            enemySpeed = 10;
        }else if(level.getLevel() == 5){
            enemySpeed = 8;
        }
        counter++;
        if(counter>enemySpeed){
            counter = 0;
            randY-= 10;
            return randY;
        }
        if(checkCollision() || randY<0){
            reload();
        }
        return randY;
    }

    public void reload(){
        randY = rand.nextInt(500)+660;
        randX = rand.nextInt(750);
    }

    public boolean checkCollision(){
        int checkX = chicken.getPosX() - randX;
        int checkY = chicken.getPosY() - randY;
        if(checkX <25 && checkX >-25 && checkY <25 && checkY >-25){
            level.setLevel(0);
            return true;
        }else{
            return false;
        }
    }

}
