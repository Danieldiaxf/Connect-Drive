import { Component } from '@angular/core';
import { RouterOutlet, RouterLink } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterLink],
  template: `
    <header class="app-header">
      <div class="header-inner">
        <a routerLink="/" class="brand">
          <div class="brand-mark">
            <svg width="26" height="26" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
              <path d="M12 2L3 7V17L12 22L21 17V7L12 2ZM12 4.18L18.5 7.5L12 10.82L5.5 7.5L12 4.18ZM5 9.18L11 12.18V19.82L5 16.82V9.18ZM13 19.82V12.18L19 9.18V16.82L13 19.82Z" fill="currentColor"/>
            </svg>
          </div>
          <div class="brand-text">
            <div class="brand-title">Connect Drive</div>
            <div class="brand-subtitle">Painel de Gestão</div>
          </div>
        </a>
        <nav class="nav">
          <a routerLink="/" routerLinkActive="active" class="nav-link">Início</a>
          <a routerLink="/alunos" routerLinkActive="active" class="nav-link">Alunos</a>
          <a routerLink="/instrutores" routerLinkActive="active" class="nav-link">Instrutores</a>
          <div class="nav-cta">
            <a routerLink="/alunos/novo" class="btn-ghost">Cadastrar Aluno</a>
            <a routerLink="/instrutores/novo" class="btn-primary">
              <svg width="18" height="18" viewBox="0 0 18 18" fill="none" xmlns="http://www.w3.org/2000/svg">
                <path d="M9 3.75V14.25M3.75 9H14.25" stroke="currentColor" stroke-width="1.8" stroke-linecap="round" stroke-linejoin="round"/>
              </svg>
              Cadastrar Instrutor
            </a>
          </div>
        </nav>
      </div>
    </header>
    <main class="container">
      <router-outlet></router-outlet>
    </main>
    <footer class="app-footer">
      <div class="footer-inner">
        <p>&copy; 2026 Connect Drive. Todos os direitos reservados.</p>
        <p class="footer-sub">Gestão integrada de Alunos e Instrutores</p>
      </div>
    </footer>
  `,
  styles: [`
    :host { display:flex; flex-direction:column; min-height:100vh; }
    .app-header { background:linear-gradient(135deg,#0052cc 0%,#0066ff 100%); color:#fff; box-shadow:0 20px 60px rgba(0,82,204,.18); position:sticky; top:0; z-index:10; }
    .header-inner { max-width:1200px; margin:0 auto; display:flex; align-items:center; justify-content:space-between; gap:1.5rem; padding:1rem 2rem; flex-wrap:wrap; }
    .brand { display:flex; align-items:center; gap:.9rem; text-decoration:none; color:#fff; }
    .brand-mark { width:48px; height:48px; border-radius:14px; background:rgba(255,255,255,.18); backdrop-filter:blur(8px); display:flex; align-items:center; justify-content:center; }
    .brand-text { display:flex; flex-direction:column; }
    .brand-title { font-weight:700; }
    .brand-subtitle { font-size:.9rem; opacity:.8; }
    .nav { display:flex; align-items:center; gap:1rem; flex-wrap:wrap; }
    .nav-link { color:rgba(255,255,255,.9); text-decoration:none; font-weight:600; padding:.6rem .8rem; border-radius:999px; }
    .nav-link.active, .nav-link:hover { background:rgba(255,255,255,.18); }
    .nav-cta { display:flex; align-items:center; gap:.6rem; flex-wrap:wrap; }
    .btn-ghost { border:1px solid rgba(255,255,255,.6); color:#fff; padding:.6rem 1.2rem; border-radius:999px; text-decoration:none; font-weight:600; }
    .btn-primary { display:flex; align-items:center; gap:.5rem; background:#fff; color:#0052cc; padding:.7rem 1.6rem; border-radius:999px; font-weight:600; text-decoration:none; box-shadow:0 18px 40px rgba(0,20,60,.18); }
    .container { flex:1; max-width:1200px; width:100%; margin:0 auto; padding:clamp(1.5rem,4vw,3rem) 1.5rem; display:flex; flex-direction:column; gap:2rem; }
    .app-footer { background:linear-gradient(180deg,rgba(0,82,204,.06) 0%,transparent 100%); }
    .footer-inner { max-width:1200px; margin:0 auto; text-align:center; padding:2rem 1.5rem 2.4rem; color:#4f4f4f; display:flex; flex-direction:column; gap:.4rem; }
    .footer-sub { opacity:.8; }
    @media (max-width:880px) { .header-inner { flex-direction:column; align-items:flex-start; } .nav { align-self:stretch; flex-direction:column; align-items:flex-start; } .nav-cta { align-self:stretch; justify-content:space-between; } }
  `]
})
export class AppComponent {}
