package fr.eni.utilisateurauhasardkotlin.dao

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import fr.eni.utilisateurauhasardkotlin.model.Personne
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors

private val IO_EXECUTOR = Executors.newSingleThreadExecutor()

fun ioThread(f : () -> Unit) {
    IO_EXECUTOR.execute(f)
}

@Database(entities = arrayOf(Personne::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract val personneDao: PersonneDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "la_base_de_donnees"
                    )
                        .fallbackToDestructiveMigration()
                        .addCallback(object : RoomDatabase.Callback() {
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                super.onCreate(db)
                                runBlocking {
                                    var dao = INSTANCE?.personneDao;
                                    dao?.insert(Personne(0,"Cosson","Anthony"))
                                    dao?.insert(Personne(0,"Cosson","Nicolas"))
                                    dao?.insert(Personne(0,"Cosson","Sylvie"))
                                    dao?.insert(Personne(0,"Cosson","Dominique"))
                                    dao?.insert(Personne(0,"Cosson","Leïla"))
                                    dao?.insert(Personne(0,"Cosson","Michel"))
                                    dao?.insert(Personne(0,"Cosson","Claudine"))
                                }
                            }
                        })
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}