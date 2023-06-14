import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

import {ContatoService} from "../services/contato.service";
import {ContatoOutput} from "../models/contato-output";

@Component({
  selector: 'app-editar-contatos-modal',
  templateUrl: './editar-contatos-modal.component.html',
  styleUrls: ['./editar-contatos-modal.component.css'],
})
export class EditarContatosModalComponent implements OnInit {
  form: FormGroup;

  constructor(
    private contatoService: ContatoService,
    private formBuilder: FormBuilder,
    @Inject(MAT_DIALOG_DATA) public data: ContatoOutput,
    private dialog: MatDialogRef<any>) {
  }

  get canSave() {
    return this.form.valid && this.form.dirty;
  }

  ngOnInit(): void {
    this.initForm();
  }

  initForm() {
    this.form = this.formBuilder.group({
      primeiroNome: [null, Validators.required],
      ultimoNome: [null, Validators.required],
      email: [null, Validators.email],
      telefone: [null, Validators.required],
    });

    if (this.data) {
      this.form.patchValue(this.data);
    }
  }

  createContato() {
    const contato: ContatoOutput = this.form.value;
    if (this.data != undefined) {
      contato.id = this.data.id;
    }

    return contato;
  }

  saveContato() {
    const contato = this.createContato();

    if (contato.id) {
      this.contatoService.updateContato(contato)
        .subscribe(() => this.dialog.close());
    } else {
      this.contatoService.createContato(contato)
        .subscribe(() => this.dialog.close());
    }
  }

}
