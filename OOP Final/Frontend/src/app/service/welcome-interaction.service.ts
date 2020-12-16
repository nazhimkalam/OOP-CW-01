import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class WelcomeInteractionService {

  //This service is used to not display the navabar and the footer for the welcome page

  // variables
  private _welcomePageMessage: Subject<boolean>;
  private welcomePageMessage: any;

  // getters
  public getWelcomePageMessage(){
    return this.welcomePageMessage;
  }

  // constructors
  public constructor() {
    this._welcomePageMessage  = new Subject<boolean>();
    this.welcomePageMessage = this._welcomePageMessage.asObservable()
   }

  // this method changes the boolean to display the navbar and footer or not.
  public sendMessage(message: boolean){
    this._welcomePageMessage.next(message);
  }
}
