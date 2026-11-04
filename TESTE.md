## Cenarios de teste da API

Os cenarios abaixo descrevem os status e os corpos de resposta esperados pela API. Para o cenario de sucesso, deve existir no banco de dados um usuario com ID `1` e os dados apresentados na resposta.

### 1. Sucesso — consultar usuario por ID

- **Endpoint:** `/usuario/1`
- **Metodo HTTP:** `GET`
- **Dados enviados:** ID `1` no caminho da URL; a requisicao nao possui corpo.
- **Status esperado:** `202 Accepted`
- **Resposta esperada:**

```json
{
  "nome": "Usuario de Teste",
  "dataNasc": "1990-05-20",
  "sexo": "MASCULINO",
  "telefone": "61999990000",
  "cidade": "Brasilia",
  "uf": "DF",
  "fotoUrl": null
}
```

### 2. Requisicao invalida — categoria de aluno invalida

- **Endpoint:** `/aluno`
- **Metodo HTTP:** `POST`
- **Dados enviados:**

```json
{
  "categoriaPretendida": "INVALIDA"
}
```

- **Status esperado:** `400 Bad Request`
- **Resposta esperada:**

```json
{
  "mensagem": "Requisição inválida."
}
```

### 3. Erro interno — falha controlada ao cadastrar instrutor

- **Endpoint:** `/instrutor`
- **Metodo HTTP:** `POST`
- **Dados enviados:**

```json
{
  "nome": "Instrutor de Teste",
  "cpf": "52998224725",
  "dataNasc": "1990-05-20",
  "sexo": "MASCULINO",
  "email": "instrutor@example.com",
  "senha": "SenhaTeste123!",
  "telefone": "61999990000",
  "cidade": "Brasilia",
  "uf": "DF",
  "fotoUrl": null,
  "numCnh": "12345678901",
  "categoriaCnh": "B",
  "carro": "Fiat Argo",
  "placaCarro": "ABC1D23",
  "precoHora": 100.00,
  "bio": "Instrutor de direcao",
  "anosExperiencia": 5
}
```

- **Falha simulada:** o servico de cadastro lanca uma excecao controlada durante o teste.
- **Status esperado:** `500 Internal Server Error`
- **Resposta esperada:**

```json
{
  "mensagem": "Erro interno do servidor."
}
```

A resposta de erro interno nao deve expor a mensagem da excecao, stack trace, SQL ou outros detalhes tecnicos. O detalhe da falha e registrado no log da aplicacao.
