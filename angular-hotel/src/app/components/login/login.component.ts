import { Component, OnInit } from '@angular/core';
import { ICredential } from 'src/app/model/credential';
import { Role, User } from 'src/app/model/user.model';

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
  ngOnInit(): void {

  }

  onLogin(): void {
   this.user = new User(
      0,              // ID temporaire
      "",         // Prénom
      "",          // Nom
      "",
      "",
      Role.USER       // Rôle par défaut (USER)
    );
  }
}
