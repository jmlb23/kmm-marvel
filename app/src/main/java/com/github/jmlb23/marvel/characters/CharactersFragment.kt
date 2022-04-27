package com.github.jmlb23.marvel.characters

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.jmlb23.marvel.databinding.FragmentCharactersBinding
import com.github.jmlb23.marvel.domain.entity.Character
import com.github.jmlb23.mvvm.CharactersViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.stateViewModel

class CharactersFragment : Fragment() {


    private val viewmodel by stateViewModel<CharactersViewModel>()
    private val adapter =
        CharactersAdapter().apply { setOnItemClickListener(::onItemClickListener) }


    private fun onItemClickListener(character: Character) {
        findNavController().navigate(
            CharactersFragmentDirections.actionCharactersFragmentToCharacterFragment(
                character.id
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentCharactersBinding.inflate(layoutInflater, container, false)
            .apply {
                charactersRv.setAdapter(adapter)
                charactersRv.setLayoutManager(LinearLayoutManager(context).apply {
                    orientation = LinearLayoutManager.VERTICAL
                })
                charactersRv.addVeiledItems(15)
                if (adapter.itemCount == 0) lifecycleScope.launchWhenCreated {
                    viewmodel.nextPage()
                    charactersRv.veil()
                }
                charactersRv.getRecyclerView()
                    .addOnScrollListener(object : RecyclerView.OnScrollListener() {
                        override fun onScrollStateChanged(
                            recyclerView: RecyclerView,
                            newState: Int
                        ) {
                            if (!recyclerView.canScrollVertically(1)) {
                                viewmodel.nextPage()
                                loader.isVisible = true
                            }
                        }
                    })

                viewmodel.elements.onEach {
                    adapter.addElements(it)
                    if (it.isEmpty())
                        lifecycleScope.launch(Dispatchers.Main) {
                            delay(2000)
                            charactersRv.unVeil()
                        }
                    loader.isVisible = false
                }.launchIn(lifecycleScope)

                viewmodel.error.filterNotNull().onEach {
                    Log.e("Error", it)
                    Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
                    loader.isVisible = false
                }.launchIn(lifecycleScope)
            }.root
    }

}