import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditarContatosModalComponent } from './editar-contatos-modal.component';

describe('EditarContatosModalComponent', () => {
  let component: EditarContatosModalComponent;
  let fixture: ComponentFixture<EditarContatosModalComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EditarContatosModalComponent]
    });
    fixture = TestBed.createComponent(EditarContatosModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
