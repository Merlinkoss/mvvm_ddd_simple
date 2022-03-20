package com.demo.anton_kondratiuk.features.countryList

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.anton_kondratiuk.R
import com.demo.anton_kondratiuk.databinding.ActivityCoutriesListBinding
import com.demo.anton_kondratiuk.di.DI
import com.demo.anton_kondratiuk.di.DI.COUNTRY_LIST_SCOPE
import com.demo.anton_kondratiuk.di.utils.ViewModelFactory
import com.demo.anton_kondratiuk.features.extended.ExtendedInfoActivity
import com.demo.anton_kondratiuk.models.Event
import com.google.android.material.snackbar.Snackbar
import toothpick.Toothpick

class CountryListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCoutriesListBinding
    private lateinit var viewModel: CountryListViewModel
    private val viewModelFactory = ViewModelFactory()

    private val errorMessage by lazy { getString(R.string.error_message_try_again) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCoutriesListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initDI()
        bindCountryList()
        bindOnClickListeners()
        observeState()
    }

    private fun initDI() {
        Toothpick.openScope(DI.MAIN_SCOPE)
                .openSubScope(COUNTRY_LIST_SCOPE)
                .installModules(CountryListModule())
                .inject(this)

        viewModel = ViewModelProvider(this, viewModelFactory).get(CountryListViewModel::class.java)
    }

    private fun bindCountryList() {
        with(binding.countryRecyclerView) {
            adapter = CountryListAdapter {
                startActivity(ExtendedInfoActivity.newInstance(context, it))
            }
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun bindOnClickListeners() {
        binding.countrySearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.searchByName(newText)
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.searchByName(query)
                return true
            }
        })
    }

    private fun observeState() {
        viewModel.state.observe(this) { state ->

            when (state) {
                is Event.Error<CountryListState> -> {
                    binding.countryLoadingView.hide()
                    (binding.countryRecyclerView.adapter as CountryListAdapter).submitList(emptyList())
                    Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_SHORT).show()
                }
                is Event.Complete<CountryListState> -> {
                    binding.countryLoadingView.hide()
                    (binding.countryRecyclerView.adapter as CountryListAdapter).submitList(state.data.countries)
                }
                is Event.Loading -> binding.countryLoadingView.show()
            }
        }

    }

}