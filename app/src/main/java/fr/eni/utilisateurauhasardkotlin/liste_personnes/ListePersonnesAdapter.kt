package fr.eni.utilisateurauhasardkotlin.liste_personnes

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import fr.eni.utilisateurauhasardkotlin.databinding.ItemPersonneBinding
import fr.eni.utilisateurauhasardkotlin.model.Personne


class ListPersonnesAdapter(val clickListener: PersonneListener)  : ListAdapter<Personne,
        ListPersonnesAdapter.ViewHolder>(PersonneDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(clickListener,item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ItemPersonneBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(clickListener: PersonneListener,item: Personne) {
            Log.i("ACOS","BIND")
            binding.personne = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemPersonneBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
}

class PersonneDiffCallback : DiffUtil.ItemCallback<Personne>() {
    override fun areItemsTheSame(oldItem: Personne, newItem: Personne): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Personne, newItem: Personne): Boolean {
        return oldItem == newItem
    }
}

class PersonneListener(val clickListener: (personneId: Long) -> Unit) {
    fun onClick(personne: Personne) = clickListener(personne.id)
}