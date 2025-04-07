import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import {BehaviorSubject, Observable, tap} from 'rxjs';
import {Router} from '@angular/router';

export class Account {
  constructor(public username: string, public password: string) {}
}

@Injectable({
  providedIn: 'root'
})

export class AuthService {
  private loggedInSubject = new BehaviorSubject<boolean>(false);
  loggedInStatus$ = this.loggedInSubject.asObservable();


  constructor(private  http: HttpClient, private router: Router) {
  }

  loginCall(username: string, password: string): Observable<any> {
    let account = new Account(username, password);

    return this.http.post(`/api/users/login?username=` + username, account, {withCredentials: true}).pipe(tap(() => this.loggedInSubject.next(true)));
  }

  registerCall(username: string, password: string): Observable<any> {
    let account = new Account(username, password);

    return this.http.post(`/api/users/register`, account, {withCredentials: true});
  }

  logout() {
    this.loggedInSubject.next(false);
    this.router.navigate(['/home']);
  }

  isLoggedIn(): Observable<boolean> {
    return this.loggedInStatus$;
  }
}
