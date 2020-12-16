import { Component } from '@angular/core';

// sponsor class
export class Sponsor {
  constructor(public imageURL: string, public sponsorName: string) {}
}

@Component({
  selector: 'app-sponsor',
  templateUrl: './sponsor.component.html',
  styleUrls: ['./sponsor.component.css'],
})
export class SponsorComponent {
  // variable used
  private sponsors: Sponsor[];

  // setter and getter
  public getSponsors(){
    return this.sponsors;
  }

  public setSponsors(data: Sponsor[]){
    this.sponsors = data;
  }

  // constructor
  public constructor() {
    // initializing the sponsor
    this.sponsors = [
      new Sponsor('../../assets/sponsorships/eaSports.png', 'Lead Partner'),
      new Sponsor('../../assets/sponsorships/barclays.png', 'Official Bank'),
      new Sponsor('../../assets/sponsorships/bud.png', 'Official Beer'),
      new Sponsor('../../assets/sponsorships/coca.png', 'Official Soft Drink'),
      new Sponsor(
        '../../assets/sponsorships/Hublot_logo.png',
        'Official Timekeeper'
      ),
      new Sponsor('../../assets/sponsorships/nike.png', 'Official Ball'),
      new Sponsor(
        '../../assets/sponsorships/Avery-Dennison-Logo.svg.png',
        'Official Licensee'
      ),
    ];
  }

}
