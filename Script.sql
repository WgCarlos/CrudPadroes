create database pessoas;

use pessoas;

create table funcionarios (
	idfuncionarios int primary key auto_increment,
	nome varchar(45) not null,
    cpf varchar(12) not null,
    endereco varchar(45),
    telefone varchar(20),
    email varchar (45)
);
