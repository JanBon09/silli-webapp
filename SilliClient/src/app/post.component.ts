import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {ApiService} from './api.service';

export class PostDisplayForm{
  constructor(
    public id: number = 0,
    public content: string = '',
    public creationDate: Date = new Date(),
    public username: string = ''
  ){}

  assignObject(object: any){
    this.id = object.id;
    this.content = object.content;
    this.creationDate = object.creationDate;
    this.username = object.username;
  }
}

@Component({
  selector: "post",
  template: `

  `
})

export class PostComponent implements OnInit{
  postId: number | undefined;
  postDisplayForm: PostDisplayForm;

  constructor(private router: Router, private apiService: ApiService) {
    this.postId = this.router.getCurrentNavigation()?.id;
    this.postDisplayForm = new PostDisplayForm();
  }

  ngOnInit() {
    this.apiService.getRequest(`/post?postId=${this.postId}`).subscribe({
      next: next => {
        this.postDisplayForm.assignObject(next);
        console.log(this.postDisplayForm);
        },
      error: error => {console.log(error)},
    })
  }
}
