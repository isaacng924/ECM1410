package cycling;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

public class Rider {
    int riderId;
    static int riderCount = 0;
    String RiderName;
    int YOB;
    int TeamID;

    HashMap<Integer, LocalTime[]> stageTime = new HashMap<Integer, LocalTime[]>();

    public Rider(int teamid, String name, int yob){
        RiderName =  name;
        TeamID = teamid;
        YOB = yob;
        riderId = riderCount++;
    }

    public int getTeamID(){
        return TeamID;
    }

    public int getRiderId(){
        return riderId;
    }

    public void addStageTime(int stageId, LocalTime... time){
        stageTime.put(stageId, time);
    }

    public LocalTime[] getStageTime(int stageId){
        int z = stageTime.get(stageId).length;
        LocalTime[] x = new LocalTime[z];
        for(int i = 0; i < z - 1; i++){
            x[i] = stageTime.get(stageId)[i+1];
        }
        x[stageTime.get(stageId).length - 1] = LocalTime.ofNanoOfDay(stageTime.get(stageId)[z-1].until(stageTime.get(stageId)[0], ChronoUnit.NANOS));
        return x;
    }

    public void removeStageTime(int stageIds){
        stageTime.remove(stageIds);
    }

    public LocalTime getElapsedTime(int stageId){
        return getStageTime(stageId)[getStageTime(stageId).length - 1];
    }

}

