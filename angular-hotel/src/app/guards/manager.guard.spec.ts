import { TestBed } from '@angular/core/testing';

import { ManagerGuard } from './manager.guard';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientModule } from '@angular/common/http';

describe('ManagerGuard', () => {
  let guard: ManagerGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        HttpClientModule
      ],
    });
    guard = TestBed.inject(ManagerGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
