import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { ITokenUser, Role } from '../model/user.model';
import { jwtDecode } from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  constructor(private router: Router) { }

  saveToken(token: string) {
    console.log("Token sauvegardé : ", token);
    localStorage.setItem('token', token);
  }

    /**
   * Vérifie si un utilisateur est connecté en regardant s'il y a un token.
   * @returns true si un token est présent, sinon false.
   */
  isLogged(): boolean {
    return localStorage.getItem('token') !== null;
  }

    /**
   * Supprime le token et redirige vers la page d'accueil.
   */
  clearToken() {
    localStorage.removeItem('token');
    this.router.navigate(['/']);
  }

    /**
   * Supprime le token en cas d'expiration et redirige vers la page de connexion.
   */
  clearTokenExpired(): void {
    localStorage.removeItem('token');
    this.router.navigate(['/login']);
  }

    /**
   * Récupère le token stocké dans le localStorage.
   * @returns Le token s'il existe, sinon null.
   */
  getToken(): string | null {
    return localStorage.getItem('token');
  }

  getPayload(): any {
    let userConnected: ITokenUser = {
      id: 0,
      firstName: '',
      lastName: '',
      email: '',
      role: ''
    };
  
    let token = this.getToken();
  
    if (token) {
      console.log("Token avant décodage:", token);
  
      try {
        if (token.split('.').length !== 3) {
          throw new Error("Token mal formé");
        }
  
        const decoded: any = jwtDecode(token);
        console.log("Token décodé:", decoded);
  
        userConnected.id = decoded.id || 0;
        userConnected.firstName = decoded.firstName || '';
        userConnected.lastName = decoded.lastName || '';
        userConnected.email = decoded.sub || ''; // ✅ Correction ici
        userConnected.role = this.extractRole(decoded.scope);
        
      } catch (error) {
        console.error("Erreur lors du décodage du token :", error);
        this.clearToken();
      }
    }
    return userConnected;
  }

    /**
   * Extrait le rôle de l'utilisateur depuis le token.
   * @param scope - La valeur du champ `scope` dans le token.
   * @returns Le rôle sous forme de chaîne de caractères.
   */
    private extractRole(scope: string): string {
      if (!scope) return '';
  
      if (scope.includes(Role.ADMIN)) return Role.ADMIN;
      if (scope.includes(Role.MANAGER)) return Role.MANAGER;
      if (scope.includes(Role.USER)) return Role.USER;
  
      return '';
    }
}
