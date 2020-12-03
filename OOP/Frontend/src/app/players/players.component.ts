import { Component, OnInit } from '@angular/core';

export class Player {
  constructor(
    public name: string,
    public imageUrl: string,
    public position: string,
    public teamName: string
  ) {}
}

@Component({
  selector: 'app-players',
  templateUrl: './players.component.html',
  styleUrls: ['./players.component.css'],
})
export class PlayersComponent implements OnInit {
  public players: Player[] = [
    new Player(
      'Cristiano Ronaldo',
      'https://talksport.com/wp-content/uploads/sites/5/2020/01/GettyImages-1192179860.jpg?strip=all&w=960&quality=100',
      'Forward',
      'Juventus'
    ),
    new Player(
      'Lionel Messi',
      'https://e0.365dm.com/20/09/768x432/skysports-lionel-messi-barcelona_5113303.jpg?20200929233110',
      'Forward',
      'Barcelona'
    ),
    new Player(
      'Neymar JR',
      'https://img.bleacherreport.net/img/images/photos/003/769/883/hi-res-b12f08482b83ecc478d0e9708320a6d3_crop_north.jpg?1539843641&w=3072&h=2048',
      'Forward',
      'Paris Saint-Germain'
    ),
    new Player(
      'Mohamed Salah',
      'https://t1.gstatic.com/images?q=tbn:ANd9GcRjYYL6HNd6tdsEFOdh2jashcKmEVGYt7kEGxbgqN1E0kYsXCJvP-nuV7GLz0Q7',
      'Forward',
      'Liverpool'
    ),
    new Player(
      'Gareth Bale',
      'https://i2-prod.walesonline.co.uk/incoming/article18724514.ece/ALTERNATES/s615/0_GettyImages-1201483728.jpg',
      'Forward',
      'Tottenham'
    ),
    new Player(
      'Paul Pogba',
      'https://images2.minutemediacdn.com/image/fetch/w_736,h_485,c_fill,g_auto,f_auto/https%3A%2F%2Freddevilarmada.com%2Fwp-content%2Fuploads%2Fgetty-images%2F2020%2F05%2F1190666177-850x560.jpeg',
      'Midfilder',
      'Man United'
    ),
    new Player(
      'James Rodriguez',
      'https://images.daznservices.com/di/library/GOAL/2e/86/james-rodriguez-everton-2020-21_6wlqlm929ch51khaya8g1dddc.jpg?t=-226409164&quality=100',
      'Midfielder',
      'Everton'
    ),
    new Player(
      'Bruno Fernandas',
      'https://images.daznservices.com/di/library/GOAL/f6/8c/bruno-fernandes-manchester-united-2019-20_h30alk79c52l155kge00jlhoz.jpg?t=-1932164368&quality=100',
      'Midfielder',
      'Man United'
    ),
    new Player(
      'Timo Werner',
      'https://img.bundesliga.com/tachyon/sites/2/2018/12/GettyImages-1074111228-2.jpg?crop=611px,0px,3058px,2447px',
      'Forward',
      'Chelsea'
    ),
    new Player(
      'Christian Pulisic',
      'https://images.daznservices.com/di/library/GOAL/9f/9c/christian-pulisic-chelsea_qhz7fbcw3hdr1848z2ts97y1y.jpg?t=1115640851&quality=100',
      'Midfielder',
      'Chelsea'
    ),
    new Player(
      'Kai Havertz',
      'https://www.talkchelsea.net/wp-content/uploads/2020/07/kai-havertz.jpg',
      'Midfielder',
      'Chelsea'
    ),
    new Player(
      'Jamie Vardy',
      'https://ichef.bbci.co.uk/news/1024/cpsprodpb/C757/production/_114113015_jamievardy.jpg',
      'Forward',
      'Leicester City'
    ),
    new Player(
      'Thiago Alcantara',
      'https://images.daznservices.com/di/library/GOAL/6d/87/thiago-alcantara-liverpool-chelsea-2020-21_1qrow2nikwn801n4e45i90t4zo.jpg?t=-1309077228&quality=100',
      'Midfielder',
      'Liverpool'
    ),
    new Player(
      'Mason Greenwood',
      'https://images.daznservices.com/di/library/GOAL/95/5a/mason-greenwood-manchester-united-2019-20_b32uzxtuu6pp10ksaoue0tvhv.jpg?t=1664782883&quality=100',
      'Forward',
      'Man United'
    ),
    new Player(
      'Willian',
      'https://images.daznservices.com/di/library/GOAL/28/3a/willian-chelsea-2019-20_105qyzwsxzgy81sagr6sbpex66.jpg?t=-1469151920&quality=60&w=1200&h=800',
      'Forward',
      'Arsenal'
    ),
    new Player(
      'Diogo Jota',
      'https://i2-prod.liverpool.com/incoming/article19336800.ece/ALTERNATES/s615/0_Jota.jpg',
      'Forward',
      'Liverpool'
    ),
    new Player(
      'Jack Grealish',
      'https://images.daznservices.com/di/library/GOAL/7a/de/jack-grealish-aston-villa_amq9p5p1xurj1ohwle48mh5wm.jpg?t=-1648035394&quality=100',
      'Midfielder',
      'Aston Villa'
    ),
    new Player(
      'Danny lngs',
      'https://images2.minutemediacdn.com/image/upload/c_fill,w_912,h_516,f_auto,q_auto,g_auto/shape/cover/sport/newcastle-united-v-southampton-fc-premier-league-5e19b3f77bf345ceb1000001.jpg',
      'Forward',
      'Southampton'
    ),
    new Player(
      'Michail Anonio',
      'https://talksport.com/wp-content/uploads/sites/5/2020/10/NINTCHDBPICT000616215883-e1604007246711.jpg?strip=all&w=960&quality=100',
      'Midfielder',
      'West Ham'
    ),
    new Player(
      'Kepa Arrizabalaga',
      'https://sportsalert.org/wp-content/uploads/2020/09/_chelsea-boss-frank-lampard-says-kepa-arrizabalaga-needs-his-support-after-latest-mistake.jpg',
      'Goalkeeper',
      'Chelsea'
    ),
    new Player(
      'Wilfried Zaha',
      'https://imgresizer.eurosport.com/unsafe/1200x0/filters:format(jpeg):focal(1369x479:1371x477)/origin-imgresizer.eurosport.com/2020/11/23/2942187-60399708-2560-1440.jpg',
      'Forward',
      'Crystal'
    ),
    new Player(
      'Hakim Ziyech',
      'https://images.daznservices.com/di/library/GOAL/a7/2e/hakim-ziyech-chelsea-2020-21_174wjv2xkjsv412px3n0gcaunx.jpg?t=-2064621035&quality=100',
      'Midfielder',
      'Chelsea'
    ),
    new Player(
      'Takumi Minamino',
      'https://images.daznservices.com/di/library/GOAL/28/3a/takumi-minamino-liverpool-2019-20_1v3y00fakwu3f1tqghhyl8fz5m.jpg?t=-543607265&quality=100',
      'Forward',
      'Liverpool'
    ),
    new Player(
      'Marcus Rashford',
      'https://c.files.bbci.co.uk/1000E/production/_112105556_gettyimages-1162543444.jpg',
      'Forward',
      'Man United'
    ),
    new Player(
      'Jesse Lingard',
      'https://www.thesun.co.uk/wp-content/uploads/2020/08/c9e6e39f-13e9-4bcc-bb1e-7edaf9583321.jpg',
      'Midfielder',
      'Man United'
    ),
    new Player(
      'Callum Wilson',
      'https://e0.365dm.com/20/09/2048x1152/skysports-callum-wilson-newcastle-united_5089625.jpg',
      'Forward',
      'Newcastle'
    ),
  ];
  constructor() {}

  ngOnInit(): void {}
}
