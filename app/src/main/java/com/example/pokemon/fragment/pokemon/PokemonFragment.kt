package com.example.pokemon.fragment.pokemon

import androidx.lifecycle.Observer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.entity.Constants.Companion.ATTACK
import com.example.entity.Constants.Companion.DEFENSE
import com.example.entity.Constants.Companion.HP
import com.example.entity.localmodels.PokemonInfo
import com.example.pokemon.R
import com.example.pokemon.fragment.pokemon.viewmodel.PokemonViewModel
import kotlinx.android.synthetic.main.fragment_pokemon.*
import org.koin.android.ext.android.get

import org.koin.androidx.viewmodel.ext.android.viewModel

class PokemonFragment : Fragment() {

    private val pokemonViewModel: PokemonViewModel by viewModel()
    private var pokemonUrl: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            pokemonUrl = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initData()
        return inflater.inflate(R.layout.fragment_pokemon, container, false)
    }

    private fun initData() {
        pokemonUrl?.run { pokemonViewModel.getPostData(this) }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
    }

    private fun initViewModel() {

        pokemonViewModel.getPokemonData.observe(
            viewLifecycleOwner,
            Observer(::initPokemonDataView)
        )
        pokemonViewModel.errorNullData.observe(viewLifecycleOwner, {
            context?.run {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        })
        pokemonViewModel.loadingData.observe(viewLifecycleOwner, { isVisible ->
            vLoadingData.visibility = View.GONE
        })
    }

    private fun initPokemonDataView(pokemon: PokemonInfo) {
        vHeight.text = pokemon.height?.run { getString(R.string.height)+this.toString() }
        vWeight.text = pokemon.weight?.run { getString(R.string.weight)+this.toString() }
        pokemon.types?.run {
            var typeName: String = getString(R.string.type)
            forEach {
                it.type?.name?.apply {
                    typeName = "$typeName $this"
                }
            }
            vType.text = typeName
        }
        pokemon.stats?.run {
            forEach {
                it.stat?.name?.apply {
                    when (this) {
                        ATTACK ->
                            vAttack.text =
                                String.format("%s %s", getString(R.string.attack), it.baseStat)
                        DEFENSE ->
                            vDefence.text =
                                String.format("%s %s", getString(R.string.defense), it.baseStat)
                        HP ->
                            vHp.text = String.format("%s %s", getString(R.string.hp), it.baseStat)
                    }
                }
            }
        }
    }

    companion object {
        private const val ARG_PARAM1 = "pokemonUrl"

        @JvmStatic
        fun newInstance(param1: String) =
            PokemonFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}




