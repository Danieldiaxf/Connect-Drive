import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { InstrutorRequest, InstrutorResponse } from './instrutor.model';

@Injectable({
  providedIn: 'root'
})
export class InstrutorService {
  private baseUrl = '/instrutor';

  constructor(private http: HttpClient) {}

  salvar(instrutor: InstrutorRequest): Observable<void> {
    return this.http.post<void>(this.baseUrl, instrutor);
  }

  buscarPorCnh(cnh: string): Observable<InstrutorResponse> {
    return this.http.get<InstrutorResponse>(`${this.baseUrl}/cnh/${cnh}`);
  }

  buscarPorSexo(sexo: string): Observable<InstrutorResponse[]> {
    return this.http.get<InstrutorResponse[]>(`${this.baseUrl}/sexo/${sexo}`);
  }

  buscarPorNota(nota: number): Observable<InstrutorResponse[]> {
    return this.http.get<InstrutorResponse[]>(`${this.baseUrl}/nota/${nota}`);
  }
}
