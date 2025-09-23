package com.project.simplenotesapp.ui.notes

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.simplenotesapp.data.Note
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class NoteViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes = _notes.asStateFlow()

    private val _selectedNoteId = MutableStateFlow<String?>(null)
    val editorTitle = MutableStateFlow("")
    val editorContent = MutableStateFlow("")

    init {
        loadNotes()
    }

    private fun loadNotes() {
        val userId = auth.currentUser?.uid ?: return
        firestore.collection("notes")
            .whereEqualTo("userId", userId)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    Log.e("NoteViewModel", "Error loading notes", error)
                    return@addSnapshotListener
                }
                snapshot?.let {
                    _notes.value = it.documents.mapNotNull { doc ->
                        Note(
                            id = doc.id,
                            title = doc.getString("title") ?: "",
                            content = doc.getString("content") ?: "",
                            userId = doc.getString("userId") ?: ""
                        )
                    }
                }
            }
    }

    fun loadNoteById(noteId: String?) {
        if (noteId.isNullOrBlank() || noteId == "new") {
            _selectedNoteId.value = null
            editorTitle.value = ""
            editorContent.value = ""
            return
        }

        viewModelScope.launch {
            try {
                val document = firestore.collection("notes").document(noteId).get().await()
                if (document != null && document.exists()) {
                    _selectedNoteId.value = noteId
                    editorTitle.value = document.getString("title") ?: ""
                    editorContent.value = document.getString("content") ?: ""
                }
            } catch (e: Exception) {
                Log.e("NoteViewModel", "Error loading note by ID", e)
                _selectedNoteId.value = null
                editorTitle.value = ""
                editorContent.value = ""
            }
        }
    }

    fun saveNote() {
        val userId = auth.currentUser?.uid ?: return
        val currentTitle = editorTitle.value
        val currentContent = editorContent.value

        viewModelScope.launch {
            val noteData = hashMapOf(
                "title" to currentTitle,
                "content" to currentContent,
                "userId" to userId
            )

            val selectedId = _selectedNoteId.value

            // Usamos isNullOrBlank para uma verificação mais segura
            if (selectedId.isNullOrBlank()) {
                // Cria uma nova nota
                firestore.collection("notes").add(noteData).await()
            } else {
                // Atualiza uma nota existente
                firestore.collection("notes").document(selectedId).set(noteData).await()
            }
        }
    }

    fun deleteNote(noteId: String) {
        //verificação de segurança para garantir que o ID não esteja vazio
        if (noteId.isBlank()) {
            Log.e("NoteViewModel", "Tentativa com nota inexistente no banco de dados")
            return
        }

        viewModelScope.launch {
            try {
                firestore.collection("notes")
                    .document(noteId)
                    .delete()
                    .await()
            } catch (e: Exception) {
                Log.e("NoteViewModel", "Error deleting note", e)
            }
        }
    }
}