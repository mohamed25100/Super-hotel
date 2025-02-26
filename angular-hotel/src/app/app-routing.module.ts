import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HotelsComponent } from './components/hotels/hotels.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { HotelDetailsComponent } from './components/hotel-details/hotel-details.component';
import { LoginComponent } from './components/login/login.component';
import { AdminComponent } from './components/admin/admin.component';
import { ManagerComponent } from './components/manager/manager.component';
import { AdminGuard } from './guards/admin.guard';
import { ManagerGuard } from './guards/manager.guard';
import { AddCityComponent } from './components/admin/add-city/add-city.component';
import { EditCityComponent } from './components/admin/edit-city/edit-city.component';
import { AddHotelComponent } from './components/admin/add-hotel/add-hotel.component';
import { EditHotelComponent } from './components/admin/edit-hotel/edit-hotel.component';

const routes: Routes = [
  {
    path: 'hotels', component: HotelsComponent
  },
  {
    path: 'hotel/:id', component: HotelDetailsComponent // Route pour les détails d'un hôtel
  },
  { 
    path : 'login' , component : LoginComponent
  },
  { 
    path : 'admin/add-city' , component : AddCityComponent,
    canActivate : [AdminGuard]
  },
  { 
    path : 'admin/edit-city/:id' , component : EditCityComponent,
    canActivate : [AdminGuard]
  },
  { 
    path : 'admin/add-hotel' , component : AddHotelComponent,
    canActivate : [AdminGuard]
  },
  { 
    path : 'admin/edit-hotel/:id' , component : EditHotelComponent,
    canActivate : [AdminGuard]
  },
  {
    path : 'admin' , component : AdminComponent,
    canActivate : [AdminGuard]
  },
  {
    path : 'manager-dashboard' , component : ManagerComponent,
    canActivate : [ManagerGuard]
  },
  {
    path: '', redirectTo: 'hotels', pathMatch: 'full'
  },
  {
    path: '404', component: NotFoundComponent
  },
  {
    path: '**', redirectTo: '/404'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
