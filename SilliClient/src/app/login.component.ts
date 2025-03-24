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
    <div class="login">
      <label for="username">Username: </label>
      <input id="username" type="text" name="username" [(ngModel)]="username">

      <label for="password">Password: </label>
      <input id="password" type="text" name="password" [(ngModel)]="password">
      <button (click)="this.loginService.login(this.username, this.password)">Login</button>
    </div>

  `
})

export class LoginComponent {
  username: string = '';
  password: string = '';

  constructor(public loginService: LoginService) {}
}
