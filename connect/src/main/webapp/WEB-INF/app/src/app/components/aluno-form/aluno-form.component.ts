import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AlunoService } from '../../services/aluno.service';

@Component({
  selector: 'app-aluno-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterLink],
  template: `
    <section class="page-header">
      <div>
        <span class="kicker">Cadastro</span>
        <h1>Novo Aluno</h1>
        <p>Preencha os dados abaixo para registrar um novo aluno no sistema Connect Drive.</p>
      </div>
      <a routerLink="/alunos" class="btn-ghost">
        <svg width="18" height="18" viewBox="0 0 18 18" fill="none" xmlns="http://www.w3.org/2000/svg">
          <path d="M11.25 14.25L5.25 9L11.25 3.75" stroke="currentColor" stroke-width="1.8" stroke-linecap="round" stroke-linejoin="round"/>
        </svg>
        Voltar para lista
      </a>
    </section>

    <section class="form-panel">
      <header class="form-panel-header">
        <div>
          <h2>Informações do Aluno</h2>
          <p>Todos os campos marcados são obrigatórios para finalizar o cadastro.</p>
        </div>
        <div class="status-pill">Dados Pessoais &amp; Habilitação</div>
      </header>

      <form [formGroup]="form" (ngSubmit)="salvar()" class="form-grid">
        <div class="field-group">
          <label>Nome Completo</label>
          <input formControlName="nome" placeholder="Ex.: Ana Carolina Martins">
        </div>
        <div class="field-group">
          <label>CPF</label>
          <input formControlName="cpf" placeholder="Somente números">
        </div>
        <div class="field-group">
          <label>Data de Nascimento</label>
          <input type="date" formControlName="dataNasc">
        </div>
        <div class="field-group">
          <label>Sexo</label>
          <select formControlName="sexo">
            <option value="">Selecione</option>
            <option value="MASCULINO">Masculino</option>
            <option value="FEMININO">Feminino</option>
            <option value="OUTRO">Outro</option>
          </select>
        </div>
        <div class="field-group">
          <label>E-mail</label>
          <input type="email" formControlName="email" placeholder="aluno@connectdrive.com">
        </div>
        <div class="field-group">
          <label>Senha</label>
          <input type="password" formControlName="senha" placeholder="Defina uma senha">
        </div>
        <div class="field-group">
          <label>Telefone</label>
          <input formControlName="telefone" placeholder="(00) 00000-0000">
        </div>
        <div class="field-group">
          <label>Cidade</label>
          <input formControlName="cidade" placeholder="São Paulo">
        </div>
        <div class="field-group">
          <label>UF</label>
          <input formControlName="uf" maxlength="2" placeholder="SP">
        </div>
        <div class="field-group">
          <label>Categoria Pretendida</label>
          <select formControlName="categoriaPretendida">
            <option value="">Selecione</option>
            <option value="A">A</option>
            <option value="B">B</option>
            <option value="C">C</option>
            <option value="D">D</option>
            <option value="E">E</option>
            <option value="AB">AB</option>
            <option value="ACC">ACC</option>
          </select>
        </div>
        <div class="field-group field-full">
          <label>URL da Foto (opcional)</label>
          <input formControlName="fotoUrl" placeholder="https://exemplo.com/foto.jpg">
        </div>
        <div class="form-actions field-full">
          <button class="btn-ghost" type="button" routerLink="/alunos">Cancelar</button>
          <button class="btn-primary" type="submit" [disabled]="form.invalid || salvando">
            <svg width="18" height="18" viewBox="0 0 18 18" fill="none" xmlns="http://www.w3.org/2000/svg">
              <path d="M15 5.25L6.75 13.5L3 9.75" stroke="currentColor" stroke-width="1.8" stroke-linecap="round" stroke-linejoin="round"/>
            </svg>
            {{ salvando ? 'Salvando...' : 'Salvar Aluno' }}
          </button>
        </div>
      </form>
    </section>

    <div class="toast" *ngIf="mensagem" [class.toast-error]="erro">{{ mensagem }}</div>
  `,
  styles: [`
    .page-header { display:flex; flex-wrap:wrap; justify-content:space-between; align-items:flex-end; gap:1rem; }
    .kicker { color:#0052cc; font-weight:600; letter-spacing:.16em; text-transform:uppercase; font-size:.85rem; }
    h1 { font-size:clamp(2rem,5vw,3.4rem); margin:.4rem 0; letter-spacing:-.01em; }
    p { color:#4f4f4f; }
    .btn-ghost { display:flex; align-items:center; gap:.5rem; border:1px solid rgba(0,82,204,.4); color:#0052cc; padding:.7rem 1.4rem; border-radius:999px; text-decoration:none; font-weight:600; }
    .form-panel { background:#fff; border-radius:18px; border:1px solid #dfe3e8; box-shadow:0 40px 70px rgba(15,23,42,.08); overflow:hidden; display:flex; flex-direction:column; }
    .form-panel-header { display:flex; flex-wrap:wrap; justify-content:space-between; gap:1rem; align-items:flex-start; padding:clamp(1.8rem,4vw,2.6rem); border-bottom:1px solid #eef1f6; }
    .form-panel-header h2 { font-size:1.6rem; margin-bottom:.4rem; }
    .status-pill { background:rgba(0,82,204,.12); color:#003d99; padding:.5rem 1.2rem; border-radius:999px; font-weight:600; }
    .form-grid { display:grid; grid-template-columns:repeat(auto-fit,minmax(280px,1fr)); gap:1.4rem; padding:clamp(1.8rem,4vw,2.6rem); }
    .field-group { display:flex; flex-direction:column; gap:.6rem; }
    .field-full { grid-column:1 / -1; }
    label { font-weight:600; }
    input, select { padding:.9rem 1.1rem; border-radius:12px; border:1px solid #dfe3e8; font-size:1rem; transition:border .2s ease, box-shadow .2s ease; }
    input:focus, select:focus { outline:none; border-color:rgba(0,82,204,.8); box-shadow:0 0 0 4px rgba(0,82,204,.08); }
    .form-actions { display:flex; flex-wrap:wrap; justify-content:flex-end; gap:.8rem; }
    .btn-primary { display:flex; align-items:center; gap:.5rem; background:linear-gradient(135deg,#0052cc,#0066ff); color:#fff; border:none; padding:.9rem 2rem; border-radius:999px; font-weight:600; cursor:pointer; box-shadow:0 18px 40px rgba(0,82,204,.2); }
    .btn-primary:disabled { opacity:.6; cursor:not-allowed; box-shadow:none; }
    .toast { position:fixed; bottom:2rem; left:50%; transform:translateX(-50%); background:linear-gradient(135deg,rgba(34,197,94,.95),rgba(22,163,74,.95)); color:#fff; padding:.9rem 2.4rem; border-radius:999px; box-shadow:0 30px 60px rgba(22,163,74,.25); font-weight:600; }
    .toast-error { background:linear-gradient(135deg,rgba(239,68,68,.98),rgba(220,38,38,.98)); box-shadow:0 30px 60px rgba(220,38,38,.25); }
  `]
})
export class AlunoFormComponent {
  form: FormGroup;
  salvando = false;
  mensagem = '';
  erro = false;

  constructor(private fb: FormBuilder, private alunoService: AlunoService, private router: Router) {
    this.form = this.fb.group({
      nome: ['', Validators.required],
      cpf: ['', [Validators.required, Validators.minLength(11), Validators.maxLength(11)]],
      dataNasc: ['', Validators.required],
      sexo: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      senha: ['', Validators.required],
      telefone: ['', Validators.required],
      cidade: ['', Validators.required],
      uf: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(2)]],
      fotoUrl: [''],
      categoriaPretendida: ['', Validators.required]
    });
  }

  salvar(): void {
    if (this.form.invalid) return;
    this.salvando = true;
    this.mensagem = '';
    this.erro = false;
    this.alunoService.criar(this.form.value).subscribe({
      next: () => {
        this.mensagem = 'Aluno cadastrado com sucesso!';
        setTimeout(() => this.router.navigate(['/alunos']), 1000);
      },
      error: (err) => {
        console.error(err);
        this.mensagem = 'Não foi possível cadastrar o aluno. Verifique os dados informados.';
        this.erro = true;
        this.salvando = false;
        setTimeout(() => (this.mensagem = ''), 3200);
      },
      complete: () => {
        this.salvando = false;
      }
    });
  }
}
