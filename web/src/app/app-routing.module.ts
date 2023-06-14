import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

const routes: Routes = [
  {
    path: 'contatos',
    loadChildren: () => import('./contatos/contatos.module').then(m => m.ContatosModule)
  },
  {
    path: '**',
    redirectTo: 'contatos'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
