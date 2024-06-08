import { Usuario } from './../../model/usuario';
import { Component, OnInit } from '@angular/core';
import { UsuarioService } from '../usuario/usuario.service';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent implements OnInit {

  form!: FormGroup;
  isUpdating: boolean = false;
  currentUser: Usuario = new Usuario();

  constructor(private formBuilder: FormBuilder, private usuarioService: UsuarioService) { }

  ngOnInit(): void {
    this.buscarTodos();

    this.formBuilder.group({
      id:[],
      name: [''],
      email: [''],
      cpf: [''],
      isAtivo: [Boolean],
      perfil: ['']

    })

  }


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
