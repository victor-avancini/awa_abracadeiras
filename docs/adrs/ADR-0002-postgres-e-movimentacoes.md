# ADR-0002 - PostgreSQL + Movimentações como Fonte de Auditoria
Status: Aceito
Data: 2026-03-01

## Contexto
Precisamos de consistência e rastreabilidade do estoque, com relatórios e histórico auditável.

## Decisão
Usar PostgreSQL como banco principal e modelar alterações de estoque via tabela de movimentações (StockMovement), atualizando saldo (InventoryBalance) transacionalmente.

## Alternativas consideradas
1. Apenas saldo, sem histórico
2. Event sourcing completo

## Trade-offs
- Saldo + movimentação:
  - (+) Auditoria e relatórios simples
  - (+) Fácil entendimento e manutenção
  - (-) Exige garantir que toda operação gere movimentação
- Apenas saldo:
  - (+) Simples
  - (-) Sem rastreabilidade
- Event sourcing:
  - (+) Auditoria perfeita
  - (-) Complexidade desnecessária para MVP

## Consequências
- Operações críticas devem ser transacionais.
- Ajustes exigem observação (note).