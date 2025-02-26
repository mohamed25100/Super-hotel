import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CityService } from 'src/app/services/city.service';
import { City } from 'src/app/model/city.model';

@Component({
  selector: 'app-add-city',
  templateUrl: './add-city.component.html',
  styleUrls: ['./add-city.component.css']
})

export class AddCityComponent {
  city: City = new City(0, ''); // Initialisation d'une ville vide
  errorMessage: string | null = null;

  constructor(private cityService: CityService, private router: Router) {}

  // Méthode pour ajouter une ville
  addCity(): void {
    if (this.city.name.trim()) {
      this.cityService.addCity(this.city).subscribe({
        next: (data) => {
          console.log("Ville ajoutée avec succès : ", data);
          this.router.navigate(['/admin']); // Redirection vers la page admin après ajout
        },
        error: (err) => {
          this.errorMessage = "Erreur lors de l'ajout de la ville : " + err.message;
        },
      });
    } else {
      this.errorMessage = "Le nom de la ville ne peut pas être vide.";
    }
  }

  // Méthode pour annuler l'ajout et revenir à la page précédente
  cancel(): void {
    this.router.navigate(['/admin']);
  }
}
