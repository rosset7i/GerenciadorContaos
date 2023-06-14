import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {MatDialogModule} from "@angular/material/dialog";
import {NgxMaskDirective, NgxMaskPipe, provideNgxMask} from "ngx-mask";
import {ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";

import {ContatosRoutingModule} from './contatos-routing.module';
import {ContatosComponent} from './contatos.component';
import {ContatosListComponent} from './contatos-list/contatos-list.component';
import {EditarContatosModalComponent} from './editar-contatos-modal/editar-contatos-modal.component';
import {UtilModule} from "../util/util.module";

@NgModule({
  declarations: [
    ContatosComponent,
    ContatosListComponent,
    EditarContatosModalComponent
  ],
  imports: [
    CommonModule,
    ContatosRoutingModule,
    HttpClientModule,
    MatDialogModule,
    ReactiveFormsModule,
    NgxMaskDirective,
    NgxMaskPipe,
    UtilModule,
  ],
  providers: [
    provideNgxMask()
  ]
})
export class ContatosModule {
}
