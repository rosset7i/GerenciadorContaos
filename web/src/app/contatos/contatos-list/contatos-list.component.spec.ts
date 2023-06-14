import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ContatosListComponent} from './contatos-list.component';
import {ContatoService} from "../services/contato.service";
import {MatDialog} from "@angular/material/dialog";
import {Direction} from "../../util/models/sorting-input";
import {EditarContatosModalComponent} from "../editar-contatos-modal/editar-contatos-modal.component";
import {of} from "rxjs";
import {ContatoOutput} from "../models/contato-output";
import {PaginatorComponent} from "../../util/paginator/paginator.component";

describe('ContatosListComponent', () => {
  let component: ContatosListComponent;
  let fixture: ComponentFixture<ContatosListComponent>;
  let contatoServiceSpy: jasmine.SpyObj<ContatoService>;
  let matDialogSpy: jasmine.SpyObj<MatDialog>;

  beforeEach(() => {
    contatoServiceSpy = jasmine.createSpyObj('ContatoService', [
      'getAllContatos',
      'removerContato'
    ]);
    matDialogSpy = jasmine.createSpyObj('MatDialog', ['open']);

    TestBed.configureTestingModule({
      declarations: [
        ContatosListComponent,
        PaginatorComponent
      ],
      providers: [
        {provide: ContatoService, useValue: contatoServiceSpy},
        {provide: MatDialog, useValue: matDialogSpy},
      ]
    }).compileComponents();
    fixture = TestBed.createComponent(ContatosListComponent);
    component = fixture.componentInstance;
    contatoServiceSpy = TestBed.inject(ContatoService) as jasmine.SpyObj<ContatoService>;
    matDialogSpy = TestBed.inject(MatDialog) as jasmine.SpyObj<MatDialog>;
  });

  it('should initialize component properties', () => {
    expect(component.contatoList).toBeUndefined();
    expect(component.totalPages).toBeUndefined();
    expect(component.currentPage).toBe(0);
    expect(component.sortingDirection).toBe(Direction.ASC);
  });

  it('should call the get all method', () => {
    component.ngOnInit();
    expect(contatoServiceSpy.getAllContatos).toHaveBeenCalled();
  });

  it('should open the modal and refresh the user page', () => {
    const mockContato: ContatoOutput = {
      id: 1,
      primeiroNome: 'Matheus',
      ultimoNome: 'Rossetti',
      telefone: '44997090799',
      email: 'mh.rossetti2002@gmail.com',
      dataDeCriacao: new Date(),
      dataDeModificacao: null,
    };
    const dialogRefSpyObj = jasmine.createSpyObj({
      afterClosed: of(),
    });
    matDialogSpy.open.and.returnValue(dialogRefSpyObj);
    spyOn(component, 'getUserPage');
    component.abrirModalContato(mockContato);

    expect(matDialogSpy.open).toHaveBeenCalledWith(EditarContatosModalComponent, {
      data: mockContato,
    });
    expect(component.getUserPage).toHaveBeenCalledWith(component.currentPage);
  });


});
