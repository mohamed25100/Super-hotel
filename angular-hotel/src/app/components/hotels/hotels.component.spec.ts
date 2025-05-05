import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HotelsComponent } from './hotels.component';
import { HttpClientModule } from '@angular/common/http';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

describe('HotelsComponent', () => {
  let component: HotelsComponent;
  let fixture: ComponentFixture<HotelsComponent>;
  let hotelsServiceSpy: { getHotels: jasmine.Spy; };
  let httpClientSpy: { get: jasmine.Spy; };

  beforeEach(async () => {
    httpClientSpy = jasmine.createSpyObj('HttpClient', ['get']);
    hotelsServiceSpy = jasmine.createSpyObj('HotelsService', ['getHotels']);
    hotelsServiceSpy.getHotels.and.returnValue(of([
      {
        id: 1,
        name: 'Hotel 1',
        address: 'Address 1',
        phone: '0123456789',
        stars: 5,
        pricePerNight: 100,
        availableRooms: 10,
        city: { id: 1, name: 'City 1' },
        manager: { id: 1, firstName: 'John', lastName: 'Doe' },
        imageUrl: 'http://localhost:8080/images/none.png',
      },
      {
        id: 2,
        name: 'Hotel 2',
        address: 'Address 2',
        phone: '0123456789',
        stars: 4,
        pricePerNight: 200,
        availableRooms: 20,
        city: { id: 2, name: 'City 2' },
        manager: { id: 2, firstName: 'Jane', lastName: 'Doe' },
        imageUrl: 'http://localhost:8080/images/none.png',
      }
    ]));
    await TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        HttpClientModule
      ],
      declarations: [ HotelsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HotelsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
