# API e Contratos

## Convenções
- Base path: `/api/v1`
- JSON: camelCase no payload, mapeado para Java.
- Erros: envelope padronizado.
- Ids: UUID.
- Paginação: `page`, `size`, `sort`.

## Padrão de erro
```json
{
  "error": {
    "code": "VALIDATION_ERROR",
    "message": "Campo inválido",
    "details": [
        {"field": "sku", "reason": "must be unique"}
    ],
    "traceId": "..."
  }
}
```

## Endpoints (MVP)

### Auth
- POST `/auth/login`
- POST `auth/logout` (opcional se JWT stateless)
- GET `/auth/me`

### Items (Catálogo)
- GET `/items`
- POST `/items`
- GET `/items/{id}`
- PUT `/items/{id}`
- DELETE `/items/{id}` (ou soft delete)

### Inventory
- GET `/inventory/balances`
- POST `/inventory/movements` (entrada manual, ajuste)
- GET `/inventory/movements` (filtros: itemId, dateRange)

### Customers
- GET `/customers`
- POST `/customers`
- PUT `/customers/{id}`

### Orders
- GET `/orders` (filtros por status, prazo)
- POST `/orders`
- GET `/orders/{id}`
- PUT `/orders/{id}`
- POST `/orders/{id}/allocate` (separar/baixar)
- POST `/orders/{id}/ship`
- POST `/orders/{id}/cancel`

### Production Orders
- GET `/production-orders` (filtros por status)
- POST `/production-orders`
- POST `/production-orders/{id}/start`
- POST `/production-orders/{id}/finish`
- POST `/production-orders/{id}/cancel`

### Reports
- GET `/reports/low-stock`
- GET `/reports/open-orders`
- GET `/reports/in-progress-production-orders`
- GET `/reports/stock-movements` (alias do inventory/movements se preferir)

## Versionamento
- Breaking changes: nova versão `/api/v2`.
- Contratos documentados com OpenAPI (Swagger) gerado pelo backend.

**Manutenção:** adicione endpoints aqui e mantenha o OpenAPI gerado consistente com este contrato.