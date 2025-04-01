import {Component} from '@angular/core';
import {RouterLink, RouterLinkActive} from '@angular/router';
import {NgIf} from '@angular/common';
import {AuthService} from './auth.service';
import {HttpClient} from '@angular/common/http';
import {ApiService} from './api.service';

export class accTest{
  constructor(public id: number) { }
}

@Component({
  selector: 'home',
  template: `
    <div *ngIf="!isLoggedIn$" class="home">
      <h1>Welcome</h1>
      <a class="home-create" routerLink="/register" routerLinkActive="active">Create account</a>
    </div>
    <div *ngIf="isLoggedIn$" class="home">
      <button (click)="toJaClickHihi()">Klik</button>
    </div>
  `,
  imports: [
    RouterLink,
    RouterLinkActive,
    NgIf,
  ],
  styleUrls: ['home.component.css'],
})

export class HomeComponent {
  isLoggedIn$;
  constructor(private authService: AuthService, private httpClient: HttpClient, private apiService: ApiService) {
    this.isLoggedIn$ = this.authService.isLoggedIn()
  }

  toJaClickHihi(){
    return this.apiService.getRequest(`/trigger`).subscribe({
      next: data => {console.log(data);},
      error: error => {console.log(error);}
    })
  }

}
