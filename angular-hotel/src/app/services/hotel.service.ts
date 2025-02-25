import { HttpClient} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Hotel } from '../model/hotel.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class HotelService {

  constructor(private http: HttpClient) { }

  public getHotels() {
    return this.http.get<Hotel[]>(environment.host + "/hotels");
  }
}
