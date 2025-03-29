import {Component} from '@angular/core';
import {RouterLink, RouterLinkActive} from '@angular/router';
import {AsyncPipe, NgIf} from '@angular/common';
import {AuthService} from './auth.service';

@Component({
  selector: 'navbar',
  templateUrl: 'navbar.component.html',
  imports: [
    RouterLink,
    RouterLinkActive,
    NgIf,
    AsyncPipe
  ],
  styleUrls: ['navbar.component.css']
})

export class NavbarComponent {
  isLoggedIn$;
  constructor(public authService: AuthService) {
   this.isLoggedIn$ = this.authService.isLoggedIn()
  }
}
