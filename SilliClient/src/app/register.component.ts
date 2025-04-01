import {Component} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {AuthService} from './auth.service';

@Component({
  selector: 'register',
  imports: [
    FormsModule
  ],
  template: `
    <div class="register-container">
      <div class="register">
        <h3 class="register-title">Register</h3>
        <label for="username">Username: </label>
        <input class="register-input" id="username" type="text" name="username" [(ngModel)]="username">

        <label for="password">Password: </label>
        <input class="register-input" id="password" type="text" name="password" [(ngModel)]="password" >
        <button (click)="this.register(this.username, this.password)" class="register-submit">Register</button>
      </div>
    </div>
  `,
  styleUrls: ['register.component.css']
})

export class RegisterComponent {
  constructor(public authService: AuthService) { }
  username: string = '';
  password: string = '';

  register(username: string, password: string) {
    this.authService.registerCall(username, password).subscribe({
      next: next => {},
      error: error => {console.log(error);}
    });
  }
}
