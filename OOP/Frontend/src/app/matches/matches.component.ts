import { MatchPlayed } from './../interfaces/MatchPlayed';
import { FootballInteractionService } from './../service/football-interaction.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-matches',
  templateUrl: './matches.component.html',
  styleUrls: ['./matches.component.css'],
})
export class MatchesComponent implements OnInit {
  public matches: MatchPlayed[]; // stores all the matches
  public currentSeason: string; // this is a must
  public seasons: string[]; // other seasons
  public selectedDate: string; // useSelectedDate
  public clubLogo: number[]; // contains a random number representing the club logo
  public loadingContent: boolean;
  public audio: any;
  public disableSearchBtn: boolean;
  public displayCelebration: string;

  constructor(private _footballService: FootballInteractionService) {
    this.currentSeason = '2020-21';
    this.selectedDate = '';
    this.matches = [];
    this.loadingContent = true;
    this.disableSearchBtn = true;
    this.displayCelebration = 'noCelebration';
  }

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

  handleClickedSeason(clickedSeason: string) {
    this.selectedDate = '';
    // get the new records by season clicked
    this.audio = new Audio();
    this.audio.src = '../../assets/matchPlayed.mp3';
    this.audio.load();
    this.audio.play();

    this.loadingContent = true;
    this.currentSeason = clickedSeason;
    this._footballService
      .getMatchesBySeason(clickedSeason)
      .subscribe((data) => {
        this.matches = data;
        this.generateClubLogo();
        this.loadingContent = false;
      });
  }

  // this is for the search btn
  handleSearchSelectedDate() {
    this.audio = new Audio();
    this.audio.src = '../../assets/matchPlayed.mp3';
    this.audio.load();
    this.audio.play();
    this.disableSearchBtn = true;

    this.loadingContent = true;
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

  // Generate match
  generateMatch() {
    this.selectedDate = '';
    this.displayCelebration = 'celebration__theme';
    this.audio = new Audio();
    this.audio.src = '../../assets/matchPlayed.mp3';
    this.audio.load();
    this.audio.play();

    this.loadingContent = true;
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
    console.log(this.matches.length);
    console.log(this.clubLogo);
  }
}
