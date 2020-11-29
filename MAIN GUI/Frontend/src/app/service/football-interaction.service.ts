import { FootballClub } from './../interfaces/FootballClub';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class FootballInteractionService {
  public allSeasonsURL = 'http://localhost:9000/seasons/all';
  public tablesRecordsSortByPoints =
    'http://localhost:9000/records/sortPoints/';
  public tablesRecordsSortByWins = 'http://localhost:9000/records/sortWins/';
  public tablesRecordsSortByGoals = 'http://localhost:9000/records/sortGoals/';

  constructor(private http: HttpClient) {}

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
    return this.http.get<FootballClub[]>(
      this.tablesRecordsSortByWins + season
    );
  }

  // get records sorted by goals
  getSortedByGoals(season: string): Observable<FootballClub[]> {
    return this.http.get<FootballClub[]>(
      this.tablesRecordsSortByGoals + season
    );
  }
}
