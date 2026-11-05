import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { AlunoService } from '../../services/aluno.service';
import { AlunoResponse } from '../../services/aluno.model';

@Component({
  selector: 'app-aluno-list',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  template: `
    <section class="hero">
      <div class="hero-content">
        <span class="hero-kicker">Gestão de Alunos</span>
        <h1 class="hero-title">Controle total da base de alunos</h1>
        <p class="hero-text">Visualize, filtre por categoria pretendida ou busque por matrícula para manter tudo organizado em um só lugar.</p>
        <div class="hero-actions">
          <a routerLink="/alunos/novo" class="btn-primary">
            <svg width="18" height="18" viewBox="0 0 18 18" fill="none" xmlns="http://www.w3.org/2000/svg">
              <path d="M9 3.75V14.25M3.75 9H14.25" stroke="currentColor" stroke-width="1.8" stroke-linecap="round" stroke-linejoin="round"/>
            </svg>
            Cadastrar Aluno
          </a>
          <button class="btn-ghost" (click)="buscarPorCategoria()">
            <svg width="18" height="18" viewBox="0 0 18 18" fill="none" xmlns="http://www.w3.org/2000/svg">
              <path d="M9 3V1.5L6 4.5L9 7.5V6C11.4853 6 13.5 8.01472 13.5 10.5C13.5 11.2956 13.2893 12.0454 12.9204 12.6956L13.9749 13.7501C14.6154 12.8639 15 11.7399 15 10.5C15 7.18629 12.3137 4.5 9 4.5V3ZM4.07957 7.30443L3.02513 6.24998C2.38463 7.1361 2 8.26013 2 9.5C2 12.8137 4.68629 15.5 8 15.5V17L11 14L8 11V12.5C5.51472 12.5 3.5 10.4853 3.5 9.5C3.5 8.70443 3.71068 7.95457 4.07957 7.30443Z" fill="currentColor"/>
            </svg>
            Atualizar Lista
          </button>
        </div>
      </div>
      <div class="hero-card">
        <div class="hero-card-header">
          <span>Total de Registros</span>
          <strong>{{ alunos.length }}</strong>
        </div>
        <p>Resultados carregados conforme os filtros aplicados.</p>
      </div>
    </section>

    <section class="panel">
      <header class="panel-header">
        <div class="panel-heading">
          <span class="panel-kicker">Consulta</span>
          <h2 class="panel-title">Listagem de Alunos</h2>
          <p class="panel-subtitle">Filtre por categoria pretendida ou pesquise diretamente pela matrícula.</p>
        </div>
        <div class="panel-toolbar">
          <div class="filter-group">
            <label>Categoria Pretendida</label>
            <div class="select-wrap">
              <select [(ngModel)]="categoria">
                <option value="A">Categoria A</option>
                <option value="B">Categoria B</option>
                <option value="C">Categoria C</option>
                <option value="D">Categoria D</option>
                <option value="E">Categoria E</option>
                <option value="AB">Categoria AB</option>
                <option value="ACC">ACC</option>
              </select>
            </div>
            <button class="btn-outline" (click)="buscarPorCategoria()">Aplicar</button>
          </div>
          <div class="search-group">
            <div class="search-field">
              <svg width="18" height="18" viewBox="0 0 18 18" fill="none" xmlns="http://www.w3.org/2000/svg">
                <path d="M8.25 14.25C11.5637 14.25 14.25 11.5637 14.25 8.25C14.25 4.93629 11.5637 2.25 8.25 2.25C4.93629 2.25 2.25 4.93629 2.25 8.25C2.25 11.5637 4.93629 14.25 8.25 14.25Z" stroke="currentColor" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"/>
                <path d="M15.75 15.75L12.4875 12.4875" stroke="currentColor" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"/>
              </svg>
              <input [(ngModel)]="matricula" placeholder="Digite a matrícula...">
            </div>
            <button class="btn-primary-sm" (click)="buscarPorMatricula()">Buscar</button>
          </div>
        </div>
      </header>

      <div class="table-wrapper" *ngIf="alunos.length">
        <table class="data-table">
          <thead>
            <tr>
              <th>Aluno</th>
              <th>Matrícula</th>
              <th>Categoria Pretendida</th>
              <th>Data de Matrícula</th>
              <th>Status</th>
            </tr>
          </thead>
          <tbody>
            <tr *ngFor="let aluno of alunos">
              <td>
                <div class="user-cell">
                  <div class="avatar">{{ (aluno.nome || aluno.matricula || 'CD').slice(0,2).toUpperCase() }}</div>
                  <div class="user-meta">
                    <span class="user-name">{{ aluno.nome || 'Não informado' }}</span>
                    <span class="user-sub">Cadastro ativo</span>
                  </div>
                </div>
              </td>
              <td class="mono">{{ aluno.matricula }}</td>
              <td><span class="tag">{{ aluno.categoriaPretendida }}</span></td>
              <td>{{ aluno.dataMatricula | date:'dd/MM/yyyy HH:mm' }}</td>
              <td><span class="pill pill-success">Ativo</span></td>
            </tr>
          </tbody>
        </table>
      </div>

      <div class="empty-state" *ngIf="!alunos.length && !carregando">
        <div class="empty-icon">
          <svg width="28" height="28" viewBox="0 0 28 28" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path d="M14 2.33325C7.56 2.33325 2.33334 7.55992 2.33334 13.9999C2.33334 20.4399 7.56 25.6666 14 25.6666C20.44 25.6666 25.6667 20.4399 25.6667 13.9999C25.6667 7.55992 20.44 2.33325 14 2.33325ZM14 23.3333C8.855 23.3333 4.66668 19.145 4.66668 13.9999C4.66668 8.85492 8.855 4.66659 14 4.66659C19.145 4.66659 23.3333 8.85492 23.3333 13.9999C23.3333 19.145 19.145 23.3333 14 23.3333ZM14.5833 8.16659H13.4167V15.1666H14.5833V8.16659ZM14.5833 17.5H13.4167V18.6666H14.5833V17.5Z" fill="currentColor"/>
          </svg>
        </div>
        <h3>Nenhum aluno encontrado</h3>
        <p>Utilize os filtros acima ou cadastre um novo aluno para começar.</p>
        <a routerLink="/alunos/novo" class="btn-primary">Cadastrar primeiro aluno</a>
      </div>
    </section>
  `,
  styles: [`
    .hero { display:grid; grid-template-columns:repeat(auto-fit,minmax(280px,1fr)); gap:1.5rem; align-items:center; background:linear-gradient(120deg,rgba(0,82,204,.12) 0%,rgba(255,255,255,.95) 100%); border-radius:18px; border:1px solid rgba(0,82,204,.18); box-shadow:0 30px 60px rgba(0,82,204,.12); padding:clamp(2rem,5vw,3.6rem); position:relative; overflow:hidden; }
    .hero::after { content:''; position:absolute; width:320px; height:320px; border-radius:50%; background:radial-gradient(circle,rgba(0,102,255,.16),transparent 70%); right:-140px; top:-160px; }
    .hero-content { display:flex; flex-direction:column; gap:1rem; position:relative; z-index:1; }
    .hero-kicker { color:#0052cc; font-weight:600; letter-spacing:.18em; text-transform:uppercase; font-size:.85rem; }
    .hero-title { font-size:clamp(2rem,5vw,3.8rem); line-height:1.05; letter-spacing:-.02em; }
    .hero-text { color:#4f4f4f; max-width:560px; font-size:1.02rem; }
    .hero-actions { display:flex; flex-wrap:wrap; gap:.8rem; }
    .btn-primary { display:flex; align-items:center; gap:.5rem; background:linear-gradient(135deg,#0052cc,#0066ff); color:#fff; padding:.8rem 1.8rem; border-radius:999px; font-weight:600; text-decoration:none; box-shadow:0 20px 50px rgba(0,82,204,.25); border:none; cursor:pointer; }
    .btn-ghost { display:flex; align-items:center; gap:.5rem; background:transparent; border:1px solid rgba(0,82,204,.4); color:#0052cc; padding:.8rem 1.6rem; border-radius:999px; font-weight:600; cursor:pointer; }
    .hero-card { position:relative; z-index:1; background:#fff; border-radius:16px; border:1px solid rgba(0,82,204,.2); box-shadow:0 30px 60px rgba(15,23,42,.12); padding:2rem; display:flex; flex-direction:column; gap:.6rem; }
    .hero-card-header { display:flex; align-items:baseline; justify-content:space-between; }
    .hero-card-header span { color:#4f4f4f; }
    .hero-card-header strong { font-size:clamp(2.4rem,6vw,4rem); }
    .panel { background:#fff; border-radius:18px; border:1px solid #dfe3e8; box-shadow:0 40px 70px rgba(15,23,42,.08); overflow:hidden; display:flex; flex-direction:column; }
    .panel-header { display:flex; flex-direction:column; gap:1.8rem; padding:clamp(1.8rem,4vw,2.8rem); }
    .panel-heading { display:flex; flex-direction:column; gap:.4rem; }
    .panel-kicker { color:#0052cc; font-weight:600; }
    .panel-title { font-size:clamp(1.8rem,4vw,2.8rem); letter-spacing:-.01em; }
    .panel-subtitle { color:#4f4f4f; }
    .panel-toolbar { display:flex; flex-wrap:wrap; gap:1rem; justify-content:space-between; align-items:flex-end; }
    .filter-group { display:flex; flex-wrap:wrap; gap:.8rem; align-items:flex-end; }
    .filter-group label { display:flex; flex-direction:column; gap:.5rem; font-weight:600; }
    .select-wrap { position:relative; }
    .select-wrap select { appearance:none; padding:.8rem 2.8rem .8rem 1rem; border-radius:999px; border:1px solid #dfe3e8; background:#fff; font-weight:600; }
    .btn-outline { border:1px solid rgba(0,82,204,.4); color:#0052cc; background:transparent; padding:.8rem 1.6rem; border-radius:999px; font-weight:600; cursor:pointer; }
    .search-group { display:flex; flex-wrap:wrap; gap:.6rem; align-items:center; }
    .search-field { display:flex; align-items:center; gap:.7rem; background:#f7f9fc; border-radius:999px; padding:.7rem 1.4rem; border:1px solid transparent; }
    .search-field:focus-within { border-color:rgba(0,82,204,.6); box-shadow:0 0 0 4px rgba(0,82,204,.08); }
    .search-field input { border:none; background:transparent; outline:none; min-width:240px; }
    .btn-primary-sm { background:linear-gradient(135deg,#0052cc,#0066ff); color:#fff; border:none; padding:.7rem 1.6rem; border-radius:999px; font-weight:600; cursor:pointer; }
    .table-wrapper { overflow-x:auto; }
    .data-table { width:100%; border-collapse:collapse; min-width:880px; }
    .data-table thead { background:linear-gradient(90deg,rgba(0,82,204,.12) 0%,transparent 100%); }
    .data-table th { text-align:left; padding:1rem 2.4rem; color:#003d99; font-weight:600; }
    .data-table td { padding:1.2rem 2.4rem; border-top:1px solid #eef1f6; vertical-align:middle; }
    .user-cell { display:flex; align-items:center; gap:1rem; }
    .avatar { width:48px; height:48px; border-radius:50%; background:linear-gradient(135deg,rgba(0,82,204,.95),rgba(0,102,255,.9)); color:#fff; display:flex; align-items:center; justify-content:center; font-weight:600; }
    .user-meta { display:flex; flex-direction:column; }
    .user-name { font-weight:600; }
    .user-sub { color:#4f4f4f; font-size:.95rem; }
    .mono { font-family:monospace; letter-spacing:.08em; }
    .tag { background:rgba(0,82,204,.14); color:#003d99; padding:.4rem 1rem; border-radius:999px; font-weight:600; }
    .pill { padding:.4rem .9rem; border-radius:999px; font-weight:600; }
    .pill-success { background:rgba(34,197,94,.16); color:#166534; }
    .empty-state { display:flex; flex-direction:column; align-items:center; gap:.8rem; text-align:center; padding:clamp(2rem,8vw,4rem); }
    .empty-icon { width:68px; height:68px; border-radius:50%; background:rgba(0,82,204,.12); color:#0052cc; display:flex; align-items:center; justify-content:center; }
    @media (max-width:720px) { .search-field input { min-width:auto; } .data-table { min-width:720px; } }
  `]
})
export class AlunoListComponent implements OnInit {
  alunos: AlunoResponse[] = [];
  matricula = '';
  categoria = 'B';
  carregando = false;

  constructor(private alunoService: AlunoService) {}

  ngOnInit(): void {
    this.buscarPorCategoria();
  }

  buscarPorMatricula(): void {
    if (!this.matricula.trim()) return;
    this.carregando = true;
    this.alunoService.buscarPorMatricula(this.matricula.trim()).subscribe({
      next: (res) => {
        this.alunos = res ? [res] : [];
        this.carregando = false;
      },
      error: () => {
        this.alunos = [];
        this.carregando = false;
      }
    });
  }

  buscarPorCategoria(): void {
    this.carregando = true;
    this.alunoService.buscarPorCategoria(this.categoria).subscribe({
      next: (res) => {
        this.alunos = res || [];
        this.carregando = false;
      },
      error: () => {
        this.alunos = [];
        this.carregando = false;
      }
    });
  }
}
