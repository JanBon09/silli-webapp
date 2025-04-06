// Class for holding Pageable object coming from server
export class Pagination{
  constructor(public content: any[] = [],
              public pageable: Object ={},
              public last: boolean = false,
              public totalElements: number = 0,
              public totalPages: number = 0,
              public first: boolean = false,
              public size: number = 3,
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
