import { FootballInteractionService } from './../service/football-interaction.service';
import { Component, OnInit } from '@angular/core';

export class Match {
  constructor(
    public club1_name: string,
    public club1_score: string,
    public club1_logo: number,
    public club2_name: string,
    public club2_score: string,
    public club2_logo: number,
    public matchType: string,
    public date: string
  ) {}
}

@Component({
  selector: 'app-matches',
  templateUrl: './matches.component.html',
  styleUrls: ['./matches.component.css'],
})
export class MatchesComponent implements OnInit {
  public matches: Match[] = [
    new Match(
      'Barcelona',
      '4',
      Math.floor(Math.random() * Math.floor(23)) + 1,
      'Juventus',
      '5',
      Math.floor(Math.random() * Math.floor(23)) + 1,
      'Away',
      '3/5/2020'
    ),
    new Match(
      'Barcelona',
      '4',
      Math.floor(Math.random() * Math.floor(23)) + 1,
      'Juventus',
      '5',
      Math.floor(Math.random() * Math.floor(23)) + 1,
      'Away',
      '3/5/2020'
    ),
    new Match(
      'Barcelona',
      '4',
      Math.floor(Math.random() * Math.floor(23)) + 1,
      'Juventus',
      '5',
      Math.floor(Math.random() * Math.floor(23)) + 1,
      'Away',
      '3/5/2020'
    ),
    new Match(
      'Barcelona',
      '4',
      Math.floor(Math.random() * Math.floor(23)) + 1,
      'Juventus',
      '5',
      Math.floor(Math.random() * Math.floor(23)) + 1,
      'Away',
      '3/5/2020'
    ),
    new Match(
      'Barcelona',
      '4',
      Math.floor(Math.random() * Math.floor(23)) + 1,
      'Juventus',
      '5',
      Math.floor(Math.random() * Math.floor(23)) + 1,
      'Away',
      '3/5/2020'
    ),
  ];
  public currentSeason: string; // this is a must
  public seasons: string[]; // other seasons
  public selectedDate: string; // useSelectedDate

  constructor(private _footballService: FootballInteractionService) {
    this.currentSeason = '2020-21';
    this.selectedDate = '';
  }

  ngOnInit(): void {
     // we have to set the seasons here when the user loads this page
     this._footballService.getSeasons().subscribe( data => this.seasons = data);
  }

  handleClickedSeason(clickedSeason: string) {
    // get the new records by season clicked
    console.log(`loading data for ${clickedSeason}`);
  }

  // this is for the search btn
  handleSearchSelectedDate() {
    console.log(
      ' This is the selected date by the user ---> ' + this.selectedDate
    );
  }

  // setting the selected data by the user to the variable for searching
  setSelectedDate(date: string) {
    this.selectedDate = date;
  }

  // Generate match
  generateMatch() {
    console.log('generating match . . .');
  }
}
