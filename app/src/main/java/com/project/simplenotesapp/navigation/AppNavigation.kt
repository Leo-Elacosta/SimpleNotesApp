package com.project.simplenotesapp.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.project.simplenotesapp.ui.notes.AuthState
import com.project.simplenotesapp.ui.notes.AuthViewModel
import com.project.simplenotesapp.ui.notes.LoginScreen
import com.project.simplenotesapp.ui.notes.NoteEditorScreen
import com.project.simplenotesapp.ui.notes.NoteListScreen
import com.project.simplenotesapp.ui.notes.NoteViewModel
import com.project.simplenotesapp.ui.notes.SignUpScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = viewModel()
    val authState by authViewModel.authState.collectAsState()
    val context = LocalContext.current

    // Efeito para observar o estado de autenticação e navegar quando houver sucesso
    LaunchedEffect(authState) {
        if (authState is AuthState.Success) {
            Toast.makeText(context, "Login bem-sucedido!", Toast.LENGTH_SHORT).show()
            navController.navigate(Routes.NoteList.route) {
                popUpTo(Routes.Login.route) { inclusive = true }
            }
        }
    }

    NavHost(navController = navController, startDestination = Routes.Login.route) {
        composable(Routes.Login.route) {
            LoginScreen(
                onLoginClick = { email, pass -> authViewModel.login(email, pass) },
                onSignUpClick = { navController.navigate(Routes.SignUp.route) }
            )
        }
        composable(Routes.SignUp.route) {
            SignUpScreen(
                onSignUpClick = { email, pass -> authViewModel.signUp(email, pass) },
                onLoginClick = { navController.navigate(Routes.Login.route) }
            )
        }
        composable(Routes.NoteList.route) {
            val noteViewModel: NoteViewModel = viewModel()
            val notes by noteViewModel.notes.collectAsState()

            NoteListScreen(
                notes = notes,
                onNoteClick = { note ->
                    navController.navigate(Routes.NoteEditor.createRoute(note.id))
                },
                onAddNewNoteClick = {
                    navController.navigate(Routes.NoteEditor.createRoute("new"))
                },
                onDeleteNoteClick = { note ->
                    noteViewModel.deleteNote(note.id)
                }
            )
        }
        composable(
            route = Routes.NoteEditor.route,
            arguments = listOf(navArgument(NOTE_ID_ARG) { type = NavType.StringType })
        ) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getString(NOTE_ID_ARG)
            val noteViewModel: NoteViewModel = viewModel()

            val title by noteViewModel.editorTitle.collectAsState()
            val content by noteViewModel.editorContent.collectAsState()

            // Efeito para carregar os dados da nota QUANDO a tela é aberta
            LaunchedEffect(key1 = noteId) {
                noteViewModel.loadNoteById(noteId)
            }

            NoteEditorScreen(
                title = title,
                onTitleChange = { noteViewModel.editorTitle.value = it },
                content = content,
                onContentChange = { noteViewModel.editorContent.value = it },
                onSaveClick = {
                    noteViewModel.saveNote()
                    navController.popBackStack()
                },
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}