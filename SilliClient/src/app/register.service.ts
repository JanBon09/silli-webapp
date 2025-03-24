import {Injectable} from '@angular/core';
import {ApiService} from './api.service';
import {Account} from './login.service';

@Injectable({
  providedIn: 'root',
})

export class RegisterService {
  constructor(private apiService: ApiService) {
  }

  register(username: string, password: string) {
    console.log("HALO");
    this.apiService.postRequest('users/register', new Account(username, password)).subscribe(response => {
      console.log(response);
    });
  }
}
