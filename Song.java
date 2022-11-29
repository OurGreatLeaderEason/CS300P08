////////////////MusicPlayer300//////////////////////////
//
//Title:    P08 MusicPlayer300
//Course:   CS 300 Fall 2022
//
//Author:   Eason Xiao
//Email:    xiao227@wisc.edu
//Lecturer: Jeff Nyhoff
//
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * A representation of a single Song. Interfaces with the provided AudioUtility class, which uses the javax.sound.sampled package to play audio to your computer's audio output device
 */
public class Song{
    private String artist;
    private AudioUtility audioClip;
    private int duration;
    private String title;

    /**
     * Initializes all instance data fields according to the provided values
     * @param title the title of the song, set to empty string if null
     * @param artist the artist of this song, set to empty string if null
     * @param filepath the full relative path to the song file, begins with the "audio" directory for P08
     * @throws IllegalArgumentException if the song file cannot be read
     */
    public Song(String title, String artist, String filepath) throws IllegalArgumentException{
        try {
            this.audioClip=new AudioUtility(filepath);
            this.artist=artist;
            this.title=title;
            this.duration=this.audioClip.getClipLength();
        } 
        catch (IOException e) {
            throw new IllegalArgumentException("Invalid file path!");
        }
        
    }

    /**
     * Tests whether this song is currently playing using the AudioUtility
     * @return true if the song is playing, false otherwise
     */
    public boolean isPlaying(){
        return this.audioClip.isRunning();
    }

    /**
     * Accessor method for the song's title
     * @return the title of this song
     */
    public String getTitle(){
        return this.title;
    }

    /**
     * Accessor method for the song's artist
     * @return the artist of this song
     */
    public String getArtist(){
        return this.artist;
    }

    /**
     * Uses the AudioUtility to start playback of this song, reopening the clip for playback if necessary
     */
    public void play(){
        if(!this.audioClip.isReadyToPlay()){
            this.audioClip.reopenClip();
            this.audioClip.startClip();
        }
        else{
            this.audioClip.startClip();
        }
        System.out.println("Playing "+this.toString());
    }

    /**
     * Uses the AudioUtility to stop playback of this song
     */
    public void stop(){
        this.audioClip.stopClip();
    }

    /**
     * Creates and returns a string representation of this Song, for example:
     * "Africa" (4:16) by Toto
     * The title should be in quotes, the duration should be split out into minutes and seconds (recall it is stored as seconds only!), and the artist should be preceded by the word "by".
     * It is intended for this assignment to leave single-digit seconds represented as 0:6, for example, but if you would like to represent them as 0:06, this is also allowed.
     * @return a formatted string representation of this Song
     */
    @Override
    public String toString(){
        int minutes=duration/60;
        int seconds=duration%60;
        char ch='"';
        return ch+this.title+ch+" ("+minutes+":"+seconds+") by "+this.artist;
    }

    
}