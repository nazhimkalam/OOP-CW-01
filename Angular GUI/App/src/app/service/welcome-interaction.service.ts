import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class WelcomeInteractionService {

  private _welcomePageMessage = new Subject<boolean>();
  welcomePageMessage = this._welcomePageMessage.asObservable();

  constructor() { }


  sendMessage(message: boolean){
    this._welcomePageMessage.next(message);
  }
}
