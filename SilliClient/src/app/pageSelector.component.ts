import {Component, Input} from "@angular/core";

export class PageSelector{
  constructor(public value: number = 1){

  }
}

@Component({
  selector: 'page-selector',
  template: `
    <button class="page-selector-btn">{{this.pageNumber}}</button>
  `,
  styleUrls: ['./pageSelector.component.css'],
})

export class PageSelectorComponent{
  @Input() pageNumber: string = '';
}
