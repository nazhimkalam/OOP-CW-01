import { MatchPlayed } from './../interfaces/MatchPlayed';
import { FootballInteractionService } from './../service/football-interaction.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-matches',
  templateUrl: './matches.component.html',
  styleUrls: ['./matches.component.css'],
})
export class MatchesComponent implements OnInit {

  // variables used
  public matches: MatchPlayed[];
  public currentSeason: string; 
  public seasons: string[];
  public selectedDate: string; 
  public clubLogo: number[];
  public loadingContent: boolean;
  public audio: any;
  public disableSearchBtn: boolean;
  public displayCelebration: string;

  // constructor for initialization
  constructor(private _footballService: FootballInteractionService) {
    this.currentSeason = '2020-21';
    this.selectedDate = '';
    this.matches = [];
    this.loadingContent = true;
    this.disableSearchBtn = true;
    this.displayCelebration = 'noCelebration';
  }

  // runs just after the constructor
  ngOnInit(): void {

    // we have to set the seasons here when the user loads this page
    this._footballService
      .getSeasons()
      .subscribe((data) => (this.seasons = data));

    // getting the matches for the current season
    this._footballService
      .getMatchesBySeason(this.currentSeason)
      .subscribe((data) => {
        this.matches = data;
        this.generateClubLogo();
        this.loadingContent = false;
      });

  }

  // this method runs when the user selects a season
  handleClickedSeason(clickedSeason: string) {
    // changes the variables accordingly when season changes
    this.selectedDate = '';
    this.audio = new Audio();
    this.audio.src = '../../assets/matchPlayed.mp3';
    this.audio.load();
    this.audio.play();
    this.loadingContent = true;
    this.currentSeason = clickedSeason;

    // get the new records by season clicked
    this._footballService
      .getMatchesBySeason(clickedSeason)
      .subscribe((data) => {
        this.matches = data;
        this.generateClubLogo();
        this.loadingContent = false;
      });
  }

  // this method runs when the user selects a date
  handleSearchSelectedDate() {
    // changes the variables accordingly when season changes
    this.audio = new Audio();
    this.audio.src = '../../assets/matchPlayed.mp3';
    this.audio.load();
    this.audio.play();
    this.disableSearchBtn = true;
    this.loadingContent = true;

    // using the service to get the matches by date
    this._footballService
      .getMatchesByDate(this.selectedDate, this.currentSeason)
      .subscribe((data) => {
        this.matches = data;
        this.generateClubLogo();
        this.loadingContent = false;
      });
    this.generateClubLogo();
  }

  // setting the selected data by the user to the variable for searching
  setSelectedDate(date: string) {
    this.disableSearchBtn = false;
    this.selectedDate = date;
  }

  // this method runs when the user clicks the generate button
  generateMatch() {
    // changes the variables accordingly when season changes
    this.selectedDate = '';
    this.displayCelebration = 'celebration__theme';
    this.audio = new Audio();
    this.audio.src = '../../assets/matchPlayed.mp3';
    this.audio.load();
    this.audio.play();
    this.loadingContent = true;

    // using the service to get all the matches with the generated match
    this._footballService
      .getGeneratedMatchesBySeason(this.currentSeason)
      .subscribe((data) => {
        this.matches = data;
        this.generateClubLogo();
        this.loadingContent = false;
      });

    this.generateClubLogo();

    // Setting a delay
    setTimeout(() => {
      this.displayCelebration = 'noCelebration';
    }, 1200);
  }

  // generate random clubLogo
  generateClubLogo() {
    this.clubLogo = [];
    this.matches.forEach((match) => {
      this.clubLogo.push(Math.floor(Math.random() * Math.floor(23)) + 1);
      this.clubLogo.push(Math.floor(Math.random() * Math.floor(23)) + 1);
      this.clubLogo.push(Math.floor(Math.random() * Math.floor(23)) + 1);
    });
   
  }
}
