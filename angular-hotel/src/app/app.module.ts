import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HotelsComponent } from './components/hotels/hotels.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { HttpClientModule } from '@angular/common/http';
import { HotelDetailsComponent } from './components/hotel-details/hotel-details.component';
import { LoginComponent } from './components/login/login.component';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { AdminComponent } from './components/admin/admin.component';
import { ManagerComponent } from './components/manager/manager.component';
import { AddCityComponent } from './components/admin/add-city/add-city.component';
import { EditCityComponent } from './components/admin/edit-city/edit-city.component';
import { AddHotelComponent } from './components/admin/add-hotel/add-hotel.component';
import { EditHotelComponent } from './components/admin/edit-hotel/edit-hotel.component';

@NgModule({
  declarations: [
    AppComponent,
    HotelsComponent,
    NotFoundComponent,
    HotelDetailsComponent,
    LoginComponent,
    AdminComponent,
    ManagerComponent,
    AddCityComponent,
    EditCityComponent,
    AddHotelComponent,
    EditHotelComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatIconModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
