package cycling;

import java.io.Serializable;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

public class Rider implements Serializable {
    int riderId;
    static int riderCount = 0;
    String RiderName;
    int YOB;
    int TeamID;
    static int points = 0;
    static int MountainPoints = 0;

    HashMap<Integer, LocalTime[]> stageTime = new HashMap<Integer, LocalTime[]>();
    HashMap<Integer, LocalTime> segmentTime = new HashMap<Integer, LocalTime>();

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
    public static void resetRider(){
        riderCount = 0;
        points = 0;
        MountainPoints = 0;
    }

    public void removeStageTime(int stageIds){
        stageTime.remove(stageIds);
    }

    public void addPoint(int p){
        points = points + p;
    }

    public int getPoints(){
        return points;
    }

    public void addMountainPoint(int p){
        MountainPoints = MountainPoints + p;
    }

    public int getMountainPoints(){
        return MountainPoints;
    }

    public void addSegmentTime(int segmentId, LocalTime t){
        segmentTime.put(segmentId, t);
    }

    public LocalTime getSegmentTime(int segmentId){
        return segmentTime.get(segmentId);
    }

}

