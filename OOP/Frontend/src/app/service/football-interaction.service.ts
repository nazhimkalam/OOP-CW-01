import { FootballClub } from './../interfaces/FootballClub';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { MatchPlayed } from '../interfaces/MatchPlayed';

@Injectable({
  providedIn: 'root',
})
export class FootballInteractionService {

  // variables used
  public allSeasonsURL: string;
  public tablesRecordsSortByPoints: string;
  public tablesRecordsSortByWins: string;
  public tablesRecordsSortByGoals: string;
  public matchesBySeason: string;
  public matchesByDate: string;
  public matchGeneration: string;

  // constructor
  constructor(private http: HttpClient) {
    this.allSeasonsURL = 'http://localhost:9000/seasons/all';
    this.tablesRecordsSortByPoints =
      'http://localhost:9000/records/sortPoints/';
    this.tablesRecordsSortByWins = 'http://localhost:9000/records/sortWins/';
    this.tablesRecordsSortByGoals = 'http://localhost:9000/records/sortGoals/';
    this.matchesBySeason = 'http://localhost:9000/matches/season/';
    this.matchesByDate = 'http://localhost:9000/matches/season/';
    this.matchGeneration =
      'http://localhost:9000/matches/season/match/generate/';
  }

  // get all the seasons
  getSeasons(): Observable<string[]> {
    return this.http.get<string[]>(this.allSeasonsURL);
  }

  // get records sorted by points
  getSortedByPoints(season: string): Observable<FootballClub[]> {
    return this.http.get<FootballClub[]>(
      this.tablesRecordsSortByPoints + season
    );
  }

  // get records sorted by wins
  getSortedByWins(season: string): Observable<FootballClub[]> {
    return this.http.get<FootballClub[]>(this.tablesRecordsSortByWins + season);
  }

  // get records sorted by goals
  getSortedByGoals(season: string): Observable<FootballClub[]> {
    return this.http.get<FootballClub[]>(
      this.tablesRecordsSortByGoals + season
    );
  }

  // get matches for a season
  getMatchesBySeason(season: string): Observable<MatchPlayed[]> {
    return this.http.get<MatchPlayed[]>(this.matchesBySeason + season);
  }

  // get matches by date
  getMatchesByDate(date: string, season: string): Observable<MatchPlayed[]> {
    return this.http.get<MatchPlayed[]>(
      this.matchesByDate + season + '/date/' + date
    );
  }

  // generate a match and get the result
  getGeneratedMatchesBySeason(season: string): Observable<MatchPlayed[]> {
    return this.http.get<MatchPlayed[]>(this.matchGeneration + season);
  }
}
