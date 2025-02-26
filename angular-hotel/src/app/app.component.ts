import { Component } from '@angular/core';
import { TokenService } from './services/token.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'angular-hotel';
  isLoggedIn = false;

  constructor(private tokenService: TokenService, private router: Router) {
    this.isLoggedIn = this.tokenService.isLogged(); // Vérifier l'état de connexion au démarrage
  }

  toggleAuth() {
    if (this.isLoggedIn) {
      this.tokenService.clearToken();
      this.isLoggedIn = false;
      this.router.navigate(['/login']); // Rediriger vers la page de connexion
    } else {
      this.router.navigate(['/login']); // Aller sur la page de connexion
    }
  }
}
