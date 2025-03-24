import {Component} from '@angular/core';
import {RouterLink, RouterLinkActive} from '@angular/router';

@Component({
  selector: 'home',
  template: `
    <div class="home">
      <h1>Welcome</h1>
      <a class="home-create" routerLink="/register" routerLinkActive="active">Create account</a>
    </div>
  `,
  imports: [
    RouterLink,
    RouterLinkActive
  ],
  styleUrls: ['home.component.css']
})

export class HomeComponent {}
