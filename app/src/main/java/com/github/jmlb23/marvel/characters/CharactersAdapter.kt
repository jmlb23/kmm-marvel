package com.github.jmlb23.marvel.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.github.jmlb23.marvel.basicDiffUtil
import com.github.jmlb23.marvel.databinding.HeroeItemLayoutBinding
import com.github.jmlb23.marvel.domain.entity.Character
import java.util.logging.Logger

class CharactersAdapter : RecyclerView.Adapter<CharactersAdapter.CharactersViewHolder>() {
	private var elements by basicDiffUtil(emptyList<Character>(), areItemsTheSame = { old, new -> old.id == new.id })
	private var listener: (Character) -> Unit = {}
	fun addElements(elemenst: Iterable<Character>) {
		elements = elemenst.toList()
	}

	fun clear() {
		elements = emptyList()
	}

	fun setOnItemClickListener(listener: (Character) -> Unit) {
		this.listener = listener
	}

	data class CharactersViewHolder(val binding: HeroeItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
		fun bind(item: Character) {
			binding.name.text = item.name
			binding.description.text = item.description
			binding.avatar.load(item.image)
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
		return HeroeItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
			.let(::CharactersViewHolder)
	}

	override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
		val item = elements[position]
		holder.apply {
			binding.root.setOnClickListener {
				listener(item)
			}
		}.bind(item)
	}

	override fun getItemCount(): Int =
		elements.size

}