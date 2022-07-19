
import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
public class KeyPicker {

    Bullet[] bullets;
    public static final int animationDelay = 10;
    public static final int chickW = 40;
    public static final int chickH = chickW;
    private static final String PRESSED = "pressed";
    private static final String RELEASED = "released";
    private final Map<Keys, Boolean> keysMap = new EnumMap<>(Keys.class);
    private int chickX = 0;

    private boolean isStart = true;
    private int chickY = 0;
    private final InputMap inputMap;
    private final ActionMap actionMap;
    private final Chicken chicken;
    private final Level level;
    public KeyPicker(InputMap inputMap, ActionMap actionMap, Chicken chicken, Level level, Bullet[] bullets) {
        this.level = level;
        this.chicken = chicken;
        this.inputMap = inputMap;
        this.actionMap = actionMap;
        this.bullets = bullets;
        for (Keys key : Keys.values()) {
            keysMap.put(key, Boolean.FALSE);
        }
        setKeys();
        Timer animationTimer = new Timer(animationDelay, new AnimsListener());
        animationTimer.start();
    }

    private void setKeys() {
        for(int i = 0;i<bullets.length;i++){
            bullets[i] = new Bullet(chicken);
        }
        for (Keys key : Keys.values()) {
            KeyStroke keyPressed = KeyStroke.getKeyStroke(key.getEventCode(), 0, false);
            KeyStroke keyReleased = KeyStroke.getKeyStroke(key.getEventCode(), 0, true);

            inputMap.put(keyPressed, key + PRESSED);
            inputMap.put(keyReleased, key + RELEASED);

            actionMap.put(key + PRESSED, new KeyAction(key, true));
            actionMap.put(key + RELEASED, new KeyAction(key, false));
        }

    }

    private class AnimsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int newX,newY;
            if(isStart){
                newX = 350;
                newY = 100;
                isStart = false;
            }else{
                newX = chickX;
                newY = chickY;
            }
            for (Keys key : Keys.values()) {
                if (keysMap.get(key)) {
                    if(!key.getName().equals("Space")){
                        newX += key.getMoveX() * 2;
                        newY += key.getMoveY() * 2;
                    }else{
                        for (Bullet bullet : bullets) {
                            if (!bullet.getIsShot()) {
                                bullet.setIsShot(true);
                                bullet.aimShot();
                                break;
                            }
                            break;
                        }
                    }
                }
            }
            if (newX < 0 || newY < 0) {
                return;
            }
            if (newX + chickW > 800 || newY + chickH > 640) {
                return;
            }
            chicken.setPosX(newX);
            chicken.setPosY(newY);
            if(chickX == newX && chickY == newY){
                if(chicken.getShootDirection() == 1)
                    chicken.setDirection(1);
                else
                    chicken.setDirection(3);
            }
            chickX = newX;
            chickY = newY;
        }
    }


    private class KeyAction extends AbstractAction {

        private final Boolean isPressed;
        private final Keys key;

        public KeyAction(Keys key, Boolean isPressed) {
            this.key = key;
            this.isPressed = isPressed;
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            if (isPressed) {
                keysMap.put(key, Boolean.TRUE);
                switch (key.getName()) {
                    case "Left": 
                        chicken.setDirection(1); //1 for Left for animation.
                        chicken.setShootDirection(1); //1 for Left for shoot.
                        break;
                    case "Right":
                        chicken.setDirection(3);//3 for Left for animation.
                        chicken.setShootDirection(2);//2 for Right for shoot.
                        break;

                    case "Up":
                        if (level.getLevel() == 0) {
                            level.setLevel(1);
                            break;
                        }
                        chicken.setDirection(2); //2 for Up.
                    
                    case "Down": 
                        chicken.setDirection(4); //4 for Down.
                        break;
                }
            } else{
                keysMap.put(key, Boolean.FALSE);
            }
        }

    }

}
