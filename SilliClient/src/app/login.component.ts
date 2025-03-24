import {Component} from '@angular/core';
import {LoginService} from './login.service';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'login',
  imports: [
    FormsModule
  ],
  providers: [LoginService],
  template: `
    <div class="login-container">
      <div class="login">
        <h3 class="login-title">Login</h3>
        <label for="username">Username: </label>
        <input class="login-input" id="username" type="text" name="username" [(ngModel)]="username">

        <label for="password">Password: </label>
        <input class="login-input" id="password" type="text" name="password" [(ngModel)]="password">
        <button (click)="this.loginService.login(this.username, this.password)" class="login-submit">Login</button>
      </div>
    </div>
  `,
  styleUrls: ['./login.component.css']
})

export class LoginComponent {
  username: string = '';
  password: string = '';

  constructor(public loginService: LoginService) {}
}
