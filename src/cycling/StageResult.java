package cycling;

import java.time.LocalTime;

public class StageResult {
    Rider rider;
    int stageId;
    LocalTime[] time;

    public StageResult(Rider r, int StageId, LocalTime... Time){
        rider = r;
        stageId = StageId;
        time = Time;
    }
    public int getStage(){
        return stageId;
    }
    public int[] getRank(){
        return null;
    }
}

