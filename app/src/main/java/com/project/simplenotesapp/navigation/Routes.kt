package com.project.simplenotesapp.navigation

//usamos um nome base para evitar erros de digitação
const val NOTE_ID_ARG = "noteId"


sealed class Routes(val route: String) {
    //Rota para a lista de notas
    data object NoteList : Routes("noteList")

    //Rota para o editor de notas
    data object NoteEditor : Routes("noteEditor/{$NOTE_ID_ARG}") {
        //Função auxiliar para construir a rota com o ID da nota
        fun createRoute(noteId: String) = "noteEditor/$noteId"
    }
}