import { Component, OnInit } from '@angular/core';
import { City } from 'src/app/model/city.model';
import { Hotel } from 'src/app/model/hotel.model';
import { HotelService } from 'src/app/services/hotel.service';
@Component({
  selector: 'app-hotels',
  templateUrl: './hotels.component.html',
  styleUrls: ['./hotels.component.css']
})
export class HotelsComponent implements OnInit{
  listHotels: Hotel[] | undefined;
  listCities: City[] | undefined;
  error = null;

  constructor(private hotelService : HotelService) {

  }

  ngOnInit(): void {
    this.getAllHotels();
    this.getAllCities();
  }

  getAllHotels() {
    this.hotelService.getHotels().subscribe({
      next: (data) => (this.listHotels = data),
      error: (err) => (this.error = err.message),
      complete: () => (this.error = null),
    })
  }

  getAllCities() {
    this.hotelService.getCities().subscribe({
      next: (data) => this.listCities = data,
      error: (err) => this.error = err.message,
      complete: () => this.error = null
    });
  }

}
