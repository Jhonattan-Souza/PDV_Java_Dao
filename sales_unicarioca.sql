create database if not exists sales_unicarioca;

create table if not exists Cliente
(
	Codigo int primary key auto_increment,
    Nome varchar(35),
    Bonus int,
    Perfil char(1),
    Status char(1)
);

create table if not exists Localidade
(
	Codigo int primary key auto_increment,
    Nome varchar(35),
    Endereco varchar(80),
    Telefone varchar(14)
);

create table if not exists Produto
(
	Codigo int primary key auto_increment,
    Codigo_Local int,
    Descricao varchar(35),
    Quantidade_Em_Estoque int,
    Preco_Unitario float,
    foreign key (Codigo_Local) references Localidade(Codigo) 
);

create table if not exists Venda
(
	Codigo_Cliente int,
    Codigo_Produto int,
    Codigo_Local int,
    Quantidade_Venda int,
    Valor_Total float,
    Data_Venda datetime,
    primary key (Codigo_Cliente, Codigo_Produto, Codigo_Local),
    foreign key (Codigo_Cliente) references Cliente(Codigo),
    foreign key (Codigo_Produto) references Produto(Codigo),
    foreign key (Codigo_Local) references Localidade(Codigo)
);

create table if not exists Desconto
(
	Codigo int primary key auto_increment,
    Codigo_Produto int,
    Percentual int,
    Quantidade_Minima int,
    Quantidade_Maxima int,
	foreign key (Codigo_Produto) references Produto(Codigo)
);
