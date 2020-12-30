import { ErrorComponent } from './error/error.component';
import { PlayersComponent } from './players/players.component';
import { MatchesComponent } from './matches/matches.component';
import { TableComponent } from './table/table.component';
import { AboutComponent } from './about/about.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

// these are the routes for the website
const routes: Routes = [
  { path: '', component: WelcomeComponent },  // this is the default route http://localhost:9000/
  { path: 'welcome', component: WelcomeComponent },
  { path: 'about', component: AboutComponent },
  { path: 'tables', component: TableComponent },
  { path: 'matches', component: MatchesComponent },
  { path: 'players', component: PlayersComponent },
  { path: '**', component: ErrorComponent },  // this is the route when an error is occurred
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
