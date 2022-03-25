package cycling;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;


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
	static int RaceNum = 0;

	ArrayList<race> Race = new ArrayList<>();
	HashMap<Integer, Team> teams = new HashMap<Integer, Team>();
	HashMap<Integer, Rider> riders = new HashMap<Integer, Rider>();
	HashMap<Integer, Stage> stageIds = new HashMap<Integer, Stage>();
	HashMap<Integer, Segment> segmentIds = new HashMap<Integer, Segment>();

	@Override
	public int[] getRaceIds() {
		int[] raceids = new int[RaceNum];
		for(int n = 0; n < raceids.length; n++){
			raceids[n] = Race.get(n).getRaceId();
		}
		return raceids;
	}

	@Override
	public int createRace(String name, String description) throws IllegalNameException, InvalidNameException {
		race newRace = new race(name, description);
		Race.add(newRace);
		RaceNum++;
		return newRace.getRaceId();
	}

	@Override
	public String viewRaceDetails(int raceId) throws IDNotRecognisedException {
		String details = null;
		for(race n: Race){
			if(n.getRaceId() == raceId){
				details = n.toString();
			}
		}
		return details;
	}

	@Override
	public void removeRaceById(int raceId) throws IDNotRecognisedException {
		Race.removeIf(n -> n.getRaceId() == raceId);
	}

	@Override
	public int getNumberOfStages(int raceId) throws IDNotRecognisedException {
		int x = 0;
		for(race n: Race){
			if(n.getRaceId() == raceId){
				x = n.getNumberOfStages();
			}
		}
		return x;
	}

	@Override
	public int addStageToRace(int raceId, String stageName, String description, double length, LocalDateTime startTime,
			StageType type)
			throws IDNotRecognisedException, IllegalNameException, InvalidNameException, InvalidLengthException {
		Stage obj = new Stage(stageName, description, length, startTime, type);
		for(race n: Race){
			if(n.getRaceId() == raceId){
				n.addStage(obj);
			}
		}
		stageIds.put(obj.stageId, obj);
		return obj.getId();
	}

	@Override
	public int[] getRaceStages(int raceId) throws IDNotRecognisedException {
		int[] z = new int[getNumberOfStages(raceId)];
		for (race n : Race) {
			if (n.getRaceId() == raceId) {
				int i = 0;
				for(Stage x :n.getStages()){
					z[i] = x.getId();
					i++;
				}
			}
		}
		return z;
	}

	@Override
	public double getStageLength(int stageId) throws IDNotRecognisedException {
		return stageIds.get(stageId).getLength();
	}

	@Override
	public void removeStageById(int stageId) throws IDNotRecognisedException {
		for(race n: Race){
			n.removeStage(stageIds.get(stageId));
		}
		stageIds.remove(stageId);
	}

	@Override
	public int addCategorizedClimbToStage(int stageId, Double location, SegmentType type, Double averageGradient,
			Double length) throws IDNotRecognisedException, InvalidLocationException, InvalidStageStateException,
			InvalidStageTypeException {
		Segment obj = new Segment(location, type, averageGradient, length);
		stageIds.get(stageId).addSegment(obj);
		segmentIds.put(obj.segmentId, obj);
		return obj.segmentId;
	}

	@Override
	public int addIntermediateSprintToStage(int stageId, double location) throws IDNotRecognisedException,
			InvalidLocationException, InvalidStageStateException, InvalidStageTypeException {
		Segment obj = new Segment(location, SegmentType.SPRINT);
		stageIds.get(stageId).addSegment(obj);
		segmentIds.put(obj.segmentId, obj);
		return obj.segmentId;
	}

	@Override
	public void removeSegment(int segmentId) throws IDNotRecognisedException, InvalidStageStateException {
		for(race n: Race){
			for(Stage i: n.getStages()){
				i.removeSegment(segmentIds.get(segmentId));
			}
		}
		segmentIds.remove(segmentId);
	}

	@Override
	public void concludeStagePreparation(int stageId) throws IDNotRecognisedException, InvalidStageStateException {
		// TODO Auto-generated method stub

	}

	@Override
	public int[] getStageSegments(int stageId) throws IDNotRecognisedException {
		int[] z = new int[stageIds.get(stageId).getNumOfSegment()];
		int i = 0;
		for(Segment x: stageIds.get(stageId).getSegments()){
			z[i] = x.getSegmentId();
			i++;
		}
		return z;
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
		riders.put(obj.getRiderId(), obj);
		return obj.getRiderId();
	}

	@Override
	public void removeRider(int riderId) throws IDNotRecognisedException {
		riders.remove(riderId);
	}

	@Override
	public void registerRiderResultsInStage(int stageId, int riderId, LocalTime... checkpoints)
			throws IDNotRecognisedException, DuplicatedResultException, InvalidCheckpointsException,
			InvalidStageStateException {
		riders.get(riderId).addStageTime(stageId, checkpoints);
	}

	@Override
	public LocalTime[] getRiderResultsInStage(int stageId, int riderId) throws IDNotRecognisedException {
		return riders.get(riderId).getStageTime(stageId);
	}

	@Override
	public LocalTime getRiderAdjustedElapsedTimeInStage(int stageId, int riderId) throws IDNotRecognisedException {
		return null;
	}

	@Override
	public void deleteRiderResultsInStage(int stageId, int riderId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub

	}

	@Override
	public int[] getRidersRankInStage(int stageId) throws IDNotRecognisedException {
		int[] x = new int[riders.size()];
		Rider[] y = new Rider[riders.size()];
		int z = 0;
		for(int r: riders.keySet()){
			y[z] = riders.get(r);
			z++;
		}

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
