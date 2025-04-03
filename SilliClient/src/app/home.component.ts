import {Component} from '@angular/core';
import {RouterLink, RouterLinkActive} from '@angular/router';
import {AsyncPipe, NgIf} from '@angular/common';
import {AuthService} from './auth.service';
import {HttpClient} from '@angular/common/http';
import {ApiService} from './api.service';
import {PostsComponent} from './posts.component';
import {PostCreatorComponent} from './postCreator.component';

@Component({
  selector: 'home',
  template: `
    <div *ngIf="!(this.isLoggedIn$ | async);" class="home">
      <h1>Welcome</h1>
      <a class="home-create" routerLink="/register" routerLinkActive="active">Create account</a>
    </div>
    <div *ngIf="this.isLoggedIn$ | async;" class="home">
      <post-creator></post-creator>
      <posts></posts>
    </div>
  `,
  imports: [
    RouterLink,
    RouterLinkActive,
    NgIf,
    AsyncPipe,
    PostsComponent,
    PostCreatorComponent
  ],
  styleUrls: ['home.component.css'],
})

export class HomeComponent {
  isLoggedIn$;
  constructor(private authService: AuthService, private httpClient: HttpClient, private apiService: ApiService) {
    this.isLoggedIn$ = this.authService.isLoggedIn()
  }

}
