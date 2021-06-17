package fr.eni.utilisateurauhasardkotlin.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import fr.eni.utilisateurauhasardkotlin.model.Personne

@Dao
interface PersonneDao {
    @Insert
    suspend fun insert(personne: Personne)

    @Query("SELECT * FROM Personne WHERE id = :id")
    suspend fun get(id: Long): Personne

    @Query("SELECT * FROM Personne ORDER BY nom,prenom")
    fun get(): LiveData<List<Personne?>>

    @Update
    fun update(personne: Personne)

    @Delete
    fun delete(personne: Personne)

    @Query("SELECT COUNT(*) FROM Personne")
    suspend fun count(): Long
}