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

  constructor(private formBuilder: FormBuilder, private usuarioService: UsuarioService) { }

  ngOnInit(): void {
    this.buscarTodos();

    this.formBuilder.group({
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

  onSubmit(): void {
    if (this.form.valid) {
      const usuario = this.form.value;
      this.usuarioService.salvarUsuario(usuario)
        .subscribe(
          (response: any) => {
            console.log('Usuário salvo com sucesso:', response);
          },
          error => {
            console.error('Erro ao salvar usuário:', error);
          }
        );
    }
  }

}
