import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Hotel } from 'src/app/model/hotel.model';
import { HotelService } from 'src/app/services/hotel.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  listHotels: Hotel[] = [];
  error = null;

  constructor(private hotelService: HotelService, private router: Router) {}

  ngOnInit(): void {
    this.getAllHotels();
  }

  getAllHotels() {
    this.hotelService.getHotels().subscribe({
      next: (data) => this.listHotels = data,
      error: (err) => (this.error = err.message),
      complete: () => (this.error = null),
    });
  }

  openAddHotelModal() {
    this.router.navigate(['/admin/add-hotel']); // Redirige vers le formulaire d'ajout
  }

  editHotel(hotelId: number) {
    this.router.navigate(['/admin/edit-hotel', hotelId]); // Redirige vers le formulaire de modification
  }

  deleteHotel(hotelId: number) {
    if (confirm("Voulez-vous vraiment supprimer cet hôtel ?")) {
      this.hotelService.deleteHotel(hotelId).subscribe({
        next: () => {
          this.listHotels = this.listHotels.filter(h => h.id !== hotelId);
          alert("Hôtel supprimé avec succès !");
        },
        error: (err) => alert("Erreur lors de la suppression : " + err.message),
      });
    }
  }
}
