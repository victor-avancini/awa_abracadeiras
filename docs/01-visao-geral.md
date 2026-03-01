# Visão Geral

## Objetivo
Construir um sistema web para uma empresa familiar que produz abraçadeiras de aço, substituindo planilhas Excel, para controlar:
- Estoque (produto final + matéria-prima) por quantidade
- Pedidos (cliente + prazo/entrega)
- Produção via Ordens de Produção (OP) com status
- Alertas (estoque mínimo, sugestão de produção, atraso)
- Relatórios operacionais

## Escopo do MVP
Inclui:
- Login simples (1 usuário)
- Cadastro de produtos (produto final e matéria-prima)
- Pedidos: criar/editar, status, prazo/entrega
- Separação: baixa de estoque para atender pedido
- Produção: OP Planejada → Em Produção → Finalizada → Cancelada
- Movimentação de estoque: entradas, saídas, ajustes, consumo/produção
- Relatórios:
    - Itens abaixo do mínimo
    - Pedidos em aberto
    - OPs em andamento
    - Histórico de movimentações (auditável)

Não inclui:
- Multi-tenant / múltiplas empresas
- BOM/ficha técnica e cálculo de consumo de matéria-prima por produto
- Integrações (ERP/CRM/pagamentos/SSO)
- Emissão fiscal
- Controle por lote/localização
- Importação automática de planilhas

## Usuários e Persona
- Usuário único: dona da empresa (admin de tudo)

## Requisitos não funcionais (mínimos)
- Baixo custo operacional
- Acesso de qualquer lugar (internet)
- Backup automatizado em nuvem
- Interface web em Português (pt-BR)
- Auditoria básica por histórico de movimentações

## Riscos
- Estoque incorreto se operações não gerarem movimentações consistentemente
- Sugestão de produção simplificada (sem BOM)
- Segurança: senha fraca / exposição pública sem hardening

## Sucesso (critério)
- A dona da empresa consegue substituir as planilhas por:
    - Cadastro confiável de estoque e produção
    - Pedidos com prazo e acompanhamento
    - Alertas e relatórios de rotina