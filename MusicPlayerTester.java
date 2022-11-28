public class MusicPlayerTester {
    
    public static void main(String[] args) throws InterruptedException{
        System.out.println("testSongConstructor():  "+testSongConstructor());
        System.out.println("testSongPlayback():  "+testSongPlayback());
        System.out.println("testSongNode():  "+testSongNode());
        System.out.println("testEnqueue():  "+testEnqueue());
        System.out.println("testDequeue():  "+testDequeue());
    }
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

    private boolean testValidSong(){
        Song a=new Song("Waterloo", "ABBA", "audio\\waterloo.mid");
        if(!a.getTitle().equals("Waterloo")){
            System.out.println("title is not properly initialized!");
            return false;
        }
        if(!a.getArtist().equals("ABBA")){
            System.out.println("artist is not properly initialized!");
            return false;
        }
        if(!a.toString().equals("\"Waterloo\" (2:45) by ABBA")){
            System.out.println("toString() is not working");
            return false;
        }
        return true;
    }

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

    public static boolean testSongPlayback() throws InterruptedException{
        Song a=new Song("Waterloo", "ABBA", "audio\\waterloo.mid");
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

    public static boolean testSongNode(){
        Song a=new Song("Waterloo", "ABBA", "audio\\waterloo.mid");
        Song b=new Song("Waterloo2", "ABBA", "audio\\waterloo.mid");
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


    public static boolean testEnqueue(){
        Playlist pl=new Playlist();
        Song a=new Song("Waterloo", "ABBA", "audio\\waterloo.mid");
        Song b=new Song("Waterloo2", "ABBA", "audio\\waterloo.mid");
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
        System.out.println(pl);
        return true;
    }


    public static boolean testDequeue(){
        Playlist pl=new Playlist();
        Song a=new Song("Waterloo", "ABBA", "audio\\waterloo.mid");
        Song b=new Song("Waterloo2", "ABBA", "audio\\waterloo.mid");
        pl.enqueue(a);
        pl.enqueue(b);
        Song c=pl.dequeue();
        if(!pl.peek().equals(b) || pl.size()!=1 || !c.equals(a)){
            System.out.println("dequeue() isn't working!");
            return false;
        }
        c=pl.dequeue();
        if(pl.size()!=0 || !c.equals(b)){
            System.out.println("dequeue() isn't working!");
            return false;
        }
        return true;
    }
}
