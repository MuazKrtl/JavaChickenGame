import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/*public class Bullet implements MouseListener {

    Chicken chicken;
    Bullet bullets[];

    public Bullet(Chicken chicken, Bullet bullets[]) {
        this.chicken = chicken;
        this.bullets = bullets;
        for(int i = 0;i<bullets.length;i++){
            bullets[i] = new Bullet(chicken);
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        for(int i = 0;i<bullets.length;i++){
            if(!bullets[i].getIsShot()){
                bullets[i].setIsShot(true);
                bullets[i].aimShot(e.getX(),e.getY());
                break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }*/

    public class Bullet {

        private int checkX,checkY;
        int counter = 0;
        Chicken chicken;

        public Bullet(Chicken chicken) {
            this.chicken = chicken;
        }
        boolean isShot = false;
        int bulletX = -50;
        int bulletY = -50;
        int aimX = 0;
        int aimY = 0;
        float degree,speedX,speedY;

        public int getBulletX() {
            if(isShot){
                counter++;
                if(counter>10){
                    counter = 0;
                    bulletX -= speedX;
                    bulletY -= speedY;
                    return bulletX;
                }
                if( bulletX > 800 || bulletY > 640 || bulletX<0 || bulletY<0){
                    isShot = false;
                    bulletX = -50;
                    bulletY = -50;
                }
            }
            return bulletX;
        }

        public int getBulletY() {
            return bulletY;
        }

        public int getX() {
            return bulletX;
        }

        public boolean getIsShot() {
            return isShot;
        }

        public void setIsShot(boolean shot) {
            isShot = shot;
            if(shot){
                bulletX = chicken.getPosX();
                bulletY = chicken.getPosY();
            }else{
                bulletX = -50;
                bulletY = -50;
            }
        }

        public void setAimX(int aimX) {
            this.aimX = aimX;
        }

        public void setAimY(int aimY) {
            this.aimY = aimY;
        }

        public void aimShot(){
            if(chicken.getShootDirection() == 1){
                speedX = 15;
            }else{
                speedX = -15;
            }
            speedY = 0;
        }

    }

