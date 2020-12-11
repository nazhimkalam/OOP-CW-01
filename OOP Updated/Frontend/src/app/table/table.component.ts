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
  private resultsRecords: FootballClub[];
  private currentSeason: string;
  private seasons: string[];
  private isLoading: boolean;
  private audio: any;

  // constructor with the service FootballInteractionService injected
  public constructor(private _footballService: FootballInteractionService) {
    this.resultsRecords = [];
    this.currentSeason = '2020-21';
    this.isLoading = true;
  }

  public ngOnInit(): void {
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

  public sortByPoints() {
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

  public sortByGoals() {
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

  public sortByWins() {
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

  public handleClickedSeason(clickedSeason: string) {
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

  // Setters and Getters
  public getResultsRecords() {
    return this.resultsRecords;
  }

  public getCurrentSeason() {
    return this.currentSeason;
  }

  public getSeasons() {
    return this.seasons;
  }

  public getIsLoading() {
    return this.isLoading;
  }

  public getAudio() {
    return this.audio;
  }

  public setResultsRecords(data: FootballClub[]){
    this.resultsRecords = data;
  }

  public setCurrentSeason(data: string){
    this.currentSeason = data;
  }

  public setSeasons(data: string[]){
    this.seasons = data;
  }

  public setIsLoading(data: boolean){
    this.isLoading = data;
  }

  public setAudio(data: string){
    this.audio = data;
  }
}
