# Infra, DevOps e Observabilidade

## Ambientes
- local: docker compose (db + backend + frontend)
- prod: VPS com docker compose (mesma topologia)

## Deploy (proposta)
- Docker images versionadas por tag (git sha)
- `docker-compose.prod.yml` com:
    - backend
    - frontend (ou servir via nginx)
    - postgres
    - reverse proxy (Caddy/Nginx) com TLS

## Backups (obrigatório)
- Backup automático diário do Postgress para estoque em nuvem (S3-compatible).
- Retenção: 14 dias.
- Restore testado (procedimento abaixo).

### Procedimento de restore (resumo)
1. Baixar dump do dia desejado
2. Subir um Postgres limpo.
3. Restaurar dump.
4. Subir backend apontando para DB restaurado.

## Observabilidade
- Health checks
    - GET `/actuator/health`
- Logs estruturados (JSON) com:
    - traceId/correlationId
    - userId (quando aplicável)
- Métricas:
    - HTTP latency
    - error rate
    - DB connections

## CI/CD (GitHub Actions)
- PR: build + tests + lint + build Docker (opcional)
- main: build + push images + deploy (SSH/runner)
- versionamento: tags semânticas (opcional) + sempre tag por commit

## Segurança operacional
- Secrets via env vars/secret store
- DB com senha forte
- Firewall restringindo portas
- Atualização periódicas da VPS

**Manutenção:** qualquer mudança de deploy/CI/CD/backup deve ser registrada aqui e em um ADR se for decisão relevante.