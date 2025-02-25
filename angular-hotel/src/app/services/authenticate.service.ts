import { Injectable } from '@angular/core';
import { Role, User } from '../model/user.model';
import { HotelService } from './hotel.service';
import { ICredential } from '../model/credential';

@Injectable({
  providedIn: 'root'
})
export class AuthenticateService {
  userConnected: User = new User(
    0,              // ID temporaire
    "",         // Prénom
    "",          // Nom
    "",
    "",
    Role.USER       // Rôle par défaut (USER)
  );

  constructor(private hotelService: HotelService) { }

  /**
   * Récupère l'utilisateur depuis le localStorage.
   * @returns L'utilisateur connecté s'il existe, sinon un utilisateur par défaut.
   */
  getUser(): User {
    let user = localStorage.getItem('user');
    if (user) {
      try {
        this.userConnected = JSON.parse(user);
      } catch (e) {
        console.error("Erreur lors du parsing du JSON :", e);
        localStorage.removeItem('user');
      }
    }
    return this.userConnected;
  }

  login(credentials: ICredential) {
    return this.hotelService.login(credentials);
  }

  /**
 * Vérifie si un utilisateur est connecté.
 * @returns true si un utilisateur est connecté, sinon false.
 */
  isConnected(): boolean {
    return localStorage.getItem('user') !== null;
  }

  /**
 * Déconnecte l'utilisateur en supprimant ses informations du localStorage.
 */
  disconnected(): void {
    localStorage.removeItem('user');
    this.userConnected = new User(0, "", "", "", "", Role.USER);
  }

  /**
 * Vérifie si l'utilisateur connecté est un administrateur.
 * @returns true si l'utilisateur est ADMIN, sinon false.
 */
  isAdmin(): boolean {
    let user = this.getUser();
    return user && user.role === Role.ADMIN;
  }

  /**
* Vérifie si l'utilisateur est un utilisateur simple.
* @returns true si l'utilisateur est USER, sinon false.
*/
  isUser(): boolean {
    let user = this.getUser();
    return user && user.role === Role.USER;
  }

  /**
 * Vérifie si l'utilisateur est un manager.
 * @returns true si l'utilisateur est MANAGER, sinon false.
 */
  isManager(): boolean {
    let user = this.getUser();
    return user && user.role === Role.MANAGER;
  }

  setUser(user: User): void {
    if (user) {
      this.userConnected = new User(
        user.id,
        user.firstName,
        user.lastName,
        user.email,
        user.password,
        user.role
      );

      localStorage.setItem('user', JSON.stringify(this.userConnected));
    } else {
      console.error("⚠️ Données utilisateur invalides:", user);
    }
  }

}
