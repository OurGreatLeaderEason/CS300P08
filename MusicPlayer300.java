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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * A linked-queue based music player which plays Actual Music Files based on keyboard input in an interactive console method.
 * This music player can load playlists of music or add individual song files to the queue.
 */
public class MusicPlayer300 {
    private String filterArtist;
    private boolean filterPlay;
    private Playlist playlist;

    /**
     * Stops any song that is playing and clears out the playlist
     */
    public MusicPlayer300(){
        this.playlist=new Playlist();
    }

    /**
     * Loads a playlist from a provided file, skipping any individual songs which cannot be loaded. Note that filenames in the provided files do NOT include the audio directory, and will need that added before they are loaded.
     * Print "Loading" and the song's title in quotes before you begin loading a song, and an "X" if the load was unsuccessful for any reason.
     * @param file the File object to load
     * @throws FileNotFoundException if the playlist file cannot be loaded
     */
    public void loadPlaylist(File file) throws FileNotFoundException{
        try{
            Scanner fileReader=new Scanner(file);
            while (fileReader.hasNextLine()){
                String song=fileReader.nextLine();
                String[] components=song.split(",", 3);
                this.loadASong(components[0], components[1], components[2]);
            }
            fileReader.close();
        }
        catch(Exception e){
            System.out.println("Unable to load file!");
        }
    }

    /**
     * Loads a single song to the end of the playlist given the title, artist, and filepath. Filepaths for P08 must refer to files in the audio directory.
     * @param title the title of the song
     * @param artist the artist of this song
     * @param filepath the full relative path to the song file, begins with the "audio" directory for P08
     * @throws IllegalArgumentException if the song file cannot be read
     */
    public void loadOneSong(String title, String artist, String filepath) throws IllegalArgumentException{
        try{
            this.playlist.enqueue(new Song(title, artist, filepath));
        }
        catch(Exception e){
            System.out.println("Unable to load that song!");
        }
    }

    /**
     * Loads a single song to the end of the playlist given the title, artist, and filepath. Filepaths for P08 must refer to files in the audio directory.
     * @param title the title of the song
     * @param artist the artist of this song
     * @param filepath the full relative path to the song file, begins with the "audio" directory for P08
     * @throws IllegalArgumentException if the song file cannot be read
     */
    private void loadASong(String title, String artist, String filepath) throws IllegalArgumentException{
        try{
            this.playlist.enqueue(new Song(title, artist, "audio"+File.separator+filepath));
            char aznable='"';
            System.out.println("Loading: "+aznable+title+aznable);
        }
        catch(Exception e){
            System.out.println("X");
        }
          
        
    }

    /**
     * Provides a string representation of all songs in the current playlist
     * @return a string representation of all songs in the current playlist
     */
    public String printPlaylist(){
        return this.playlist.toString();
    }

    /**
     * Stops playback of the current song (if one is playing) and advances to the next song in the playlist.
     * @throws IllegalStateException if the playlist is null or empty, or becomes empty at any time during this method
     */
    public void playNextSong() throws IllegalStateException{
        if(this.playlist.size()<=1){
            throw new IllegalStateException("No more songs in playlist");
        }
        else{
            this.playlist.dequeue().stop();
            if(this.filterPlay){
                if(this.playlist.peek().getArtist().equals(this.filterArtist)){
                    this.playlist.peek().play();
                }
                else{
                    this.playlist.dequeue();
                }
            }
            else{
                this.playlist.peek().play();
            }
        }
    }

    /**
     * Creates and returns the menu of options for the interactive console program.
     * @return the formatted menu String
     */
    public String getMenu(){
        String str="";
        str+="Enter one of the following options:\n";
        str+="[A <filename>] to enqueue a new song file to the end of this playlist\n";
        str+="[F <filename>] to load a new playlist from the given file\n";
        str+="[L] to list all songs in the current playlist\n";
        str+="[P] to start playing ALL songs in the playlist from the beginning\n";
        str+="[P -t <Title>] to play all songs in the playlist starting from <Title>\n";
        str+="[P -a <Artist>] to start playing only the songs in the playlist by Artist\n";
        str+="[N] to play the next song\n";
        str+="[Q] to stop playing music and quit the program\n";
        return str;
    }

  
    /**
     * Interactive method to display the MusicPlayer300 menu and get keyboard input from the user. See writeup for details.
     * @param in The scanner, or should I say scammer. 
     */
    public void runMusicPlayer300(Scanner in){
        boolean end=false;
        Song currentSong=null;
        while(end==false){
            try{
                System.out.println("\n"+this.getMenu());
                System.out.print(">");
                String input=in.nextLine();
                if(input.charAt(0)==('F')){
                    String filename=input.substring(2, input.length());
                    this.loadPlaylist(new File(filename));
                }
                if(input.charAt(0)==('A')){
                    String filename=input.substring(2, input.length());
                    System.out.print("Title: ");
                    String title=in.next();
                    System.out.print("Artist: ");
                    String artist=in.next();
                    this.loadOneSong(title, artist, filename);
                }
                if(input.charAt(0)==('L')){
                    System.out.println(this.printPlaylist());
                }
                if(input.charAt(0)==('P')){
                    int count = 0;
                    for(int e = 0; e < input.length(); e++){
                        if(input.charAt(e) != ' '){
                            count++;
                            while(input.charAt(e) != ' ' && e < input.length()-1){
                                e++;
                            }
                        }
                    }
                    if(count==1){

                        while(!this.playlist.isEmpty()){
                            Song s=this.playlist.dequeue();
                            s.play();
                            MusicPlayer300 mew=new MusicPlayer300();
                            Thread.sleep(mew.getDuration(s.toString()));
                        }
                        System.out.println("No songs left :(");
                        this.runMusicPlayer300(in);
                    }
                    else{
                        char aznable=input.charAt(3);
                        if(aznable=='a'){
                            this.filterArtist=input.substring(5, input.length());
                            while(!this.playlist.isEmpty()){
                                Song s=this.playlist.dequeue();
                                if(s.getArtist().equals(this.filterArtist)){
                                    s.play();
                                    MusicPlayer300 mew=new MusicPlayer300();
                                    Thread.sleep(mew.getDuration(s.toString()));
                                }
                            }
                            System.out.println("No songs left :(");
                            this.runMusicPlayer300(in);
                        }
                        if(aznable=='t'){
                            String title=input.substring(5, input.length());
                            this.filterPlay=false;
                            while(!this.playlist.isEmpty()){
                                Song s=this.playlist.dequeue();
                                if(s.getTitle().equals(title)){
                                    this.filterPlay=true;
                                }
                                if(this.filterPlay==true){
                                    s.play();
                                    MusicPlayer300 mew=new MusicPlayer300();
                                    Thread.sleep(mew.getDuration(s.toString()));
                                }
                            }
                            System.out.println("No songs left :(");
                            this.runMusicPlayer300(in);
                        }
                    }
                }
                if(input.charAt(0)=='N'){
                   if(currentSong==null){
                        currentSong=this.playlist.dequeue();
                        currentSong.play();
                   }
                   else if(this.playlist.isEmpty()){
                        currentSong.stop();
                        System.out.println("No more songs:(");
                    }
                    else{
                        currentSong.stop();
                        currentSong=this.playlist.dequeue();
                        currentSong.play();
                    }
                }
                if(input.charAt(0)=='Q'){
                    System.out.println("Goodbye!");
                    break;
                }
            }
            catch(Exception e){
                this.runMusicPlayer300(in);
            }
        }
    }

    /**
     * Main method
     * @param args literally just anything
     */
    public static void main(String[] args){
        MusicPlayer300 mew=new MusicPlayer300();
        Scanner kbReader=new Scanner(System.in);
        mew.runMusicPlayer300(kbReader);
    }

    /**
     * Private helper method to get a song's duration
     * @param s the string representation of the song
     * @return duration of said song, in miliseconds
     */
    private int getDuration(String s){
        char[] pain=new char[s.length()];
        for(int i=0; i<pain.length; i++){
            pain[i]=s.charAt(i);
        }
        boolean isSec=false;
        char[] nums=new char[pain.length];
        int index=0;
        for(int i=0; i<pain.length; i++){
            if(Character.isDigit(pain[i]) || pain[i]==(':')){
                nums[index]=pain[i];
                index++;
            }
        }
        String minute="";
        String second="";
        for(int i=0; i<index; i++){
            if(nums[i]==':'){
                isSec=true;
            }
            if(!isSec && Character.isDigit(nums[i])){
                minute+=nums[i];
            }
            if(isSec && Character.isDigit(nums[i])){
                second+=nums[i];
            }
        }
        int minutes=Integer.valueOf(minute);
        int seconds=Integer.valueOf(second);
        int duration=minutes*60+seconds;
        return duration*1000;
    }

    /**
     * Stops any song that is playing and clears out the playlist
     */
    public void clear(){
        while(!this.playlist.isEmpty()){
            Song s=this.playlist.dequeue();
            s.stop();
        }
    }

}
