import { WelcomeInteractionService } from './service/welcome-interaction.service';
import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  private visibleNavFooter = false;

  public getVisibleNavFooter(){
    return this.visibleNavFooter;
  }
  // visibleNavFooter this makes the nav bar and the footer invisible when displaying the home page
  // and makes it visible when displaying the important components
  public constructor(private welcomeInteractionService: WelcomeInteractionService) {
    this.welcomeInteractionService.getWelcomePageMessage().subscribe((message) => {
      this.visibleNavFooter = message;
    });
  }
  
}
