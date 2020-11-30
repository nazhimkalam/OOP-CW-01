import { WelcomeInteractionService } from './../service/welcome-interaction.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css'],
})
export class WelcomeComponent implements OnInit {

  constructor(private welcomeInteractionService: WelcomeInteractionService) {}

  ngOnInit(): void {}

  handleWelcome() {
    this.welcomeInteractionService.sendMessage(true)
  }
}
