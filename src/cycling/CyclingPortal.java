package cycling;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class CyclingPortal implements CyclingPortalInterface {

	static int TeamIdNum = 0;
	static int RaceNum = 0;

	ArrayList<race> Race = new ArrayList<>();
	HashMap<Integer, Team> teams = new HashMap<Integer, Team>();
	HashMap<Integer, Rider> riders = new HashMap<Integer, Rider>();
	HashMap<Integer, Stage> stageIds = new HashMap<Integer, Stage>();
	HashMap<Integer, Segment> segmentIds = new HashMap<Integer, Segment>();
	HashMap<LocalTime, Rider> riderLocalTimeHashMap = new HashMap<LocalTime, Rider>();


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
		stageIds.get(stageId).prepare();
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
		StageResult r = new StageResult(riderId, stageId, checkpoints);
		Segment[] s  = stageIds.get(stageId).getSegments();
		for(int u = 0; u < riders.size(); u++){
			riders.get(riderId).addSegmentTime(s[u].getSegmentId(),StageResult.getElapsed(checkpoints[u],checkpoints[u+1]));
		}

	}

	@Override
	public LocalTime[] getRiderResultsInStage(int stageId, int riderId) throws IDNotRecognisedException {
		return riders.get(riderId).getStageTime(stageId);
	}

	@Override
	public LocalTime getRiderAdjustedElapsedTimeInStage(int stageId, int riderId) throws IDNotRecognisedException {
		StageResult r = StageResult.getResult(stageId, riderId);
		LocalTime[] t = r.adjustedCheckpoints();
		LocalTime[] t2 = r.getTime();
		return r.getElapsed(t2[0], t[t.length-1]);
	}

	@Override
	public void deleteRiderResultsInStage(int stageId, int riderId) throws IDNotRecognisedException {
		riders.get(riderId).removeStageTime(stageId);

	}

	@Override
	public int[] getRidersRankInStage(int stageId) throws IDNotRecognisedException {
		StageResult[] results = StageResult.getResultInStage(stageId);
		int[] RiderRanks = new int[results.length];
		Arrays.fill(RiderRanks, -1);
		for(StageResult r: results){
			for(int n = 0; n < RiderRanks.length; n++){
				if(RiderRanks[n] == -1){
					RiderRanks[n] = r.getRider();
					break;
				}
				else if(r.getTotalElapsed().isBefore(StageResult.getResult(stageId, RiderRanks[n]).getTotalElapsed())){
					int t;
					int p = r.getRider();
					for(int j = n; j < RiderRanks.length; j++){
						t= RiderRanks[j];
						RiderRanks[j] = p;
						p = t;
						if(p == -1){
							break;
						}
					}
					break;
				}
			}
		}
		return RiderRanks;
	}

	@Override
	public LocalTime[] getRankedAdjustedElapsedTimesInStage(int stageId) throws IDNotRecognisedException {
		LocalTime[] t = new	LocalTime[riders.size()];
		int n = 0;
		int[] x = getRidersRankInStage(stageId);
		for(Rider r: riders.values()){
			t[n] = getRiderAdjustedElapsedTimeInStage(stageId, x[n]);
			n++;
		}

		return t;
	}

	@Override
	public int[] getRidersPointsInStage(int stageId) throws IDNotRecognisedException {
		int[] RiderPoints = new int[getRidersRankInStage(stageId).length];
		StageType n = stageIds.get(stageId).getStageType();
		switch (n){
			case FLAT -> {
				int[] FlatPoints = new int[]{50, 30, 20, 18, 16, 14, 12, 10, 8, 7, 6, 5, 4, 3, 2};
				for(int i = 0; i < 15; i++){
					RiderPoints[i] = FlatPoints[i];
				}
				for(int i = 15; i < getRidersRankInStage(stageId).length; i++){
					RiderPoints[i] = 0;
				}
				break;
			}
			case MEDIUM_MOUNTAIN -> {
				int[] MediumPoints = new int[]{30, 25, 22, 19, 17, 15, 13, 11, 9, 7, 6, 5, 4, 3, 2};
				for(int i = 0; i < 15; i++){
					RiderPoints[i] = MediumPoints[i];
				}
				for(int i = 15; i < getRidersRankInStage(stageId).length; i++){
					RiderPoints[i] = 0;
				}
				break;
			}
			case HIGH_MOUNTAIN -> {
				int[] HighPoints = new int[]{20, 17, 15, 13, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
				for(int i = 0; i < 15; i++){
					RiderPoints[i] = HighPoints[i];
				}
				for(int i = 15; i < getRidersRankInStage(stageId).length; i++){
					RiderPoints[i] = 0;
				}
				break;
			}
			case TT -> {
				int[] TTPoints = new int[]{20, 17, 15, 13, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
				for(int i = 0; i < 15; i++){
					RiderPoints[i] = TTPoints[i];
				}
				for(int i = 15; i < getRidersRankInStage(stageId).length; i++){
					RiderPoints[i] = 0;
				}
			}

		}
		int length = Math.min(RiderPoints.length, getRidersRankInStage(stageId).length);
		for(int s = 0; s < length; s++){
			riders.get(getRidersRankInStage(stageId)[s]).addPoint(RiderPoints[s]);
		}
		return RiderPoints;
	}

	@Override
	public int[] getRidersMountainPointsInStage(int stageId) throws IDNotRecognisedException {
		int[] RiderPoints = new int[getRidersRankInStage(stageId).length];
		Segment[] s = stageIds.get(stageId).getSegments();
		for(int i =0; i < s.length; i++){
			switch(s[i].getType()){
				case C4 -> {
					int[] C4Points = new int[]{1};
					for(int q = 0; q < 1; q++){
						RiderPoints[q] += C4Points[q];
					}
					for(int q = 1; q < getRidersRankInStage(stageId).length; q++){
						RiderPoints[q] += 0;
					}
					break;
				}
				case C3 -> {
					int[] C3Points = new int[]{2, 1};
					for(int q = 0; q < 2; q++){
						RiderPoints[q] += C3Points[q];
					}
					for(int q = 2; q < getRidersRankInStage(stageId).length; q++){
						RiderPoints[q] += 0;
					}
					break;
				}
				case C2 -> {
					int[] C2Points = new int[]{5,3,2,1};
					for(int q = 0; q < 4; q++){
						RiderPoints[q] += C2Points[q];
					}
					for(int q = 4; q < getRidersRankInStage(stageId).length; q++){
						RiderPoints[q] += 0;
					}
					break;
				}
				case C1 -> {
					int[] C1Points = new int[]{10,8,6,4,2,1};
					for(int q = 0; q < 6; q++){
						RiderPoints[q] += C1Points[q];
					}
					for(int q = 6; q < getRidersRankInStage(stageId).length; q++){
						RiderPoints[q] += 0;
					}
					break;
				}
				case HC -> {
					int[] HCPoints = new int[]{20,15,12,10,8,6,4,2};
					for(int q = 0; q < 8; q++){
						RiderPoints[q] += HCPoints[q];
					}
					for(int q = 8; q < getRidersRankInStage(stageId).length; q++){
						RiderPoints[q] += 0;
					}
					break;
				}
				case SPRINT -> {
					int[] SprintPoints = new int[]{20,17,15,13,11,10,9,8,7,6,5,4,3,2,1};
					for(int q = 0; q < 15; q++){
						RiderPoints[q] += SprintPoints[q];
					}
					for(int q = 15; q < getRidersRankInStage(stageId).length; q++){
						RiderPoints[q] += 0;
					}
					break;
				}
			}
		}
		int y = 0;
		LocalTime[] p = new LocalTime[riders.size()];
		for(Rider r : riders.values()){
			p[y] =  r.getSegmentTime(s[y].getSegmentId());
			y++;
		}
		Arrays.sort(p, new Sort());
		for(LocalTime t: p){
			int q = 0;
			for(Rider r : riders.values()){
				if(r.getSegmentTime(s[q].getSegmentId()) == t ){
					r.addMountainPoint(RiderPoints[q]);
				}
				q++;
			}
		}

		return RiderPoints;
	}

	@Override
	public void eraseCyclingPortal() {
		Race.clear();
		riders.clear();
		teams.clear();
		stageIds.clear();
		segmentIds.clear();
		riderLocalTimeHashMap.clear();
		TeamIdNum = 0;
		RaceNum = 0;
		race.resetRaceCount();
		Stage.resetStageCount();
		Segment.resetSegmentCount();
		Rider.resetRider();

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
		Race.removeIf(r -> r.getName().equals(name));
	}

	@Override
	public LocalTime[] getGeneralClassificationTimesInRace(int raceId) throws IDNotRecognisedException {
		race r = Race.get(raceId);
		Stage[] s = r.getStages();
		LocalTime[] t0 = new LocalTime[riders.size()];
		int q = 0;
		for(Rider rider: riders.values()){
			int x = 0;
			int y = 0;
			int z = 0;
			int v = 0;
			for(Stage n: s){
				x += getRiderAdjustedElapsedTimeInStage(n.getId(), rider.getRiderId()).getHour();
				y += getRiderAdjustedElapsedTimeInStage(n.getId(), rider.getRiderId()).getMinute();
				z += getRiderAdjustedElapsedTimeInStage(n.getId(), rider.getRiderId()).getSecond();
				v += getRiderAdjustedElapsedTimeInStage(n.getId(), rider.getRiderId()).getNano();
			}
			riderLocalTimeHashMap.put(LocalTime.of(x, y, z, v), rider);
			t0[q] = LocalTime.of(x, y, z, v);
			q++;
		}
		Arrays.sort(t0, new Sort());
		return t0;
	}

	@Override
	public int[] getRidersPointsInRace(int raceId) throws IDNotRecognisedException {
		int[] n =  getRaceStages(raceId);
		int[] a = new int[riders.size()];
		for(int i = 0; i< n.length; i++){
			getRidersPointsInStage(n[i]);
		}
		for(int i = 0; i< riders.size(); i++){
			a[i] = riders.get(getRidersGeneralClassificationRank(raceId)[i]).getPoints();
		}
		return a;
	}

	@Override
	public int[] getRidersMountainPointsInRace(int raceId) throws IDNotRecognisedException {
		int[] n =  getRaceStages(raceId);
		for(int i =0; i < n.length; i++){
			getRidersMountainPointsInStage(n[i]);
		}
		int[] r = getRidersGeneralClassificationRank(raceId);
		for(int i =0; i < r.length; i++){
			r[i] = riders.get(r[i]).getMountainPoints();
		}
		return r;
	}

	@Override
	public int[] getRidersGeneralClassificationRank(int raceId) throws IDNotRecognisedException {
		LocalTime[] t = getGeneralClassificationTimesInRace(raceId);
		int[] n = new int[t.length];
		for(int i = 0; i < n.length; i++){
			n[i] = riderLocalTimeHashMap.get(t[i]).getRiderId();
		}
		return n;
	}

	@Override
	public int[] getRidersPointClassificationRank(int raceId) throws IDNotRecognisedException {
		int[] s = getRidersPointsInRace(raceId);
		Arrays.sort(s);
		int[] a = new int[riders.size()];
		for(int i = 0; i< riders.size(); i++){
			for(Rider r : riders.values()){
				if(r.getPoints() == s[i]){
					a[i] = r.getRiderId();
				}
			}
		}
		return a;
	}

	@Override
	public int[] getRidersMountainPointClassificationRank(int raceId) throws IDNotRecognisedException {
		int[] s = getRidersMountainPointsInRace(raceId);
		Arrays.sort(s);
		int[] a = new int[riders.size()];
		for(int i = 0; i< riders.size(); i++){
			for(Rider r : riders.values()){
				if(r.getPoints() == s[i]){
					a[i] = r.getRiderId();
				}
			}
		}
		return a;
	}

}
