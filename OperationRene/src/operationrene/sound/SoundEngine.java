package operationrene.sound;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class SoundEngine {
    
    private static SoundEngine instance = null;
    
    private final String SOUND_PATH = "assets/sounds/";
    private final String MUSIC_PATH = "assets/musics/";
    
    private Music music;
    private Sound sound;
    private Sound finalSound;
    private boolean musicEnabled;
    private boolean soundEnabled;
    private float musicVolume;
    private float soundVolume;
    
    
    private SoundEngine(){
    
        this.music = null;
        this.finalSound = null;
        this.sound = null;
        this.musicEnabled = true;
        this.soundEnabled = true;
        this.musicVolume = (float) 0.3;
        this.soundVolume = (float) 1;
        
    }
    
    public static synchronized SoundEngine getIstance(){
        
        if(instance == null){
            
            instance = new SoundEngine();
            
        }
        
        return instance;
        
    }
    
    public synchronized void playBackgroundMusic(int track){
        
        if(this.musicEnabled){
        
            if(this.music != null){
                this.stopBackgroundMusic();
            }

            try {

                this.music = new Music(MUSIC_PATH + "music"+track+".ogg");
                this.music.play(1,this.musicVolume);

            } catch (SlickException ex) {
                Logger.getLogger(SoundEngine.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }
    
    public synchronized void playSoundEffect(int track){
        
        if(this.soundEnabled){
        
            try {

                if(track == SoundEffect.SOUND_CLOCK_TICK){
                    
                    this.finalSound = new Sound(SOUND_PATH + "sound"+track+".ogg");
                    this.finalSound.play(1,this.soundVolume);
                    
                }else if(track == SoundEffect.SOUND_CORRECT){
                    
                    sound = new Sound(SOUND_PATH + "sound"+SoundEffect.SOUND_PRESS_BUTTON+".ogg");
                    sound.play((float) 2,this.soundVolume);
                    
                }else if(track == SoundEffect.SOUND_LOSE){
                    
                    if(finalSound != null){
                        finalSound.stop();
                    }
                    
                    finalSound = new Sound(SOUND_PATH + "sound"+SoundEffect.SOUND_FBI+".ogg");
                    sound = new Sound(SOUND_PATH + "sound"+SoundEffect.SOUND_SIREN+".ogg");
                    sound.play(1,this.soundVolume);
                    finalSound.play(1,this.soundVolume);
                    
                    
                }else if(track == SoundEffect.SOUND_WIN){
                    
                    if(finalSound != null){
                        finalSound.stop();
                    }
                    
                    finalSound = new Sound(SOUND_PATH + "sound"+SoundEffect.SOUND_WIN+".ogg");
                    finalSound.play(1,this.soundVolume);
                    System.out.println("musica");
                    
                }else{
                
                    sound = new Sound(SOUND_PATH + "sound"+track+".ogg");
                    sound.play(1,this.soundVolume);
                    
                }

            } catch (SlickException ex) {
                Logger.getLogger(SoundEngine.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        
    }
    
    public synchronized void changeBackgroundMusic(int track){
        
        //vedere se si riesce a passare meglio da una canzone all'altra
           
    }
    
    public synchronized void stopBackgroundMusic(){
        
        if(this.music != null){
            this.music.stop();
        }
        this.music = null;
        
    }
    
    public synchronized void stopSoundEffect(){
        
        if(this.sound != null){
            this.sound.stop();
        }
        this.sound = null;
        
        if(this.finalSound != null){
            this.finalSound.stop();
        }
        this.finalSound = null;
        
    }
    
    public synchronized void setBackgroundMusicVolume(int volume){
        
        if (volume < 0){
            
            volume = 0;
            
        }else if(volume > 10){
            
            volume = 10;
            
        }
               
        this.music.setVolume(((float)volume)/10);
        
    }
    
    public synchronized void enabledBackgroudMusic(boolean isEnabled){
        
        this.musicEnabled = isEnabled;
        
        if(!this.musicEnabled){
            
            this.stopBackgroundMusic();
            
        }
        
    }
    
    public synchronized void enabledSoundEffect(boolean isEnabled){
        
        this.soundEnabled = isEnabled;
        
    }
    
}
