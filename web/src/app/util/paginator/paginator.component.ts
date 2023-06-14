import {Component, EventEmitter, Input, Output} from '@angular/core';

@Component({
  selector: 'app-paginator',
  templateUrl: './paginator.component.html',
  styleUrls: ['./paginator.component.css']
})
export class PaginatorComponent {
  @Input() totalPages: number;
  @Input() currentPage: number;

  @Output() pageChanged = new EventEmitter<number>();

  previousPage() {
    if (this.currentPage > 0) {
      this.pageChanged.emit(this.currentPage - 1);
    }
  }

  nextPage() {
    if (this.currentPage < this.totalPages - 1) {
      this.pageChanged.emit(this.currentPage + 1);
    }
  }
}
