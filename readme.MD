# Renderizador 3D para 2D

Este projeto é um renderizador que converte objetos tridimensionais (3D) representados em arquivos `.json` para uma projeção bidimensional (2D). O renderizador utiliza Java e a biblioteca `javax.swing` para desenhar as arestas do objeto 3D em uma tela 2D.

## Funcionalidades

- **Leitura de arquivos JSON**: O renderizador aceita arquivos `.json` que contêm a definição de vértices e arestas de um objeto 3D.
- **Projeção 3D para 2D**: Utiliza uma projeção simples para converter coordenadas 3D em 2D.
- **Renderização em tempo real**: Desenha as arestas do objeto 3D em uma janela gráfica.

## Estrutura do Projeto

O projeto é composto por duas classes principais:

1. **`RenderPanel`**: Responsável por renderizar o objeto 3D em uma tela 2D.
    - **Método `project`**: Converte coordenadas 3D em 2D.
    - **Método `paintComponent`**: Desenha as arestas do objeto na tela.

2. **`Geometry`**: Representa a geometria do objeto 3D.
    - **Atributos**:
        - `vertex`: Uma matriz de vértices (coordenadas 3D).
        - `edges`: Uma matriz de arestas (conexões entre vértices).

## Exemplo de Arquivo JSON

O arquivo `.json` deve seguir o seguinte formato:

```json
{
  "Vertex": [
    [-100, 50, 100], [100, 50, 100], [150, 100, 50], [100, 150, 100],
    [-100, 150, 100], [-150, 100, 50], [-100, 200, 100], [100, 200, 100],
    [150, 225, 50], [100, 300, 100], [-100, 300, 100], [-150, 225, 50]
  ],
  "Edges": [
    [0, 1], [1, 2], [2, 3], [3, 4], [4, 5],
    [5, 0], [6, 7], [7, 8], [8, 9], [9, 10],
    [10, 11], [11, 6], [0, 6], [1, 7], [2, 8],
    [3, 9], [4, 10], [5, 11]
  ]
}