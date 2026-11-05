import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { AlunoFormComponent } from './components/aluno-form/aluno-form.component';
import { AlunoListComponent } from './components/aluno-list/aluno-list.component';
import { InstrutorListComponent } from './components/instrutor-list/instrutor-list.component';
import { InstrutorFormComponent } from './components/instrutor-form/instrutor-form.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'alunos', component: AlunoListComponent },
  { path: 'alunos/novo', component: AlunoFormComponent },
  { path: 'instrutores', component: InstrutorListComponent },
  { path: 'instrutores/novo', component: InstrutorFormComponent }
];
