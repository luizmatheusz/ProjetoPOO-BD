-- -----------------------------------------------------
-- Projeto Laboratório C207-L8
-- Luiz Matheus R. Cândido, 1817
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS NBA;
CREATE SCHEMA NBA;
USE NBA;

-- -----------------------------------------------------
-- Informações das franquias
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Franquias (
  nome VARCHAR(45) NOT NULL,
  conferencia VARCHAR(45) NOT NULL,
  mascote VARCHAR(45),
  nJogos INT,
  vitorias INT,
  derrotas INT,
  totalPontos INT,
  mediaPontos DECIMAL(5,1),
  saldoPontos INT,
  PRIMARY KEY (nome));

-- -----------------------------------------------------
-- Informações dos jogadores
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Jogadores (
  id INT NOT NULL AUTO_INCREMENT,
  identificacao VARCHAR(45) NOT NULL,
  nome VARCHAR(100) NOT NULL,
  posicao VARCHAR(45) NOT NULL,
  sigla_posicao VARCHAR(20) NOT NULL,
  data_nasc DATE NOT NULL,
  idade INT,
  pais VARCHAR(45) NOT NULL,
  nomeFranquia VARCHAR(45),
  nJogos INT,
  totalMinutos INT,
  mediaMinutos DECIMAL(5,1),
  totalPontos INT,
  mediaPontos DECIMAL(5,1),
  totalRebotes INT,
  mediaRebotes DECIMAL(5,1),
  totalAssistencias INT,
  mediaAssistencias DECIMAL(5,1),
  PRIMARY KEY (id),
  CONSTRAINT fk_Jogadores_Franquias1
    FOREIGN KEY (nomeFranquia)
    REFERENCES Franquias (nome)
    ON DELETE SET NULL
    ON UPDATE CASCADE);

-- -----------------------------------------------------
-- Informações das arenas de cada time (franquia)
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Arenas (
  nomeFranquia VARCHAR(45) NOT NULL,
  nome VARCHAR(45) NOT NULL,
  capacidade INT NOT NULL,
  PRIMARY KEY (nomeFranquia),
  CONSTRAINT fk_Arenas_Franquias
    FOREIGN KEY (nomeFranquia)
    REFERENCES Franquias (nome)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- -----------------------------------------------------
-- Informações dos jogos
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Jogos (
  id INT NOT NULL AUTO_INCREMENT,
  idArbitro INT,
  fase VARCHAR(20) NOT NULL,
  nomeFranquia1 VARCHAR(45) NOT NULL,
  nomeFranquia2 VARCHAR(45) NOT NULL,
  pontuacao_franquia1 INT,
  pontuacao_franquia2 INT,
  data DATE,
  PRIMARY KEY (id));

-- -----------------------------------------------------
-- Informações sobre as premiações
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Premiacoes (
  id INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(45) NOT NULL,
  idJogador INT,
  PRIMARY KEY (id),
  CONSTRAINT fk_Premiacoes_Jogadores1
    FOREIGN KEY (idJogador)
    REFERENCES Jogadores (id)
    ON DELETE NO ACTION
    ON UPDATE CASCADE);

-- -----------------------------------------------------
-- Estatísticas dos jogadores em cada jogo
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Jogadores_atuam_Jogos (
  idJogador INT NOT NULL,
  idJogo INT NOT NULL,
  minutos INT,
  pontos INT,
  rebotes INT,
  assistencias INT,
  faltas INT,
  fg_tentados INT,
  fg_acertos INT,
  fg_sucesso DECIMAL(5,2),
  ft_tentados INT,
  ft_acertos INT,
  ft_sucesso DECIMAL(5,2),
  three_tentados INT,
  three_acertos INT,
  three_sucesso DECIMAL(5,2),
  PRIMARY KEY (idJogador, idJogo),
  CONSTRAINT fk_Jogadores_has_Jogos_Jogadores1
    FOREIGN KEY (idJogador)
    REFERENCES Jogadores (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Jogadores_has_Jogos_Jogos1
    FOREIGN KEY (idJogo)
    REFERENCES Jogos (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- -----------------------------------------------------
-- Estatísticas dos árbitros
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Arbitros(
  id INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(45),
  identificacao VARCHAR(45),
  data_nasc DATE,
  idade INT,
  nJogos INT,
  PRIMARY KEY(id)
);

-- -----------------------------------------------------
-- Trigger Franquias
-- -----------------------------------------------------
DELIMITER $$
CREATE TRIGGER configFranquias BEFORE INSERT
ON Franquias
FOR EACH ROW
BEGIN
	SET NEW.nJogos = 0, NEW.vitorias = 0, NEW.derrotas = 0, NEW.totalPontos = 0, NEW.mediaPontos = 0, NEW.saldoPontos = 0;
END $$
DELIMITER ;

-- -----------------------------------------------------
-- Trigger Jogadores
-- -----------------------------------------------------
DELIMITER $$
CREATE TRIGGER configJogadores BEFORE INSERT
ON Jogadores
FOR EACH ROW
BEGIN
	SET NEW.totalMinutos = 0, NEW.mediaMinutos = 0, NEW.nJogos = 0, NEW.totalPontos = 0, NEW.mediaPontos = 0,
		NEW.totalRebotes = 0, NEW.mediaRebotes = 0, NEW.totalAssistencias = 0, NEW.mediaAssistencias = 0,
        NEW.idade = TIMESTAMPDIFF(YEAR, NEW.data_nasc, CURDATE());
END $$
DELIMITER ;

-- -----------------------------------------------------
-- Trigger Árbitros
-- -----------------------------------------------------
DELIMITER $$
CREATE TRIGGER configArbitros BEFORE INSERT
ON Arbitros
FOR EACH ROW
BEGIN
	SET NEW.nJogos = 0, NEW.idade = TIMESTAMPDIFF(YEAR, NEW.data_nasc, CURDATE());
END $$
DELIMITER ;

-- -----------------------------------------------------
-- Trigger Jogos
-- -----------------------------------------------------
DELIMITER $$
CREATE TRIGGER configJogosFranquias BEFORE INSERT
ON Jogos
FOR EACH ROW
BEGIN
    UPDATE Franquias SET saldoPontos = saldoPontos + NEW.pontuacao_franquia1 - NEW.pontuacao_franquia2, totalPontos = totalPontos + new.pontuacao_franquia1
    WHERE NEW.nomeFranquia1 = nome;
    UPDATE Franquias SET saldoPontos = saldoPontos + NEW.pontuacao_franquia2 - NEW.pontuacao_franquia1, totalPontos = totalPontos + new.pontuacao_franquia2
    WHERE NEW.nomeFranquia2 = nome;
    
    IF NEW.pontuacao_franquia1 > NEW.pontuacao_franquia2
    THEN
    UPDATE Franquias SET vitorias = vitorias + 1
    WHERE NEW.nomeFranquia1 = nome;
    UPDATE Franquias SET derrotas = derrotas + 1
    WHERE NEW.nomeFranquia2 = nome;
    
    ELSEIF NEW.pontuacao_franquia2 > NEW.pontuacao_franquia1
    THEN
    UPDATE Franquias SET vitorias = vitorias + 1
    WHERE NEW.nomeFranquia2 = nome;
    UPDATE Franquias SET derrotas = derrotas + 1
    WHERE NEW.nomeFranquia1 = nome;
	END IF;

	UPDATE Franquias SET nJogos = nJogos + 1, mediaPontos = totalPontos/nJogos
    WHERE NEW.nomeFranquia1 = nome OR NEW.nomeFranquia2 = nome;
    
    UPDATE Arbitros SET nJogos = nJogos + 1 WHERE NEW.idArbitro = id;
END $$
DELIMITER ;

-- -----------------------------------------------------
-- Trigger Jogadores em um Jogo
-- -----------------------------------------------------
DELIMITER $$
CREATE TRIGGER configJogadoresJogos BEFORE INSERT
ON Jogadores_atuam_Jogos
FOR EACH ROW
BEGIN
    SET NEW.pontos = (NEW.fg_acertos - NEW.three_acertos) * 2 + NEW.three_acertos * 3 + NEW.ft_acertos;
    SET NEW.fg_sucesso = NEW.fg_acertos/NEW.fg_tentados;
    SET NEW.three_sucesso = NEW.three_acertos/NEW.three_tentados;
    SET NEW.ft_sucesso = NEW.ft_acertos / NEW.ft_tentados;
    
    UPDATE Jogadores
    SET nJogos = nJogos + 1,
    totalPontos = totalPontos + NEW.pontos,
	totalRebotes = totalRebotes + NEW.rebotes,
    totalAssistencias = totalAssistencias + NEW.assistencias,
    mediaPontos = totalPontos / nJogos,
    mediaRebotes = totalRebotes / nJogos,
    mediaAssistencias = totalAssistencias / nJogos,
    totalMinutos = totalMinutos + NEW.minutos,
    mediaMinutos = totalMinutos / nJogos
    WHERE id = NEW.idJogador;
END $$
DELIMITER ;
    
-- -----------------------------------------------------
-- Inserts
-- -----------------------------------------------------
INSERT INTO Franquias (nome, conferencia, mascote) VALUES
("Philadelphia 76ers", "Leste", null),
("Boston Celtics", "Leste", null),
("New York Knicks", "Leste", null),
("Brooklyn Nets", "Leste", null),
("Toronto Raptors", "Leste", null),
("Denver Nuggets", "Oeste", null),
("Minnesota Timberwolves", "Oeste", null),
("Oklahoma City Thunder", "Oeste", null),
("Portland Trail Blazers", "Oeste", null),
("Utah Jazz", "Oeste", null),
("Golden State Warriors", "Oeste", null);

INSERT INTO Jogadores (nome, identificacao, posicao, sigla_posicao, data_nasc, pais, nomeFranquia) VALUES
("Nikola Jokic", "1234", "Pivô", "C", "1995-02-19", "Sérvia", "Denver Nuggets"),
("Jamal Murray", "54321", "Armador", "PG", "1997-02-23", "Canadá", "Denver Nuggets"),
("Stephen Curry", "5689", "Armador", "PG", "1988-03-14", "Estados Unidos", "Golden State Warriors"),
("Damian Lillard", "1526", "Armador", "PG", "1990-07-15", "Estados Unidos", "Portland Trail Blazers");

INSERT INTO Arenas VALUES
("Philadelphia 76ers", "Wells Fargo Center", 21000),
("Boston Celtics", "TD Garden", 19580),
("New York Knicks", "Madison Square Garden", 19500),
("Brooklyn Nets", "Barclays Center", 19000),
("Toronto Raptors", "Scotiabank Arena", 19800),
("Denver Nuggets", "Ball Arena", 19155),
("Minnesota Timberwolves", "Target Center", 18798),
("Oklahoma City Thunder", "Paycom Center", 18203),
("Portland Trail Blazers", "Moda Center", 20630),
("Utah Jazz", "Vivint Arena", 20000),
("Golden State Warriors", "Chase Center", 18064);
;

INSERT INTO Arbitros (nome, identificacao, data_nasc) VALUES
("Wilson", "15444", "1988-01-05"),
("Robert", "18944", "1972-05-08")
;

INSERT INTO Jogos VALUES
(default, 1, "Regular", "Philadelphia 76ers", "Boston Celtics", 120, 100, "2023-06-22"),
(default, 1, "Regular", "New York Knicks", "Brooklyn Nets", 117, 103, "2023-06-23"),
(default, 1, "Regular", "Denver Nuggets", "Golden State Warriors", 135, 134, "2023-06-21"),
(default, 2, "Regular", "Utah Jazz", "Portland Trail Blazers", 88, 91, "2023-06-22");

INSERT INTO Jogadores_atuam_Jogos (idJogador, idJogo, minutos, fg_tentados, fg_acertos, three_tentados, three_acertos, ft_tentados, ft_acertos, rebotes, assistencias, faltas) VALUES
(1,3,38, 10,9,3,2,4,3,12,14,2),
(2,3,36,12,8,8,3,5,3,5,10,1),
(3,3,39,14,11,10,6,6,5,4,9,1)
;

-- -----------------------------------------------------
-- View, maiores pontuadores da liga
-- -----------------------------------------------------
CREATE VIEW maioresPontuadores AS(
	SELECT j.nome "Nome", j.totalPontos "Total de pontos"
    FROM Jogadores AS j
    ORDER BY j.totalPontos DESC
);

-- -----------------------------------------------------
-- Alguns selects para teste
-- -----------------------------------------------------
#SELECT * FROM maioresPontuadores;
#SELECT * FROM Franquias;
#SELECT * FROM Jogos;
#SELECT * FROM Arenas;
#SELECT * FROM Arbitros;
#SELECT j.nome AS "Jogador", jj.minutos AS "Minutos", jj.pontos AS "Pontos", jj.rebotes AS "Rebotes", jj.assistencias AS "Assistências" FROM Jogadores_atuam_Jogos AS jj, Jogadores AS j WHERE jj.idJogador = j.id ORDER BY jj.pontos DESC;
#SELECT id, nome, sigla_posicao, idade, pais, nomeFranquia FROM Jogadores;