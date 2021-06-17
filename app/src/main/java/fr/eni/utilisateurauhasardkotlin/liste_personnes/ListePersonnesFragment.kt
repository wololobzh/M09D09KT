package fr.eni.utilisateurauhasardkotlin.liste_personnes

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import fr.eni.utilisateurauhasardkotlin.R
import fr.eni.utilisateurauhasardkotlin.databinding.FragmentListePersonnesBinding
import fr.eni.utilisateurauhasardkotlin.random_personne.RandomPersonneViewModel
import fr.eni.utilisateurauhasardkotlin.utils.ViewModelFactory

class ListePersonnesFragment : Fragment() {

    /*
     * Permet d'indiquer que l'on va afficher un menu
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentListePersonnesBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_liste_personnes, container, false)

        val application = requireNotNull(this.activity).application

        val listePersonnesViewModel = ViewModelProvider(this, ViewModelFactory(application)).get(
            ListePersonnesViewModel::class.java)

        binding.listePersonnesViewModel = listePersonnesViewModel

        binding.lifecycleOwner = this

        val adapter = ListPersonnesAdapter(PersonneListener { personneId ->
            Toast.makeText(context, "${personneId}", Toast.LENGTH_LONG).show()
        })
        binding.rvPersonnes.adapter = adapter

        listePersonnesViewModel.personnes.observe(viewLifecycleOwner, Observer {  it?.let {
            Log.i("ACOS","COUNT : " + it.count())
            adapter.submitList(it)
        } })

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.mon_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_all -> {
                true
            }
            R.id.item_insert -> {
                Navigation.findNavController(requireNotNull(view)).navigate(R.id.action_listePersonnesFragment_to_nouvellePersonneFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}