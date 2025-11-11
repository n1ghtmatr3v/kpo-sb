# HSE Bank Financial Management System

Система финансового учета для HSE-Банка с использованием паттернов проектирования.

## 📋 Функциональность

### Основная функциональность
- ✅ Создание, редактирование и удаление счетов
- ✅ Управление категориями доходов/расходов  
- ✅ Выполнение финансовых операций
- ✅ Автоматическое обновление балансов счетов

### Дополнительная функциональность
- 📊 Аналитика финансовых данных
- 📁 Импорт данных (JSON, CSV, YAML)
- 📤 Экспорт данных (JSON, CSV, YAML)
- 🔄 Управление данными и пересчет балансов
- ⏱️ Измерение времени выполнения операций
- 💾 In-memory кэширование данных

## 🏗️ Архитектура и паттерны

### Реализованные паттерны GoF

#### Порождающие паттерны
- **Фабрика (Factory)** - `DomainFactory`, `SimpleDomainFactory`
  - Централизованное создание доменных объектов
  - Валидация при создании

#### Структурные паттерны  
- **Фасад (Facade)** - `BankAccountFacade`, `CategoryFacade`, `OperationFacade`, `AnalyticsFacade`, `ExportFacade`, `ImportFacade`, `DataManagementFacade`
  - Инкапсуляция сложной логики
  - Упрощение API для клиентов
- **Декоратор (Decorator)** - `TimedCommandDecorator`
  - Добавление функциональности измерения времени
- **Прокси (Proxy)** - `BankRepositoryProxy`
  - In-memory кэширование данных
- **Посетитель (Visitor)** - `ExportVisitor`, `JSONExportVisitor`, `CSVExportVisitor`, `YAMLExportVisitor`
  - Экспорт данных в различные форматы

#### Поведенческие паттерны
- **Команда (Command)** - `Command`, `CreateAccountCommand`, `CreateCategoryCommand`, `CreateOperationCommand`, `ShowAnalyticsCommand`
  - Инкапсуляция пользовательских сценариев
- **Шаблонный метод (Template Method)** - `DataImporter`, `JSONDataImporter`, `CSVDataImporter`, `YAMLDataImporter`
  - Унификация процесса импорта данных

### Принципы SOLID
- **SRP** - каждый класс имеет одну ответственность
- **OCP** - система открыта для расширения
- **LSP** - реализации интерфейсов взаимозаменяемы  
- **ISP** - узконаправленные интерфейсы
- **DIP** - зависимости от абстракций

### Принципы GRASP
- **High Cohesion** - высокая связанность внутри модулей
- **Low Coupling** - низкая связанность между модулями

## 🗂️ Структура проекта
src/main/java/hse/bank/
├── area/ # Доменные сущности
│ ├── BankAccount.java # Банковский счет
│ ├── Category.java # Категория операций
│ ├── Operation.java # Финансовая операция
│ └── OperationType.java # Тип операции (доход/расход)
├── factory/ # Фабрики объектов
│ ├── DomainFactory.java
│ └── SimpleDomainFactory.java
├── facade/ # Фасады для работы с системой
│ ├── AnalyticsFacade.java
│ ├── BankAccountFacade.java
│ ├── CategoryFacade.java
│ ├── DataManagementFacade.java
│ ├── ExportFacade.java
│ ├── ImportFacade.java
│ └── OperationFacade.java
├── command/ # Паттерн Команда
│ ├── Command.java
│ ├── CreateAccountCommand.java
│ ├── CreateCategoryCommand.java
│ ├── CreateOperationCommand.java
│ ├── ShowAnalyticsCommand.java
│ └── TimedCommandDecorator.java
├── visitor/ # Паттерн Посетитель
│ ├── ExportVisitor.java
│ ├── CSVExportVisitor.java
│ ├── JSONExportVisitor.java
│ └── YAMLExportVisitor.java
├── template/ # Паттерн Шаблонный метод
│ ├── DataImporter.java
│ ├── CSVDataImporter.java
│ ├── JSONDataImporter.java
│ └── YAMLDataImporter.java
├── proxy/ # Паттерн Прокси
│ ├── BankRepository.java
│ └── BankRepositoryProxy.java
└── Main.java # Точка входа



## 🗂️ Структура проекта
Папка для домашки
