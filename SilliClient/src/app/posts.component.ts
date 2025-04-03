import {Component, OnInit} from '@angular/core';
import {Post} from './post.component';
import {ApiService} from './api.service';

export class Pagination{
  constructor(public content: any[] = [{id: 0, content: ''}],
              public pageable: Object ={},
              public last: boolean = false,
              public totalElements: number = 0,
              public totalPages: number = 0,
              public first: boolean = false,
              public size: number = 0,
              public number: number = 0
  ) {}

  assignObject(object: any) {
    this.content = object.content;
    this.pageable = object.pageable;
    this.last = object.last;
    this.totalElements = object.totalElements;
    this.totalPages = object.totalPages;
    this.first = object.first;
    this.size = object.size;
    this.number = object.number;
  }
}

@Component({
  selector: "posts",
  template: `
    <div class="posts-container">
      @for (post of this.posts.content; track post.id){
        <div class="post-entity">
          {{post.content}}
        </div>
      } @empty {
        No posts to load
      }
    </div>
  `,
  styleUrls: ['./posts.component.css'],
})

export class PostsComponent implements OnInit {
  posts: Pagination
  constructor(private apiService: ApiService){
    this.posts = new Pagination();
  }

  ngOnInit() {
    this.apiService.getRequest(`/post?page=0&size=3&sortBy=id&ascending=1`).subscribe({
      next: (next) => {this.posts.assignObject(next)},
      error: error => {console.log(error);},
    })
  }
}
