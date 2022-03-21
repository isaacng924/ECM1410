package cycling;

public class Rider {
    String RiderName;
    int YOB;
    int TeamID;

    public Rider(int teamid, String name, int yob){
        RiderName =  name;
        TeamID = teamid;
        YOB = yob;
    }

    public int getTeamID(){
        return TeamID;
    }

}
