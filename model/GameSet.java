package ca.ubc.cs304.model;



/**
 * The intent for this class is to update/store information about the GameSet relation
 */
public class GameSet {
    private final int setNumber;
    private final int winnerScore;
    private final int loserScore;
    private final int gid;


    public GameSet(int setNumber, int winnerScore, int loserScore, int gid) {
        this.setNumber = setNumber;
        this.winnerScore = winnerScore;
        this.loserScore = loserScore;
        this.gid = gid;
    }

    public int getSetNumber(){
        return setNumber;
    }
    public int getWinnerScore() {
        return winnerScore;
    }
    public int getLoserScore() {
        return loserScore;
    }
    public int getGid() {
        return gid;
    }


}
