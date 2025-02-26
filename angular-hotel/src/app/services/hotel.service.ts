import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Hotel } from '../model/hotel.model';
import { environment } from 'src/environments/environment';
import { City } from '../model/city.model';
import { Observable } from 'rxjs';
import { ICredential } from '../model/credential';
import { IToken } from '../model/token';
import { User } from '../model/user.model';

@Injectable({
  providedIn: 'root'
})
export class HotelService {

  constructor(private http: HttpClient) { }

  public getHotels() {
    return this.http.get<Hotel[]>(environment.host + "/hotels");
  }

  public getCities() {
    return this.http.get<City[]>(environment.host + "/cities");
  }

  public getHotelById(id: number): Observable<Hotel> {
    return this.http.get<Hotel>(environment.host + "/hotel/" + id);
  }

  public login(credentials: ICredential): Observable<IToken> {
    console.log("Login service " + credentials.email + " " + credentials.password);
    return this.http.post<IToken>(environment.host + "/login", credentials);
  }
  // 🔹 Supprimer un hôtel par son ID
  public deleteHotel(hotelId: number): Observable<void> {
    const token = localStorage.getItem('token'); // Récupération du token
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json' // Assure-toi que le format est bien JSON
    });

    return this.http.delete<void>(`${environment.host}/hotel/${hotelId}`, { headers });
  }

  // 🔹 Supprimer une ville par son ID
  public deleteCity(cityId: number): Observable<void> {
    const token = localStorage.getItem('token'); // Récupération du token
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json' // Assure que le format est bien JSON
    });

    return this.http.delete<void>(`${environment.host}/city/${cityId}`, { headers });
  }


  // 🔹 Récupérer tous les utilisateurs
  public getUsers(): Observable<User[]> {
    return this.http.get<User[]>(`${environment.host}/users`);
  }

  // 🔹 Récupérer un utilisateur via son email
  public getUserByEmail(email: string): Observable<User> {
    return this.http.get<User>(`${environment.host}/users/email/${email}`);
  }

  // 🔹 Ajouter un nouvel utilisateur
  public postUser(user: User): Observable<User> {
    return this.http.post<User>(`${environment.host}/users`, user);
  }

  // 🔹 Vérifier un utilisateur par email et mot de passe (Auth)
  public getUserByEmailAndPassword(credentials: ICredential): Observable<User> {
    return this.http.post<User>(`${environment.host}/users/authenticate`, credentials);
  }
}