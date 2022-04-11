package com.github.jmlb23.marvel.character

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.github.jmlb23.marvel.basicDiffUtil
import com.github.jmlb23.marvel.databinding.ComicItemLayoutBinding
import com.github.jmlb23.marvel.domain.entity.Comic

class ComicsAdapter : RecyclerView.Adapter<ComicsAdapter.ComicsViewHolder>() {

	private var elements by basicDiffUtil(emptyList<Comic>(), areItemsTheSame = { old, new -> old.name == new.name })


	fun addAll(elements: Iterable<Comic>) {
		this.elements = elements.toList()
	}

	fun clear() {
		this.elements = emptyList()
	}

	data class ComicsViewHolder(val binding: ComicItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
		fun bind(comic: Comic) {
			binding.image.load(comic.thumbnail)
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsViewHolder =
		ComicItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false).let(::ComicsViewHolder)

	override fun onBindViewHolder(holder: ComicsViewHolder, position: Int) {
		holder.bind(elements[position])
	}

	override fun getItemCount(): Int = elements.size
}