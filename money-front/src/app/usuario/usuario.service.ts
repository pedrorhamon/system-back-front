import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  baseUrl = "http://localhost:8080/api/usuario"

  constructor() { }
}
