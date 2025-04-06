import {Component, OnInit} from '@angular/core';
import {ApiService} from './api.service';
import {PageSelector, PageSelectorComponent} from './pageSelector.component';
import {RouterLink} from '@angular/router';
import {Pagination} from './paging.class';



@Component({
  selector: "posts",
  template: `
    <!-- Direct container where posts are displayed -->
    <div class="posts-container">
      @for (post of this.posts.content; track post.id) {
        <div class="post-entity" [routerLink]="['/post', post.id]">
          {{ post.content }}
        </div>
      } @empty {
        No posts to load
      }
    </div>

    <!-- Section for page selection -->
    <div class="page-selector-container">
      <page-selector (click)="differentPage(0)" pageNumber="<<">First page</page-selector>
      @for (pageSelector of this.pageSelectors; track pageSelector.value) {
        <page-selector (click)="differentPage(pageSelector.value)" pageNumber='{{pageSelector.value + 1}}'>

        </page-selector>
      }
      <page-selector (click)="differentPage(this.posts.totalPages - 1)" pageNumber=">>"></page-selector>
    </div>
  `,
  styleUrls: ['./posts.component.css'],
  imports: [
    PageSelectorComponent,
    RouterLink
  ]
})

export class PostsComponent implements OnInit {
  posts: Pagination
  pageSelectors: PageSelector[]

  constructor(private apiService: ApiService){
    this.posts = new Pagination();
    this.pageSelectors = []
  }

  ngOnInit() {
    this.apiService.getRequest(`/post?page=${this.posts.number}&size=${this.posts.size}&sortBy=id&ascending=1`).subscribe({
      next: (next) => {
        this.posts.assignObject(next)
        this.fillPageSelectorsArr()
      },
      error: error => {console.log(error);},
    })
  }

  differentPage(pageNumber : number) {
    this.apiService.getRequest(`/post?page=${pageNumber}&size=${this.posts.size}&sortBy=id&ascending=1`).subscribe({
      next: (next) => {
        this.posts.assignObject(next)
        this.pageSelectors = []
        this.fillPageSelectorsArr()
      },
      error: error => {console.log(error);},
    })
  }

  fillPageSelectorsArr(){
    for(let i = 0; i < (this.posts.totalPages > 3 ? 3 : this.posts.totalPages); i++){
      this.pageSelectors.push(new PageSelector(this.posts.number >= this.posts.totalPages - 3 ? this.posts.totalPages - (3 - i) : this.posts.number + i))
    }
  }
}
