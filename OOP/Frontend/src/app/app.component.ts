import { WelcomeInteractionService } from './service/welcome-interaction.service';
import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  visibleNavFooter = false;

  // visibleNavFooter this makes the nav bar and the footer invisible when displaying the home page
  // and makes it visible when displaying the important components
  constructor(private welcomeInteractionService: WelcomeInteractionService) {
    this.welcomeInteractionService.welcomePageMessage.subscribe((message) => {
      this.visibleNavFooter = message;
    });
  }
  
}
