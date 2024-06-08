import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Usuario } from '../../model/usuario';
import { UsuarioService } from '../usuario/usuario.service';

@Component({
  selector: 'app-novo-usuario',
  templateUrl: './novo-usuario.component.html',
  styleUrl: './novo-usuario.component.scss'
})
export class NovoUsuarioComponent implements OnInit{

  form!: FormGroup;
  isUpdating: boolean = false;
  currentUser: Usuario = new Usuario();


  constructor(private formBuilder: FormBuilder, private usuarioService: UsuarioService) {}

  ngOnInit(): void {
    this.formBuilder.group({
      id:[],
      name: [''],
      email: [''],
      cpf: [''],
      isAtivo: [Boolean],
      perfil: ['']

    })
  }


  onSubmit(): void {
    if (this.form.valid) {
      const usuario: Usuario = this.form.value;
      if (this.isUpdating) {
        this.usuarioService.atualizarUsuario(usuario)
          .subscribe(
            (response: any) => {
              console.log('Usuário atualizado com sucesso:', response);
            },
            error => {
              console.error('Erro ao atualizar usuário:', error);
            }
          );
      } else {
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

  loadUserForEdit(userId: number): void {
    this.usuarioService.getUsuarioById(userId).subscribe(
      (usuario: Usuario) => {
        this.currentUser = usuario;
        this.form.patchValue(usuario);
        this.isUpdating = true;
      },
      error => {
        console.error('Erro ao carregar usuário:', error);
      }
    );
  }


}
