package cycling;

import java.util.ArrayList;

public class race {
    public int raceId;
    public static int RaceCount = 0;
    public String RaceName;
    public String RaceDescription;
    public ArrayList<Stage> stages = new ArrayList<>();

    public race(String name, String description) {
        RaceName = name;
        RaceDescription = description;
        raceId = RaceCount++;
    }

    public int getRaceId() {
        return raceId;
    }

    public String getName() {
        return RaceName;
    }

    public String getdescription() {
        return RaceDescription;
    }

    public int getNumberOfStages() {
        return stages.size();
    }

    private double getTotalLength() {
        double totalLength = 0;
        double currentStageLength;
        for (Stage stage : stages) {
            currentStageLength = stage.getLength();
            totalLength += currentStageLength;
        }
        return totalLength;
    }

    public Stage[] getStages() {
        Stage[] array = new Stage[stages.size()];
        array = stages.toArray(array);
        return array;
    }

    public void addStage(Stage stage) {
        stages.add(stage);
    }

    public void removeStage(Stage stage) {
        if (stages.contains(stage)) {
            stages.remove(stage);
        }
    }

    public String toString() {
        return String.format("[ID:%d" + ", Name:" + RaceName + ", description:" + RaceDescription + ", Number of stages:%d" + ", Total length:%f]", getRaceId(), getNumberOfStages(), getTotalLength());
    }
}
