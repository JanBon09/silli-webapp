import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ErrorService} from './error.service';

@Injectable({
  providedIn: 'root'
})

export class ApiService {
  private uri: string = '/api';

  constructor(private http: HttpClient, private errorService: ErrorService) { }

  getRequest(path: string){
    return this.http.get(`${this.uri}/${path}`, {withCredentials: true, transferCache: true});
  }

  postRequest(path: string, body: any){
    return this.http.post(`${this.uri}/${path}`, body, {withCredentials: true});
  }
}
