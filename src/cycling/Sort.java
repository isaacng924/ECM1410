package cycling;

import java.time.LocalTime;
import java.util.Comparator;

public class Sort implements Comparator<LocalTime> {

    /**
     *
     * @param o1 A start time
     * @param o2 An end time
     * @return The elapsed time
     */

    @Override
    public int compare(LocalTime o1, LocalTime o2) {
        return (int)(o1.toNanoOfDay() - o2.toNanoOfDay());
    }
}
