package fr.eni.utilisateurauhasardkotlin.liste_personnes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import fr.eni.utilisateurauhasardkotlin.dao.PersonneDao

class ListePersonnesViewModel(val personneDao: PersonneDao, application: Application) : AndroidViewModel(application)
{
    val personnes = personneDao.get()
}