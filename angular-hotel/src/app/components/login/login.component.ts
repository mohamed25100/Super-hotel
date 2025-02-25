import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ICredential } from 'src/app/model/credential';
import { Role, User } from 'src/app/model/user.model';
import { AuthenticateService } from 'src/app/services/authenticate.service';

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

  constructor(public authService: AuthenticateService,private router: Router) {

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

  onLogin(): void {
    console.log("Connexion " + this.myForm.email + " " + this.myForm.password);
  
    this.authService.login(this.myForm).subscribe(
      
    )
  }

  goToHome(): void {
    this.router.navigateByUrl('/');
  }
}
