package fr.eni.utilisateurauhasardkotlin.utils

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.eni.utilisateurauhasardkotlin.dao.AppDatabase
import fr.eni.utilisateurauhasardkotlin.liste_personnes.ListePersonnesFragment
import fr.eni.utilisateurauhasardkotlin.liste_personnes.ListePersonnesViewModel
import fr.eni.utilisateurauhasardkotlin.nouvelle_personne.NouvellePersonneFragment
import fr.eni.utilisateurauhasardkotlin.nouvelle_personne.NouvellePersonneViewModel
import fr.eni.utilisateurauhasardkotlin.random_personne.RandomPersonneFragment
import fr.eni.utilisateurauhasardkotlin.random_personne.RandomPersonneViewModel
import java.lang.IllegalArgumentException


/**
 * L'injection de dépendance c'est séparer l'utilisation de la configuration.
 * Ici on s'occupe de la configuration
 */
class ViewModelFactory(private val application: Application) : ViewModelProvider.Factory {


    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RandomPersonneViewModel::class.java)) {
            var personneDao = AppDatabase.getInstance(application).personneDao
            return RandomPersonneViewModel(
                personneDao,
                application
            ) as T
        }
        if (modelClass.isAssignableFrom(NouvellePersonneViewModel::class.java)) {
            var personneDao = AppDatabase.getInstance(application).personneDao
            return NouvellePersonneViewModel(
                personneDao,
                application
            ) as T
        }
        if (modelClass.isAssignableFrom(ListePersonnesViewModel::class.java)) {
            var personneDao = AppDatabase.getInstance(application).personneDao
            return ListePersonnesViewModel(
                personneDao,
                application
            ) as T
        }
        throw IllegalArgumentException("ViewModel inconnu")
    }
}