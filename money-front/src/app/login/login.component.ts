import { Component } from '@angular/core';
import { UsuarioService } from '../usuario/usuario.service';
import { Usuario } from '../../model/Usuario';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {

  constructor(private usuarioService: UsuarioService) { }

  buscarTodos() {
    this.usuarioService.findAll().subscribe(
    (usuarios: Usuario) => {
      console.log('Usuarios: ', usuarios);
    },
    error => {
      console.error('Erro ao buscar usuários:', error);
    }
  )
  }

}
