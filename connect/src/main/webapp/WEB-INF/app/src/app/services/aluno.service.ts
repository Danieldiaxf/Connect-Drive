import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AlunoRequest, AlunoResponse } from './aluno.model';

@Injectable({
  providedIn: 'root'
})
export class AlunoService {
  private baseUrl = '/aluno';

  constructor(private http: HttpClient) {}

  criar(aluno: AlunoRequest): Observable<void> {
    return this.http.post<void>(this.baseUrl, aluno);
  }

  buscarPorMatricula(matricula: string): Observable<AlunoResponse> {
    return this.http.get<AlunoResponse>(`${this.baseUrl}/matricula/${matricula}`);
  }

  buscarPorCategoria(categoria: string): Observable<AlunoResponse[]> {
    return this.http.get<AlunoResponse[]>(`${this.baseUrl}/categoria/${categoria}`);
  }
}
