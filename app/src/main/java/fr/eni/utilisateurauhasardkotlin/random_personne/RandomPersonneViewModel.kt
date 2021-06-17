package fr.eni.utilisateurauhasardkotlin.random_personne

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import fr.eni.utilisateurauhasardkotlin.dao.AppDatabase
import fr.eni.utilisateurauhasardkotlin.dao.PersonneDao
import fr.eni.utilisateurauhasardkotlin.model.Personne
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.*
import java.util.concurrent.ThreadLocalRandom
import kotlin.random.Random.Default.nextInt

class RandomPersonneViewModel(val personneDao:PersonneDao, application: Application) : AndroidViewModel(application) {
    val personne = MutableLiveData<Personne>();
    val personnes = personneDao.get()

    init {
        viewModelScope.launch {
            personne.value = personneDao.get(1)
        }
    }

    fun randomPersonne() {
        viewModelScope.launch {
            randomFromDatabase()
        }
    }

    private suspend fun randomFromDatabase() {
        var count = personneDao.count()
        var id = ThreadLocalRandom.current().nextLong(1, count)
        var hasard = personneDao.get(id)
        personne.value = hasard
    }




}