import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Hotel } from 'src/app/model/hotel.model';
import { HotelService } from 'src/app/services/hotel.service';

@Component({
  selector: 'app-hotel-details',
  templateUrl: './hotel-details.component.html',
  styleUrls: ['./hotel-details.component.css']
})
export class HotelDetailsComponent implements OnInit  {
  hotel!: Hotel;

  constructor(
    private route: ActivatedRoute,
    private hotelService: HotelService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.getHotelDetails();
  }

  getHotelDetails(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.hotelService.getHotelById(+id).subscribe(
        (data) => (this.hotel = data),
        (error) => console.error('Erreur lors de la récupération des détails de l\'hôtel', error)
      );
    }
  }

  bookRoom(): void {
    alert(`Vous avez réservé une chambre à l'hôtel ${this.hotel.name} !`);
    this.router.navigate(['/hotels']);
  }
}
