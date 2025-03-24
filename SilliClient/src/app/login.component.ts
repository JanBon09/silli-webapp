import {Component} from '@angular/core';

@Component({
  selector: 'login',
  template: `
    <div class="login">
      <label for="username">Username: </label>
      <input id="username" type="text" name="username">

      <label for="password">Password: </label>
      <input id="password" type="text" name="password">
      <button (click)="">Login</button>
    </div>

  `
})

export class LoginComponent {}
