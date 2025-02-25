import { Component, OnInit } from '@angular/core';
import { Hotel } from 'src/app/model/hotel.model';
import { HotelService } from 'src/app/services/hotel.service';
@Component({
  selector: 'app-hotels',
  templateUrl: './hotels.component.html',
  styleUrls: ['./hotels.component.css']
})
export class HotelsComponent implements OnInit{
  listHotels: Hotel[] | undefined;
  error = null;

  constructor(private hotelService : HotelService) {

  }

  ngOnInit(): void {
    this.getAllHotels();
  }

  getAllHotels() {
    this.hotelService.getHotels().subscribe({
      next: (data) => {
        console.log("Hotels data:", data);
        this.listHotels = data;
      },
      error: (err) => (this.error = err.message),
      complete: () => (this.error = null),
    })
  }

}
