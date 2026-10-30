import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [RouterLink],
  template: `
    <section class="home-hero">
      <div class="home-content">
        <span class="hero-kicker">Bem-vindo ao Connect Drive</span>
        <h1 class="hero-title">Escolha o tipo de cadastro para gerenciar</h1>
        <p class="hero-text">Gerencie alunos e instrutores em um único painel, com integração direta com a API e experiência moderna em azul e branco.</p>
      </div>
      <div class="cards-grid">
        <a routerLink="/alunos" class="card">
          <div class="card-icon">
            <svg width="28" height="28" viewBox="0 0 28 28" fill="none" xmlns="http://www.w3.org/2000/svg">
              <path d="M14 14C17.3137 14 20 11.3137 20 8C20 4.68629 17.3137 2 14 2C10.6863 2 8 4.68629 8 8C8 11.3137 10.6863 14 14 14Z" stroke="currentColor" stroke-width="1.6"/>
              <path d="M4 26C4 21.5817 7.58172 18 12 18H16C20.4183 18 24 21.5817 24 26" stroke="currentColor" stroke-width="1.6" stroke-linecap="round"/>
            </svg>
          </div>
          <div>
            <h2>Alunos</h2>
            <p>Cadastrar, buscar por matrícula e filtrar por categoria pretendida.</p>
          </div>
          <span class="card-action">Acessar painel</span>
        </a>
        <a routerLink="/instrutores" class="card">
          <div class="card-icon">
            <svg width="28" height="28" viewBox="0 0 28 28" fill="none" xmlns="http://www.w3.org/2000/svg">
              <path d="M14 14C16.7614 14 19 11.7614 19 9C19 6.23858 16.7614 4 14 4C11.2386 4 9 6.23858 9 9C9 11.7614 11.2386 14 14 14Z" stroke="currentColor" stroke-width="1.6"/>
              <path d="M4.5 24C4.5 19.3056 8.30558 15.5 13 15.5H15C19.6944 15.5 23.5 19.3056 23.5 24" stroke="currentColor" stroke-width="1.6" stroke-linecap="round"/>
              <path d="M18 6L21 9M21 6L18 9" stroke="currentColor" stroke-width="1.6" stroke-linecap="round"/>
            </svg>
          </div>
          <div>
            <h2>Instrutores</h2>
            <p>Cadastrar instrutores com dados da CNH, veículo, preço/hora e bio.</p>
          </div>
          <span class="card-action">Acessar painel</span>
        </a>
      </div>
    </section>
  `,
  styles: [`
    .home-hero { display:flex; flex-direction:column; gap:2rem; background:linear-gradient(120deg,rgba(0,82,204,.12) 0%,rgba(255,255,255,.95) 100%); border-radius:18px; border:1px solid rgba(0,82,204,.18); box-shadow:0 40px 70px rgba(0,82,204,.12); padding:clamp(2rem,6vw,4rem); position:relative; overflow:hidden; }
    .home-hero::after { content:''; position:absolute; width:360px; height:360px; border-radius:50%; background:radial-gradient(circle,rgba(0,102,255,.16),transparent 70%); right:-160px; top:-180px; }
    .home-content { position:relative; z-index:1; display:flex; flex-direction:column; gap:1rem; max-width:720px; }
    .hero-kicker { color:#0052cc; font-weight:600; letter-spacing:.18em; text-transform:uppercase; font-size:.85rem; }
    .hero-title { font-size:clamp(2.4rem,6vw,4.6rem); line-height:1.02; letter-spacing:-.02em; }
    .hero-text { color:#4f4f4f; font-size:1.05rem; }
    .cards-grid { position:relative; z-index:1; display:grid; grid-template-columns:repeat(auto-fit,minmax(280px,1fr)); gap:1.2rem; }
    .card { background:#fff; border-radius:16px; border:1px solid rgba(0,82,204,.18); box-shadow:0 30px 60px rgba(15,23,42,.12); padding:2rem; display:flex; flex-direction:column; gap:1rem; text-decoration:none; color:inherit; transition:transform .2s ease, box-shadow .2s ease; }
    .card:hover { transform:translateY(-6px); box-shadow:0 36px 70px rgba(0,82,204,.18); }
    .card-icon { width:58px; height:58px; border-radius:16px; background:rgba(0,82,204,.12); color:#0052cc; display:flex; align-items:center; justify-content:center; }
    .card h2 { font-size:1.8rem; }
    .card p { color:#4f4f4f; }
    .card-action { color:#0052cc; font-weight:600; }
  `]
})
export class HomeComponent {}
