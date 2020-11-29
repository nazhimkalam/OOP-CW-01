import { MatchPlayed } from './../interfaces/MatchPlayed';
import { FootballInteractionService } from './../service/football-interaction.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-matches',
  templateUrl: './matches.component.html',
  styleUrls: ['./matches.component.css'],
})
export class MatchesComponent implements OnInit {
  public matches: MatchPlayed[];
  public currentSeason: string; // this is a must
  public seasons: string[]; // other seasons
  public selectedDate: string; // useSelectedDate
  public clubLogo: number[]; // contains a random number representing the club logo

  constructor(private _footballService: FootballInteractionService) {
    this.currentSeason = '2020-21';
    this.selectedDate = '';
    this.matches = [];
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
      });
  }

  handleClickedSeason(clickedSeason: string) {
    // get the new records by season clicked
    this.currentSeason = clickedSeason;
    this._footballService
      .getMatchesBySeason(clickedSeason)
      .subscribe((data) => {
        this.matches = data;
        this.generateClubLogo();
      });
  }

  // this is for the search btn
  handleSearchSelectedDate() {
    console.log(
      ' This is the selected date by the user ---> ' + this.selectedDate
    );
    this.generateClubLogo();
  }

  // setting the selected data by the user to the variable for searching
  setSelectedDate(date: string) {
    this.selectedDate = date;
  }

  // Generate match
  generateMatch() {
    console.log('generating match . . .');

    this.generateClubLogo();
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
