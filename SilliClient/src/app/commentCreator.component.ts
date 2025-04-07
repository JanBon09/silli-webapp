import {Component, Input} from '@angular/core';
import {ApiService} from './api.service';
import {FormsModule} from '@angular/forms';

export class CommentDto{
  constructor(public content: string = '', public postId: string = ''){}
}

@Component({
  selector: 'comment-creator',
  imports: [
    FormsModule
  ],
  template: `
    <div class="comment-creator">
      <textarea [(ngModel)]="this.commentDto.content"></textarea>
      <button (click)="postComment()">Comment</button>
    </div>
  `
})

export class CommentCreatorComponent {
  commentDto: CommentDto;
  @Input() postId: string = '';

  constructor(private apiService: ApiService){
    this.commentDto = new CommentDto();
  }

  postComment(){
    this.commentDto.postId = this.postId;
    this.apiService.postRequest("/comment/create", this.commentDto).subscribe({
      next: next => {},
      error: error => {console.log(error)},
    })
  }
}
