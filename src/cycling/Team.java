package cycling;

import java.io.Serializable;

public class Team implements Serializable {
    String TeamName;
    String TeamDes;

    public Team(String name, String description){
        TeamName = name;
        TeamDes = description;
    }
}
