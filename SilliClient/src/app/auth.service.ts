import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import {BehaviorSubject, Observable, tap} from 'rxjs';

export class Account {
  constructor(public username: string, public password: string) {}
}

@Injectable({
  providedIn: 'root'
})

export class AuthService {
  private loggedInSubject = new BehaviorSubject<boolean>(false);
  private uri: string = 'http://localhost:8080';
  loggedInStatus$ = this.loggedInSubject.asObservable();


  constructor(private  http: HttpClient) {
  }

  loginCall(username: string, password: string): Observable<any> {
    let account = new Account(username, password);

    return this.http.post(`${this.uri}/users/login?username=` + username, account, {withCredentials: true}).pipe(tap(() => this.loggedInSubject.next(true)));
  }

  registerCall(username: string, password: string): Observable<any> {
    let account = new Account(username, password);

    return this.http.post(`${this.uri}/users/register`, account);
  }

  logout() {
    this.loggedInSubject.next(false);
  }

  isLoggedIn(): Observable<boolean> {
    return this.loggedInStatus$;
  }
}
