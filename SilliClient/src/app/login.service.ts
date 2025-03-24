import {Injectable} from '@angular/core';
import {ApiService} from './api.service';



export class Account{
  constructor(public username: String, public password: String){}
}

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  constructor(private apiService: ApiService) {}

  login(username: string, password: string) {
    let account = new Account(username, password);
    this.apiService.postRequest('users/login', account);
  }
}
