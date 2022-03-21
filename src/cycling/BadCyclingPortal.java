package cycling;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.ArrayList;


/**
 * BadCyclingPortal is a minimally compiling, but non-functioning implementor
 * of the CyclingPortalInterface interface.
 * 
 * @author Diogo Pacheco
 * @version 1.0
 *
 */
public class BadCyclingPortal implements CyclingPortalInterface {

	static int TeamIdNum = 0;
	static int RiderIdNum = 0;
	static int RaceIdNum = 0;
	static int StageIdNum = 0;
	static int SegmentIdNum = 0;

	HashMap<Integer, Team> teams = new HashMap<Integer, Team>();
	HashMap<Integer, Rider> riders = new HashMap<Integer, Rider>();
	HashMap<Integer, race> races = new HashMap<Integer, race>();
	HashMap<Stage, Integer> stage = new HashMap<Stage, Integer>();
	HashMap<Integer, Stage> stageIds = new HashMap<Integer, Stage>();
	HashMap<Segment, Integer> segment = new HashMap<Segment, Integer>();
	HashMap<Integer, Segment> segmentIds = new HashMap<Integer, Segment>();

	@Override
	public int[] getRaceIds() {
		int[] raceids = new int[races.size()];
		int n = 0;
		for(int i:races.keySet()){
			raceids[n] = i;
			n++;
		}
		return raceids;
	}

	@Override
	public int createRace(String name, String description) throws IllegalNameException, InvalidNameException {
		race newRace = new race(name, description);
		races.put(RaceIdNum, newRace);
		RaceIdNum++;
		return RaceIdNum - 1;
	}

	@Override
	public String viewRaceDetails(int raceId) throws IDNotRecognisedException {
		String details = null;
		race newRace = races.get(raceId);
		details = newRace.getRace();
		return details;
	}

	@Override
	public void removeRaceById(int raceId) throws IDNotRecognisedException {
		races.remove(raceId);

	}

	@Override
	public int getNumberOfStages(int raceId) throws IDNotRecognisedException {
		int n = 0;
		for( Stage i: stage.keySet() ){
			if(stage.get(i) == raceId){
				n++;
			}
		}
		return n;
	}

	@Override
	public int addStageToRace(int raceId, String stageName, String description, double length, LocalDateTime startTime,
			StageType type)
			throws IDNotRecognisedException, IllegalNameException, InvalidNameException, InvalidLengthException {
		Stage obj = new Stage(stageName, description, length, startTime, type);
		stage.put(obj, raceId);
		stageIds.put(StageIdNum, obj);
		StageIdNum++;
		return StageIdNum - 1;
	}

	@Override
	public int[] getRaceStages(int raceId) throws IDNotRecognisedException {
		int[] stages = new int[stageIds.size()];
		int n = 0;
		for(int i:stageIds.keySet()){
			stages[n] = i;
			n++;
		}
		return stages;
	}

	@Override
	public double getStageLength(int stageId) throws IDNotRecognisedException {
		return stageIds.get(stageId).getLength();
	}

	@Override
	public void removeStageById(int stageId) throws IDNotRecognisedException {
		stageIds.remove(stageId);

	}

	@Override
	public int addCategorizedClimbToStage(int stageId, Double location, SegmentType type, Double averageGradient,
			Double length) throws IDNotRecognisedException, InvalidLocationException, InvalidStageStateException,
			InvalidStageTypeException {
		Segment obj = new Segment(location, type, averageGradient, length);
		segment.put(obj, stageId);
		segmentIds.put(SegmentIdNum, obj);
		SegmentIdNum++;
		return SegmentIdNum - 1;
	}

	@Override
	public int addIntermediateSprintToStage(int stageId, double location) throws IDNotRecognisedException,
			InvalidLocationException, InvalidStageStateException, InvalidStageTypeException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void removeSegment(int segmentId) throws IDNotRecognisedException, InvalidStageStateException {
		// TODO Auto-generated method stub

	}

	@Override
	public void concludeStagePreparation(int stageId) throws IDNotRecognisedException, InvalidStageStateException {
		// TODO Auto-generated method stub

	}

	@Override
	public int[] getStageSegments(int stageId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int createTeam(String name, String description) throws IllegalNameException, InvalidNameException {
		Team obj = new Team(name, description);
		teams.put(TeamIdNum, obj);
		TeamIdNum++;
		return TeamIdNum - 1;
	}

	@Override
	public void removeTeam(int teamId) throws IDNotRecognisedException {
		teams.remove(teamId);
	}

	@Override
	public int[] getTeams() {
		int[] TeamList = new int[teams.size()];
		int n = 0;
		for(int i: teams.keySet()){
			TeamList[n] = i;
			n++;
		}
		return TeamList;
	}

	@Override
	public int[] getTeamRiders(int teamId) throws IDNotRecognisedException {
		int NumberOfRiders = 0;
		for (Rider i: riders.values()){
			if (i.getTeamID() == teamId){
				NumberOfRiders++;
			}
		}
		int[] TeamRiders = new int[NumberOfRiders];
		int x = 0;
		for (Rider i: riders.values()){
			if (i.getTeamID() == teamId){
				TeamRiders[x] = i.getTeamID();
			}
			x++;
		}
		return TeamRiders;
	}

	@Override
	public int createRider(int teamID, String name, int yearOfBirth)
			throws IDNotRecognisedException, IllegalArgumentException {
		Rider obj = new Rider(teamID, name, yearOfBirth);
		riders.put(RiderIdNum, obj);
		RiderIdNum++;
		return RiderIdNum - 1;
	}

	@Override
	public void removeRider(int riderId) throws IDNotRecognisedException {
		riders.remove(riderId);
	}

	@Override
	public void registerRiderResultsInStage(int stageId, int riderId, LocalTime... checkpoints)
			throws IDNotRecognisedException, DuplicatedResultException, InvalidCheckpointsException,
			InvalidStageStateException {
		// TODO Auto-generated method stub

	}

	@Override
	public LocalTime[] getRiderResultsInStage(int stageId, int riderId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LocalTime getRiderAdjustedElapsedTimeInStage(int stageId, int riderId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteRiderResultsInStage(int stageId, int riderId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub

	}

	@Override
	public int[] getRidersRankInStage(int stageId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LocalTime[] getRankedAdjustedElapsedTimesInStage(int stageId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getRidersPointsInStage(int stageId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getRidersMountainPointsInStage(int stageId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eraseCyclingPortal() {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveCyclingPortal(String filename) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadCyclingPortal(String filename) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeRaceByName(String name) throws NameNotRecognisedException {
		// TODO Auto-generated method stub

	}

	@Override
	public LocalTime[] getGeneralClassificationTimesInRace(int raceId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getRidersPointsInRace(int raceId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getRidersMountainPointsInRace(int raceId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getRidersGeneralClassificationRank(int raceId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getRidersPointClassificationRank(int raceId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getRidersMountainPointClassificationRank(int raceId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

}
