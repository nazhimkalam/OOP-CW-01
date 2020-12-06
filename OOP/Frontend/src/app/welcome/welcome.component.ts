import { WelcomeInteractionService } from './../service/welcome-interaction.service';
import { Component } from '@angular/core';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css'],
})
export class WelcomeComponent{

  // injecting the service
  public constructor(private welcomeInteractionService: WelcomeInteractionService) {}

  // when the welcome button is clicked it sets the send message as true so that we can display the nav bar
  // and footer
  public handleWelcome() {
    this.welcomeInteractionService.sendMessage(true)
  }
}
