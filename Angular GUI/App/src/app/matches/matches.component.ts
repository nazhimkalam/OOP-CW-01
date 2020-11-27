import { Component, OnInit } from '@angular/core';

export class Match {
  constructor(
    public club1_name: string,
    public club1_score: number,
    public club1_logo: number,
    public club2_name: string,
    public club2_score: number,
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
  matches: Match[] = [
    new Match(
      'Barcelona',
      4,
      Math.floor(Math.random() * Math.floor(23)) + 1,
      'Juventus',
      5,
      Math.floor(Math.random() * Math.floor(23)) + 1,
      'Away',
      '3/5/2020'
    ),
    new Match(
      'Barcelona',
      4,
      Math.floor(Math.random() * Math.floor(23)) + 1,
      'Juventus',
      5,
      Math.floor(Math.random() * Math.floor(23)) + 1,
      'Away',
      '3/5/2020'
    ),
    new Match(
      'Barcelona',
      4,
      Math.floor(Math.random() * Math.floor(23)) + 1,
      'Juventus',
      5,
      Math.floor(Math.random() * Math.floor(23)) + 1,
      'Away',
      '3/5/2020'
    ),
    new Match(
      'Barcelona',
      4,
      Math.floor(Math.random() * Math.floor(23)) + 1,
      'Juventus',
      5,
      Math.floor(Math.random() * Math.floor(23)) + 1,
      'Away',
      '3/5/2020'
    ),
    new Match(
      'Barcelona',
      4,
      Math.floor(Math.random() * Math.floor(23)) + 1,
      'Juventus',
      5,
      Math.floor(Math.random() * Math.floor(23)) + 1,
      'Away',
      '3/5/2020'
    ),
  ];

  constructor() {}

  ngOnInit(): void {}
}
