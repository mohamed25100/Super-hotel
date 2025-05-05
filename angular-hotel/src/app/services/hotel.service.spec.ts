import { TestBed } from '@angular/core/testing';

import { HotelService } from './hotel.service';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { of } from 'rxjs';
import { Hotel } from '../model/hotel.model';

describe('HotelService', () => {
  let service: HotelService;
  let httpClientSpy: { get: jasmine.Spy; };

  beforeEach(() => {
    httpClientSpy = jasmine.createSpyObj('HttpClient', ['get']);
    TestBed.configureTestingModule({
      providers: [HotelService,
        { provide: HttpClient, useValue: httpClientSpy }
      ],
      imports: [
        RouterTestingModule,
        HttpClientModule
      ],
    });
    service = TestBed.inject(HotelService);
  });

  it('should return all values in an array', (done: DoneFn) => {
    const expectedResults = [
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
    ];
    httpClientSpy.get.and.returnValue(of(expectedResults));
    service.getHotels().subscribe(hotels => {
      expect(hotels.length).toBe(2);
      expect(hotels).toEqual(expectedResults as Hotel[]);
      done();
    });
  })

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
