import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ApiService} from './api.service';
import {CommentsComponent} from './comments.component';
import {CommentCreatorComponent} from './commentCreator.component';

export class PostDisplay{
  constructor(
    public id: number = 0,
    public content: string = '',
    public createdAt: Date = new Date(),
    public username: string = ''
  ){}

  assignObject(object: any){
    this.id = object.id;
    this.content = object.content;
    this.createdAt = object.createdAt;
    this.username = object.username;
  }
}

@Component({
  selector: "post",
  imports: [
    CommentsComponent,
    CommentCreatorComponent
  ],
  template: `
    <div class="post-details-page">
    <div class="post-details-container">
      <div class="post-details-header">
        {{ this.postDisplay.id }} {{ this.postDisplay.username }}
      </div>
      <hr class="post-details-hr">
      <div class="post-details-content">
        {{ this.postDisplay.content }}
      </div>
      <hr class="post-details-hr">
      <div class="post-details-date">
        {{ this.dateDisplay }}
      </div>
    </div>

      <comment-creator postId="{{postId}}"></comment-creator>
      <h3 class="comment-section-title">Comments</h3>
    <comments postId="{{postId}}"></comments>
    </div>
  `,
  styleUrls: ['./post.component.css'],
})

export class PostComponent implements OnInit{
  postId: string | null;
  postDisplay: PostDisplay;
  dateDisplay: string;

  constructor(private route: ActivatedRoute, private apiService: ApiService) {
    this.postId = this.route.snapshot.paramMap.get("id");
    this.postDisplay = new PostDisplay();
    this.dateDisplay = '';
  }

  ngOnInit() {
    this.apiService.getRequest(`/post/display-form?postId=${this.postId}`).subscribe({
      next: next => {
        this.postDisplay.assignObject(next);
        this.dateDisplay = new Date(this.postDisplay.createdAt).toLocaleDateString();
        },
      error: error => {console.log(error)},
    })
  }
}
