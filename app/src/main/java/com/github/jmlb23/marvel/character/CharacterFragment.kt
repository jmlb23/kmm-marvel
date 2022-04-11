package com.github.jmlb23.marvel.character

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import coil.load
import com.github.jmlb23.marvel.databinding.FragmentCharacterBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.stateViewModel

class CharacterFragment : Fragment() {

    private val viewModel by stateViewModel<CharacterViewModel>()
    private val adapter = ComicsAdapter()
    private val args by navArgs<CharacterFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return FragmentCharacterBinding.inflate(inflater, container, false).apply {
            comics.adapter = adapter
            veilLayout.startShimmer()
            description.movementMethod = ScrollingMovementMethod()
            viewModel.getDetail(args.id)
            viewModel.errorMessage.onEach {
                Log.e("Error", it)
            }.launchIn(lifecycleScope)
            viewModel.detail.onEach {
                description.text = it.description
                name.text = it.name
                image.load(it.image)
                adapter.addAll(it.comics)
                veilLayout.stopShimmer()
            }.launchIn(lifecycleScope)
        }.root
    }

}