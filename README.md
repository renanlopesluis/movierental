# Movie Rental 

Este é um projeto de locadora de filmes baseado apenas em serviços para serem consumidos.

**Características**
<br/>
MovieRental fora desenvolvido utilizando a linguagem Java. Foram utilizados os frameworks Springboot (responsável por toda a aplicação desde manipulação de dados e requisições a execução dos testes) e o SpringSecurity para realização do login/logout do usuário e controle das requisições quanto à autenticação, além das próprias bibliotecas internas do Java 8. A nível de confiabilidade e qualidade, o projeto fora desenvolvido sob a metodologia de TDD. 
Vale ressaltar também a utilização da bibliotecas externas para melhor utilização do código visando um código mais claro e enxuto com alguns controles de entrada/saída que poderiam ser substituídos por múltiplos if/elses. Tais bibliotecas foram Lombok, Google gson, Apache Commons, Jackson e Swagger.
Dentre as característias propostas para seu desenvolvimento, cabe relatar o que fora utilizado para atingi-las.
<br/>
- Clean Code: Focando nos conceitos de SOLID para garantir um bom "clean code", princípios como responsabilidade única, inversão de dependência com uso de abstrações, interfaces específicas e o princípio aberto-fechado foram muito utilizados ao longo do desenvolvimento;
- Simplicidade: Utilizando os conceitos mencionados no item anterior, todo o código acabou ficando simples e bem legível. Vale ressaltar também a quase ausência de condicionais graças ao emprego de SOLID;
- Lógica: Toda a lógica realmente de valor encontra-se nas camadas Service e Factory, onde na segunda todos as devidas verificações são realizadas antes de criar de fato um objeto consciso dada uma entrada oriunda da requisição, assim que construído o objeto então é transacionado na primeira camada mencionada através de uma chamada ao repositório.
- Separação de Conceitos: Este quesito foi levado muito em conta, visto que, pelo frequente uso de interfaces e abstrações, quaisquer alterações ou novos requisitos implicantes a determinada característica do projeto (Ex: as classes Factory), bastaria apenas a criação de uma nova classe com novas regras implementando/extendendo suas respectivas interfaces/abstrações.
- Escalabilidade e Perfomance: Novamente, ressalto aqui que o uso de SOLID fora de suma importância para este quesito, e, como mencionado, a ausência de condicionais é um bom exemplo a ser implicado na perfomance do projeto, pois a perfomance se restringe apenas ao número de dados/arquivos a serem lidos e processados, já que o código ficou bem simples e sem ausência de laços com mais de uma dimensão.
<br/>

**Notas importantes**
- Qualidade: 91,7% de cobertura de testes;
- Para uma melhor didática quanto à API, dado que um desenvolvedor leigo com relação ao seu uso e chamadas, foi criado um Swagger onde todas as informações sobre cada método encontram-se disponíveis, tais como: qual a finalidade do método, o retorno, os parâmetros a serem inserido,  o tipo de método e é necessário ou não autenticar-se antes de executá-lo.
- Ao baixar este projeto para acessar seu código fonte, a biblioteca do lombok precisa ser instalada executando o arquivo java lombok contido na pasta de dependências do pom.xml que se encontra em: {pasta users da máquina}/.m2/repository/org/projectlombok/lombok/1.18.6/lombok-1.18.6.jar
<br/>

**Possíveis Melhorias**
- Restringir o body de exemplo dos métodos no Swagger à apenas propriedades pertinentes às chamadas, hoje todas as proriedades obrigatórias são expressas de maneira geral e não de acordo com o método, embora exista na "Description" uma nota dizendo quais propriedades devem ser preenchidas.
- Acrescentar BDD para fortalecer melhor as regras e os testes, garantindo assim mais qualidade ao projeto.
