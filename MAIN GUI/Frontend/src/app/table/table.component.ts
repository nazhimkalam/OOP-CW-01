import { FootballClub } from './../interfaces/FootballClub';
import { FootballInteractionService } from './../service/football-interaction.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css'],
})
export class TableComponent implements OnInit {
  public resultsRecords: FootballClub[]; // rows data
  public currentSeason: string; // this is a must
  public seasons: string[]; // other seasons

  constructor(private _footballService: FootballInteractionService) {
    this.resultsRecords = [];
    this.currentSeason = '2020-21';
  }

  ngOnInit(): void {
    // get all the records sorted by points initially when the records are loaded
    this._footballService
      .getSortedByPoints(this.currentSeason)
      .subscribe((data) => (this.resultsRecords = data));

    // we have to set the seasons here when the user loads this page
    this._footballService
      .getSeasons()
      .subscribe((data) => (this.seasons = data));
  }

  sortByPoints() {
    //  get the records sorted by points
    this._footballService
      .getSortedByPoints(this.currentSeason)
      .subscribe((data) => (this.resultsRecords = data));
  }

  sortByGoals() {
    // get the records sorted by goals
    this._footballService
      .getSortedByGoals(this.currentSeason)
      .subscribe((data) => (this.resultsRecords = data));
  }

  sortByWins() {
    // get the records sorted by wins
    this._footballService
      .getSortedByWins(this.currentSeason)
      .subscribe((data) => (this.resultsRecords = data));
  }

  handleClickedSeason(clickedSeason: string) {
    // get the new records by season clicked
    this.currentSeason = clickedSeason;
    this._footballService
      .getSortedByPoints(clickedSeason)
      .subscribe((data) => (this.resultsRecords = data));
  }
}
