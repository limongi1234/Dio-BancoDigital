# Banco Digital 🏦

Simulação de um **banco digital** em **Java**, com forte ênfase em **Programação Orientada a Objetos** — herança, polimorfismo, enums e tratamento de exceções. Desafio do programa **DIO**.

## ✨ Domínio modelado

- **Contas**: classe abstrata `Conta` com `ContaCorrente` e `ContaPoupanca`
- **Usuários**: hierarquia `Usuario` → `Cliente` e `Funcionario` (`Gerente`, `Diretor`, `Presidente`)
- **Enums**: tipos de agência, usuário e conta
- **Exceções** customizadas (`UsuarioException`)
- **Sistema interno** com persistência simples em arquivo

## 🧩 Conceitos de OOP

- **Herança** e **polimorfismo** (contas e usuários)
- **Abstração** (classe `Conta` abstrata)
- **Enums** e **exceções personalizadas**

## 🛠️ Tecnologias

![Java](https://img.shields.io/badge/Java-ED8B00?style=flat&logo=openjdk&logoColor=white)

- **Java** puro (projeto Eclipse)

## 🚀 Como executar

```bash
git clone https://github.com/limongi1234/Dio-BancoDigital.git
cd Dio-BancoDigital/src

javac sistema/Principal.java
java sistema.Principal
```
