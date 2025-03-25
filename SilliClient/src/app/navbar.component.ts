import {Component} from '@angular/core';
import {RouterLink, RouterLinkActive} from '@angular/router';
import {NgIf} from '@angular/common';
import {LoginService} from './login.service';

@Component({
  selector: 'navbar',
  templateUrl: 'navbar.component.html',
  imports: [
    RouterLink,
    RouterLinkActive,
    NgIf
  ],
  styleUrls: ['navbar.component.css']
})

export class NavbarComponent {
  isLoggedIn$: boolean;
  constructor(public loginService: LoginService) {
    this.isLoggedIn$ = this.loginService.isLoggedIn();
  }
}
