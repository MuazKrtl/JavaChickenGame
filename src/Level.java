public class Level {
    private int level = 1;
    private final Sound sound;

    public Level(Sound sound) {
        this.sound = sound;
        sound.playMusic(0);
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
        if(level == 0){
            sound.stopMusic();
            sound.playEffects(1);
        }else if(level == 1){
            sound.stopMusic();
            sound.playMusic(0);
        }
    }
}
