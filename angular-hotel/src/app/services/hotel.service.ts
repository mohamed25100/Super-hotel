import { HttpClient} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Hotel } from '../model/hotel.model';
import { environment } from 'src/environments/environment';
import { City } from '../model/city.model';
import { Observable } from 'rxjs';
import { ICredential } from '../model/credential';
import { IToken } from '../model/token';

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
}