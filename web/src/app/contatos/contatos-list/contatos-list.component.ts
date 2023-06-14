import {Component, OnInit} from '@angular/core';
import {MatDialog} from "@angular/material/dialog";

import {ContatoOutput} from "../models/contato-output";
import {ContatoService} from "../services/contato.service";
import {EditarContatosModalComponent} from "../editar-contatos-modal/editar-contatos-modal.component";
import {Direction, SortingInput} from "../../util/models/sorting-input";
import {ConfirmDeleteModalComponent} from "../../util/confirm-delete-modal/confirm-delete-modal.component";

@Component({
  selector: 'app-contatos-list',
  templateUrl: './contatos-list.component.html',
  styleUrls: ['./contatos-list.component.css'],
})
export class ContatosListComponent implements OnInit {
  contatoList: ContatoOutput[];
  totalPages: number;
  currentPage: number = 0;
  sortingDirection: Direction = Direction.ASC;

  constructor(
    private dialog: MatDialog,
    private contatoService: ContatoService) {
  }

  ngOnInit(): void {
    this.getUserPage(0);
  }

  onPageChanged(currentPage: number) {
    this.getUserPage(currentPage);
    this.currentPage = currentPage;
  }

  getUserPage(page: number) {
    this.contatoService.getAllContatos(page)
      .subscribe(response => {
        this.contatoList = response.content;
        this.totalPages = response.totalPages;
      });
  }

  abrirModalContato(contatoOutput?: ContatoOutput) {
    this.dialog.open(EditarContatosModalComponent, {data: contatoOutput})
      .afterClosed().subscribe(() => this.getUserPage(this.currentPage));
  }

  removeContato(contatoId: number) {
    this.dialog.open(ConfirmDeleteModalComponent)
      .afterClosed()
      .subscribe(isConfirmed => {
        if (isConfirmed) {
          this.contatoService.removerContato(contatoId)
            .subscribe(() => this.getUserPage(this.currentPage));
        }
      });

  }

  onSortingChange(campo: string) {
    if (this.sortingDirection == Direction.ASC) {
      this.sortingDirection = Direction.DESC;
    } else {
      this.sortingDirection = Direction.ASC;
    }

    const sorting: SortingInput = {
      field: campo,
      direction: this.sortingDirection
    }

    this.contatoService.getAllContatos(0, sorting)
      .subscribe(response => {
        this.contatoList = response.content;
      });
  }
}
