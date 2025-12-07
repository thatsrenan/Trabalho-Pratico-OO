# Trabalho Prático de Orientação a Objeto
# 🚖 **Sistema Mobil - Aplicativo de Transporte**
## Autores
Caio Bechepeche Mota -  
Renan Curione de Castro - 242024834

## Professor
André Luiz Peron Martins Lanna

## 📋 **Sobre o Projeto**
O **Mobil** é um sistema completo de transporte por aplicativo desenvolvido em Java, que simula todas as etapas de uma corrida: desde a solicitação pelo passageiro até o pagamento e avaliação do serviço.

## ✨ **Funcionalidades Principais**

### 🧑‍💼 **Para Passageiros**
- ✅ **Cadastro completo** com validação de dados
- ✅ **Login rápido** com usuário de teste pré-configurado
- ✅ **Solicitação de corridas** (Comum ou Luxo)
- ✅ **3 métodos de pagamento**: Dinheiro, PIX ou Cartão
- ✅ **Acompanhamento em tempo real** da corrida
- ✅ **Sistema de avaliação** (1-5 estrelas)
- ✅ **Gestão de perfil**: atualizar dados, localização e senha
- ✅ **Histórico completo** de corridas realizadas

### 🚗 **Para Motoristas**
- ✅ **8 motoristas pré-cadastrados** com veículos reais
- ✅ **Status dinâmico** (Disponível/Em corrida)
- ✅ **Sistema de localização** em tempo real
- ✅ **Validação de CNH** com data de expiração
- ✅ **Recebimento de avaliações** dos passageiros

### 💳 **Sistema de Pagamento**
- ✅ **Dinheiro físico** com cálculo automático de troco
- ✅ **PIX** com validação de senha (3 tentativas)
- ✅ **Cartão de crédito** com validação de senha
- ✅ **Verificação de saldo** disponível

## 🏗️ **Arquitetura do Projeto**
```bash
com.mobil/  
├── app/ # Camada de Apresentação  
│ └── Principal.java # Ponto de entrada da aplicação  
│  
├── modelos/ # Camada de Domínio  
│ ├── corrida/ # Tipos de corrida (Strategy Pattern)  
│ │ ├── Corrida.java (Abstract)  
│ │ ├── CorridaComum.java (R$5 + R$1/km)  
│ │ └── CorridaDeLuxo.java (R$9 + R$2.20/km)  
│ │  
│ ├── pessoas/ # Usuários do sistema (Herança)  
│ │ ├── Usuario.java (Abstract)  
│ │ ├── Passageiro.java (Cliente)  
│ │ └── Motorista.java (Prestador de serviço)  
│ │  
│ ├── pagamento/ # Métodos de pagamento (Strategy Pattern)  
│ │ ├── MetodoDePagamento.java (Abstract)  
│ │ ├── Dinheiro.java (Com troco)  
│ │ ├── PIX.java (Digital)  
│ │ └── CartaoDeCredito.java (Cartão)  
│ │  
│ └── propriedades/ # Objetos de Valor  
│ ├── Localizacao.java # Sistema de coordenadas 100x100  
│ ├── Veiculo.java # Dados do veículo  
│ ├── CNH.java # Carteira de motorista  
│ └── Avaliacao.java # Sistema de 1-5 estrelas  
│  
└── servicos/ # Camada de Serviços/Lógica de Negócio  
├── PrincipalServico.java # Controlador principal  
├── AppServico.java # Configuração e utilidades  
├── PassageiroServico.java # Gestão de passageiros  
├── MotoristaServico.java # Gestão de motoristas  
├── CorridaServico.java # Gestão de corridas  
├── PagamentoServico.java # Processamento de pagamentos  
└── LocalizacaoServico.java # Cálculos geográficos  
```

## 🚀 **Como Executar o Projeto**

### **Pré-requisitos**
- Java JDK 11 ou superior
- Terminal/Command Prompt

### **Passos para Execução**
1. **Clone ou copie os arquivos** para uma pasta local
2. **Compile todos os arquivos Java**:


## Predefinições do Sitema
### 🔧 Sistema de Coordenadas
#### Mapa Virtual
Área: 100x100 unidades
Coordenadas
1 unidade ≈ 1 km

Limites: X (0-100), Y (0-100)

### Cálculos
Distância: Fórmula euclidiana √[(x₂-x₁)² + (y₂-y₁)²]

Tempo estimado: (distância / 60 km/h) × 60 minutos

Velocidade média: 60 km/h

### Preço das Corridas
#### Corrida Comum
Fórmula: R$ 5,00 + (R$ 1,00 × km)

#### Corrida de Luxo
Fórmula: R$ 9,00 + (R$ 2,20 × km)

### 🎮 Menu Principal
```text
╔══════════════════════════════════════════╗
║              MENU PRINCIPAL              ║
╠══════════════════════════════════════════╣
║ 1 - 🚖 Chamar corrida                    ║
║ 2 - 👤 Ver informações do perfil         ║
║ 3 - 🚗 Ver motoristas disponíveis        ║
║ 4 - 📜 Histórico de corridas             ║
║ 5 - ⚙️  Configurações                    ║
║ 6 - 🚪 Sair                              ║
╚══════════════════════════════════════════╝
```

### 👥 Motoristas Cadastrados
São criados 10 motoristas, por padrão, para a experiência do app.

### 🔐 Regras e Validações
### Senhas
4 dígitos obrigatórios (1000-9999)

Validação por senha em operações mais importantes

Alteração apenas com senha atual

### Pagamento
Dinheiro: Troco calculado em notas/moedas

PIX/Cartão: 3 tentativas de senha

Saldo: Verificação antes da transação

### Localização
Coordenadas válidas: 0 a 100

Atualização em tempo real

Cálculo de distância automático

## 🛠️ Tecnologias e Conceitos
Linguagem e Paradigma
Java (Orientação a Objetos)

Herança e Polimorfismo

Encapsulamento completo

Padrões Arquiteturais
Separação em camadas (apresentação, domínio, serviços)

Baixo acoplamento entre componentes

Alta coesão dentro de cada módulo

## 📈 Possíveis Melhorias Futuras
### Funcionalidades
Persistência de dados (arquivos/banco de dados)

Sistema de promoções e cupons

Múltiplos passageiros por corrida

Tipos de veículo adicionais (moto, van)

### Técnicas
Interface gráfica (JavaFX/Swing)

Conexão com API de mapas real

Sistema de notificações

Relatórios e estatísticas

Testes unitários automatizados

👨‍💻 Desenvolvimento
Opção 1: Compilação Manual
```bash
# Compilar tudo
find . -name "*.java" > sources.txt
javac @sources.txt

# Executar
java com.mobil.app.Principal
Estrutura de Pastas Recomendada
text
projeto-mobil/
├── README.md
├── src/
│   └── com/mobil/
│       ├── app/
│       ├── modelos/
│       └── servicos/
└── bin/ (gerado após compilação)
```

Opção 2: usar uma IDE que rode Java com um simples botão, como o IntelliJ, ao clicar no botão RUN, após baixar todos arquivos do programa.

# 🤝 Contribuição
Reportar Bugs
Descreva o problema encontrado

Informe os passos para reproduzir

Inclua mensagens de erro (se houver)

Sugerir Melhorias
Descreva a funcionalidade desejada

Explique o benefício para os usuários

Proponha uma implementação (opcional)

# 📄 Licença
Este projeto foi desenvolvido para fins educacionais e demonstração de conceitos de programação orientada a objetos em Java.

# 🎯 Pronto para Usar!
O Sistema Mobil está completo e funcional. Basta compilar e executar para começar a solicitar suas corridas virtuais!

Divirta-se explorando todas as funcionalidades! 🚗💨

## "Chegue onde quiser, quando quiser. Mobil - Seu transporte premium!"