# ADR-0004 - API REST Versionada /api/v1 + OpenAPI
Status: Aceito
Data: 2026-03-01

## Contexto
Frontend web consumirá o backend. Precisamos de contratos claros e evolutivos.

## Decisão
Adotar API REST versionada em `/api/v1` e gerar documentação OpenAPI (Swagger).

## Alternativas consideradas
1. GraphQL
2. REST sem versionamento

## Trade-offs
- REST versionado:
  - (+) Simples, amplo suporte
  - (+) Evolução com /v2 em breaking changes
  - (-) Pode gerar endpoints mais numerosos
- GraphQL:
  - (+) Flexível para frontend
  - (-) Overhead e complexidade desnecessários no MVP

## Consequências
- Mudanças breaking exigem nova versão.
- OpenAPI será fonte de contrato.