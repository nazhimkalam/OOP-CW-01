import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css'],
})
export class NavBarComponent implements OnInit {
  // variables 
  private linkNames: string[];
  private activeLinks: string[];

  // getters
  public getLinkNames(){
    return this.linkNames;
  }

  public getActiveLinks(){
    return this.activeLinks;
  }

  // setters
  public setLinkNames(data: string[]){
    this.linkNames = data
  }

  public setActiveLinks(data: string[]){
    this.activeLinks = data
  }

  // constructor
  public constructor() {
    this.linkNames = ['about', 'table', 'matches', 'players'];
    this.activeLinks = [];
  }

  public ngOnInit(): void {
    this.activeLinks[0] = 'active';
  }

  // THIS IS TO MAKE THE ACTIVE LINKS VISIBLE IN THE NAV BAR
  public onHandleClick(linkName: string) {
    this.activeLinks = [];
    this.activeLinks[this.linkNames.indexOf(linkName)] = 'active';
  }
}
