# Gerenciador de Tarefas via Console

Aplicação de console em Java para gerenciamento de tarefas, com CRUD completo e persistência local em arquivo.

## Funcionalidades

- **Criar** tarefas com título, descrição, prazo e status
- **Listar** todas as tarefas cadastradas
- **Editar** tarefas existentes, campo a campo, mantendo valores não alterados
- **Deletar** tarefas por índice ou por título (com tratamento de ambiguidade quando há títulos duplicados)
- **Persistência** automática em arquivo local (`tarefas.dat`), via serialização Java — os dados são restaurados automaticamente ao reiniciar o programa

## Decisões técnicas

- **Separação de responsabilidades:** a classe `Tarefa` representa apenas o dado (modelo), enquanto `GerenciadorTarefas` concentra toda a lógica de manipulação da lista — aplicando o princípio de responsabilidade única.
- **Tratamento de ambiguidade na deleção por título:** antes de remover uma tarefa pelo título, o sistema conta quantas correspondências existem. Se houver mais de uma, a deleção é recusada e o usuário é orientado a usar o índice, evitando remoção acidental da tarefa errada.
- **Encapsulamento:** o método que expõe a lista de tarefas (`getTarefas()`) retorna uma cópia, não a referência direta, prevenindo que código externo modifique a lista interna sem passar pelos métodos de controle da classe.
- **Persistência via serialização nativa do Java** (`Serializable`, `ObjectOutputStream`/`ObjectInputStream`), sem dependências externas — escolha adequada ao escopo do projeto (aplicação local, single-user).

## Tecnologias

- Java 25
- Sem build system (Maven/Gradle) — projeto sem dependências externas

## Como executarD

1. Clone o repositório
2. Abra no IntelliJ IDEA (ou outra IDE Java)
3. Execute a classe `Main`

## Autor

João Vitor Carvalho de Sousa