import {Component} from '@angular/core';
import {RouterLink, RouterLinkActive} from '@angular/router';

@Component({
  selector: 'navbar',
  templateUrl: 'navbar.component.html',
  imports: [
    RouterLink,
    RouterLinkActive
  ],
  styleUrls: ['navbar.component.css']
})

export class NavbarComponent {}
