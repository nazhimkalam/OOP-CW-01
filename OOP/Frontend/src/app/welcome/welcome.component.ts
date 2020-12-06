import { WelcomeInteractionService } from './../service/welcome-interaction.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css'],
})
export class WelcomeComponent implements OnInit {

  // injecting the service
  constructor(private welcomeInteractionService: WelcomeInteractionService) {}

  ngOnInit(): void {}

  // when the welcome button is clicked it sets the send message as true so that we can display the nav bar
  // and footer
  handleWelcome() {
    this.welcomeInteractionService.sendMessage(true)
  }
}
