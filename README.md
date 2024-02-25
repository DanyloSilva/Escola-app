[ x ]subir toda ambiente com docker composer

[ ]documentaçao da API no swagger


[ X] Código de migração SQL que crie a base de dados


[X ] CRIAR ALUNO todos os campos são obrigatórios


[ X] CRIAR TURMA  todos os campos são obrigatórios


[ X] LISTAR ALUNO (nome completo, matrícula e data de nascimento)


[ X] LISTAR TURMA


[ X] lISTE alunos de uma turma


[ X]  listagem alunos da turma, deve haver a opção de remover o aluno da turma

[ X] EXCLUIR TURMA 

[X ] EXCLUIR ALUNO



TO-DO

Solucionar o erro de rede da requisição front e back no container. possivel erro de endereço (localmente funciona)

- Adicionar Swagger










Código de migração SQL que crie a base de dados. Escolha qualquer solução de migration.

aluno e turma 


Criar tadabase escola 
detro da databas escola vou ter aluno e turma 



request para cadastrar 

aluno 

para deletar e so para o id na url 

{
  "nome": "kleber",
  "matricula": "Matricula123",
  "data_nascimento": "1998-01-01",
  "ativo": true
}



para turmas  exemplo 


{

  "nome": "Programacao",
  "horario_inicio": "08:00:00",
  "ativo": true
}

---------- documentação de estudo------
configuração do a imagem mysql docker caso ja tenha feito isso so crie a base " escola" e pule essa parte.

passo 1- baixe o docker (abra o terminal como admin) e execute um docker -v" para ter certeza que esta instalado.

passo 2 - baixe uma imagem do mysql no docker execute o comando "docker pull mysql:5.7".

passo 3 - "docker run -e MYSQL_ROOT_PASSWORD=root --name meu-mysql -d mysql:5.7"

passo 4 - verifique a posta que esta rodando seu sql geralmente e a 172.17.0.2 confira dando o seguinta comando  "docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' meu-mysql
container ls" dps de um "docker container ls" para ter certeza que seu container esta rodando.

passo 5- execute o comando "docker exec  -it meu-mysql bash"

 passo 6 - "mysql -u root -p" se deu certo execute um show databases; " deve mostrar as bases padroes.

passo 7 - agora vamos criar nossa base execute "create database escola".
  o retorno deve ser esse :

mysql> show databases;
+--------------------+
| Database           |
+--------------------+
| information_schema |
| escola             |
| mysql              |
| performance_schema |
| sys                |
+--------------------+
5 rows in set (0.00 sec)
