package cycling;

import java.io.Serializable;
import java.time.LocalTime;

public class Segment implements Serializable {
    static int SegmentCount = 0;
    int segmentId;
    double Location;
    SegmentType Type;
    double AverageGradient;
    double Length;

    public Segment(double location,SegmentType type, double averageGradient, double length){
        Location = location;
        Type = type;
        AverageGradient = averageGradient;
        Length = length;
        segmentId = SegmentCount++;
    }

    public Segment(double location, SegmentType type){
        Location = location;
        Type = type;
        segmentId = SegmentCount++;
    }

    public int getSegmentId(){
        return segmentId;
    }

    public SegmentType getType(){
        return Type;
    }

    public static void resetSegmentCount(){
        SegmentCount = 0;
    }

}
