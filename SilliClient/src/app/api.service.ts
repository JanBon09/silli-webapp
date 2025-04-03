import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ErrorService} from './error.service';

@Injectable({
  providedIn: 'root'
})

export class ApiService {
  constructor(private http: HttpClient) { }

  getRequest(path: string){
    return this.http.get(`/api${path}`, {withCredentials: true, transferCache: true});
  }

  postRequest(path: string, body: any){
    return this.http.post(`/api${path}`, body, {withCredentials: true});
  }
}
