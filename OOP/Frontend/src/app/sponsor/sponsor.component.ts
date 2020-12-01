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
      'https://logos-download.com/wp-content/uploads/2016/11/EA_Sports_logo_logotype.png',
      'Lead Partner'
    ),
    new Sponsor(
      'https://assets.stickpng.com/images/584825d2cef1014c0b5e49d6.png',
      'Official Bank'
    ),
    new Sponsor(
      'https://assets.stickpng.com/images/585e5d9dcb11b227491c33ea.png',
      'Official Beer'
    ),
    new Sponsor(
      'https://assets.stickpng.com/images/58737bd3f3a71010b5e8ef37.png',
      'Official Soft Drink'
    ),
    new Sponsor(
      'https://upload.wikimedia.org/wikipedia/commons/a/ab/Hublot_logo.png',
      'Official Timekeeper'
    ),
    new Sponsor(
      'https://i.pinimg.com/originals/20/60/2d/20602d43cc993811e5a6bd1886af4f33.png',
      'Official Ball'
    ),
    new Sponsor(
      'https://upload.wikimedia.org/wikipedia/commons/thumb/a/a1/Avery-Dennison-Logo.svg/1280px-Avery-Dennison-Logo.svg.png',
      'Official Licensee'
    ),
  ];

  constructor() {}

  ngOnInit(): void {}
}
