import {LOCALE_ID, NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {CommonModule, registerLocaleData} from "@angular/common";
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {NavbarComponent} from './navbar/navbar.component';
import {MainContainerComponent} from './main-container/main-container.component';
import {ContatosModule} from "./contatos/contatos.module";
import localeFr from '@angular/common/locales/fr';

registerLocaleData(localeFr);

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    MainContainerComponent
  ],
  imports: [
    CommonModule,
    BrowserModule,
    AppRoutingModule,
    ContatosModule,
    BrowserAnimationsModule,
  ],
  providers: [{provide: LOCALE_ID, useValue: 'fr'}],
  bootstrap: [AppComponent]
})
export class AppModule {
}
