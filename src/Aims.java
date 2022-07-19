import java.util.Random;

public class Aims {
    private final Random rand = new Random();
    private int randX;
    private int randY;
    private int smallOrBig;
    private boolean isBig;
    private int width;
    private int height;
    private int counter = 0;
    private final Bullet[] bullets;
    private final Point point;
    private final Level level;
    private int aimSpeed = 30;

    public Aims(Bullet[] bullets, Point point, Level level) {
        this.bullets = bullets;
        this.point = point;
        this.level = level;
        randY = rand.nextInt(900)+660;
        if(randY%2 == 0){
            randX = rand.nextInt(100);
        }else {
            randX = rand.nextInt(100)+500;
        }
        smallOrBig = rand.nextInt();
        if(smallOrBig %2 == 0){
            isBig = true; //true for big;
            width = 100;
            height = 100;
        }else{
            isBig = false; //false for small;
            width = 50;
            height = 50;
        }
    }

    public int getRandX() {
        return randX;
    }

    public int getRandY() {
        if(level.getLevel() == 1){
            aimSpeed = 30;
        }else if(level.getLevel() == 2){
            aimSpeed = 20;
        }else if(level.getLevel() == 3){
            aimSpeed = 15;
        }
        else if(level.getLevel() == 4){
            aimSpeed = 10;
        }else if(level.getLevel() == 5){
            aimSpeed = 8;
        }
        counter++;
        if(counter>aimSpeed){
            counter = 0;
            randY-= 10;
            return randY;
        }
        if(checkCollision() || randY<0){
            reload();
        }
        return randY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void reload(){
        randY = rand.nextInt(900)+660;
        if(randY%2 == 0){
            randX = rand.nextInt(100);
        }else {
            randX = rand.nextInt(100)+500;
        }
        smallOrBig = rand.nextInt();
        if(smallOrBig %2 == 0){
            isBig = true; //true for big;
            width = 100;
            height = 100;
        }else{
            isBig = false; //false for small;
            width = 50;
            height = 50;
        }
    }

    public boolean checkCollision(){
        for (Bullet bullet : bullets) {
            int checkX = bullet.getX() - randX;
            int checkY = bullet.getBulletY() - randY;
            if(isBig){
                if (checkX < 50 && checkX > -50 && checkY < 50 && checkY > -50) {
                    bullet.setIsShot(false);
                    point.setPoints(point.getPoints() + 1);
                    if (point.getPoints() > 15 && point.getPoints() < 30) {
                        level.setLevel(2);
                    } else if (point.getPoints() > 30 && point.getPoints() < 50) {
                        level.setLevel(3);
                    } else if (point.getPoints() > 50 && point.getPoints() < 100) {
                        level.setLevel(4);
                    } else if (point.getPoints() > 100) {
                        level.setLevel(5);
                    }
                    return true;
                } else {
                    return false;
                }
            }else{
                if (checkX < 25 && checkX > -25 && checkY < 25 && checkY > -25) {
                    bullet.setIsShot(false);
                    point.setPoints(point.getPoints() + 2);
                    if (point.getPoints() > 15 && point.getPoints() < 30) {
                        level.setLevel(2);
                    } else if (point.getPoints() > 30 && point.getPoints() < 50) {
                        level.setLevel(3);
                    } else if (point.getPoints() > 50 && point.getPoints() < 100) {
                        level.setLevel(4);
                    } else if (point.getPoints() > 100) {
                        level.setLevel(5);
                    }
                    return true;
                } else {
                    return false;
                }
            }

        }
        return false;
    }

}
