package cycling;

import java.io.Serializable;
import java.util.ArrayList;

public class race implements Serializable {
    int raceId;
    static int RaceCount = 0;
    String RaceName;
    String RaceDescription;
    ArrayList<Stage> stages = new ArrayList<>();

    /**
     * race: Create a race entry.
     * @param name The riderId
     * @param description The stageId
     *
     */

    public race(String name, String description) {
        RaceName = name;
        RaceDescription = description;
        raceId = RaceCount++;
    }

    /**
     *
     * @return The ID of the race
     */

    public int getRaceId() {
        return raceId;
    }

    /**
     *
     * @return The name of the race
     */

    public String getName() {
        return RaceName;
    }

    /**
     *
     * @return The description of the race
     */

    public String getdescription() {
        return RaceDescription;
    }

    /**
     *
     * @return The number of stage in the race
     */

    public int getNumberOfStages() {
        return stages.size();
    }

    /**
     * Reset the race count
     */

    public static void resetRaceCount(){
        RaceCount = 0;
    }

    /**
     *
     * @return The total length of the race
     */

    private double getTotalLength() {
        double totalLength = 0;
        double currentStageLength;
        for (Stage stage : stages) {
            currentStageLength = stage.getLength();
            totalLength += currentStageLength;
        }
        return totalLength;
    }

    /**
     *
     * @return An array of stage instances in the race
     */

    public Stage[] getStages() {
        Stage[] array = new Stage[stages.size()];
        array = stages.toArray(array);
        return array;
    }

    /**
     * Add a stage to the race
     * @param stage A stage instance
     */

    public void addStage(Stage stage) {
        stages.add(stage);
    }

    /**
     * Remove a stage in the race
     * @param stage A stage instance
     */

    public void removeStage(Stage stage) {
        if (stages.contains(stage)) {
            stages.remove(stage);
        }
    }

    /**
     *
     * @return A string contains the information of the race
     */

    public String toString() {
        return String.format("[ID:%d" + ", Name:" + RaceName + ", description:" + RaceDescription + ", Number of stages:%d" + ", Total length:%f]", getRaceId(), getNumberOfStages(), getTotalLength());
    }
}
