import {Injectable} from '@angular/core';
import {ApiService} from './api.service';
import {BehaviorSubject, Observable} from 'rxjs';

export class Account{
  constructor(public username: String, public password: String){}
}

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  loggedIn$ = false;

  constructor(private apiService: ApiService) {}

  login(username: string, password: string) {
    let account = new Account(username, password);
    this.apiService.postRequest('users/login?username=' + username, account).subscribe(response => {
      this.loggedIn$ = true;
    });
  }

  logout() {
    this.loggedIn$ = false;
  }

  isLoggedIn(): boolean {
    return this.loggedIn$;
  }
}
