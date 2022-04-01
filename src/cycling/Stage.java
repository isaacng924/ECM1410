package cycling;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Stage implements Serializable {
    static int StageCount = 0;
    int stageId;
    String stageName;
    String description;
    double length;
    LocalDateTime startTime;
    StageType Type;
    public boolean prepared = false;
    public ArrayList<Segment> segments = new ArrayList<>();

    /**
     * Stage: Create a stage entry
     * @param name The name of the stage
     * @param des The description of the stage
     * @param leng The length of the stage
     * @param time The start time of the stage
     * @param type The type of the stage
     */

    public Stage(String name, String des, double leng, LocalDateTime time, StageType type){
        stageName = name;
        description = des;
        length = leng;
        startTime = time;
        Type = type;
        stageId = StageCount++;
    }

    /**
     * Change the stage's state to be prepared
     */

    public void prepare(){
        prepared = true;
    }

    /**
     *
     * @return The stage's state
     */

    public boolean isPrepared(){
        return prepared;
    }

    /**
     * Reset the stage count
     */

    public static void resetStageCount(){
        StageCount = 0;
    }

    /**
     *
     * @return The ID of the stage
     */

    public int getId(){
        return stageId;
    }

    /**
     *
     * @return The type of the stage
     */

    public StageType getStageType(){
        return Type;
    }

    /**
     *
     * @return The length of the stage
     */

    public double getLength(){
        return length;
    }

    /**
     *
     * @return An array of segment instances in a stage
     */

    public Segment[] getSegments() {
        Segment[] array = new Segment[segments.size()];
        array = segments.toArray(array);
        return array;
    }

    /**
     * Add a segment to the stage
     * @param segment A segment instance
     */

    public void addSegment(Segment segment) {
        segments.add(segment);
    }

    /**
     * Remove the segment in the stage
     * @param segment A segment instance
     */

    public void removeSegment(Segment segment) {
        if (segments.contains(segment)) {
            segments.remove(segment);
        }
    }

    /**
     *
     * @return The number of segment in the stage
     */

    public int getNumOfSegment(){
        return segments.size();
    }

    /**
     *
     * @return The name of the stage
     */

    public String getName() {
        return stageName;
    }
}

