# Segurança

## Threat model (MVP)
Ameaças principais:
- Acesso não autorizado (credenciais fracas)
- Ataques de força brutal no login
- Vazamento de dados por má configuração do servidor
- Perda de dados (sem backup/restore)
- Alterações não auditadas no estoque

## Autenticação
- Login com email + senha
- Senha armazenada com hach forte (bcrypt/argon2)
- Sessão via JWT (access token curto) + refresh token (opcional)
- TLS obrigatório em produção

## Autorização
- MVP: usuário único → autorização simplificada
- Preparar RBAC futuro:
    - roles: OWNER/ADMIN/OPERATOR/VIEWER (não implementar agora)
    - estrutura pronta no código (interfaces/guards)

## Proteções
- Rate limit no endpoint de login
- Bloqueio temporário após N tentativas (opcional)
- CSRF: se usar cookies; se JWT em header, manter boas práticas.
- Headers de segurança (HSTS, X-Content-Type-Options etc.)

## LGPD/PII
- PII mínima:
    - usuário: email
    - clientes: nome e contato (se coletado)
- Boas práticas:
    - limitar campos e acesso
    - logs sem dados sensíveis
    - Política de retenção futura (fora do MVP)

## Auditoria
- Fonte principal: `StockMovement` registra evento, referência e timestamp.
- Para ações críticas (cancelar pedido, ajuste de estoque), exigir observação (note).

**Manutenção:** quando adicionar mais usuários, SSO ou integrações, expanda authz e políticas LGPD aqui (referenciando mudanças em ADRs)