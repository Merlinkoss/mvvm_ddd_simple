package com.demo.anton_kondratiuk.features.extended

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.demo.anton_kondratiuk.R
import com.demo.anton_kondratiuk.databinding.ActivityExtenededInfoBinding
import com.demo.anton_kondratiuk.di.DI
import com.demo.anton_kondratiuk.di.utils.ViewModelFactory
import com.demo.anton_kondratiuk.models.CountryUIModel
import com.demo.anton_kondratiuk.models.Event
import com.google.android.material.snackbar.Snackbar
import toothpick.Toothpick

class ExtendedInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExtenededInfoBinding
    private lateinit var viewModel: ExtendedInfoViewModel

    private val errorMessage by lazy { getString(R.string.error_message_back_and_try_again) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityExtenededInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initToolbar()
        initDI()
        observeState()
        init(intent.getParcelableExtra(COUNTRY_ARGS))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initDI() {
        Toothpick.openScope(DI.MAIN_SCOPE)
                .openSubScope(DI.COUNTRY_EXTENDED_SCOPE)
                .inject(this)

        viewModel = ViewModelProvider(this, ViewModelFactory()).get(ExtendedInfoViewModel::class.java)
    }

    private fun initToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it
        }
    }

    private fun init(countryModel: CountryUIModel?) {
        viewModel.init(countryModel)
    }

    private fun observeState() {
        viewModel.state.observe(this) { state ->

            when (state) {
                is Event.Error<ExtendedInfoState> -> {
                    Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_SHORT).show()
                }
                is Event.Complete<ExtendedInfoState> -> {
                    bindState(state.data)
                }
                is Event.Loading -> {

                }
            }
        }
    }

    private fun bindState(extendedInfoState: ExtendedInfoState) {
        supportActionBar?.title = extendedInfoState.country.name
        binding.countryName.text = extendedInfoState.country.name
        binding.capitalName.text = extendedInfoState.country.capital
        extendedInfoState.country.flagUrl?.let {
            Glide
                    .with(this)
                    .load(it)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .into(binding.countryFlag)
        }
    }

    companion object {

        private const val COUNTRY_ARGS = "COUNTRY_ARGS"

        fun newInstance(context: Context, countryModel: CountryUIModel) = Intent(context, ExtendedInfoActivity::class.java).apply {
            putExtra(COUNTRY_ARGS, countryModel)
        }

    }

}