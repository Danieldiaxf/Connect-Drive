UPDATE usuarios
SET nome            = 'Instrutor de Teste',
    data_nascimento = DATE '1990-05-20',
    sexo            = 'MASCULINO',
    email           = 'instrutor@example.com',
    senha           = 'SenhaTeste123!',
    telefone        = '61999990000',
    cidade          = 'Brasília',
    uf              = 'DF',
    foto_url        = NULL,
    data_cadastro   = TIMESTAMP '2026-01-01 00:00:00'
WHERE cpf = '52998224725';

INSERT INTO usuarios (nome, cpf, data_nascimento, sexo, email, senha, telefone, cidade, uf, foto_url, data_cadastro)
SELECT 'Instrutor de Teste', '52998224725', DATE '1990-05-20', 'MASCULINO', 'instrutor@example.com', 'SenhaTeste123!', '61999990000', 'Brasília', 'DF', NULL, TIMESTAMP '2026-01-01 00:00:00'
WHERE NOT EXISTS (SELECT 1 FROM usuarios WHERE cpf = '52998224725');

UPDATE instrutores
SET numero_cnh       = '12345678901',
    categoria_cnh    = 'B',
    carro            = 'Fiat Argo',
    placa_carro      = 'ABC1D23',
    preco_hora       = 100.00,
    bio              = 'Instrutor de direção',
    anos_experiencia = 5,
    status_verificacao = 'VERIFICADO',
    nota_media       = 4.8
WHERE usuario_id = (SELECT id FROM usuarios WHERE cpf = '52998224725');

INSERT INTO instrutores (usuario_id, numero_cnh, categoria_cnh, carro, placa_carro, preco_hora, bio, anos_experiencia, status_verificacao, nota_media)
SELECT u.id, '12345678901', 'B', 'Fiat Argo', 'ABC1D23', 100.00, 'Instrutor de direção', 5, 'VERIFICADO', 4.8
FROM usuarios u
WHERE u.cpf = '52998224725'
  AND NOT EXISTS (SELECT 1 FROM instrutores i WHERE i.usuario_id = u.id);

UPDATE usuarios
SET nome            = 'Ana Paula Souza',
    data_nascimento = DATE '1988-11-12',
    sexo            = 'FEMININO',
    email           = 'ana.souza@connectdrive.local',
    senha           = 'Senha123!',
    telefone        = '61988887777',
    cidade          = 'Goiânia',
    uf              = 'GO',
    foto_url        = NULL,
    data_cadastro   = TIMESTAMP '2026-01-02 00:00:00'
WHERE cpf = '12345678909';

INSERT INTO usuarios (nome, cpf, data_nascimento, sexo, email, senha, telefone, cidade, uf, foto_url, data_cadastro)
SELECT 'Ana Paula Souza', '12345678909', DATE '1988-11-12', 'FEMININO', 'ana.souza@connectdrive.local', 'Senha123!', '61988887777', 'Goiânia', 'GO', NULL, TIMESTAMP '2026-01-02 00:00:00'
WHERE NOT EXISTS (SELECT 1 FROM usuarios WHERE cpf = '12345678909');

UPDATE instrutores
SET numero_cnh       = '98765432100',
    categoria_cnh    = 'B',
    carro            = 'Toyota Yaris',
    placa_carro      = 'GOI2A34',
    preco_hora       = 120.00,
    bio              = 'Aulas para iniciantes e retomada de habilitação',
    anos_experiencia = 8,
    status_verificacao = 'VERIFICADO',
    nota_media       = 4.9
WHERE usuario_id = (SELECT id FROM usuarios WHERE cpf = '12345678909');

INSERT INTO instrutores (usuario_id, numero_cnh, categoria_cnh, carro, placa_carro, preco_hora, bio, anos_experiencia, status_verificacao, nota_media)
SELECT u.id, '98765432100', 'B', 'Toyota Yaris', 'GOI2A34', 120.00, 'Aulas para iniciantes e retomada de habilitação', 8, 'VERIFICADO', 4.9
FROM usuarios u
WHERE u.cpf = '12345678909'
  AND NOT EXISTS (SELECT 1 FROM instrutores i WHERE i.usuario_id = u.id);

UPDATE usuarios
SET nome            = 'Carlos Eduardo Lima',
    data_nascimento = DATE '1979-03-05',
    sexo            = 'MASCULINO',
    email           = 'carlos.lima@connectdrive.local',
    senha           = 'Senha123!',
    telefone        = '61977776666',
    cidade          = 'Brasília',
    uf              = 'DF',
    foto_url        = NULL,
    data_cadastro   = TIMESTAMP '2026-01-03 00:00:00'
WHERE cpf = '11144477735';

INSERT INTO usuarios (nome, cpf, data_nascimento, sexo, email, senha, telefone, cidade, uf, foto_url, data_cadastro)
SELECT 'Carlos Eduardo Lima', '11144477735', DATE '1979-03-05', 'MASCULINO', 'carlos.lima@connectdrive.local', 'Senha123!', '61977776666', 'Brasília', 'DF', NULL, TIMESTAMP '2026-01-03 00:00:00'
WHERE NOT EXISTS (SELECT 1 FROM usuarios WHERE cpf = '11144477735');

UPDATE instrutores
SET numero_cnh       = '45678912300',
    categoria_cnh    = 'D',
    carro            = 'Volkswagen Gol',
    placa_carro      = 'DFC3B45',
    preco_hora       = 150.00,
    bio              = 'Instrutor de cargas e ônibus',
    anos_experiencia = 12,
    status_verificacao = 'VERIFICADO',
    nota_media       = 4.7
WHERE usuario_id = (SELECT id FROM usuarios WHERE cpf = '11144477735');

INSERT INTO instrutores (usuario_id, numero_cnh, categoria_cnh, carro, placa_carro, preco_hora, bio, anos_experiencia, status_verificacao, nota_media)
SELECT u.id, '45678912300', 'D', 'Volkswagen Gol', 'DFC3B45', 150.00, 'Instrutor de cargas e ônibus', 12, 'VERIFICADO', 4.7
FROM usuarios u
WHERE u.cpf = '11144477735'
  AND NOT EXISTS (SELECT 1 FROM instrutores i WHERE i.usuario_id = u.id);
