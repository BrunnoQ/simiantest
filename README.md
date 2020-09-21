# Simian Test
Projeto criado para realização de puzzle.

## Utilizar as seguintes versões:
1. JRE 10.0.2 ou superior
2. Maven 3.5.4 ou superior

## Executar a aplicação
Para executar a aplicação, utilizar o JAR *dnaapi-0.0.1-SNAPSHOT.jar* , contido na pasta
**EXECUTAR** deste repositório utiizando o seguinte comando:
**java -Xms500m -Xmx1g -jar dnaapi-0.0.1-SNAPSHOT.jar**

**NOTA:** Executar o jar *com permissão de escrita e leitura* ao diretório em que for executado, 
por conta do *LOG*.

## Endpoints Disponibilizados Após Execução do jar:
1. http://localhost:8080/simian (Nivel 1)
2. http://localhost:8080/simian/stats (Nivel 2)
3. http://localhost:8080/simian?id=1 (para testes)

## Demais Considerações:
Banco de dados utilizado foi o *H2* (banco em memória) que pode ser acessado através da console; *http://localhost:8080/h2-console/* .
Através dela, consultas poderam serem realizadas.
No demais as consultas nas APIs seguem o padrao de JSON, passado no enunciado do exercício.

