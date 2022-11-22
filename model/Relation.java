package ca.ubc.cs304.model;

public abstract class Relation {

    private final static String[] allRelationNames = {"BMI", "CoachExperience", "Country", "Experience", "Game",
                                            "GameSet", "HeadCoach", "League", "Libero", "LiberoBMI",
                                            "MiddleBlocker", "MiddleBlockerBMI", "OutsideHitter", "OutsideHitterBMI",
                                            "Ref", "RefsFor", "ServerSpecialist", "ServerSpecialistBMI",
                                            "Setter", "SetterBMI", "Stadium", "StadiumAddress",
                                            "StadiumName", "Team", "WorksFor"};

    /*
    private final Relation[] allRelations = {new Bmi(), new CoachExperience(), new Country(), new Experience(), new Game(),
                                    new GameSet(), new HeadCoach(), new League(), new Libero(), new LiberoBMI(),
                                    new MiddleBlocker(), new MiddleBlockerBMI(), new OutsideHitter(), new OutsideHitterBMI(),
                                    new Ref(), new RefsFor(), new ServerSpecialist(), new ServerSpecialistBMI(),
                                    new Setter(), new SetterBMI(), new Stadium(), new StadiumAddress(),
                                    new StadiumName(), new Team(), new WorksFor()};
    */

    public static String[] getAllRelationNames(){
        return allRelationNames;
    }
    abstract public String getRelationName();
    abstract public String[] getKeyNames();
    abstract public String[] getNonKeyNames();
}
