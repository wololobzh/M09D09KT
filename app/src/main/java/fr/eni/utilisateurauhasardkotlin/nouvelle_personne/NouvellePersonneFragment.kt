package fr.eni.utilisateurauhasardkotlin.nouvelle_personne

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import fr.eni.utilisateurauhasardkotlin.R
import fr.eni.utilisateurauhasardkotlin.databinding.FragmentNouvellePersonneBinding
import fr.eni.utilisateurauhasardkotlin.databinding.FragmentRandomPersonneBinding
import fr.eni.utilisateurauhasardkotlin.model.Personne
import fr.eni.utilisateurauhasardkotlin.random_personne.RandomPersonneViewModel
import fr.eni.utilisateurauhasardkotlin.utils.ViewModelFactory


class NouvellePersonneFragment : Fragment() {
    private lateinit var binding: FragmentNouvellePersonneBinding

    //Permet d'indiquer que l'on va afficher un menu
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_nouvelle_personne,container,false)

        val application = requireNotNull(this.activity).application

        val nouvellePersonneViewModel = ViewModelProvider(this, ViewModelFactory(application)).get(
            NouvellePersonneViewModel::class.java)

        binding.nouvellePersonneViewModel = nouvellePersonneViewModel

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
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}