import { Routes } from '@angular/router';
import {HomeComponent} from './home.component';

export const routes: Routes = [
  {
    path: '/home', component: HomeComponent, pathMatch: 'full'
  },
  {
    path: '/login', component: LoginComponent, pathMatch: 'full'
  },
  {
    path: '/', redirectTo: 'home', pathMatch: 'full'
  }

];
