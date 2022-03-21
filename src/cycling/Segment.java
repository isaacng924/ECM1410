package cycling;

public class Segment {
    double Location;
    SegmentType Type;
    double AverageGradient;
    double Length;

    public Segment(double location,SegmentType type, double averageGradient, double length){
        Location = location;
        Type = type;
        AverageGradient = averageGradient;
        Length = length;
    }

}
