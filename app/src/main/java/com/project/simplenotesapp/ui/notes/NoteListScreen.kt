package com.project.simplenotesapp.ui.notes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.simplenotesapp.data.Note

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteListScreen(
    notes: List<Note>,
    onNoteClick: (Note) -> Unit,
    onAddNewNoteClick: () -> Unit,
    onDeleteNoteClick: (Note) -> Unit // 1. Passe a função para o NoteListScreen
) {
    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text("Notes") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddNewNoteClick) {
                Icon(Icons.Default.Add, contentDescription = "Adicionar nova nota")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(8.dp)
        ) {
            items(notes) { note ->
                NoteItem(
                    note = note,
                    onClick = { onNoteClick(note) },
                    onDeleteClick = { onDeleteNoteClick(note) } // 2. Passe a função para o NoteItem
                )
            }
        }
    }
}

@Composable
fun NoteItem(
    note: Note,
    onClick: () -> Unit,
    onDeleteClick: () -> Unit // Nova função para o clique de deletar
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick), // O card inteiro ainda é clicável para editar
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween // Alinha os itens nas pontas
        ) {
            // Coluna com o título e conteúdo (ocupa o espaço disponível)
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = note.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = note.content,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }

            // Espaçador para garantir uma distância
            Spacer(modifier = Modifier.width(16.dp))

            // Ícone de lixeira
            IconButton(onClick = onDeleteClick) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Deletar Nota",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NoteListScreenPreview() {
    val sampleNotes = listOf(
        Note(title = "Nota 1", content = "Conteúdo da nota 1"),
        Note(title = "Nota 2", content = "Conteúdo da nota 2"),
        Note(title = "Nota 3", content = "Conteúdo da nota 3")
    )
    NoteListScreen(notes = sampleNotes, onNoteClick = {}, onAddNewNoteClick = {}, onDeleteNoteClick = {})
}