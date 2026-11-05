import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { InstrutorService } from '../../services/instrutor.service';
import { InstrutorResponse } from '../../services/instrutor.model';

@Component({
  selector: 'app-instrutor-list',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  template: `
    <section class="page-header">
      <div>
        <span class="kicker">Gestão de Instrutores</span>
        <h1>Instrutores Cadastrados</h1>
        <p>Consulte por CNH, sexo ou nota média para visualizar os perfis disponíveis.</p>
      </div>
      <div class="header-actions">
        <a routerLink="/" class="btn-ghost">Voltar ao início</a>
        <a routerLink="/instrutores/novo" class="btn-primary">
          <svg width="18" height="18" viewBox="0 0 18 18" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path d="M9 3.75V14.25M3.75 9H14.25" stroke="currentColor" stroke-width="1.8" stroke-linecap="round" stroke-linejoin="round"/>
          </svg>
          Novo Instrutor
        </a>
      </div>
    </section>

    <section class="panel">
      <header class="panel-header">
        <div class="panel-heading">
          <span class="panel-kicker">Consulta</span>
          <h2 class="panel-title">Filtrar Instrutores</h2>
          <p class="panel-subtitle">Busque por número da CNH ou filtre por sexo e nota média.</p>
        </div>
        <div class="filters-grid">
          <div class="filter-card">
            <label>Buscar por CNH</label>
            <div class="search-field">
              <svg width="18" height="18" viewBox="0 0 18 18" fill="none" xmlns="http://www.w3.org/2000/svg">
                <path d="M8.25 14.25C11.5637 14.25 14.25 11.5637 14.25 8.25C14.25 4.93629 11.5637 2.25 8.25 2.25C4.93629 2.25 2.25 4.93629 2.25 8.25C2.25 11.5637 4.93629 14.25 8.25 14.25Z" stroke="currentColor" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"/>
                <path d="M15.75 15.75L12.4875 12.4875" stroke="currentColor" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"/>
              </svg>
              <input [(ngModel)]="cnh" placeholder="Digite o número da CNH">
            </div>
            <button class="btn-primary-sm" (click)="buscarPorCnh()">Buscar</button>
          </div>
          <div class="filter-card">
            <label>Filtrar por Sexo</label>
            <div class="inline">
              <select [(ngModel)]="sexo">
                <option value="MASCULINO">Masculino</option>
                <option value="FEMININO">Feminino</option>
                <option value="OUTRO">Outro</option>
              </select>
              <button class="btn-outline" (click)="buscarPorSexo()">Aplicar</button>
            </div>
          </div>
          <div class="filter-card">
            <label>Filtrar por Nota Média</label>
            <div class="inline">
              <input type="number" step="0.1" min="0" max="5" [(ngModel)]="nota">
              <button class="btn-outline" (click)="buscarPorNota()">Aplicar</button>
            </div>
          </div>
        </div>
      </header>

      <div class="table-wrapper" *ngIf="instrutores.length">
        <table class="data-table">
          <thead>
            <tr>
              <th>Instrutor</th>
              <th>Categoria CNH</th>
              <th>Veículo</th>
              <th>Preço/Hora</th>
              <th>Anos de Experiência</th>
              <th>Nota Média</th>
              <th>Localização</th>
            </tr>
          </thead>
          <tbody>
            <tr *ngFor="let instrutor of instrutores">
              <td>
                <div class="user-cell">
                  <div class="avatar">{{ (instrutor.nome || 'CD').slice(0,2).toUpperCase() }}</div>
                  <div class="user-meta">
                    <span class="user-name">{{ instrutor.nome }}</span>
                    <span class="user-sub">{{ instrutor.telefone || 'Contato não informado' }}</span>
                  </div>
                </div>
              </td>
              <td><span class="tag">{{ instrutor.categoriaCNH }}</span></td>
              <td>{{ instrutor.carro }} - {{ instrutor.placaCarro || 'S/PLACA' }}</td>
              <td>R$ {{ instrutor.precoHora | number:'1.2-2' }}</td>
              <td>{{ instrutor.anosExperiencia }} anos</td>
              <td><span class="pill pill-warn">{{ instrutor.notaMedia | number:'1.1-1' }}</span></td>
              <td>{{ instrutor.cidade }}/{{ instrutor.uf }}</td>
            </tr>
          </tbody>
        </table>
      </div>

      <div class="empty-state" *ngIf="!instrutores.length && !carregando">
        <div class="empty-icon">
          <svg width="28" height="28" viewBox="0 0 28 28" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path d="M14 2.33325C7.56 2.33325 2.33334 7.55992 2.33334 13.9999C2.33334 20.4399 7.56 25.6666 14 25.6666C20.44 25.6666 25.6667 20.4399 25.6667 13.9999C25.6667 7.55992 20.44 2.33325 14 2.33325ZM14 23.3333C8.855 23.3333 4.66668 19.145 4.66668 13.9999C4.66668 8.85492 8.855 4.66659 14 4.66659C19.145 4.66659 23.3333 8.85492 23.3333 13.9999C23.3333 19.145 19.145 23.3333 14 23.3333ZM14.5833 8.16659H13.4167V15.1666H14.5833V8.16659ZM14.5833 17.5H13.4167V18.6666H14.5833V17.5Z" fill="currentColor"/>
          </svg>
        </div>
        <h3>Nenhum instrutor encontrado</h3>
        <p>Utilize os filtros ou cadastre um novo instrutor.</p>
        <a routerLink="/instrutores/novo" class="btn-primary">Cadastrar instrutor</a>
      </div>
    </section>
  `,
  styles: [`
    .page-header { display:flex; flex-wrap:wrap; justify-content:space-between; align-items:flex-end; gap:1rem; }
    .kicker { color:#0052cc; font-weight:600; letter-spacing:.16em; text-transform:uppercase; font-size:.85rem; }
    h1 { font-size:clamp(2rem,5vw,3.4rem); margin:.4rem 0; letter-spacing:-.01em; }
    p { color:#4f4f4f; }
    .header-actions { display:flex; flex-wrap:wrap; gap:.8rem; }
    .btn-ghost { display:flex; align-items:center; gap:.5rem; border:1px solid rgba(0,82,204,.4); color:#0052cc; padding:.7rem 1.4rem; border-radius:999px; text-decoration:none; font-weight:600; }
    .btn-primary { display:flex; align-items:center; gap:.5rem; background:linear-gradient(135deg,#0052cc,#0066ff); color:#fff; padding:.8rem 1.8rem; border-radius:999px; font-weight:600; text-decoration:none; box-shadow:0 18px 40px rgba(0,82,204,.25); }
    .panel { background:#fff; border-radius:18px; border:1px solid #dfe3e8; box-shadow:0 40px 70px rgba(15,23,42,.08); overflow:hidden; }
    .panel-header { display:flex; flex-direction:column; gap:1.8rem; padding:clamp(1.8rem,4vw,2.8rem); }
    .panel-kicker { color:#0052cc; font-weight:600; }
    .panel-title { font-size:clamp(1.8rem,4vw,2.6rem); }
    .panel-subtitle { color:#4f4f4f; }
    .filters-grid { display:grid; grid-template-columns:repeat(auto-fit,minmax(280px,1fr)); gap:1rem; }
    .filter-card { background:linear-gradient(180deg,rgba(0,82,204,.08) 0%,transparent 100%); border-radius:14px; border:1px solid rgba(0,82,204,.18); padding:1.4rem; display:flex; flex-direction:column; gap:.8rem; }
    .filter-card label { font-weight:600; }
    .search-field { display:flex; align-items:center; gap:.7rem; background:#fff; border-radius:999px; padding:.7rem 1.2rem; border:1px solid #dfe3e8; }
    .search-field input { border:none; outline:none; flex:1; }
    .inline { display:flex; flex-wrap:wrap; gap:.6rem; align-items:center; }
    .inline select, .inline input { padding:.8rem 1rem; border-radius:999px; border:1px solid #dfe3e8; }
    .btn-primary-sm { background:linear-gradient(135deg,#0052cc,#0066ff); color:#fff; border:none; padding:.7rem 1.4rem; border-radius:999px; font-weight:600; cursor:pointer; }
    .btn-outline { border:1px solid rgba(0,82,204,.4); color:#0052cc; background:transparent; padding:.7rem 1.4rem; border-radius:999px; font-weight:600; cursor:pointer; }
    .table-wrapper { overflow-x:auto; }
    .data-table { width:100%; border-collapse:collapse; min-width:980px; }
    .data-table thead { background:linear-gradient(90deg,rgba(0,82,204,.12) 0%,transparent 100%); }
    .data-table th { text-align:left; padding:1rem 2.2rem; color:#003d99; }
    .data-table td { padding:1.1rem 2.2rem; border-top:1px solid #eef1f6; vertical-align:middle; }
    .user-cell { display:flex; align-items:center; gap:1rem; }
    .avatar { width:48px; height:48px; border-radius:50%; background:linear-gradient(135deg,rgba(0,82,204,.95),rgba(0,102,255,.9)); color:#fff; display:flex; align-items:center; justify-content:center; font-weight:600; }
    .user-meta { display:flex; flex-direction:column; }
    .user-name { font-weight:600; }
    .user-sub { color:#4f4f4f; font-size:.95rem; }
    .tag { background:rgba(0,82,204,.14); color:#003d99; padding:.4rem .9rem; border-radius:999px; font-weight:600; }
    .pill { padding:.4rem .9rem; border-radius:999px; font-weight:600; }
    .pill-warn { background:rgba(245,158,11,.18); color:#92400e; }
    .empty-state { display:flex; flex-direction:column; align-items:center; gap:.8rem; text-align:center; padding:clamp(2rem,8vw,4rem); }
    .empty-icon { width:68px; height:68px; border-radius:50%; background:rgba(0,82,204,.12); color:#0052cc; display:flex; align-items:center; justify-content:center; }
  `]
})
export class InstrutorListComponent implements OnInit {
  instrutores: InstrutorResponse[] = [];
  cnh = '';
  sexo = 'MASCULINO';
  nota = 4.5;
  carregando = false;

  constructor(private instrutorService: InstrutorService) {}

  ngOnInit(): void {
    this.buscarPorSexo();
  }

  buscarPorCnh(): void {
    if (!this.cnh.trim()) return;
    this.carregando = true;
    this.instrutorService.buscarPorCnh(this.cnh.trim()).subscribe({
      next: (res) => {
        this.instrutores = res ? [res as InstrutorResponse] : [];
        this.carregando = false;
      },
      error: () => {
        this.instrutores = [];
        this.carregando = false;
      }
    });
  }

  buscarPorSexo(): void {
    this.carregando = true;
    this.instrutorService.buscarPorSexo(this.sexo).subscribe({
      next: (res) => {
        this.instrutores = res || [];
        this.carregando = false;
      },
      error: () => {
        this.instrutores = [];
        this.carregando = false;
      }
    });
  }

  buscarPorNota(): void {
    this.carregando = true;
    this.instrutorService.buscarPorNota(this.nota).subscribe({
      next: (res) => {
        this.instrutores = res || [];
        this.carregando = false;
      },
      error: () => {
        this.instrutores = [];
        this.carregando = false;
      }
    });
  }
}
