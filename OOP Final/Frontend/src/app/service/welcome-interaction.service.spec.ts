import { TestBed } from '@angular/core/testing';

import { WelcomeInteractionService } from './welcome-interaction.service';

describe('WelcomeInteractionService', () => {
  let service: WelcomeInteractionService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(WelcomeInteractionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
