


import java.awt.*;
import javax.swing.*;

public class Game extends JPanel implements Runnable{

    private final Bullet[] bullets = new Bullet[5];
    private final Enemy[] enemies = new Enemy[5];
    private final Aims[] aims = new Aims[3];
    private final Image bg,egg,cat,nest;
    private final Chicken chicken;
    private final Point point;
    private final Level level;
    public Game(){
        this.setFocusable(true);
        this.setVisible(true);
        bg = new ImageIcon("src/Images/bg.png").getImage();
        egg = new ImageIcon("src/Images/egg.png").getImage();
        cat = new ImageIcon("src/Images/cat.png").getImage();
        nest = new ImageIcon("src/Images/nest.png").getImage();
        Sound sound = new Sound();
        chicken = new Chicken();
        point = new Point();
        level = new Level(sound);
        int frameW = 800;
        int frameH = 640;
        this.setPreferredSize(new Dimension(frameW, frameH));
        new KeyPicker(getInputMap(WHEN_IN_FOCUSED_WINDOW), getActionMap(), chicken, level,bullets);
        for(int i = 0;i<enemies.length;i++) enemies[i] = new Enemy(chicken,level);
        for(int i = 0;i<aims.length;i++) aims[i] = new Aims(bullets,point,level);
        Thread thread = new Thread(this);
        thread.start();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.yellow);
        g.setFont( new Font("Ink Free",Font.BOLD, 40));

        draw(g);
    }

    public void draw(Graphics g){
        g.drawImage(bg,0,0,800,640,null);
        g.drawImage(chicken.getImage(),chicken.getPosX(),chicken.getPosY(),40,40,null);
        for(int i = 0;i<bullets.length;i++){
            g.drawImage(egg,bullets[i].getBulletX(),bullets[i].getBulletY(),25,25,null);
            g.drawImage(cat,enemies[i].getRandX(),enemies[i].getRandY(),50,50,null);
        }
        for (Aims aim : aims) {
            g.drawImage(nest, aim.getRandX(), aim.getRandY(), aim.getWidth(), aim.getHeight(), null);
        }
        g.drawString("Level: "+level.getLevel(), 220, 40);
        g.drawString("Score: "+point.getPoints(), 450, 40);
        if(level.getLevel() == 0){
            g.drawString("You Died!",320,300);
            g.drawString("Press 'Up' to restart...",230,400);
        }
    }


     @Override
     public void run() {
         while(true){
             if(level.getLevel() != 0){
                 repaint();
             }else {
                 point.setPoints(0);
             }
         }
     }



}
