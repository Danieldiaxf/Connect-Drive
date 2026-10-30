export interface AlunoRequest {
  nome: string;
  cpf: string;
  dataNasc: string;
  sexo: string;
  email: string;
  senha: string;
  telefone: string;
  cidade: string;
  uf: string;
  fotoUrl?: string;
  categoriaPretendida: string;
}

export interface AlunoResponse {
  nome?: string;
  matricula?: string;
  categoriaPretendida?: string;
  dataMatricula?: string;
}
