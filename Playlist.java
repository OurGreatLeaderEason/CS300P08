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
 * A FIFO linked queue of SongNodes, conforming to our QueueADT interface.
 */
public class Playlist {
    private SongNode first;
    private SongNode last;
    private int numSongs;

    /**
     * Constructs a new, empty playlist queue
     */
    public Playlist(){
        this.first=null;
        this.last=null;
        this.numSongs=0;
    }

    /**
     * Adds a new song to the end of the queue
     * @param element the song to add to the Playlist
     */
    public void enqueue(Song element){
        SongNode node=new SongNode(element);
        if(this.numSongs==0){
            this.first=node;
            this.last=node;
        }
        else{
            this.last.setNext(node);
            this.last=node;
            this.last.setNext(null);
        }
        this.numSongs++;
    }

    /**
     * Removes the song from the beginning of the queue
     * @return the song that was removed from the queue, or null if the queue is empty
     */
    public Song dequeue(){
        if(this.numSongs==0){
            return null;
        }
        else if(this.numSongs==1){
            Song n=this.first.getSong();
            this.first=null;
            this.last=null;
            this.numSongs--;
            return n;
        }
        else{
            Song n=this.first.getSong();
            this.first=this.first.getNext();
            this.numSongs--;
            return n;
        }
        
      
    }

    /**
     * Returns the song at the front of the queue without removing it
     * @return the song that is at the front of the queue, or null if the queue is empty
     */
    public Song peek(){
        if(this.numSongs==1){
            return this.last.getSong();
        }
        else if(this.numSongs==0){
            return null;
        }
        else{
            return this.first.getSong();
        }
    }

    /**
     * Returns true if and only if there are no songs in this queue
     * @return true if this queue is empty, false otherwise
     */
    public boolean isEmpty(){
        if(this.numSongs==0){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Returns the number of songs in this queue
     * @return the number of songs in this queue
     */
    public int size(){
        return this.numSongs;
    }

    /**
     * Creates and returns a formatted string representation of this playlist, with the string version of each song in the list on a separate line.
     * @return the string representation of this playlist
     */
    @Override
    public String toString(){
        int size=this.size();
        Song[] temp=new Song[size];
        for(int i=0; i<size; i++){
            Song n=this.dequeue();
            temp[i]=n;
        }
        for(int i=0; i<temp.length; i++){
            this.enqueue(temp[i]);
        }
        String ret="";
        for(int i=0; i<temp.length; i++){
            ret=ret+"\n"+temp[i].toString();
        }
        return ret;
    }
}
