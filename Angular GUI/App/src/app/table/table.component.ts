import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css'],
})
export class TableComponent implements OnInit {
  results: string[] = [];

  constructor() {}

  ngOnInit(): void {
    for (let index = 0; index < 50; index++) {
      this.results.push('');
    }
  }
}
