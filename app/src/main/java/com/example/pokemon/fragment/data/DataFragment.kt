package com.example.pokemon.fragment.data

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pokemon.R
import com.example.pokemon.fragment.data.adapter.DataAdapter
import com.example.pokemon.fragment.data.viewmodel.DataViewModel
import com.example.pokemon.fragment.pokemon.PokemonFragment
import com.example.pokemon.utils.hasNetworkAvailable
import com.example.pokemon.supportclass.PaginationScrollListener
import kotlinx.android.synthetic.main.fragment_data.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DataFragment : Fragment() {

    private lateinit var dataAdapter: DataAdapter
    private val dataViewModel: DataViewModel by viewModel()
    private var isLastPage: Boolean = false
    private var isLoading: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context?.run {
            if (this.hasNetworkAvailable()) {
                dataViewModel.getDataList()
            } else {
                dataViewModel.loadLocalData()
            }
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFragmentView()
        initViewModel()
        initClickListerner()
    }

    private fun initClickListerner() {
        var isCheckedAttack = false
        var isCheckedDefence = false
        var isCheckedHp = false
        vFiltrAttack.setOnCheckedChangeListener { buttonView, isChecked ->
            isCheckedAttack = isChecked
            dataViewModel.sortBy(isCheckedAttack, isCheckedDefence, isCheckedHp)
        }
        vFiltrDefence.setOnCheckedChangeListener { buttonView, isChecked ->
            isCheckedDefence = isChecked
            dataViewModel.sortBy(isCheckedAttack, isCheckedDefence, isCheckedHp)
        }
        vFiltrHp.setOnCheckedChangeListener { buttonView, isChecked ->
            isCheckedHp = isChecked
            dataViewModel.sortBy(isCheckedAttack, isCheckedDefence, isCheckedHp)
        }
    }

    private fun initFragmentView() {
        dataAdapter = DataAdapter(mutableListOf()) {
            context?.run {
                if (this.hasNetworkAvailable()) {

                    val specialtyFragment = PokemonFragment.newInstance(it)
                    val fragment = activity?.supportFragmentManager?.beginTransaction()
                    fragment?.replace(R.id.rootFragment, specialtyFragment)
                    fragment?.addToBackStack(null)
                    fragment?.commit()
                }else{
                    Toast.makeText(this, getString(R.string.no_internet_connection),Toast.LENGTH_SHORT).show()
                }
            }
        }
        rvData.adapter = dataAdapter
        val layoutManager = GridLayoutManager(context, 3)
        rvData.layoutManager = layoutManager
        rvData.addOnScrollListener(object : PaginationScrollListener(layoutManager) {
            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun loadMoreItems() {
                isLoading = true
                getMorePopularItemData()
            }
        })
    }

    private fun getMorePopularItemData() {
        dataViewModel.getDataList()
        vFiltrAttack.isChecked = false
        vFiltrHp.isChecked = false
        vFiltrDefence.isChecked = false
        vLoadingPaginition.visibility = View.VISIBLE
    }

    private fun initViewModel() {
        dataViewModel.getUserDataList.observe(viewLifecycleOwner, {

            dataAdapter.updateList(it)
        })

        dataViewModel.getSortedList.observe(viewLifecycleOwner, {
            dataAdapter.updateList(it)
        })
        dataViewModel.loadingData.observe(viewLifecycleOwner, { isVisible ->
            if (isVisible) {
                vLoadingData.visibility = View.VISIBLE
            } else {
                vLoadingData.visibility = View.GONE
            }
        })

        dataViewModel.errorNullData.observe(viewLifecycleOwner, {
            context?.run {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        })
        dataViewModel.finishPagination.observe(
            viewLifecycleOwner, { isFinishPagination ->
                if (isFinishPagination) {
                    vLoadingPaginition.visibility = View.VISIBLE
                    isLoading = true
                    isLastPage = true
                } else {
                    vLoadingPaginition.visibility = View.GONE
                    isLoading = false
                }
            })
    }
}