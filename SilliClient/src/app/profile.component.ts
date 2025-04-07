import {Component} from '@angular/core';
import {ApiService} from './api.service';
import {OnInit} from '@angular/core';

@Component({
  selector: 'profile',
  template: `
    <div class="profile-main">
      <div class="profile-username">
        {{this.username}}
      </div>
    </div>
  `,
})

export class ProfileComponent implements OnInit {
  public username: any = '';

  constructor(private apiService: ApiService) {}

  ngOnInit() {
    this.username = "John Doe";
  }
}
