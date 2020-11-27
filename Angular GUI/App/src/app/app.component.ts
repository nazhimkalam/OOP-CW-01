import { WelcomeInteractionService } from './service/welcome-interaction.service';
import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  visibleNavFooter = false;
  constructor(private welcomeInteractionService: WelcomeInteractionService) {
    this.welcomeInteractionService.welcomePageMessage.subscribe((message) => {
      this.visibleNavFooter = message;
    });
  }
}
