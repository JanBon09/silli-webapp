import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ErrorService} from './error.service';
import {catchError} from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class ApiService {
  private uri: string = 'http://localhost:8080';

  constructor(private http: HttpClient, private errorService: ErrorService) { }

  getRequest(path: string){
    this.http.get(`${this.uri}/${path}`, {}).pipe(catchError(this.errorService.handleError));
  }

  postRequest(path: string, body: any){
    console.log("anything?");
    this.http.post(`${this.uri}/${path}`, body).subscribe(response =>
    {console.log(response)});
  }
}
