import { Routes } from '@angular/router';
import {HomeComponent} from './home.component';
import {LoginComponent} from './login.component';
import {RegisterComponent} from './register.component';

export const routes: Routes = [
  {
    path: 'home', component: HomeComponent, pathMatch: 'full'
  },
  {
    path: 'login', component: LoginComponent, pathMatch: 'full'
  },
  {
    path: 'register', component: RegisterComponent, pathMatch: 'full'
  },
  {
    path: '', redirectTo: 'home', pathMatch: 'full'
  }

];
