import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HotelDetailsComponent } from './hotel-details.component';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientModule } from '@angular/common/http';

describe('HotelDetailsComponent', () => {
  let component: HotelDetailsComponent;
  let fixture: ComponentFixture<HotelDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        HttpClientModule
      ],
      declarations: [ HotelDetailsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HotelDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
