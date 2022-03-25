package cycling;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Stage {
    public static int StageCount = 0;
    public int stageId;
    String stageName;
    String description;
    double length;
    LocalDateTime startTime;
    StageType Type;
    public ArrayList<Segment> segments = new ArrayList<>();

    public Stage(String name, String des, double leng, LocalDateTime time, StageType type){
        stageName = name;
        description = des;
        length = leng;
        startTime = time;
        Type = type;
        stageId = StageCount++;
    }

    public int getId(){
        return stageId;
    }

    public StageType getStageType(){
        return Type;
    }

    public double getLength(){
        return length;
    }

    public Segment[] getSegments() {
        Segment[] array = new Segment[segments.size()];
        array = segments.toArray(array);
        return array;
    }
    public void addSegment(Segment segment) {
        segments.add(segment);
    }

    public void removeSegment(Segment segment) {
        if (segments.contains(segment)) {
            segments.remove(segment);
        }
    }

    public int getNumOfSegment(){
        return segments.size();
    }
}

