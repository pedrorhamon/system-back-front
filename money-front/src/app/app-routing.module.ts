import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { NovoUsuarioComponent } from './novo-usuario/novo-usuario.component';
import { NavComponent } from './template/nav/nav.component';

const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'novo', component: NovoUsuarioComponent },
  { path: 'home', component: HomeComponent },
  { path: 'nav', component: NavComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
