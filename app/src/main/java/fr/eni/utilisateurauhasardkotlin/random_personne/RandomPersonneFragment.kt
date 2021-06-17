package fr.eni.utilisateurauhasardkotlin.random_personne

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import fr.eni.utilisateurauhasardkotlin.R
import fr.eni.utilisateurauhasardkotlin.dao.AppDatabase
import fr.eni.utilisateurauhasardkotlin.databinding.FragmentRandomPersonneBinding
import fr.eni.utilisateurauhasardkotlin.model.Personne
import fr.eni.utilisateurauhasardkotlin.utils.ViewModelFactory


class RandomPersonneFragment : Fragment() {
    private lateinit var binding: FragmentRandomPersonneBinding
    var test = listOf<Personne?>()

    //Permet d'indiquer que l'on va afficher un menu
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_random_personne,container,false)

        val application = requireNotNull(this.activity).application

        val randomPersonneViewModel = ViewModelProvider(this, ViewModelFactory(application)).get(RandomPersonneViewModel::class.java)

        binding.randomPersonneViewModel = randomPersonneViewModel

        randomPersonneViewModel.personnes.observe(viewLifecycleOwner, Observer {  it?.let {
            Log.i("ACOS","COUNT : " + it.count())
            test = it
        } })

        binding.btnToutLeMonde.setOnClickListener {
            test.forEach {
                Log.i("ACOS","Element $it")
            }
        }

        binding.setLifecycleOwner (this)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.mon_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_all -> {
                Navigation.findNavController(requireNotNull(view)).navigate(R.id.action_nouvellePersonneFragment_to_listePersonnesFragment)
                true
            }
            R.id.item_insert -> {
                Navigation.findNavController(requireNotNull(view)).navigate(R.id.action_nouvellePersonneFragment_to_listePersonnesFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}