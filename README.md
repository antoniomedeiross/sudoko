# Sudoku Solver

## Descrição

Este é um jogo de Sudoku simples com um solucionador embutido. A interface gráfica foi construída com Java Swing.

## Funcionalidades

- **Grade de Sudoku 9x9:** Uma grade de Sudoku padrão.
- **Solucionador:** Um botão "Solve" que resolve o quebra-cabeça para você.
- **Limpar:** Um botão "Clear" que limpa a grade.
- **Finalizar:** Um botão "Finish" que verifica se a sua solução está correta.
- **Puzzle Padrão:** O jogo começa com um quebra-cabeça padrão.
- **Argumentos de linha de comando:** Você pode iniciar o jogo com um quebra-cabeça personalizado passando um argumento de linha de comando.

## Como executar

Para executar a aplicação, você precisa ter o Java instalado. Compile todos os arquivos `.java` e execute a classe `Main`:

```bash
javac --release 11 -d out/production/sudoko src/br/com/antonio/sudoku/main/Main.java src/br/com/antonio/sudoku/model/SudokuGrid.java src/br/com/antonio/sudoku/solver/SudokuSolver.java src/br/com/antonio/sudoku/ui/SudokuGUI.java src/br/com/antonio/sudoku/model/CellData.java

java -cp out/production/sudoko br.com.antonio.sudoku.main.Main
```

## Como jogar

1.  Preencha os campos vazios da grade com números de 1 a 9.
2.  Quando você terminar, clique no botão "Finish".
3.  Se a sua solução estiver correta, você receberá uma mensagem de parabéns.
4.  Se você ficar preso, pode clicar no botão "Solve" para ver a solução.

## Argumentos de linha de comando

Você pode iniciar a aplicação com um quebra-cabeça personalizado passando uma string de argumento para a classe `Main`. O formato da string é:

`"linha,coluna;valor,editável linha,coluna;valor,editável ..."`

**Exemplo:**

```bash
java -cp out/production/sudoko br.com.antonio.sudoku.main.Main "0,0;4,false 1,0;7,false ..."
```
