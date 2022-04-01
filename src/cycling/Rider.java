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

    /**
     * The hash map of the Stage ID and the stage instance
     */

    HashMap<Integer, LocalTime[]> stageTime = new HashMap<Integer, LocalTime[]>();

    /**
     * THe hash map of the segment ID and the finish time of the segment
     */

    HashMap<Integer, LocalTime> segmentTime = new HashMap<Integer, LocalTime>();

    /**
     * Rider: Create a rider entry
     * @param teamid The ID of the team that the rider belongs to
     * @param name The name of the rider
     * @param yob THe year of birth of the rider
     */

    public Rider(int teamid, String name, int yob){
        RiderName =  name;
        TeamID = teamid;
        YOB = yob;
        riderId = riderCount++;
    }

    /**
     *
     * @return The ID of the team that the rider belongs to
     */

    public int getTeamID(){
        return TeamID;
    }

    /**
     *
     * @return The ID of the rider
     */

    public int getRiderId(){
        return riderId;
    }

    /**
     * Map the stage ID and the finish time of the checkpoints in a stage
     * @param stageId The ID of the stage
     * @param time An array of time that the rider reach each checkpoint in the stage
     */

    public void addStageTime(int stageId, LocalTime... time){
        stageTime.put(stageId, time);
    }

    /**
     *
     * @param stageId The ID of the stage
     * @return An array of time that the rider finish at each checkpoint and the total elapsed time
     */

    public LocalTime[] getStageTime(int stageId){
        int z = stageTime.get(stageId).length;
        LocalTime[] x = new LocalTime[z];
        for(int i = 0; i < z - 1; i++){
            x[i] = stageTime.get(stageId)[i+1];
        }
        x[stageTime.get(stageId).length - 1] = LocalTime.ofNanoOfDay(stageTime.get(stageId)[z-1].until(stageTime.get(stageId)[0], ChronoUnit.NANOS));
        return x;
    }

    /**
     * Reset the rider ID
     */

    public static void resetRider(){
        riderCount = 0;
        points = 0;
        MountainPoints = 0;
    }

    /**
     * Remove the mapping of the stage time in the Hash map
     * @param stageIds The ID of the stage
     */

    public void removeStageTime(int stageIds){
        stageTime.remove(stageIds);
    }

    /**
     * Add the points to the rider
     * @param p Number of points to add
     */

    public void addPoint(int p){
        points = points + p;
    }

    /**
     *
     * @return The number of points of the rider
     */

    public int getPoints(){
        return points;
    }

    /**
     * Add the mountain points to the rider
     * @param p The mountain points to add
     */

    public void addMountainPoint(int p){
        MountainPoints = MountainPoints + p;
    }

    /**
     *
     * @return The mountain points of the rider
     */

    public int getMountainPoints(){
        return MountainPoints;
    }

    /**
     * Map the segment time to the segment ID
     * @param segmentId The ID of the segment
     * @param t The elapsed time of the rider to finish the segment
     */

    public void addSegmentTime(int segmentId, LocalTime t){
        segmentTime.put(segmentId, t);
    }

    /**
     *
     * @param segmentId The ID of the segment
     * @return The elapsed time of the rider to finish the segment
     */

    public LocalTime getSegmentTime(int segmentId){
        return segmentTime.get(segmentId);
    }

}

