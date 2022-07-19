import javax.swing.*;
import java.awt.*;

public class Chicken {

    private final int startPositionX = 350;
    private final int startPositionY = 100;
    private int direction = 3;
    private int posX = startPositionX;
    private int posY = startPositionY;
    private int spriteNum = 0;
    private int spriteCounter = 0;

    private int shootDirection = 1;//Default is 1 for left.

    private final Image[] animDown = new Image[4];

    private final Image[] animUp = new Image[4];
    private final Image[] animLeft = new Image[4];
    private final Image[] animRight = new Image[4];

    public Chicken() {
        loadAnims();
    }

    public Image getImage() {
        spriteCounter++;
        if(spriteCounter > 70){
            spriteNum++;
            spriteCounter = 0;
            if(spriteNum>3){
                spriteNum = 0;
            }
        }
        
        switch (direction) {
            case 2: return  animUp[spriteNum];
            case 3:  return animRight[spriteNum];
            case 4: return animDown[spriteNum];
            default: return animLeft[spriteNum];
        }
    }

    public int getShootDirection() {
        return shootDirection;
    }

    public void setShootDirection(int shootDirection) {
        this.shootDirection = shootDirection;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    private void loadAnims(){
        for(int i = 1;i<5;i++){
            animDown[i-1] = new ImageIcon("src/Images/animations/down/d"+i+".png").getImage();
            animUp[i-1] = new ImageIcon("src/Images/animations/up/u"+i+".png").getImage();
            animLeft[i-1] = new ImageIcon("src/Images/animations/left/l"+i+".png").getImage();
            animRight[i-1] = new ImageIcon("src/Images/animations/right/r"+i+".png").getImage();
        }
    }

}
