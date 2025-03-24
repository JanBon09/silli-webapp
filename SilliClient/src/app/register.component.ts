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
    <div class="register">
      <label for="username">Username: </label>
      <input id="username" type="text" name="username" [(ngModel)]="username">

      <label for="password">Password: </label>
      <input id="password" type="text" name="password" [(ngModel)]="password" >
      <button (click)="this.registerService.register(this.username, this.password)">Register</button>
    </div>
  `
})

export class RegisterComponent {
  constructor(public registerService: RegisterService) { }
  username: string = '';
  password: string = '';

}
