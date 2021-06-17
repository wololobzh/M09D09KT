package fr.eni.utilisateurauhasardkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.eni.utilisateurauhasardkotlin.dao.AppDatabase
import androidx.databinding.DataBindingUtil
import fr.eni.utilisateurauhasardkotlin.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)



    }


}