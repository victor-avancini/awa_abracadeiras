# Requisitos e Critérios de Aceite
> Referências: 
> - Fluxos e arquitetura: ver [03-arquitetura.md](03-arquitetura.md)
> - Domínio e dados: ver [04-dominio-e-dados.md](04-dominio-e-dados.md)

## Requisitos Funcionais (MVP)

### RF-01 Autenticação
- O sistema deve permitir login com usuário e senha
- Deve existir 1 usuário inicial (criado via seed/admin tool)

**Aceite:**
- Login/logout funcionando; sessão expira após período configurável

### RF-02 Cadastro de Itens (Produtos e Matéria-prima)
- CRUD de item com:
    - tipo: PRODUTO_FINAL | MATÉRIA_PRIMA
    - sku/código (único)
    - nome
    - unidade (ex: UN, KG)
    - estoque_atual
    - estoque_mínimo
    - ativo (sim/não)

**Aceite:**
- Não permite sku duplicado
- Estoque mínimo é usado nos relatórios/alertas

### RF-03 Pedidos
- CRUD de pedido com:
    - cliente (nome + contato opcional)
    - itens (item + quantidade)
    - prazo/entrega (data)
    - status: ABERTO | EM_SEPARAÇÃO | EM_PRODUÇÃO | FINALIZADO | EXPEDIDO | CANCELADO
- Deve permitir "reservar/separar" itens do estoque para pedido.

**Aceite:**
- Ao separar, gera movimentação de saída/reserva conforme regra definida (ver 04).

### RF-04 Ordens de Produção (OP)
- CRUD de OP associada a um pedido (opcional) e a um produto final:
    - status: PLANEJADA | EM_PRODUÇÃO | FINALIZADA | CANCELADA
    - quantidade a produzir
- Ao finalizar OP, aumenta estoque do produto final e registra movimentação.

**Aceite:**
- OP finalizada reflete no estoque e no histórico.

### RF-05 Movimentações de Estoque
- Registrar eventos:
    - ENTRADA_INICIAL
    - SAIDA_POR_PEDIDO
    - AJUSTE
    - PRODUCAO_FINALIZADA
- Cada movimentação deve registrar: item, quantidade, tipo, referência (pedido/op), data/hora, observação.

**Aceite:**
- Todo impacto de estoque gera movimentação correspondente.

### RF-06 Alertas e Sugestões
- Relatório/alerta de itens abaixo do mínimo.
- Sugestão de produção baseada em:
    - pedidos em aberto e estoque disponível do produto final
    - estoque mínimo
- Lembrete de atraso para pedidos com prazo vencido e status não concluído.

**Aceite:**
- Exibe lista de itens/pedidos com critérios claros e filtros.

### RF-07 Relatórios
- Itens abaixo do mínimo
- Pedidos em aberto (com prazo e status)
- OPs em andamento
- Histórico de movimentações (filtros por item, data, tipo)

**Aceite:**
- Relatórios carregam em tempo aceitável para volume pequeno/médio.

## Requisitos Não Funcionais

### RNF-01 Disponibilidade
- Alvo MVP: 99% (best effort) com rollback simples.

### RNF-02 Performance
- Operações CRUD e listagens usuais: < 1s em rede normal para base pequena.

### RNF-03 Segurança
- TLS obrigatório em produção
- Senhas com hash forte (bcrypt/argon2)
- Rate limiting básico no login.
- Logs de auditoria por movimentação.

### RNF-04 Backup/Recuperação
- Backup automático do Postgres para storage em nuvem (diário).
- Retenção mínima: 14 dias.
- Procedimento documentado de restore.

### RNF-05 Observabilidade
- Logs estruturados + métricas básicas (health, erros).