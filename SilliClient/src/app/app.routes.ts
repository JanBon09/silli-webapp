import { Routes } from '@angular/router';
import {HomeComponent} from './home.component';
import {LoginComponent} from './login.component';
import {RegisterComponent} from './register.component';
import {ProfileComponent} from './profile.component';
import {PostComponent} from './post.component';
import {AuthGuard} from './authGuard';

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
    path: 'profile', component: ProfileComponent, pathMatch: 'full', canActivate: [AuthGuard]
  },
  {
    path: 'post/:id', component: PostComponent, pathMatch: 'full'
  },
  {
    path: '', redirectTo: 'home', pathMatch: 'full'
  }

];
