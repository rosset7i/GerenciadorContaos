import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";

import {API_CONST_V1} from "../../app-consts/api-consts";
import {ContatoOutput} from "../models/contato-output";
import {PagedResult} from "../models/paged-result";
import {SortingInput} from "../../util/models/sorting-input";

@Injectable({
  providedIn: 'root'
})
export class ContatoService {
  private basePath = API_CONST_V1.concat('contatos');

  constructor(private httpClient: HttpClient) {
  }

  public getAllContatos(
    page: number,
    sortingInput?: SortingInput) {
    let params = new HttpParams()
      .set('page', page.toString())

    if (sortingInput) {
      params = params.append('sort', sortingInput.field + ',' + sortingInput.direction);
    }

    return this.httpClient.get<PagedResult<ContatoOutput>>(this.basePath, {params});
  }

  public createContato(createContatoInput: ContatoOutput) {
    return this.httpClient.post(this.basePath, createContatoInput);
  }

  public updateContato(updateContatoInput: ContatoOutput) {
    return this.httpClient.put(this.basePath, updateContatoInput);
  }

  public removerContato(contatoId: number) {
    return this.httpClient.delete(`${this.basePath}/${contatoId}`);
  }

}
