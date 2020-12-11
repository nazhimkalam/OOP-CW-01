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
  private allSeasonsURL: string;
  private tablesRecordsSortByPoints: string;
  private tablesRecordsSortByWins: string;
  private tablesRecordsSortByGoals: string;
  private matchesBySeason: string;
  private matchesByDate: string;
  private matchGeneration: string;

  // constructor
  public constructor(private http: HttpClient) {
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
  public getSeasons(): Observable<string[]> {
    return this.http.get<string[]>(this.allSeasonsURL);
  }

  // get records sorted by points
  public getSortedByPoints(season: string): Observable<FootballClub[]> {
    return this.http.get<FootballClub[]>(
      this.tablesRecordsSortByPoints + season
    );
  }

  // get records sorted by wins
  public getSortedByWins(season: string): Observable<FootballClub[]> {
    return this.http.get<FootballClub[]>(this.tablesRecordsSortByWins + season);
  }

  // get records sorted by goals
  public getSortedByGoals(season: string): Observable<FootballClub[]> {
    return this.http.get<FootballClub[]>(
      this.tablesRecordsSortByGoals + season
    );
  }

  // get matches for a season
  public getMatchesBySeason(season: string): Observable<MatchPlayed[]> {
    return this.http.get<MatchPlayed[]>(this.matchesBySeason + season);
  }

  // get matches by date
  public getMatchesByDate(date: string, season: string): Observable<MatchPlayed[]> {
    return this.http.get<MatchPlayed[]>(
      this.matchesByDate + season + '/date/' + date
    );
  }

  // generate a match and get the result
  public getGeneratedMatchesBySeason(season: string): Observable<MatchPlayed[]> {
    return this.http.get<MatchPlayed[]>(this.matchGeneration + season);
  }
}
