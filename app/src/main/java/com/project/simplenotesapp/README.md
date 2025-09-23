# To-Do List App com Kotlin e Firebase 📝✅

Um aplicativo Android simples e funcional para gerenciamento de tarefas (To-Do list). Desenvolvido como um projeto de portfólio para demonstrar o uso de tecnologias modernas do ecossistema Android nativo, incluindo Jetpack Compose para a UI e Firebase para o backend em tempo real.

Cada usuário pode se cadastrar e ter sua própria lista de tarefas privada, sincronizada na nuvem e acessível de qualquer dispositivo.

## 📱 Screenshots

![Screenshot do App To-Do List](screenshots/screenshot_main.png)


## ✨ Funcionalidades Principais

* **Autenticação de Usuário:** Sistema completo de Cadastro e Login utilizando **Firebase Authentication** com provedor de E-mail e Senha.
* **Gerenciamento de Tarefas (CRUD Completo):**
    * **Criar:** Adicionar novas tarefas de forma rápida.
    * **Ler:** Visualizar a lista de tarefas em tempo real.
    * **Atualizar:** Marcar e desmarcar tarefas como "concluídas".
    * **Deletar:** Remover tarefas da lista.
* **Interface Reativa:** A lista de tarefas se atualiza automaticamente na tela assim que os dados são modificados no banco de dados.
* **Privacidade de Dados:** Cada usuário tem acesso apenas às suas próprias tarefas, garantido por regras de segurança do Firestore.
* **UI Organizada:** Separação visual clara entre tarefas ativas e concluídas para uma melhor organização.

## 🛠️ Tecnologias e Arquitetura

Este projeto foi construído utilizando as seguintes tecnologias e boas práticas:

* **Linguagem:** [Kotlin](https://kotlinlang.org/)
* **UI Toolkit:** [Jetpack Compose](https://developer.android.com/jetpack/compose) para uma interface declarativa e moderna.
* **Arquitetura:** MVVM (Model-View-ViewModel) para separar a lógica de negócios da interface do usuário.
* **Gerenciamento de Estado:** `ViewModel` e `StateFlow` para gerenciar e expor o estado da UI de forma reativa e segura.
* **Backend (BaaS):** [Firebase](https://firebase.google.com/)
    * **Autenticação:** [Firebase Authentication](https://firebase.google.com/docs/auth)
    * **Banco de Dados:** [Cloud Firestore](https://firebase.google.com/docs/firestore) para armazenamento e sincronização de dados em tempo real.
* **Navegação:** [Navigation Compose](https://developer.android.com/jetpack/compose/navigation) para gerenciar o fluxo entre as telas de autenticação e a lista principal.

## 🚀 Como Configurar e Rodar o Projeto

Para rodar este projeto localmente, siga estes passos:

1.  Clone o repositório: `git clone https://github.com/SEU_USUARIO/SEU_REPOSITORIO.git`
2.  Abra o projeto no Android Studio.
3.  Crie um novo projeto no [Console do Firebase](https://console.firebase.google.com/).
4.  No seu projeto Firebase, habilite os seguintes serviços:
    * **Authentication:** Ative o provedor "E-mail/senha".
    * **Firestore Database:** Crie um novo banco de dados e inicie em "modo de teste".
5.  Adicione um novo aplicativo Android ao seu projeto Firebase.
    * Use o `applicationId` encontrado no arquivo `app/build.gradle.kts` do projeto.
6.  Faça o download do arquivo `google-services.json` gerado pelo Firebase e coloque-o na pasta `app/` do projeto no Android Studio.
7.  Sincronize, construa e rode o projeto.

## 🔮 Melhorias Futuras

* [ ] Adicionar um indicador de carregamento (`Loading`) durante o login e o carregamento inicial das tarefas.
* [ ] Permitir a edição do texto de uma tarefa existente.
* [ ] Adicionar um botão de "Sair" (Logout).

## 🚀 Como Configurar e Rodar o Projeto

Este aplicativo utiliza o Firebase como backend para autenticação e armazenamento de dados. Para que ele funcione no seu ambiente de desenvolvimento, é **crucial criar e configurar seu próprio projeto no Firebase** seguindo os passos abaixo.

1.  **Clone o Repositório:**
    ```bash
    git clone [https://github.com/SEU_USUARIO/SEU_REPOSITORIO.git](https://github.com/SEU_USUARIO/SEU_REPOSITORIO.git)
    ```

2.  **Abra no Android Studio:**
    * Importe o projeto clonado no Android Studio. Ele irá sincronizar as dependências do Gradle automaticamente.

3.  **Crie e Configure o Projeto no Firebase:**
    * Acesse o [Console do Firebase](https://console.firebase.google.com/) e crie um novo projeto.
    * Dentro do seu projeto no Firebase, você precisará **ativar e configurar dois serviços essenciais**:

        * **Authentication (Autenticação):**
            1. No menu "Build", vá para **Authentication**.
            2. Clique em "Começar".
            3. Na aba "Sign-in method", habilite o provedor **"E-mail/senha"**.

        * **Cloud Firestore (Banco de Dados):**
            1. No menu "Build", vá para **Firestore Database**.
            2. Clique em **"Criar banco de dados"**.
            3. Inicie em **"modo de teste"**. Isso permite que o app leia e escreva dados durante o desenvolvimento.
            4. Escolha a localização do servidor mais próxima de você (ex: `southamerica-east1` para São Paulo).

4.  **Conecte o App ao Firebase:**
    * Nas configurações do seu projeto Firebase (clicando na engrenagem ⚙️), adicione um novo aplicativo Android (ícone do Android).
    * O "Nome do pacote Android" solicitado deve ser exatamente o `applicationId` que está no arquivo `app/build.gradle.kts` do seu projeto.
    * Ao final do processo, faça o download do arquivo **`google-services.json`**.
    * Copie este arquivo e cole-o dentro da pasta **`app/`** no seu projeto do Android Studio.

5.  **Rode o Aplicativo:**
    * Com o arquivo `google-services.json` no lugar, sincronize, construa (`Build`) e rode (`Run`) o app no seu emulador ou dispositivo físico.
