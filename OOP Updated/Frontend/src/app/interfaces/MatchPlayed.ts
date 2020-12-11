// interface for the match played
export interface MatchPlayed {
  goalScored: number;
  goalReceived: number;
  season: string;
  matchStats: object;
  date: Date;
  opponentClubName: string;
  matchType: string;
  participatedCLubName: string;
}
