import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {PaginatorComponent} from './paginator/paginator.component';
import {ConfirmDeleteModalComponent} from './confirm-delete-modal/confirm-delete-modal.component';
import {MatDialogModule} from "@angular/material/dialog";

@NgModule({
  declarations: [
    PaginatorComponent,
    ConfirmDeleteModalComponent,
  ],
  exports: [
    PaginatorComponent,
    ConfirmDeleteModalComponent
  ],
  imports: [
    CommonModule,
    MatDialogModule
  ]
})
export class UtilModule {
}
