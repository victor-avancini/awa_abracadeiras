# ADR-0003 - Deploy em VPS com Docker Compose + Backups em Nuvem
Status: Aceito
Data: 2026-03-01

## Contexto
Requisito: acesso de qualquer lugar e backup que não dependa de pendrive/HD local. Custo deve ser baixo.

## Decisão
Hospedar em VPS com Docker Compose (backend, frontend, postgres, reverse proxy TLS) e realizar backups automáticos do Postgres para storage em nuvem (S3-compatible).

## Alternativas consideradas
1. Rodar apenas em máquina local
2. PaaS gerenciado completo (Heroku-like)
3. Kubernetes

## Trade-offs
- VPS + Docker:
  - (+) Baixo custo e controle
  - (+) Portável
  - (-) Requer disciplina de operação (updates, firewall, monitoramento)
- PaaS:
  - (+) Menos operação
  - (-) Pode custar mais / limites
- Local:
  - (+) Muito barato
  - (-) Não atende “acesso de qualquer lugar” e tem risco de backup

## Consequências
- Precisamos documentar restore e testar periodicamente.
- TLS e hardening são obrigatórios.