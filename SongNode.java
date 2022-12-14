////////////////MusicPlayer300//////////////////////////
//
//Title:    P08 MusicPlayer300
//Course:   CS 300 Fall 2022
//
//Author:   Eason Xiao
//Email:    xiao227@wisc.edu
//Lecturer: Jeff Nyhoff
//

/**
 * A singly-linked node for our linked queue, which contains a Song object
 */
public class SongNode {
    private SongNode next;
    private Song song;

    /**
     * Constructs a single SongNode containing the given data, not linked to any other SongNodes
     * @param data the Song for this node
     * @throws IllegalArgumentException if data is null
     */
    public SongNode(Song data) throws IllegalArgumentException{
        if(data==null){
            throw new IllegalArgumentException("Song is null!");
        }
        this.song=data;
    }

    /**
     * Constructs a single SongNode containing the given data, linked to the specified SongNode
     * @param data the Song for this node
     * @param next the next node in the queue
     * @throws IllegalArgumentException if data is null
     */
    public SongNode(Song data, SongNode next) throws IllegalArgumentException{
        if(data==null){
            throw new IllegalArgumentException("Song is null!");
        }
        this.song=data;
        this.next=next;
    }

    /**
     * Accessor method for this node's data
     * @return the Song in this node
     */
    public Song getSong(){
        return this.song;
    }

    /**
     * Accessor method for the next node in the queue
     * @return the SongNode following this one, if any
     */
    public SongNode getNext(){
        return this.next;
    }

    /**
     * Changes the value of this SongNode's next data field to the given value
     * @param next the SongNode to follow this one; may be null
     */
    public void setNext(SongNode next){
        this.next=next;
    }
}
