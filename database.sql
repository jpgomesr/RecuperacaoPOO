create database db_joaopaulo;

use db_joaopaulo;

create table tb_alimento (
	codigo int auto_increment primary key,
    nome varchar(150) not null,
    nutricao int not null
);

create table tb_pessoa (
	codigo int auto_increment primary key,
    cpf long not null,
    nome varchar(150) not null,
    senha varchar(50) not null,
    codigo_pet int,
    foreign key (codigo_pet) references tb_pet(codigo)
);

create table tb_pet (
	codigo int auto_increment primary key,
    nome varchar(150) not null,
    vivo boolean not null,
    acordado boolean not null,
    sede int not null,
    fome int not null, 
    energia int not null,
	diversao int not null,
    higiene int not null,
    vontade_banheiro int not null
);

select * from tb_alimento;
select * from tb_pessoa
