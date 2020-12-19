import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatButtonModule } from '@angular/material/button';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { AboutComponent } from './about/about.component';
import { TableComponent } from './table/table.component';
import { MatchesComponent } from './matches/matches.component';
import { PlayersComponent } from './players/players.component';
import { FooterComponent } from './footer/footer.component';
import { ErrorComponent } from './error/error.component';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { SponsorComponent } from './sponsor/sponsor.component';
import { NewTableComponent } from './new-table/new-table.component'

@NgModule({
  // this is were the declaration of the modules go when you create a new component
  declarations: [
    AppComponent,
    WelcomeComponent,
    NavBarComponent,
    AboutComponent,
    TableComponent,
    MatchesComponent,
    PlayersComponent,
    FooterComponent,
    ErrorComponent,
    SponsorComponent,
    NewTableComponent,
  ],

  // importing angular modules
  imports: [BrowserModule, BrowserAnimationsModule, MatButtonModule, AppRoutingModule, HttpClientModule],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
