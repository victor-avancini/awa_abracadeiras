# Checklist micro-detalhado (do zero à produção) — GitHub Flow + Monorepo

> Regras de fluxo:
> - Sempre iniciar trabalho em `develop` ou criar branch de feature a partir de `develop`.
> - Feature branch → PR → review → merge em `develop`.
> - Convenção de branch: `feature/<slug>` (ex.: `feature/inventory-movements`).

## 0. Repo e estrutura
- [x] Criar monorepo com pastas:
  - [x] `apps/backend/`
  - [x] `apps/frontend/`
  - [x] `infra/`
  - [x] `docs/`
- [x] Configurar `.editorconfig`, `.gitattributes`, `.gitignore`
- [x] Configurar padrão de commits (opcional) e template de PR

## 1. Backend (Spring Boot)
- [ ] (Branch) `feature/backend-bootstrap`
- [ ] Criar projeto Spring Boot (Web, Validation, Security, Data JPA, Flyway, Actuator)
- [ ] Configurar `application.yml` com profiles: `local`, `prod`
- [ ] Configurar conexão Postgres via env vars
- [ ] Adicionar padrão de erro (exception handler)
- [ ] Adicionar OpenAPI/Swagger
- [ ] PR → review → merge

## 2. Banco e migrações (Flyway)
- [ ] (Branch) `feature/db-schema-v1`
- [ ] Criar `V1__init.sql` com tabelas:
  - [ ] users
  - [ ] items
  - [ ] inventory_balance
  - [ ] stock_movement
  - [ ] customers
  - [ ] orders
  - [ ] order_items
  - [ ] production_orders
- [ ] Criar índices essenciais (sku único, datas, status)
- [ ] Seed do usuário admin inicial (via migration controlada)
- [ ] PR → review → merge

## 3. Auth
- [ ] (Branch) `feature/auth-login`
- [ ] Implementar login (JWT ou sessão)
- [ ] Implementar hash de senha (bcrypt/argon2)
- [ ] Implementar rate limit no login (simples)
- [ ] Endpoint `/auth/me`
- [ ] Testes unitários de auth
- [ ] PR → review → merge

## 4. Catálogo (Items)
- [ ] (Branch) `feature/items-crud`
- [ ] CRUD Items + validações (sku único, tipo)
- [ ] DTOs + mapper
- [ ] Tests (service + integration básico)
- [ ] PR → review → merge

## 5. Estoque (saldos + movimentações)
- [ ] (Branch) `feature/inventory-movements`
- [ ] Service transacional:
  - [ ] `addMovement(itemId, type, delta, ref, note)`
  - [ ] update balance com lock
  - [ ] bloquear saldo negativo (default)
- [ ] Endpoints: balances, movements, create movement (entrada/ajuste)
- [ ] PR → review → merge

## 6. Clientes e Pedidos
- [ ] (Branch) `feature/orders`
- [ ] CRUD Customers
- [ ] CRUD Orders + OrderItems
- [ ] Implementar fluxo `allocate`:
  - [ ] calcular faltas
  - [ ] registrar SAIDA_POR_PEDIDO para quantidade atendida
  - [ ] retornar faltante por item
- [ ] Status de pedidos e transições válidas
- [ ] PR → review → merge

## 7. Produção (OP)
- [ ] (Branch) `feature/production-orders`
- [ ] CRUD ProductionOrder
- [ ] start/finish/cancel com transições válidas
- [ ] finish gera PRODUCAO_FINALIZADA e incrementa saldo
- [ ] PR → review → merge

## 8. Relatórios e Alertas
- [ ] (Branch) `feature/reports-alerts`
- [ ] Implementar queries:
  - [ ] low stock (balance < min)
  - [ ] open orders
  - [ ] in-progress OPs
  - [ ] movement history (filtros)
- [ ] Lembrete de atraso (pedido due_date < hoje e status != EXPEDIDO/FINALIZADO)
- [ ] Sugestão de produção (ver 04)
- [ ] PR → review → merge

## 9. Frontend (React)
- [ ] (Branch) `feature/frontend-shell`
- [ ] Setup React + router + state + forms
- [ ] Tela Login
- [ ] Layout base (menu: Itens, Estoque, Pedidos, Produção, Relatórios)
- [ ] PR → review → merge

- [ ] (Branch) `feature/frontend-items`
- [ ] CRUD Itens
- [ ] PR → review → merge

- [ ] (Branch) `feature/frontend-orders-production`
- [ ] Pedidos: CRUD + allocate + status
- [ ] OPs: CRUD + start/finish
- [ ] PR → review → merge

- [ ] (Branch) `feature/frontend-reports`
- [ ] Relatórios e alertas
- [ ] PR → review → merge

## 10. Infra local (Docker Compose)
- [ ] (Branch) `feature/infra-local`
- [ ] `docker-compose.yml` (postgres + backend + frontend)
- [ ] Scripts: `make up`, `make down`, `make logs` (ou npm scripts)
- [ ] PR → review → merge

## 11. Produção (VPS)
- [ ] (Branch) `feature/prod-deploy`
- [ ] Escolher VPS e domínio
- [ ] Configurar reverse proxy (TLS)
- [ ] `docker-compose.prod.yml`
- [ ] Secrets via env vars
- [ ] Health checks
- [ ] PR → review → merge

## 12. Backups e Restore
- [ ] (Branch) `feature/backups`
- [ ] Job diário de dump do Postgres
- [ ] Upload para storage cloud (S3-compatible)
- [ ] Retenção 14 dias
- [ ] Doc de restore (passo a passo) e teste real
- [ ] PR → review → merge

## 13. CI/CD
- [ ] (Branch) `feature/ci-cd`
- [ ] GitHub Actions:
  - [ ] PR: build + tests + lint
  - [ ] main: build images + deploy via SSH
- [ ] PR → review → merge

## 14. Qualidade e acabamento
- [ ] Ajustar README (setup local, deploy, restore)
- [ ] Revisar docs/ e ADRs (atualizar se algo mudou)
- [ ] Criar template de PR:
  - [ ] Contexto
  - [ ] Mudanças
  - [ ] Como testar
  - [ ] Checklist QA