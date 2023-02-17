package com.tossdesu.recipestest.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.tossdesu.recipestest.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

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

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.uiState.observe(this) { uiState ->
            when(uiState) {
                is MainUiState.Loading -> {

                }
                is MainUiState.Success -> {

                }
                is MainUiState.Error -> {

                }
            }
        }
    }
}