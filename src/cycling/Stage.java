package cycling;

import java.time.LocalDateTime;

public class Stage {
    String stageName;
    String description;
    double length;
    LocalDateTime startTime;
    StageType Type;

    public Stage(String name, String des, double leng, LocalDateTime time, StageType type){
        stageName = name;
        description = des;
        length = leng;
        startTime = time;
        Type = type;
    }

    public StageType getStageType(){
        return Type;
    }

    public double getLength(){
        return length;
    }







}
