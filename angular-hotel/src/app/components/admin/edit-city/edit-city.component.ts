import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { City } from 'src/app/model/city.model';
import { CityService } from 'src/app/services/city.service';

@Component({
  selector: 'app-edit-city',
  templateUrl: './edit-city.component.html',
  styleUrls: ['./edit-city.component.css']
})
export class EditCityComponent implements OnInit {
  city: City = new City(0, ''); // Initialisation d'une ville vide
  errorMessage: string = '';

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private cityService: CityService
  ) {}

  ngOnInit(): void {
    this.loadCity();
  }

  // Charger la ville à partir de l'ID dans l'URL
  loadCity(): void {
    const cityId = Number(this.route.snapshot.paramMap.get('id'));
    if (cityId) {
      this.cityService.getCityById(cityId).subscribe(
        (data) => (this.city = data),
        (error) => {
          console.error('Erreur lors du chargement de la ville', error);
          this.errorMessage = "Impossible de charger la ville.";
        }
      );
    }
  }

  // Mettre à jour la ville
  updateCity(): void {
    if (!this.city.name.trim()) {
      this.errorMessage = "Le nom de la ville est obligatoire.";
      return;
    }

    this.cityService.updateCity(this.city).subscribe(
      () => this.router.navigate(['/admin']), // Redirection après la mise à jour
      (error) => {
        console.error('Erreur lors de la mise à jour', error);
        this.errorMessage = "Erreur lors de la mise à jour.";
      }
    );
  }

  // Annuler et revenir à la liste des villes
  cancel(): void {
    this.router.navigate(['/admin']);
  }

}