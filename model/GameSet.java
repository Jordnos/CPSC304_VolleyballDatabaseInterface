package ca.ubc.cs304.model;



/**
 * The intent for this class is to update/store information about the GameSet relation
 */
public class GameSet extends Relation{
    private final String relationName = "GameSet";
    private final String[] keyNames = {"setNumber","gid"};
    private final String[] attributeNames = {"winnerScore","loserScore"};
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

    public GameSet() {
        this.setNumber = 0;
        this.winnerScore = 0;
        this.loserScore = 0;
        this.gid = 0;
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
