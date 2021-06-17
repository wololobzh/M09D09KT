package fr.eni.utilisateurauhasardkotlin

import android.util.Log
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import fr.eni.utilisateurauhasardkotlin.dao.AppDatabase
import fr.eni.utilisateurauhasardkotlin.dao.PersonneDao
import fr.eni.utilisateurauhasardkotlin.model.Personne
import kotlinx.coroutines.runBlocking
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class AppDatabaseTest {
    private lateinit var personneDao: PersonneDao
    private lateinit var db: AppDatabase
    @Before
    @Throws(Exception::class)
    fun creerBdd()
    {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).allowMainThreadQueries().build()
        personneDao = db.personneDao

        runBlocking {
            personneDao.insert(Personne(0, nom = "Terieur", prenom = "Alex"))
            personneDao.insert(Personne(0, nom ="Centrique", prenom = "Alex"))
            personneDao.insert(Personne(0, "Terieur",  "Alain"))
            personneDao.insert(Personne(0, prenom = "Alex",nom = "Aider"))
        }
    }
    @After
    fun deconnexionBdd()
    {
        db.close()
    }

    @Test
    fun insertions()
    {
        runBlocking {
            personneDao.insert(Personne(0, "Cosson", "Anthony"))
            val personne = personneDao.get(5)
            Log.d("ACOS", "personneDao.get(5) = $personne")
            assertTrue(personne.nom == "Cosson")
            assertTrue(personne.prenom == "Anthony")
        }
    }

    @Test
    fun getById()
    {
        runBlocking {
            val personne = personneDao.get(1)
            Log.d("ACOS", "personneDao.get(1) = $personne")
            assertTrue(personne.nom == "Terieur")
            assertTrue(personne.prenom == "Alex")
        }
    }

    @Test
    fun update()
    {
        val NOUVEAU_NOM = "MODIFICATION NOM"
        val NOUVEAU_PRENOM = "MODIFICATION PRENOM"

        runBlocking {
            val personneAvant = personneDao.get(2)
            personneAvant.nom = NOUVEAU_NOM
            personneAvant.prenom = NOUVEAU_PRENOM

            personneDao.update(personneAvant)

            val personneApres = personneDao.get(2)

            assertTrue(personneAvant.nom == NOUVEAU_NOM)
            assertTrue(personneAvant.prenom == NOUVEAU_PRENOM)
        }
    }

    @Test
    fun delete()
    {
        runBlocking {
            val personneAvant = personneDao.get(3)

            assertNotNull(personneAvant)

            personneDao.delete(personneAvant)
            val personneApres = personneDao.get(3)

            assertNull(personneApres)
        }
    }
}