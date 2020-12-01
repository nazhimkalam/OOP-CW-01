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
  public isLoading: boolean; // loading gif
  public audio: any;

  constructor(private _footballService: FootballInteractionService) {
    this.resultsRecords = [];
    this.currentSeason = '2020-21';
    this.isLoading = true;
  }

  ngOnInit(): void {
    // get all the records sorted by points initially when the records are loaded
    this.isLoading = true;
    this._footballService
      .getSortedByPoints(this.currentSeason)
      .subscribe((data) => {
        this.resultsRecords = data;
        this.isLoading = false;
      });

    // we have to set the seasons here when the user loads this page
    this._footballService
      .getSeasons()
      .subscribe((data) => (this.seasons = data));
  }

  sortByPoints() {
    //  get the records sorted by points
    this.audio = new Audio();
    this.audio.src = '../../assets/matchPlayed.mp3';
    this.audio.load();
    this.audio.play();
    
    this.isLoading = true;
    this._footballService
      .getSortedByPoints(this.currentSeason)
      .subscribe((data) => {
        this.resultsRecords = data;
        this.isLoading = false;
      });
  }

  sortByGoals() {
    // get the records sorted by goals
    this.audio = new Audio();
    this.audio.src = '../../assets/matchPlayed.mp3';
    this.audio.load();
    this.audio.play();

    this.isLoading = true;
    this._footballService
      .getSortedByGoals(this.currentSeason)
      .subscribe((data) => {
        this.resultsRecords = data;
        this.isLoading = false;
      });
  }

  sortByWins() {
    // get the records sorted by wins
    this.audio = new Audio();
    this.audio.src = '../../assets/matchPlayed.mp3';
    this.audio.load();
    this.audio.play();

    this.isLoading = true;
    this._footballService
      .getSortedByWins(this.currentSeason)
      .subscribe((data) => {
        this.resultsRecords = data;
        this.isLoading = false;
      });
  }

  handleClickedSeason(clickedSeason: string) {
    // get the new records by season clicked
    this.audio = new Audio();
    this.audio.src = '../../assets/matchPlayed.mp3';
    this.audio.load();
    this.audio.play();

    this.currentSeason = clickedSeason;
    this.isLoading = true;
    this._footballService.getSortedByPoints(clickedSeason).subscribe((data) => {
      this.resultsRecords = data;
      this.isLoading = false;
    });
  }
}
