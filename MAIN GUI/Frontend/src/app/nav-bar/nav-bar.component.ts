import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css'],
})
export class NavBarComponent implements OnInit {
  public linkNames: string[] = ['about', 'table', 'matches', 'players'];
  public activeLinks: string[] = [];

  constructor() {}

  ngOnInit(): void {
    this.activeLinks[0] = 'active'
  }

  // THIS IS TO MAKE THE ACTIVE LINKS VISIBLE IN THE NAV BAR
  onHandleClick(linkName: string) {
    this.activeLinks = [];
    this.activeLinks[this.linkNames.indexOf(linkName)] = 'active';
    
  }
}
