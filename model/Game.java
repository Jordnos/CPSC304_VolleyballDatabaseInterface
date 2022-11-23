package ca.ubc.cs304.model;



/**
 * The intent for this class is to update/store information about the Game relation
 */
public class Game extends Relation{
    private final String relationName = "Game";
    private final String[] keyNames = {"gid"};
    private final String[] attributeNames = {"sid","winTid","loseTid","lid"};
    private final int gid;
    private final int sid;
    private final int winTid;
    private final int loseTid;
    private final int lid;


    public Game(int gid, int sid, int winTid, int loseTid, int lid) {
        this.gid = gid;
        this.sid = sid;
        this.winTid = winTid;
        this.loseTid = loseTid;
        this.lid = lid;
    }

    public Game() {
        this.gid = 0;
        this.sid = 0;
        this.winTid = 0;
        this.loseTid = 0;
        this.lid = 0;
    }

    public int getGid() {
        return gid;
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
    @Override
    public String getRelationName() {
        return relationName;
    }

    @Override
    public String[] getKeyNames() {
        return keyNames;
    }

    @Override
    public String[] getNonKeyNames() {
        return attributeNames;
    }


}
