package cycling;

public class race {
    int raceID;
    String RaceName;
    String RaceDescription;

    public race(String name, String description){
        RaceName = name;
        RaceDescription = description;
    }

    public int getRaceID(){
        return raceID;
    }

    public String getName(){
        return RaceName;
    }

    public String getRaceDescription(){
        return RaceDescription;
    }

    public String toString(){
        return String.format("[Name = " + RaceName + ", description = " + RaceDescription + ", stages = ");
    }

    public String getRace(){
        return String.format("%d", "%s", getRaceID(), toString());
    }


}
