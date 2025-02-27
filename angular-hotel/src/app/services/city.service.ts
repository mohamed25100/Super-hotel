import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { City } from '../model/city.model';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CityService {

  constructor(private http: HttpClient) { }

  // 🔹 Récupérer une ville par son ID
  public getCityById(cityId: number): Observable<City> {
    return this.http.get<City>(`${environment.host}/city/${cityId}`);
  }

  // 🔹 Mettre à jour une ville
  public updateCity(city: City): Observable<City> {
    return this.http.put<City>(`${environment.host}/city/${city.id}`, city);
  }

  // 🔹 Supprimer une ville par son ID
  public deleteCity(cityId: number): Observable<void> {
    return this.http.delete<void>(`${environment.host}/city/${cityId}`);
  }

  // Ajouter une ville
  addCity(city: City): Observable<City> {
    return this.http.post<City>(`${environment.host}/city`, city);
  }
}
