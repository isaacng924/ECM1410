package cycling;

import java.io.Serializable;

public class Segment implements Serializable {
    static int SegmentCount = 0;
    int segmentId;
    double Location;
    SegmentType Type;
    double AverageGradient;
    double Length;

    /**
     * Segment: Create a segment entry
     * @param location The starting location of the segment
     * @param type The type of the segment
     * @param averageGradient The average gradient of the segment
     * @param length The length of the segment
     */

    public Segment(double location,SegmentType type, double averageGradient, double length){
        Location = location;
        Type = type;
        AverageGradient = averageGradient;
        Length = length;
        segmentId = SegmentCount++;
    }

    /**
     * Segment: Create a segment entry
     * @param location The starting location of the segment
     * @param type The type of the segment
     */

    public Segment(double location, SegmentType type){
        Location = location;
        Type = type;
        segmentId = SegmentCount++;
    }

    /**
     *
     * @return The ID of the segment
     */

    public int getSegmentId(){
        return segmentId;
    }

    /**
     *
     * @return The type of the segment
     */

    public SegmentType getType(){
        return Type;
    }

    /**
     * Reset the segment count
     */

    public static void resetSegmentCount(){
        SegmentCount = 0;
    }

}
