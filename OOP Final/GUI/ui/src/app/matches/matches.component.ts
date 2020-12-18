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
  private matches: MatchPlayed[];
  private currentSeason: string;
  private seasons: string[];
  private selectedDate: string;
  private clubLogo: number[];
  private loadingContent: boolean;
  private audio: any;
  private displayCelebration: string;
  private validationDate__visible: string;
  private noMatchesAvailable: boolean;
  private displaySearchButton: boolean;
  private matchGenerateHeaderMessage: string;
  private matchGenerateBodyMessage: string;
  private headerModalColor: string;
  private tempTotalMatches: number;

  // constructor for initialization
  public constructor(private _footballService: FootballInteractionService) {
    this.currentSeason = '2020-21';
    this.selectedDate = '';
    this.noMatchesAvailable = false;
    this.matches = [];
    this.loadingContent = true;
    this.displayCelebration = 'noCelebration';
    this.validationDate__visible = 'validationDate__invisible';
    this.displaySearchButton = true;
    this.tempTotalMatches = 0;
  }

  // runs just after the constructor
  public ngOnInit(): void {
    // we have to set the seasons here when the user loads this page
    this._footballService
      .getSeasons()
      .subscribe((data) => (this.seasons = data));

    // getting the matches for the current season
    this._footballService
      .getMatchesBySeason(this.currentSeason)
      .subscribe((data) => {
        // the temTotalMatches stores the total number of matches currently for checking
        // purpose when generating match(match limit)
        this.matches = data;
        this.tempTotalMatches = this.matches.length;
        this.generateClubLogo();
        this.loadingContent = false;
        this.validationDate__visible = 'validationDate__invisible';
        this.displaySearchButton = true;

        // if the matches list is empty we display the div container for no matches
        if (this.matches.length === 0) {
          this.noMatchesAvailable = true;
        } else {
          this.noMatchesAvailable = false;
        }
      });
  }

  // this method runs when the user selects a season
  public handleClickedSeason(clickedSeason: string) {
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
        // the temTotalMatches stores the total number of matches currently for checking
        // purpose when generating match(match limit)
        this.matches = data;
        this.tempTotalMatches = this.matches.length;
        this.generateClubLogo();
        this.loadingContent = false;
        this.validationDate__visible = 'validationDate__invisible';
        this.displaySearchButton = true;

        // if the matches list is empty we display the div container for no matches
        if (this.matches.length === 0) {
          this.noMatchesAvailable = true;
        } else {
          this.noMatchesAvailable = false;
        }
      });
  }

  // this method runs when the user selects a date
  public handleSearchSelectedDate() {
    if (this.selectedDate !== '' && this.selectedDate !== null) {
      // changes the variables accordingly when season changes
      this.audio = new Audio();
      this.audio.src = '../../assets/matchPlayed.mp3';
      this.audio.load();
      this.audio.play();
      this.loadingContent = true;
      this.displaySearchButton = false;

      // using the service to get the matches by date
      this._footballService
        .getMatchesByDate(this.selectedDate, this.currentSeason)
        .subscribe((data) => {
          this.matches = data;
          this.generateClubLogo();
          this.loadingContent = false;
          this.validationDate__visible = 'validationDate__invisible';

          // if the matches list is empty we display the div container for no matches
          if (this.matches.length === 0) {
            this.noMatchesAvailable = true;
          } else {
            this.noMatchesAvailable = false;
          }
        });
      this.generateClubLogo();
    } else {
      this.validationDate__visible = 'validationDate__visible';
    }
  }

  // setting the selected data by the user to the variable for searching
  public setSelectedDate(date: string) {
    // validating the date
    var dateReg = /^\d{4}[-]\d{2}[-]\d{2}$/;

    console.log(date.match(dateReg));
    if (date === '') {
      this.validationDate__visible = 'validationDate__invisible';
      this.selectedDate = null;
    } else if (date.match(dateReg) === null) {
      this.validationDate__visible = 'validationDate__visible';
      this.selectedDate = null;
    } else {
      this.validationDate__visible = 'validationDate__invisible';
      this.selectedDate = date;
    }
  }

  // The reset button reloads the data for the current season selected
  public handleReset() {
    this.selectedDate = '';

    this._footballService
      .getMatchesBySeason(this.getCurrentSeason())
      .subscribe((data) => {
        this.matches = data;
        this.generateClubLogo();
        this.loadingContent = false;
        this.validationDate__visible = 'validationDate__invisible';
        this.displaySearchButton = true;

        // if the matches list is empty we display the div container for no matches
        if (this.matches.length === 0) {
          this.noMatchesAvailable = true;
        } else {
          this.noMatchesAvailable = false;
        }
      });
  }

  // When the user closed the modal we again load the matches
  public handleCloseModal() {
    this._footballService
      .getMatchesBySeason(this.getCurrentSeason())
      .subscribe((data) => {
        // the temTotalMatches stores the total number of matches currently for checking
        // purpose when generating match(match limit)
        this.matches = data;
        this.tempTotalMatches = this.matches.length;
        this.generateClubLogo();
        this.loadingContent = false;
        this.validationDate__visible = 'validationDate__invisible';
        this.displaySearchButton = true;

        // if the matches list is empty we display the div container for no matches
        if (this.matches.length === 0) {
          this.noMatchesAvailable = true;
        } else {
          this.noMatchesAvailable = false;
        }
      });
  }

  // this method runs when the user clicks the generate button
  public generateMatch() {
    // changes the variables accordingly when season changes
    this.selectedDate = '';
    this.audio = new Audio();
    this.audio.src = '../../assets/matchPlayed.mp3';
    this.audio.load();
    this.audio.play();
    this.loadingContent = true;

    // using the service to get all the matches with the generated match
    this._footballService
      .getGeneratedMatchesBySeason(this.currentSeason)
      .subscribe((data) => {
        // the temTotalMatches stores the total number of matches currently for checking
        // purpose when generating match(match limit)
        this.matches = data;
        this.matchGenerateHeaderMessage = 'Error!';
        this.headerModalColor = '#FF0134';

        // if the data = null then we change the content of the model
        if (data === null) {
          this.displayCelebration = 'error__theme';
          this.matchGenerateBodyMessage =
            'Cannot generate match, at least two clubs should be present to generate a match';
        
          } else if (this.matches.length === this.tempTotalMatches) {
          this.displayCelebration = 'error__theme';
          this.matchGenerateBodyMessage = "Cannot generate match, this is due to the random club selected has already reached it's maximum number of matches played, please re-generate to select another random club";
        
        } else {
          this.displayCelebration = 'celebration__theme';
          this.headerModalColor = '#2DBF64';
          this.matchGenerateHeaderMessage = 'Congratulations!';
          this.matchGenerateBodyMessage = 'Match Successfully generated.';

        }

        this.generateClubLogo();
        this.loadingContent = false;
        this.validationDate__visible = 'validationDate__invisible';
        this.displaySearchButton = true;

        // if the matches list is empty we display the div container for no matches
        if (this.matches.length === 0) {
          this.noMatchesAvailable = true;
        } else {
          this.noMatchesAvailable = false;
        }

        // In this case the tempTotalMatches has to be updated after the above code is executed
        this.tempTotalMatches = this.matches.length;
      });

    this.generateClubLogo();

    // Setting a delay
    setTimeout(() => {
      this.displayCelebration = 'noCelebration';
    }, 1500);
  }

  // generate random clubLogo
  public generateClubLogo() {
    this.clubLogo = [];
    this.matches.forEach((match) => {
      this.clubLogo.push(Math.floor(Math.random() * Math.floor(23)) + 1);
      this.clubLogo.push(Math.floor(Math.random() * Math.floor(23)) + 1);
      this.clubLogo.push(Math.floor(Math.random() * Math.floor(23)) + 1);
    });
  }

  // setters and getters
  public setMatches(data: MatchPlayed[]) {
    this.matches = data;
  }

  public getMatches() {
    return this.matches;
  }

  public setCurrentSeason(data: string) {
    this.currentSeason = data;
  }

  public getCurrentSeason() {
    return this.currentSeason;
  }

  public setSeason(data: string[]) {
    this.seasons = data;
  }

  public getSeason() {
    return this.seasons;
  }

  public getMatchGenerateHeaderMessage() {
    return this.matchGenerateHeaderMessage;
  }

  public getMatchGenerateBodyMessage() {
    return this.matchGenerateBodyMessage;
  }

  public getSelectedDate() {
    return this.selectedDate;
  }

  public setClubLogo(data: number[]) {
    this.clubLogo = data;
  }

  public getClubLogo() {
    return this.clubLogo;
  }

  public setLoadingContent(data: boolean) {
    this.loadingContent = data;
  }

  public getLoadingContent() {
    return this.loadingContent;
  }

  public setAudio(data: string) {
    this.audio = data;
  }

  public getAudio() {
    return this.audio;
  }

  public getNoMatchesAvailable() {
    return this.noMatchesAvailable;
  }

  public setNoMatchesAvailable(data: boolean) {
    this.noMatchesAvailable = data;
  }

  public getDisplaySearchButton() {
    return this.displaySearchButton;
  }

  public setDisplaySearchButton(data: boolean) {
    this.displaySearchButton = data;
  }

  public setDisplayCelebration(data: string) {
    this.displayCelebration = data;
  }

  public getDisplayCelebration() {
    return this.displayCelebration;
  }

  public setValidationDate__visible(data: string) {
    this.validationDate__visible = data;
  }

  public getValidationDate__visible() {
    return this.validationDate__visible;
  }

  public getHeaderModalColor() {
    return this.headerModalColor;
  }

  public setHeaderModalColor(data: string) {
    this.headerModalColor = data;
  }

  public getTempTotalMatches() {
    return this.tempTotalMatches;
  }

  public setTempTotalMatches(data: number) {
    this.tempTotalMatches = data;
  }
}