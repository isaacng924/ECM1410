package cycling;

public class Segment {
    public static int SegmentCount = 0;
    public int segmentId;
    public double Location;
    public SegmentType Type;
    public double AverageGradient;
    public double Length;

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

}
