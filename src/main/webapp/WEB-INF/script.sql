CREATE DATABASE db_agis_001
GO
USE db_agis_001

CREATE TABLE aluno(
	ra VARCHAR(09),
	cpf VARCHAR(11),
	nome VARCHAR(200),
	dataNascimento DATE,
	emailPessoal VARCHAR(200),
	emailCorporativo VARCHAR(200),
	PRIMARY KEY(ra)
)

GO
CREATE TABLE aluno_detalhes(
	aluno_ra VARCHAR(09),
	data_conclusao DATE,
	instituicao_seg_grau VARCHAR(200),
	pontuacao DECIMAL(4,2),
	posicao INT,
	PRIMARY KEY(aluno_ra),
	FOREIGN KEY(aluno_ra) REFERENCES aluno(ra)
)

CREATE TABLE telefone(
	id INT IDENTITY(8001, 1),
	aluno_ra VARCHAR(09),
	telefone VARCHAR(11),
	PRIMARY KEY(id),
	FOREIGN KEY(aluno_ra) REFERENCES aluno(ra)
)

CREATE TABLE curso(
	codigo INT IDENTITY(1,1),
	nome VARCHAR(200),
	carga_horaria INT,
	sigla VARCHAR(100),
	nota_enade DECIMAL(4,2),
	PRIMARY KEY(codigo)
)



CREATE TABLE matricula(
	curso_codigo INT,
	aluno_ra VARCHAR(09),
	ano_ingresso INT,
	semestre_ingresso INT,
	ano_limite INT
)

CREATE TABLE disciplina(
	id INT IDENTITY(1001, 1),
	curso_codigo INT,
	nome VARCHAR(200),
	dia_semana VARCHAR(20),
	hora_comeco INT,
	PRIMARY KEY(id),
	FOREIGN KEY(curso_codigo) REFERENCES curso(codigo)
)

CREATE TABLE matricula_disciplina(
	disciplina_id INT,
	aluno_ra VARCHAR(09),
	PRIMARY KEY(disciplina_id, aluno_ra),
	FOREIGN KEY(disciplina_id) REFERENCES disciplina(id),
	FOREIGN KEY(aluno_ra) REFERENCES aluno(ra)
)

CREATE TABLE conteudo(
	id INT IDENTITY(10001,1),
	disciplina_id INT,
	nome VARCHAR(200),
	PRIMARY KEY(id),
	FOREIGN KEY(disciplina_id) REFERENCES disciplina(id)
)


CREATE PROCEDURE valida_cpf(@cpf VARCHAR(11), @resultado BIT OUT)
AS
BEGIN
	DECLARE @ite1 INT
	DECLARE @ite2 INT
	DECLARE @contador INT

	SET @contador = 0
	SET @resultado = 0
	SET @ite1 = 9
	SET @ite2 = 10
	WHILE(@ite1 > 0) BEGIN
		SET @contador = CAST(SUBSTRING(@cpf, @ite1, 1) AS INT) * @ite2 + @contador
		SET @ite1 = @ite1 - 1
		SET @ite2 = @ite2 - 1
	END

	IF( ((@contador * 10) % 11) = (CAST(SUBSTRING(@cpf, 10, 1) AS INT)) ) BEGIN
		SET @contador = 0
		SET @ite1 = 10
		SET @ite2 = 11

		WHILE(@ite1 > 0) BEGIN
			SET @contador = CAST(SUBSTRING(@cpf, @ite1, 1) AS INT) * @ite2 + @contador
			SET @ite1 = @ite1 - 1
			SET @ite2 = @ite2 - 1
		END
	END

	IF((@contador * 10) % 11) = (CAST(SUBSTRING(@cpf, 11, 1) AS INT) ) BEGIN
		SET @resultado = 1
	END

END


CREATE PROCEDURE valida_idade(@data_nascimento DATE, @resultado BIT OUT)
AS
BEGIN
	SET @resultado = 0

	IF(DATEDIFF(YEAR, @data_nascimento, GETDATE()) >= 18) BEGIN
		SET @resultado = 1
	END 
END




CREATE PROCEDURE insere_aluno(@cpf VARCHAR(11), @nome VARCHAR(200), 
@nome_social VARCHAR(200), @data_nascimento DATE, @telefone1 VARCHAR(9), @telefone2 VARCHAR(9), @email_pessoal VARCHAR(200),
@email_corporativo VARCHAR(200), @data_conclusao_segundo_grau DATE,
@instituicao_segundo_grau VARCHAR(200), @pontuacao DECIMAL(4,2), @posicao INT, @curso_id INT, @resultado BIT OUT)
AS
BEGIN
	DECLARE @cpf_valido BIT
	DECLARE @idade_valida BIT
	DECLARE @ano_ingresso INT
	DECLARE @semestre_ingresso INT
	DECLARE @semestre_limite INT
	DECLARE @ano_limite INT
	DECLARE @ra VARCHAR(20)

	SET @resultado = 0

	SET @ano_ingresso = YEAR(GETDATE())
	
	IF(MONTH(GETDATE()) > 6) BEGIN
		SET @semestre_ingresso = 2
	END
	ELSE SET @semestre_ingresso = 1
	
	EXEC valida_cpf @cpf, @cpf_valido OUT
	EXEC valida_idade @data_nascimento, @idade_valida OUT

	IF(@idade_valida = 1) BEGIN
		IF(@cpf_valido = 1) BEGIN

			SET @ra = CONCAT(CAST(@ano_ingresso AS VARCHAR(06)), CAST(@semestre_ingresso AS VARCHAR(04)))
			DECLARE @contador INT
			SET @contador = 0

			WHILE(@contador < 4) BEGIN
				SET @ra = CONCAT(@ra, CAST(FLOOR(RAND() * 10) AS VARCHAR(02)))
				SET @contador = @contador + 1
			END

			SET @resultado = 1
				INSERT INTO aluno(cpf, ra, nome, dataNascimento, emailPessoal, emailCorporativo) VALUES (@cpf, @ra,@nome, @data_nascimento, @email_pessoal, @email_corporativo)
				INSERT INTO telefone(aluno_ra, telefone) VALUES (@ra, @telefone1), (@ra, @telefone2)

				INSERT INTO aluno_detalhes(aluno_ra, data_conclusao, instituicao_seg_grau, pontuacao, posicao)
				VALUES (@ra, @data_conclusao_segundo_grau, @instituicao_segundo_grau, @pontuacao, @posicao)

				INSERT INTO matricula(aluno_ra, curso_codigo, ano_ingresso, semestre_ingresso, ano_limite) VALUES (@ra, @curso_id, @ano_ingresso, @semestre_ingresso, @ano_limite)
		END
	END
END



CREATE PROCEDURE atualiza_aluno(@ra VARCHAR(09), @telefone1 VARCHAR(11), @telefone2 VARCHAR(11), @emailPessoal VARCHAR(200), @emailCorporativo VARCHAR(200))
AS
BEGIN
	UPDATE aluno SET emailPessoal = @emailPessoal, emailCorporativo = @emailCorporativo WHERE ra = @ra
	UPDATE TOP (1) telefone SET telefone = @telefone1 WHERE aluno_ra = @ra
	UPDATE TOP (2) telefone SET telefone = @telefone2 WHERE aluno_ra = @ra
END
	
EXEC atualiza_aluno '202325494', '111111111', '2222222222', 'julia.font@email.com', 'julia.fontes14@fatec.sp'

SELECT * FROM aluno