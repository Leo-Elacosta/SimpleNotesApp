package com.project.simplenotesapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.project.simplenotesapp.ui.notes.NoteListScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.NoteList.route //tela inicial é a tela de notas
    ) {
        //Define a tela de notas
        composable(Routes.NoteList.route) {
            NoteListScreen(
                notes = emptyList(),//por enquanto vazia
                onNoteClick = { note ->
                    //navega para o editor de notas passando o ID da nota selecionada
                    navController
                        .navigate(Routes.NoteEditor.createRoute(note.id))
                },
                onAddNewNoteClick = {
                    //Navega para o editor, mas com um id NEW para indicar uma nova nota
                    navController
                        .navigate(Routes.NoteEditor.createRoute("new"))
                }
            )
        }
        //Define a tela de edição de notas
        composable(
            route = Routes.NoteEditor.route,
            //Declarando que essa rota recebe um argumento chamado "NoteId"
            arguments = listOf(navArgument(NOTE_ID_ARG) { type = NavType.StringType})
        ) { backStackEntry ->
            //recupera o argumento da rota
            val noteId = backStackEntry.arguments?.getString(NOTE_ID_ARG)
        }
    }
}