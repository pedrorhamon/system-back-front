import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  baseUrl = "http://localhost:8080/api/usuario";

  constructor(private httpClient: HttpClient) { }

  findAll() {
    return this.httpClient.get<Usuario>(`${this.baseUrl}`);
  }
}
