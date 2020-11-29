import { Component, OnInit } from '@angular/core';


export class ClubDetails {
  constructor(
    public position: string,
    public clubName: string,
    public numberOfPlayed: string,
    public numberOfWins: string,
    public numberOfDraws: string,
    public numberOfLosses: string,
    public totalGoalScored: string,
    public totalGoalReceived: string,
    public totalGoalDifference: string,
    public totalPoints: string,
    
  ) {}
}

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css'],
})
export class TableComponent implements OnInit {
  resultsRecords: ClubDetails[]; // rows data
  currentSeason: string;   // this is a must 
  seasons: string[];       // other seasons

  constructor() {
    this.resultsRecords = [];
    this.currentSeason = '2020-21';
  }

  ngOnInit(): void {
    for (let index = 0; index < 50; index++) {
      this.resultsRecords.push(new ClubDetails('1','Juventus','5','2','1','2','10','9','1','30'));
    }
    this.seasons = ['2020-21', '2019-20', '2018-19'];
  }

  sortByPoints() {
    // sort by points
    console.log('Sorting rows by points . . .');
  }

  sortByGoals() {
    // sort by goals
    console.log('Sorting rows by goals . . .');
  }

  sortByWins() {
    // sort by wins
    console.log('Sorting rows by wins . . .');
  }

  handleClickedSeason(clickedSeason: string) {
    // get the new records by season clicked
    console.log(`loading data for ${clickedSeason}`);
  }
}
