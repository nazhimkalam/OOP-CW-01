import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class WelcomeInteractionService {

  //This service is used to not display the navabar and the footer for the welcome page
  private _welcomePageMessage = new Subject<boolean>();
  welcomePageMessage = this._welcomePageMessage.asObservable();

  constructor() { }

  sendMessage(message: boolean){
    this._welcomePageMessage.next(message);
  }
}
