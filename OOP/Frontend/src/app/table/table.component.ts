import { FootballClub } from './../interfaces/FootballClub';
import { FootballInteractionService } from './../service/football-interaction.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css'],
})
export class TableComponent implements OnInit {
  
  // variables used 
  public resultsRecords: FootballClub[]; 
  public currentSeason: string;
  public seasons: string[]; 
  public isLoading: boolean; 
  public audio: any;

  // constructor with the service FootballInteractionService injected
  constructor(private _footballService: FootballInteractionService) {
    this.resultsRecords = [];
    this.currentSeason = '2020-21';
    this.isLoading = true;
  }

  ngOnInit(): void {
    // get all the records sorted by points initially when the records are loaded
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

    // plays audio when clicked
    this.audio = new Audio();
    this.audio.src = '../../assets/matchPlayed.mp3';
    this.audio.load();
    this.audio.play();
    
    // displays the gif until the data is received
    this.isLoading = true;

    // gets the football clubs sorted by points
    this._footballService
      .getSortedByPoints(this.currentSeason)
      .subscribe((data) => {
        this.resultsRecords = data;
        this.isLoading = false;
      });
  }

  sortByGoals() {
    // get the records sorted by goals

    // plays audio when clicked
    this.audio = new Audio();
    this.audio.src = '../../assets/matchPlayed.mp3';
    this.audio.load();
    this.audio.play();
    
    // displays the gif until the data is received
    this.isLoading = true;

    // gets the football clubs sorted by goals
    this._footballService
      .getSortedByGoals(this.currentSeason)
      .subscribe((data) => {
        this.resultsRecords = data;
        this.isLoading = false;
      });
  }

  sortByWins() {
    // get the records sorted by wins

    // plays audio when clicked
    this.audio = new Audio();
    this.audio.src = '../../assets/matchPlayed.mp3';
    this.audio.load();
    this.audio.play();
    
    // displays the gif until the data is received
    this.isLoading = true;

    // gets the football clubs sorted by wins
    this._footballService
      .getSortedByWins(this.currentSeason)
      .subscribe((data) => {
        this.resultsRecords = data;
        this.isLoading = false;
      });
  }

  handleClickedSeason(clickedSeason: string) {
    // get the new records by season clicked

    // plays audio when clicked
    this.audio = new Audio();
    this.audio.src = '../../assets/matchPlayed.mp3';
    this.audio.load();
    this.audio.play();

    // changes the current season selected
    this.currentSeason = clickedSeason;
    
    // displays the gif until the data is received
    this.isLoading = true;

    // gets the football clubs by season
    this._footballService.getSortedByPoints(clickedSeason).subscribe((data) => {
      this.resultsRecords = data;
      this.isLoading = false;
    });
  }
}
