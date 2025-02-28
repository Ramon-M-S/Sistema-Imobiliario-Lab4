# Sistema Imobiliário - Lab 04

## Descrição
Backend para gestão imobiliária, seguindo arquitetura em camadas:

- **Modelo**: Mapeamento JPA.
- **Repositório**: CRUDs com padrão Repository.
- **Serviço**: Regras de negócio.

## Tecnologias
- Java
- JPA/Hibernate
- MySQL
- Lombok

## Funcionalidades
- **Modelo **: Classes mapeadas com JPA. Uso de boas práticas e Lombok.
- **Repositório **: CRUDs de Imóveis, Profissionais, Locação e Aluguéis com verificações.
- **Serviço **: Registrar locação e atualizar status do imóvel.
- **Pagamento **: Registro de pagamentos e cálculo de multa (0,33% ao dia, até 20%).

## Como Rodar
Clone o repositório:

```bash
git clone https://github.com/Ramon-M-S/Sistema-Imobiliario-Lab4.git
