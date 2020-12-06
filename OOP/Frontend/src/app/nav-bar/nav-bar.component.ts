import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css'],
})
export class NavBarComponent implements OnInit {
  // variables 
  public linkNames: string[];
  public activeLinks: string[];

  // constructor
  constructor() {
    this.linkNames = ['about', 'table', 'matches', 'players'];
    this.activeLinks = [];
  }

  ngOnInit(): void {
    this.activeLinks[0] = 'active';
  }

  // THIS IS TO MAKE THE ACTIVE LINKS VISIBLE IN THE NAV BAR
  onHandleClick(linkName: string) {
    this.activeLinks = [];
    this.activeLinks[this.linkNames.indexOf(linkName)] = 'active';
  }
}
