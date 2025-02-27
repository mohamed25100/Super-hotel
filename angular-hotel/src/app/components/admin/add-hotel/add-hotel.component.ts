import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { City } from 'src/app/model/city.model';
import { Hotel } from 'src/app/model/hotel.model';
import { User } from 'src/app/model/user.model';
import { CityService } from 'src/app/services/city.service';
import { HotelService } from 'src/app/services/hotel.service';

@Component({
  selector: 'app-add-hotel',
  templateUrl: './add-hotel.component.html',
  styleUrls: ['./add-hotel.component.css']
})
export class AddHotelComponent implements OnInit {
  hotel: Hotel = new Hotel(0, '', '', '', 1, 0, 0, null, null, '');
  cities: City[] = [];
  users: User[] = [];

  constructor(
    private hotelService: HotelService,
    private cityService: CityService,
    private router: Router
  ) {}

  ngOnInit(): void {
    // Récupérer toutes les villes et utilisateurs pour remplir les sélecteurs
    this.cityService.getCities().subscribe(cities => {
      this.cities = cities;
    });
    /*
    this.hotelService.getUsers().subscribe(users => {
      this.users = users;
    });
    */
  }

  onSubmit(): void {
    // Ajouter l'hôtel avec une image vide ("")
    this.hotel.imageUrl = ""; // L'image URL est vide pour l'instant
    this.hotelService.addHotel(this.hotel).subscribe({
      next: (data) => {
        alert("Hôtel ajouté avec succès ! " + data);
        this.router.navigate(['/admin']); // Rediriger vers la page admin après ajout
      },
      error: (err) => {
        alert("Erreur lors de l'ajout de l'hôtel : " + err.message);
      }
    });
  }
}