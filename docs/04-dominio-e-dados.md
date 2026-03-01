# Domínio e dados

## Conceitos principais (ubiquitous language)
- Item: algo que pode estar em estoque (PRODUTO_FINAL ou MATERIA_PRIMA)
- Saldo/Estoque: quantidade atual por item
- Movimentação: evento que altera (ou registra) o saldo
- Pedido: demanda do cliente com prazo e itens
- Ordem de Produção (OP): produção de produto final com status

## Entidades (alto nível)

### User
- id, email, password_hash, created_at

### Item
- id
- type: PRODUTO_FINAL | MATERIA_PRIMA
- sku (único)
- name
- unit (ex.: UN, KG)
- stock_min (int)
- is_active (bool)

### InventoryBalance
- item_id (PK/FK)
- quantity_on_hand (int)

### StockMovement
- id
- item_id
- type: ENTRADA_MANUAL | SAIDA_POR_PEDIDO | AJUSTE | PRODUCAO_FINALIZADA
- quantity_delta (int) (positivo/negativo)
- ref_type: ORDER | PRODUCTION_ORDER | NONE
- ref_id (nullable)
- note (nullable)
- created_at

### Customer
- id
- name
- contact (nullable)

### Order
- id
- customer_id
- due_date
- status: ABERTO | EM_SEPARACAO | EM_PRODUCAO | FINALIZADO | EXPEDIDO | CANCELADO
- created_at

### OrderItem
- order_id
- item_id (somente PRODUTO_FINAL no MVP)
- quantity

### ProductionOrder
- id
- order_id (nullable)
- item_id (PRODUTO_FINAL)
- quantity
- status: PLANEJADA | EM_PRODUCAO | FINALIZADA | CANCELADA
- created_at, finished_at (nullable)

## Regras de consistência e concorrência
- Postgres é a fonte de verdade
- Alteração de saldo deve ser feita via transação:
    - inserir StockMovement
    - atualizar InventoryBalance com lock/UPDATE (evitar corrida)
- Validações:
    - Não permitir saldo negativo (configurável: no MVP, bloquear por padrão)
    - OP só finaliza se status permitir

## Volumetria (estima - MVP)
- Pedido: dezenas a centenas/mês
- Itens (SKUs): dezenas e poucas centenas
- Movimentações: centenas a milhares/mês
=> Postgres single instance suficiente.

## Tenancy
- Single-tenant (uma empresa).
- Preparar base para multi-tenant futuro:
    - adicionar tenant_id nas tabelas e aplicar filtros globais
    - sem implementar agora

## Migrações
- Flyway migrations versionadas (V1__init.sql, V2__...).
- Seeds:
    - criar usuário admin inicial via migration/seed controlada.

## Sugesttão de produção (MVP)
Sem BOM:
- Para cada produto final:
    - demanda_aberta = soma(quantidade nos pedidos em aberto) - (se houver lógica de reservado)
    - sugestao = max(estoque_min - estoque_atual, demanda_aberta - estoque_atual, 0)

**Manutenção:** quando entrar BOM, lotes/localização, ou multi-tenant, este é o arquivo principal a atualizar (evite duplicar isso em outros docs).