package br.com.fiap.carbontrack.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val nome: String, // Novo campo
    val email: String,
    val password: String
)