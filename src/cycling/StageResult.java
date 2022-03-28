package cycling;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class StageResult {
    int riderId;
    int stageId;
    static LocalTime[] time;
    static ArrayList<StageResult> allResults = new ArrayList<StageResult>();

    public StageResult(int r, int StageId, LocalTime... Time){
        riderId = r;
        stageId = StageId;
        time = Time;
        allResults.add(this);
    }
    public int getStage(){
        return stageId;
    }
    public int getRider(){return riderId;}
    public int[] getRank(){
        return null;
    }

    public static StageResult getResult(int stageId, int riderId){
        for(StageResult r: allResults){
            if(r.getRider() == riderId && r.getStage() == stageId){
                return r;
            }
        }
        return null;
    }

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

    public StageResult[] getRiderResult(int riderId){
        ArrayList<StageResult> r = new ArrayList<StageResult>(allResults);
        r.removeIf(i -> i.getRider() != riderId);
        StageResult[] riderResult = new StageResult[r.size()];
        for(int n = 0; n< r.size(); n++){
            riderResult[n] = r.get(n);
        }
        return riderResult;
    }

    public static LocalTime getElapsed(LocalTime t1, LocalTime t2){
        int h = (int)t1.until(t2, ChronoUnit.HOURS);
        int m = (int)t1.until(t2, ChronoUnit.MINUTES);
        int s = (int)t1.until(t2, ChronoUnit.SECONDS);
        double n = t1.until(t2, ChronoUnit.NANOS);
        n = n%Math.pow(10, 9);
        return LocalTime.of(h%24, m%60, s%60, (int)n);
    }

    public LocalTime getTotalElapsed(){
        LocalTime[] t = time;
        return StageResult.getElapsed(t[0], t[t.length-1]);
    }

}

