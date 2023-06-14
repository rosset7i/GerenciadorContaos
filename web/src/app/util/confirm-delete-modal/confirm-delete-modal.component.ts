import {Component, EventEmitter, Output} from '@angular/core';

@Component({
  selector: 'app-confirm-delete-modal',
  templateUrl: './confirm-delete-modal.component.html',
  styleUrls: ['./confirm-delete-modal.component.css']
})
export class ConfirmDeleteModalComponent {
  @Output() deleteConfirmed = new EventEmitter();

  deleteItem() {
    this.deleteConfirmed.emit();
  }
}
