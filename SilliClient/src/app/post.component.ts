import {Component} from '@angular/core';
import {ApiService} from './api.service';

export class Author{
  constructor(username: string) {
  }
}

export class Post{
  constructor(public id: number, public content: string, public createdAt: Date) {}
}

@Component({
  selector: "post",
  template: `
    <div class="post-container">
      <div class="post-header"></div>
      <div class="post-main"></div>
      <div class="post-footer"></div>
    </div>
  `
})

export class PostComponent {
}
