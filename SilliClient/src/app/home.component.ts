import {Component} from '@angular/core';
import {RouterLink, RouterLinkActive} from '@angular/router';
import {NgIf} from '@angular/common';
import {LoginService} from './login.service';
import {AuthService} from './auth.service';

@Component({
  selector: 'home',
  template: `
    <div *ngIf="isLoggedIn$" class="home">
      <h1>Welcome</h1>
      <a class="home-create" routerLink="/register" routerLinkActive="active">Create account</a>
    </div>
    <div *ngIf="!isLoggedIn$" class="home">
        Post
    </div>
  `,
  imports: [
    RouterLink,
    RouterLinkActive,
    NgIf,
  ],
  styleUrls: ['home.component.css'],
  providers: [LoginService],
})


export class HomeComponent {
  isLoggedIn$= false;
  constructor(private loginService: LoginService) {
    this.isLoggedIn$ = this.loginService.isLoggedIn();
  }

}
