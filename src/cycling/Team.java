package cycling;

import java.io.Serializable;

public class Team implements Serializable {
    String TeamName;
    String TeamDes;

    /**
     * Team:
     * @param name The team name
     * @param description  The description of the team
     */

    public Team(String name, String description){
        TeamName = name;
        TeamDes = description;
    }

    /**
     *
     * @return The team name
     */

    public String getTeamName(){
        return TeamName;
    }
}
