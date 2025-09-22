package com.project.simplenotesapp.data

/**
 * Representa o modelo de dados de uma única nota.
 *
 * @property id O ID único do documento no Firestore.
 * @property title O título da nota.
 * @property content O conteúdo principal da nota.
 * @property userId O ID do usuário que criou a nota, para privacidade.
 */
data class Note (
    val id: String = "",
    val title: String = "",
    val content: String = "",
    val userId: String = ""
)