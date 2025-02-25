import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ICredential } from 'src/app/model/credential';
import { Role, User } from 'src/app/model/user.model';
import { AuthenticateService } from 'src/app/services/authenticate.service';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  myForm: ICredential = {
    email: "",
    password: ""
  };
  user: User | undefined;
  errorMessage: string | undefined;

  constructor(public authService: AuthenticateService, private router: Router, private tokenService: TokenService) {

  }

  ngOnInit(): void {
    this.user = new User(
      0,              // ID temporaire
      "",         // Prénom
      "",          // Nom
      "",
      "",
      Role.USER       // Rôle par défaut (USER)
    );
  }

  /**
   * Méthode de connexion de l'utilisateur
   */
  onLogin(): void {
    console.log("Tentative de connexion pour :", this.myForm.email);

    this.authService.login(this.myForm).subscribe(
      (data) => {
        if (!data["access-token"]) {
          console.error("⚠️ Aucun token reçu !");
          this.errorMessage = "Erreur de connexion. Aucun token reçu.";
          return;
        }

        // Sauvegarde du token et récupération des informations de l'utilisateur
        this.tokenService.saveToken(data["access-token"]);
        let userInfo = this.tokenService.getPayload();
        console.log("Utilisateur après décodage :", userInfo);

        if (userInfo.email && userInfo.role) {
          this.authService.setUser(userInfo);
          this.user = new User(
            userInfo.id,
            userInfo.firstName,
            userInfo.lastName,
            userInfo.email,
            "",
            userInfo.role as Role
          );

          // Redirection en fonction du rôle
          switch (userInfo.role) {
            case Role.ADMIN:
              this.router.navigate(['/admin']);
              break;
            case Role.MANAGER:
              this.router.navigate(['/manager-dashboard']);
              break;
            default:
              this.router.navigate(['/home']);
          }
        } else {
          console.error("Erreur : Impossible de récupérer les infos utilisateur.");
          this.errorMessage = "Erreur de connexion.";
        }
      },
      (err) => {
        console.error("Erreur lors de la connexion :", err);
        this.errorMessage = "Identifiants incorrects ou problème serveur.";
      }
    );
  }

  /**
 * Redirige vers la page d'accueil
 */
  goToHome(): void {
    this.router.navigateByUrl('/');
  }
}
