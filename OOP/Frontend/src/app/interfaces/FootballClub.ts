import { MatchPlayed } from './MatchPlayed';
import { ClubStatistics } from './ClubStatistics';
export interface FootballClub {
  name: string;
  location: string;
  clubStatistics: ClubStatistics;
  coachName: string;
  totalGoalsReceived: number;
  totalGoalsScored: number;
  totalGoalsDifference: number;
  totalYellowCards: number;
  totalRedCards: number;
  matchesPlayed: MatchPlayed[];
  playersList: object[];
  mainStatistics: number[];
}
