package com.example.aimtrener;

import javafx.scene.media.*;

import java.io.File;


public class sound {
    static Media media;
    static MediaPlayer mediaPlayer;
    public static void musicPlay(String songName) {
        File song = new File(songName);
        media = new Media(song.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }
    public static void musicPause() {
        mediaPlayer.pause();
    }
    public static void musicContinue() {
        mediaPlayer.play();
    }
    public static void musicStop() {
        if (media != null){
            mediaPlayer.pause();
        }
    }
}
