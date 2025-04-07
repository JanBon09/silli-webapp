import {Component} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {AuthService} from './auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'login',
  imports: [
    FormsModule
  ],
  template: `
    <div class="login-container">
      <div class="login">
        <h3 class="login-title">Login</h3>
        <label for="username">Username: </label>
        <input class="login-input" id="username" type="text" name="username" [(ngModel)]="username">

        <label for="password">Password: </label>
        <input class="login-input" id="password" type="password" name="password" [(ngModel)]="password">
        <button (click)="this.login(this.username, this.password)" class="login-submit">Login</button>
      </div>
    </div>
    <div class="login-alert-container">
        {{this.alert}}
    </div>
  `,
  styleUrls: ['./login.component.css']
})

export class LoginComponent {
  username: string = '';
  password: string = '';
  alert: string = '';

  constructor(public authService: AuthService, private router: Router) { }

  login(username: string, password: string) {
    this.authService.loginCall(username, password).subscribe({
        next: next => {
          this.router.navigate(['/home']);
        },
        error: error => {
          this.alert = 'Invalid username or password';
        }
      }
    );
  }
}
