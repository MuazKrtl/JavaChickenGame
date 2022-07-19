import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {
    private Clip clip;
    private final URL[] soundUrl = new URL[2];

    public Sound() {
        soundUrl[0] = getClass().getResource("Musics/main.wav"); //Game Music
        soundUrl[1] = getClass().getResource("Musics/game_over.wav"); //GameOver Effect
    }
    public void setFile(int i){
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundUrl[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void play(){
        clip.start();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }

    public void playMusic(int i){
        this.setFile(i);
        this.play();
        this.loop();
    }
    public void stopMusic(){
        this.stop();
    }
    public void playEffects(int i){
        this.setFile(i);
        this.play();
    }

}
