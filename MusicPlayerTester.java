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

/**
 * A class that contains tester methods
 */
public class MusicPlayerTester {
    
    /**
     * Main method
     * @param args literally anything
     * @throws InterruptedException I don't even know where this came from
     */
    public static void main(String[] args) throws InterruptedException{
        System.out.println("testSongConstructor():  "+testSongConstructor());
        System.out.println("testSongPlayback():  "+testSongPlayback());
        System.out.println("testSongNode():  "+testSongNode());
        System.out.println("testEnqueue():  "+testEnqueue());
        System.out.println("testDequeue():  "+testDequeue());
    }

    /**
     * Checks the constructor of song class
     * @return true if implemented correctly, false if otherwise
     */
    public static boolean testSongConstructor(){
        MusicPlayerTester t=new MusicPlayerTester();
        if(!t.testValidSong()){
            System.out.println("Constructor doesn't work for valid songs!");
            return false;
        }
        
        if(!t.testInvalidSong()){
            System.out.println("Constructor doesn't work for invalid songs!");
            return false;
        }
        
        return true;
    }

    /**
     * private helper method that tests a valid implementation of Song
     * @return true if implemented correctly, false if otherwise
     */
    private boolean testValidSong(){
        Song a=new Song("Waterloo", "ABBA", "audio"+File.separator+"waterloo.mid");
        if(!a.getTitle().equals("Waterloo")){
            System.out.println("title is not properly initialized!");
            return false;
        }
        if(!a.getArtist().equals("ABBA")){
            System.out.println("artist is not properly initialized!");
            return false;
        }
        return true;
    }


    /**
     * Private helper method that tests an invalid implementation of Song
     * @return true if implemented correctly, false if otherwise
     */
    private boolean testInvalidSong(){
        try{
            Song a=new Song("Waterloo", "ABBA", "yourmom");
            return false;
        }
        catch(IllegalArgumentException e){
            return true;
        }
        catch(Exception e){
            System.out.println("Didn't throw IllegalArgumentException!");
            return false;
        }
    }

    /**
     * Tests audioUtility methods
     * @return true if implemented correctly, false if otherwise
     * @throws InterruptedException from the Thread
     */
    public static boolean testSongPlayback() throws InterruptedException{
        Song a=new Song("Waterloo", "ABBA", "audio"+File.separator+"waterloo.mid");
        a.play();
        Thread.sleep(1000);
        if(!a.isPlaying()){
            return false;
        }
        Thread.sleep(1000);
        a.stop();
        Thread.sleep(1000);
        if(a.isPlaying()){
            return false;
        }
        return true;
    }

    /**
     * Tests accessor/mutator of SongNode
     * @return true if implemented correctly, false if otherwise
     */
    public static boolean testSongNode(){
        try{
            Song a=new Song("Waterloo", "ABBA", "audio"+File.separator+"waterloo.mid");
            Song b=new Song("Waterloo2", "ABBA", "audio"+File.separator+"waterloo.mid");
            SongNode node=new SongNode(a);
            SongNode node2=new SongNode(b, node);
            SongNode node3=new SongNode(b);
            if(!node.getSong().equals(a)){
                System.out.println("getSong() isn't working!");
                return false;
            }
            if(!node2.getNext().equals(node)){
                System.out.println("getNext() is not working");
                return false;
            }
            node3.setNext(node2);
            if(!node3.getNext().equals(node2)){
                System.out.println("setNext() is not working");
                return false;
            }
            return true;
        }
        catch(Exception e){
            return false;
        }
        
    }

    /**
     * Tests enqueue method
     * @return true if implemented correctly, false if otherwise
     */
    public static boolean testEnqueue(){
        Playlist pl=new Playlist();
        Song a=new Song("Waterloo", "ABBA", "audio"+File.separator+"waterloo.mid");
        Song b=new Song("Waterloo2", "ABBA", "audio"+File.separator+"waterloo.mid");
        pl.enqueue(a);
        if(pl.size()!=1 || !pl.peek().equals(a)){
            System.out.println("enqueue() isn't working!");
            return false;
        }
        pl.enqueue(b);
        if(pl.size()!=2 || !pl.peek().equals(a)){
            System.out.println("enqueue() isn't working!");
            return false;
        }
        return true;
    }


    /**
     * Tests dequeue() method
     * @return true if implemented correctly, false if otherwise
     */
    public static boolean testDequeue(){
        try{
            Playlist pl=new Playlist();
            Song a=new Song("Waterloo", "ABBA", "audio"+File.separator+"waterloo.mid");
            Song b=new Song("Waterloo2", "ABBA", "audio"+File.separator+"waterloo.mid");
            pl.enqueue(a);
            pl.enqueue(b);
            Song c=pl.dequeue();
            if(!pl.peek().equals(b) || pl.size()!=1 || !c.equals(a)){
                System.out.println("dequeue() isn't working1!");
                return false;
            }
            c=pl.dequeue();
            if(pl.size()!=0 || !c.equals(b) || pl.peek()!=null){
                System.out.println(c);
                System.out.println(pl.size());
                System.out.println("dequeue() isn't working2!");
                return false;
            }
            return true;
        }
        catch(Exception e){
            return false;
        }
        
    }
}
