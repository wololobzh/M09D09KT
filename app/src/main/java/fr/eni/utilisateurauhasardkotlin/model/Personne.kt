package fr.eni.utilisateurauhasardkotlin.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Personne (@PrimaryKey(autoGenerate = true) var id:Long = 0L,var nom:String, var prenom:String)