import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ErrorService} from './error.service';

@Injectable({
  providedIn: 'root'
})

export class ApiService {
  private uri: string = 'http://localhost:8080';

  constructor(private http: HttpClient, private errorService: ErrorService) { }

  getRequest(path: string){
    this.http.get(`${this.uri}/${path}`, {});
  }

  postRequest(path: string, body: any){
    return this.http.post(`${this.uri}/${path}`, body, {withCredentials: true});
  }
}
