package Loaders;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.FileNotFoundException;

public class SoundLoader {

    // Audio clips
    public AudioClip clickSound;
    public AudioClip backgroundMusic;

    public AudioClip jumpSound;
    public AudioClip coughtSound;
    public AudioClip awardSound;
    public AudioClip gameOverSound;

    public AudioClip slideSound;
    public AudioClip citySound;
    public AudioClip gameMusic;

    // Path to audio clip, ERROR can occur here
    public final String CLICK_SOUND_PATH = "src/lib/sound/click2.wav";
    public final String BACKGROUND_mENU_SOUND_PATH = "src/lib/sound/backgroudnMenu.mp3";

    public final String JUMP_SOUND = "src/lib/sound/jump.wav";
    public final String SLIDE_SOUND = "src/lib/sound/slide.mp3";

    public final String CITY_SOUND = "src/lib/sound/citySound.wav";
    public final String GAME_MUSIC_SOUND = "src/lib/sound/gameMusic.mp3";
    public final String COUGHT_MUSIC_SOUND = "src/lib/sound/cought.mp3";

    public final String AWARD_MUSIC_SOUND = "src/lib/sound/award2.mp3";
    public final String GAME_OVER_MUSIC_SOUND = "src/lib/sound/game_over.mp3";

    // init menu sound
    public void initMenuSound() {
        try {
            clickSound = new AudioClip(new File(CLICK_SOUND_PATH).toURI().toString());

            backgroundMusic = new AudioClip(new File(BACKGROUND_mENU_SOUND_PATH).toURI().toString());
            backgroundMusic.setCycleCount(MediaPlayer.INDEFINITE);
            backgroundMusic.setVolume(60);

        } catch (ArithmeticException e) {
            System.out.println(e);
        }
    }

    // init game sound
    public void initGameSound() {
        jumpSound = new AudioClip(new File(JUMP_SOUND).toURI().toString());
        coughtSound = new AudioClip(new File(COUGHT_MUSIC_SOUND).toURI().toString());
        slideSound = new AudioClip(new File(SLIDE_SOUND).toURI().toString());
        awardSound = new AudioClip(new File(AWARD_MUSIC_SOUND).toURI().toString());
        gameOverSound = new AudioClip(new File(GAME_OVER_MUSIC_SOUND).toURI().toString());

        citySound = new AudioClip(new File(CITY_SOUND).toURI().toString());
        System.out.println(citySound);
        citySound.setVolume(70);
        citySound.setCycleCount(AudioClip.INDEFINITE);


        gameMusic = new AudioClip(new File(GAME_MUSIC_SOUND).toURI().toString());
        gameMusic.setVolume(85);
        gameMusic.setCycleCount(AudioClip.INDEFINITE);

    }




}
