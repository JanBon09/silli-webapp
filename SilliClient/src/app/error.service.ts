import {Injectable} from '@angular/core';
import {throwError} from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class ErrorService {
  handleError(err: Error) {
    return throwError(() => {console.log(err)});
  }
}
