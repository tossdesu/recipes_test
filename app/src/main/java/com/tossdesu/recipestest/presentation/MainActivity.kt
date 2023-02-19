package com.tossdesu.recipestest.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.tossdesu.recipestest.R
import com.tossdesu.recipestest.databinding.ActivityMainBinding
import com.tossdesu.recipestest.domain.Resource
import com.tossdesu.recipestest.presentation.adapter.RecipesAdapter
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var recipeAdapter: RecipesAdapter

    private val component by lazy {
        (application as RecipeApp).component
    }

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initAdapter()
        observeViewModel()
        binding.btnReload.setOnClickListener {
            viewModel.getRecipes()
        }
    }

    private fun initAdapter() {
        binding.rvRecipes.adapter = recipeAdapter
    }

    private fun observeViewModel() {
        viewModel.uiState.observe(this) {
            with(binding) {
                progressBar.visibility = View.GONE
                noConnectionMessage.visibility = View.GONE
                when (it) {
                    is Resource.Loading -> {
                        progressBar.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        rvRecipes.visibility = View.VISIBLE
                        recipeAdapter.submitList(it.data)
                    }
                    is Resource.NetworkError -> {
                        if (it.isCacheExists) {
                            val message = resources.getText(R.string.no_internet_connection_snackbar)
                            showErrorMessage(message.toString())
                        } else {
                            rvRecipes.visibility = View.GONE
                            noConnectionMessage.visibility = View.VISIBLE
                        }
                    }
                    is Resource.GenericError -> {
                        showErrorMessage(it.message)
                    }
                }
            }
        }
    }

    private fun showErrorMessage(message: String) {
        Snackbar.make(
            binding.root,
            message,
            Snackbar.LENGTH_INDEFINITE
        )
            .setAction(resources.getText(R.string.button_reload)) {
                viewModel.getRecipes()
            }.show()
    }
}