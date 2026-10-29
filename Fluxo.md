# Connect Drive — Criação de Aluno e Instrutor

Documentação dos fluxos de cadastro das entidades **Aluno** e **Instrutor**, conforme o comportamento atual do código (branch `development`).

Ambas as entidades herdam de `Usuario` (herança `JOINED`): os dados comuns ficam em `usuarios` e os dados específicos em `alunos` / `instrutores`, ligados por `usuario_id`.

---

## 1. Criação de Aluno

**Endpoint:** `POST /aluno`
**Ator:** Candidato à CNH (ainda sem autenticação no sistema)
**Pré-condição:** nenhuma (o cadastro é público).

**Dados de entrada (`AlunoRequestDTO`):** nome, cpf, dataNasc, sexo, email, senha, telefone, cidade, uf, fotoUrl (opcional), categoriaPretendida.

### Fluxo Principal

1. O ator envia um `POST /aluno` com o JSON do cadastro.
2. O `AlunoController` converte o JSON em `AlunoRequestDTO` (os enums `Sexo` e `CategoriaPretendido` são validados nesta etapa) e repassa ao `AlunoService.criarAluno`.
3. O `AlunoMapper` converte o DTO em uma entidade `Aluno`.
4. O sistema **gera a matrícula**: 11 caracteres hexadecimais maiúsculos extraídos de um UUID. A matrícula não é informada pelo ator.
5. O sistema persiste o aluno (`AlunoRepository.save`). Antes de gravar, `dataCadastro` é preenchida com a data/hora atual.
6. O sistema grava uma linha em `usuarios` e uma linha em `alunos` (mesmo `id`/`usuario_id`).
7. O sistema responde **HTTP 204 (No Content)**.

**Pós-condição:** aluno cadastrado, com matrícula única gerada e `dataCadastro` preenchida.

### Fluxos Alternativos

| Id | Condição | Comportamento atual |
|---|---|---|
| A1 | JSON malformado ou valor de enum inválido (ex.: `categoriaPretendida = "INVALIDA"`) | A requisição é rejeitada com **HTTP 400** e o service não é chamado (coberto por teste). |
| A2 | Campo obrigatório ausente (nome, cpf, dataNasc, sexo, email, senha, telefone, cidade, uf, categoriaPretendida) | Não há validação na entrada. O erro aparece apenas ao gravar, por violação de `NOT NULL` no banco. |
| A3 | CPF ou e-mail já cadastrados | O banco rejeita por violação de unicidade. Não há checagem prévia nem mensagem de negócio. |
| A4 | Falha no mapeamento DTO → entidade | A exceção é propagada e nada é salvo (coberto por teste). |
| A5 | Falha ao persistir | A exceção original é propagada ao chamador (coberto por teste). |
| A6 | Matrícula gerada já existente | A constraint `unique` do banco rejeita a gravação. Não há nova tentativa de geração. |

> Nos fluxos A2, A3, A5 e A6 não existe tratador global de exceções no projeto, então a resposta HTTP depende do tratamento padrão do Spring (provavelmente erro 500).

---

## 2. Criação de Instrutor

**Endpoint:** `POST /instrutor`
**Ator:** Instrutor autônomo de direção (ainda sem autenticação no sistema)
**Pré-condição:** nenhuma (o cadastro é público).

**Dados de entrada (`InstrutorRequestDTO`):** nome, cpf, dataNasc, sexo, email, senha, telefone, cidade, uf, fotoUrl (opcional), numCnh, categoriaCnh, carro, placaCarro, precoHora, bio (opcional), anosExperiencia.

### Fluxo Principal

1. O ator envia um `POST /instrutor` com o JSON do cadastro.
2. O `InstrutorController` converte o JSON em `InstrutorRequestDTO` (os enums `Sexo` e `CategoriaCNH` são validados nesta etapa) e repassa ao `InstrutorService.salvarInstrutor`.
3. O `InstrutorMapper` converte o DTO em uma entidade `Instrutor`.
4. O sistema define os valores iniciais do instrutor: `statusVerificacao = PENDENTE` e `notaMedia = 0`. O ator não informa esses campos.
5. O sistema persiste o instrutor (`InstrutorRepository.save`). Antes de gravar, `dataCadastro` é preenchida com a data/hora atual.
6. O sistema grava uma linha em `usuarios` e uma linha em `instrutores` (mesmo `id`/`usuario_id`).
7. O sistema responde **HTTP 200 (OK)**, sem corpo.

**Pós-condição:** instrutor cadastrado com status `PENDENTE`, aguardando verificação.

### Fluxos Alternativos

| Id | Condição | Comportamento atual |
|---|---|---|
| B1 | JSON malformado ou valor de enum inválido (ex.: `categoriaCnh = "INVALIDA"`) | A requisição é rejeitada com **HTTP 400** e o service não é chamado (coberto por teste). |
| B2 | Campo obrigatório ausente (inclui numCnh, categoriaCnh, carro, placaCarro, precoHora) | Não há validação na entrada. O erro aparece ao gravar, por violação de `NOT NULL`, e segue o fluxo B5. |
| B3 | CPF, e-mail ou número da CNH já cadastrados | O banco rejeita por violação de unicidade, e segue o fluxo B5. |
| B4 | Falha no mapeamento DTO → entidade | Nada é salvo e é lançada `IllegalArgumentException("Erro ao tentar criar Usuario Instrutor")` (coberto por teste). |
| B5 | Falha ao persistir (qualquer causa) | A exceção original é descartada e substituída por `IllegalArgumentException("Erro ao tentar criar Usuario Instrutor")` (coberto por teste). |

> Diferente do aluno, o service do instrutor captura qualquer `Exception` e lança uma mensagem genérica. O ator não consegue distinguir se o problema foi CPF duplicado, CNH duplicada ou campo ausente.

---

## 3. Resumo das diferenças entre os dois cadastros

| Aspecto | Aluno | Instrutor |
|---|---|---|
| Dado gerado pelo sistema | `matricula` | `statusVerificacao = PENDENTE`, `notaMedia = 0` |
| Identificador único próprio | matrícula (gerada) | número da CNH (informado) |
| Resposta de sucesso | 204 | 200 |
| Tratamento de erro no service | propaga a exceção original | mascara com mensagem genérica |
| Transação explícita no service | sim (`@Transactional`) | não (usa a transação do `save`) |

## 4. Observações sobre o estado atual

- Senha gravada em texto puro (sem hash).
- Sem Bean Validation: formato de CPF, e-mail, placa, UF, preço e idade não são checados.
- Os métodos `existsByCpf`, `existsByEmail`, `existsByMatricula` e `existsByNumCnh` existem nos repositories, mas ainda não são usados no cadastro.
- Instrutor `PENDENTE` ainda não tem fluxo de verificação (`VERIFICADO` / `REJEITADO`).