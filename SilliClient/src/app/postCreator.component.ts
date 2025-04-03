import {Component} from '@angular/core';
import {ApiService} from './api.service';
import {FormsModule} from '@angular/forms';

export class PostDto{
  constructor(public content: string){}
}

@Component({
  selector: 'post-creator',
  imports: [
    FormsModule
  ],
  template: `
    <div class="postCreator-container">
      <textarea class="postCreator-input" [(ngModel)]="this.postDto.content"></textarea>
      <button (click)="this.post()">Post</button>
    </div>
  `
})

export class PostCreatorComponent {
  postDto: PostDto = new PostDto('');

  constructor(private apiService: ApiService) { }

  post(){
    this.apiService.postRequest('/post/create', this.postDto).subscribe({
      next: next => {},
      error: error => {console.log(error)}
    })
  }
}
