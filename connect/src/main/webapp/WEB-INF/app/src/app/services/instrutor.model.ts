export interface InstrutorRequest {
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
  numCnh: string;
  categoriaCnh: string;
  carro: string;
  placaCarro: string;
  precoHora: number;
  bio: string;
  anosExperiencia: number;
}

export interface InstrutorResponse {
  placaCarro?: string;
  nome?: string;
  dataNasc?: string;
  sexo?: string;
  telefone?: string;
  cidade?: string;
  uf?: string;
  fotoUrl?: string;
  categoriaCNH?: string;
  carro?: string;
  precoHora?: number;
  bio?: string;
  anosExperiencia?: number;
  notaMedia?: number;
}



