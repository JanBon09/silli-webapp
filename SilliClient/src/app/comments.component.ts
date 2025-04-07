import {Component, Input, OnInit} from '@angular/core';
import {ApiService} from './api.service';
import {Pagination} from './paging.class';

@Component({
  selector: 'comments',
  template: `
    <div class="comments-container">
        @for (comment of this.comments.content; track comment.id){
          <div class="comment">
            <div class="comment-content">
            {{comment.content}}
            </div>
            <div class="comment-date">
              {{comment.createdat}}
            </div>
          </div>
        }
    </div>
  `,
  styleUrls: ['./comments.component.css'],
})

export class CommentsComponent implements OnInit {
  comments: Pagination;
  @Input() postId: string = '';

  newCommentContent: string = '';

  constructor(private apiService: ApiService) {
    this.comments = new Pagination();
  }

  ngOnInit() {
    this.apiService.getRequest(`/comment/page-by-id?page=0&size=3&sortBy=createdat&ascending=1&postId=${this.postId}`).subscribe({
      next: next => {
        this.comments.assignObject(next);
      },
      error: err => {console.log(err)}
    })
  }
}
