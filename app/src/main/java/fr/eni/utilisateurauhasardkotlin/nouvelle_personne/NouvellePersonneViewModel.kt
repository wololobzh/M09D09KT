package fr.eni.utilisateurauhasardkotlin.nouvelle_personne

import android.app.Application
import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import fr.eni.utilisateurauhasardkotlin.dao.AppDatabase
import fr.eni.utilisateurauhasardkotlin.dao.PersonneDao
import fr.eni.utilisateurauhasardkotlin.model.Personne
import kotlinx.coroutines.launch

class NouvellePersonneViewModel(val personneDao: PersonneDao, application: Application) : AndroidViewModel(application) {

    var nom:String = ""
    var prenom:String = "";

    fun enregistrer() {
        viewModelScope.launch {
            Log.i("ACOS","nom : " + nom)
            Log.i("ACOS","prenom : " + prenom)
            personneDao.insert(Personne(0L,nom,prenom))
        }
    }


}