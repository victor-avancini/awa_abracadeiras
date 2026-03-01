# ADR-0001 - Arquitetura Monólito Modular
Status: Aceito
Data: 2026-03-01

## Contexto
Sistema para uma única empresa, um usuário no MVP, foco em baixo custo e rapidez, com domínio coeso (estoque/pedidos/produção).

## Decisão
Adotar monólito modular (Spring Boot) com módulos por domínio (auth, catálogo, estoque, pedidos, produção, relatórios).

## Alternativas consideradas
1. Microserviços
2. Monólito "camadas" sem modularização clara

## Trade-offs
- Monólito modular:
  - (+) Menor complexidade operacional e de deploy
  - (+) Consistência transacional simples
  - (+) Time-to-market
  - (-) Exige disciplina para manter limites entre módulos
- Microserviços:
  - (+) Escala e deploy independente
  - (-) Overhead alto (observabilidade, contratos, ownership) inadequado para MVP

## Consequências
- Limites de módulo devem ser reforçados por estrutura de pacotes e dependências.
- Se crescer (multi-tenant, múltiplos usuários, integrações), modularização facilita extração futura.