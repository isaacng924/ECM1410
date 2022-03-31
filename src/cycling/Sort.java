package cycling;

import java.time.LocalTime;
import java.util.Comparator;

public class Sort implements Comparator<LocalTime> {
    @Override
    public int compare(LocalTime o1, LocalTime o2) {
        return (int)(o1.toNanoOfDay() - o2.toNanoOfDay());
    }
}
