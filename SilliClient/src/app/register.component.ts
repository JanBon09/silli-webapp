import {Component} from '@angular/core';
import {RegisterService} from './register.service';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'register',
  imports: [
    FormsModule
  ],
  providers: [RegisterService],
  template: `
    <div class="register-container">
      <div class="register">
        <h3 class="register-title">Register</h3>
        <label for="username">Username: </label>
        <input class="register-input" id="username" type="text" name="username" [(ngModel)]="username">

        <label for="password">Password: </label>
        <input class="register-input" id="password" type="text" name="password" [(ngModel)]="password" >
        <button (click)="this.registerService.register(this.username, this.password)" class="register-submit">Register</button>
      </div>
    </div>
  `,
  styleUrls: ['register.component.css']
})

export class RegisterComponent {
  constructor(public registerService: RegisterService) { }
  username: string = '';
  password: string = '';

}
