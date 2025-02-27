import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { City } from 'src/app/model/city.model';
import { Hotel } from 'src/app/model/hotel.model';
import { CityService } from 'src/app/services/city.service';
import { HotelService } from 'src/app/services/hotel.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  listHotels: Hotel[] = [];
  listCities: City[] = [];
  error = null;

  constructor(private hotelService: HotelService,private cityService: CityService, private router: Router) {}

  ngOnInit(): void {
    this.getAllHotels();
    this.getAllCities();
  }

  getAllHotels() {
    this.hotelService.getHotels().subscribe({
      next: (data) => this.listHotels = data,
      error: (err) => (this.error = err.message),
      complete: () => (this.error = null),
    });
  }

  getAllCities() {
    this.hotelService.getCities().subscribe({
      next: (data) => this.listCities = data,
      error: (err) => this.error = err.message,
      complete: () => (this.error = null),
    });
  }

  openAddHotelModal() {
    this.router.navigate(['/admin/add-hotel']);
  }

  editHotel(hotelId: number) {
    this.router.navigate(['/admin/edit-hotel', hotelId]);
  }

  deleteHotel(hotelId: number) {
    if (confirm("Voulez-vous vraiment supprimer cet hôtel ?")) {
      this.hotelService.deleteHotel(hotelId).subscribe({
        next: () => {
          this.listHotels = this.listHotels.filter(h => h.id !== hotelId);
          alert("Hôtel supprimé avec succès !");
        },
        //error: (err) => alert("Erreur lors de la suppression : " + err.message),
        error: (err) => alert("Impossible de supprimer cette l'hôtel car il contient des chambres."),
      });
    }
  }

  // 🔹 Redirection pour ajouter une ville
  openAddCityModal() { 
    this.router.navigate(['/admin/add-city']); 
  }

  // 🔹 Redirection pour modifier une ville
  openEditCityModal(cityId: number) {
    this.router.navigate(['/admin/edit-city', cityId]);
  }

  // 🔹 Supprimer une ville
  deleteCity(cityId: number) {
    if (confirm("Voulez-vous vraiment supprimer cette ville ?")) {
      this.cityService.deleteCity(cityId).subscribe({
        next: () => {
          this.listCities = this.listCities.filter(c => c.id !== cityId);
          alert("Ville supprimée avec succès !");
        },
        //error: (err) => alert("Erreur lors de la suppression : " + err.message),
        error: (err) => alert("Impossible de supprimer cette ville car elle contient des hôtels."),
      });
    }
  }
}
