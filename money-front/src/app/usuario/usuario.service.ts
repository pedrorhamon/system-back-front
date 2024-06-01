import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';
import { Usuario } from '../../model/usuario';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  baseUrl = "http://localhost:8080/api/usuario";

  constructor(private httpClient: HttpClient) { }

  salvarUsuario(usuario: Usuario): Observable<any> {
    return this.httpClient.post<Usuario>(this.baseUrl, usuario)
      .pipe(
        catchError(this.handleError)
      );
  }

  atualizarUsuario(usuario: Usuario): Observable<any> {
    const url = `${this.baseUrl}/${usuario.id}`;  // Assumindo que o ID do usuário está em `usuario.id`
    return this.httpClient.put<Usuario>(url, usuario)
      .pipe(
        catchError(this.handleError)
      );
  }



  findAll() {
    return this.httpClient.get<Usuario>(`${this.baseUrl}`);
  }

  private handleError(error: any): Observable<any> {
    console.error('Ocorreu um erro:', error);
    return throwError(error);
  }
}
