package ca.ubc.cs304.model;



/**
 * The intent for this class is to update/store information about the Game relation
 */
public class Game {
    private final int gid;
    private final int sid;
    private final int winTid;
    private final int loseTid;
    private final int lid


    public Game(int gid, int sid, int winTid, int loseTid, int lid) {
        this.tid = tid;
        this.sid = sid;
        this.winTid = winTid;
        this.loseTid = loseTid;
        this.lid = lid;
    }

    public int getTid() {
        return tid;
    }

    public int getSid() {
        return sid;
    }

    public int getWinTid() {
        return winTid;
    }

    public int getLoseTid() {
        return loseTid;
    }

    public int getLid() {
        return lid;
    }


}
