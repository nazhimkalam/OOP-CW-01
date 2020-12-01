import { Component, OnInit } from '@angular/core';

export class Sponsor {
  constructor(public imageURL: string, public sponsorName: string) {}
}

@Component({
  selector: 'app-sponsor',
  templateUrl: './sponsor.component.html',
  styleUrls: ['./sponsor.component.css'],
})
export class SponsorComponent implements OnInit {
  public sponsors = [
    new Sponsor(
      '../../assets/sponsorships/eaSports.png',
      'Lead Partner'
    ),
    new Sponsor(
      '../../assets/sponsorships/barclays.png',
      'Official Bank'
    ),
    new Sponsor(
      '../../assets/sponsorships/bud.png',
      'Official Beer'
    ),
    new Sponsor(
      '../../assets/sponsorships/coca.png',
      'Official Soft Drink'
    ),
    new Sponsor(
      '../../assets/sponsorships/Hublot_logo.png',
      'Official Timekeeper'
    ),
    new Sponsor(
      '../../assets/sponsorships/nike.png',
      'Official Ball'
    ),
    new Sponsor(
      '../../assets/sponsorships/Avery-Dennison-Logo.svg.png',
      'Official Licensee'
    ),
  ];

  constructor() {}

  ngOnInit(): void {}
}
