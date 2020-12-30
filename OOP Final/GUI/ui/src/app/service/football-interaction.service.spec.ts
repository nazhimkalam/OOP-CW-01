import { TestBed } from '@angular/core/testing';

import { FootballInteractionService } from './football-interaction.service';

describe('FootballInteractionService', () => {
  let service: FootballInteractionService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FootballInteractionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
