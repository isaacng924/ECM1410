package cycling;

import java.io.Serializable;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

/**
 * StageResult encapsulates the rider's result in a stage, and include the time management.
 */

public class StageResult implements Serializable {

    static int riderId;
    static int stageId;
    static LocalTime[] time;
    static ArrayList<StageResult> allResults = new ArrayList<StageResult>();

    /**
     * StageResult: create a result entry and adds it to an arraylist.
     * @param r The riderId
     * @param StageId The stageId
     * @param Time An array of time for which the rider arrived at each checkpoint and the finish time
     *
     */

    public StageResult(int r, int StageId, LocalTime... Time){
        riderId = r;
        stageId = StageId;
        time = Time;
        allResults.add(this);
    }

    /**
     *
     * @return StageId
     */

    public int getStage(){
        return stageId;
    }

    /**
     *
     * @return RiderId
     */

    public int getRider(){return riderId;}

    /**
     *
     * @return An array of time at which the rider arrived at each checkpoints
     */

    public LocalTime[] getTime(){return time;}

    /**
     *
     * @param stageId The ID of the stage
     * @param riderId The ID of the rider
     * @return A StageResult instance
     */

    public static StageResult getResult(int stageId, int riderId){
        for(StageResult r: allResults){
            if(r.getRider() == riderId && r.getStage() == stageId){
                return r;
            }
        }
        return null;
    }

    /**
     *
     * @param stageId The ID of the stage
     * @return An array of StageResult instances in a given stage
     */

    public static StageResult[] getResultInStage(int stageId){
        ArrayList<StageResult> stage = new ArrayList<StageResult>();
        for(StageResult r: allResults){
            stage.add(r);
        }
        stage.removeIf(r -> r.getStage()!= stageId);
        StageResult[] n = new StageResult[stage.size()];
        for(int i = 0; i < stage.size(); i++){
            n[i] = stage.get(i);
        }
        return n;
    }

    /**
     *
     * @param riderId The ID of the rider
     * @return An array of StageResult instances with a given rider
     */

    public static StageResult[] getRiderResult(int riderId){
        ArrayList<StageResult> r = new ArrayList<StageResult>(allResults);
        r.removeIf(i -> i.getRider() != riderId);
        StageResult[] riderResult = new StageResult[r.size()];
        for(int n = 0; n< r.size(); n++){
            riderResult[n] = r.get(n);
        }
        return riderResult;
    }

    /**
     *
     * @param t1 A start time
     * @param t2 An end time
     * @return The elapsed time
     */

    public static LocalTime getElapsed(LocalTime t1, LocalTime t2){
        int h = (int)t1.until(t2, ChronoUnit.HOURS);
        int m = (int)t1.until(t2, ChronoUnit.MINUTES);
        int s = (int)t1.until(t2, ChronoUnit.SECONDS);
        double n = t1.until(t2, ChronoUnit.NANOS);
        n = n%Math.pow(10, 9);
        return LocalTime.of(h%24, m%60, s%60, (int)n);
    }

    /**
     *
     * @return The total elapsed time for a rider to complete a stage
     */

    public LocalTime getTotalElapsed(){
        LocalTime[] t = time;
        return getElapsed(t[0], t[t.length-1]);
    }

    /**
     *
     * @return An array of time at which the rider finish different segments in a stage
     */

    public LocalTime[] getCheckpoints(){
        LocalTime[] out = new LocalTime[time.length-1];
        for(int n = 0; n< time.length-1; n++){
            out[n] = getElapsed(time[n], time[n+1]);
        }
        return out;
    }

    /**
     *
     * @param i The index of the checkpoint to adjust
     * @return An adjusted time for the checkpoint
     */

    public LocalTime adjustedCheckpoint(int i){
        for(int n = 0; n < allResults.size(); n++){
            StageResult r = allResults.get(n);
            if(r.getRider() == getRider() && r.getStage() == getStage()){
                continue;
            }
            LocalTime t1 = getCheckpoints()[i];
            LocalTime t2 = r.getCheckpoints()[i];
            if(t1.until(t2, ChronoUnit.SECONDS) < 1){
                return r.adjustedCheckpoint(i);
            }
            else {
                return t1;
            }
        }
        return null;
    }

    /**
     *
     * @return An array of adjusted time for the checkpoints in a stage
     */

    public LocalTime[] adjustedCheckpoints(){
        LocalTime[] adjusted = getCheckpoints();
        for(int i = 0; i < adjusted.length; i++ ){
            adjusted[i] = adjustedCheckpoint(i);
        }
        return adjusted;
    }

}

