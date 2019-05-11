# Movie Rental 

Este é um projeto de locadora de filmes baseado apenas em serviços.

**Características**
<br/>
Flatfile fora desenvolvido utilizando a linguagem Java. Algumas funcionalidades do do framework Springboot também foi utilizada, porém, em suma, as próprias bibliotecas internas do Java foram capazes de suprir todas as necessidades. A nível de confiabilidade e qualidade, o projeto fora desenvolvido sob a metodologia de TDD. Dentre as característias propostas para seu desenvolvimento, cabe relatar o que fora utilizado para atingi-las.
<br/>
- Clean Code: Focando nos conceitos de SOLID para garantir um bom "clean code", princípios como responsabilidade única, inversão de dependência com uso de abstrações, interfaces específicas e o princípio aberto-fechado foram muito utilizados ao longo do desenvolvimento;
- Simplicidade: Utilizando os conceitos mencionados no item anterior, todo o código acabou ficando simples e bem legível. Vale ressaltar também a ausência de condicionais graças ao emprego de SOLID;
- Lógica: Toda a lógica realmente de valor encontra-se na camada Service onde todos os seguimentos são reconhecidos, separados conforme seus respectivos códigos de identificação e, a partir daí, contabilizados os cálculos necessários para geração de um relatório com o resumo das vendas.
- Separação de Conceitos: Este quesito foi levado muito em conta, visto que, pelo frequente uso de interfaces e abstrações, quaisquer alterações ou novos requisitos implicantes a determinada característica do projeto (Ex: as classes FileStream ou Statistic), bastaria apenas a criação de uma nova classe com novas regras implementando/extendendo suas respectivas interfaces/abstrações.
- Escalabilidade e Perfomance: Novamente, ressalto aqui que o uso de SOLID fora de suma importância para este quesito, e, como mencionado, a ausência de condicionais é um bom exemplo a ser implicado na perfomance do projeto, pois a perfomance se restringe apenas ao número de dados/arquivos a serem lidos e processados, já que o código ficou bem simples e sem ausência de laços com mais de uma dimensão.
<br/>

**Notas importantes**
- Qualidade: 94,7% de cobertura de testes;
- Dada a natureza principal (vendas) do projeto, algumas tipagens de dados foram escolhidas especificamente em algumas modelo:
  - O tipo Long para id em SalesDataRow (já que uma nova venda é algo que frequentemente ocorrerá, ids exorbitantes não seriam raros);
  - O tipo BigDecimal para valores monetários como salary em SalesmanDataRow e price em Item (já que estamos lidando com dinheiro e a precisão é extremamente importante).
<br/>

**Possíveis Melhorias**
- Durante o desenvolvimento foi constatado uma situação em que a leitura dos arquivos .DAT iria resultar em um problema, gerando assim uma inconformidade no decorrer do processamento. Devido ao fato de os dados serem separados pelo caracter "ç" e, este foi o caracter especificado para ser desenvolvido no projeto, os casos em que houvessem nomes como "Mendonça" ou "Gonçalves" podem quebrar os caracteres de forma equivocada e comprometendo, assim, o relatório de vendas. Uma possível melhoria poderia ser a troca do caracter "ç" por ";" nos arquivos a serem lidos e na própria aplicação, garantindo assim uma leitura sem inconsistências;
- Acrescentar BDD para fortalecer melhor as regras e os testes, garantindo assim mais qualidade ao projeto.
