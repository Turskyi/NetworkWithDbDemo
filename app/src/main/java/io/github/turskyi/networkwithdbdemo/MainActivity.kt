package io.github.turskyi.networkwithdbdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import io.github.turskyi.networkwithdbdemo.databinding.ActivityMainBinding
import io.github.turskyi.networkwithdbdemo.features.restaurants.RestaurantAdapter
import io.github.turskyi.networkwithdbdemo.features.restaurants.RestaurantViewModel
import io.github.turskyi.networkwithdbdemo.common.Resource

// for the sake of simplicity, MainActivity will hold the main screen without creating a fragment
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: RestaurantViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val restaurantAdapter = RestaurantAdapter()

        binding.apply {
            recyclerView.apply {
                adapter = restaurantAdapter
                layoutManager = LinearLayoutManager(this@MainActivity)
            }

            viewModel.restaurants.observe(this@MainActivity) { result ->
                restaurantAdapter.submitList(result.data)

                progressBar.isVisible = result is Resource.Loading && result.data.isNullOrEmpty()
                textViewError.isVisible = result is Resource.Error && result.data.isNullOrEmpty()
                textViewError.text = result.error?.localizedMessage
            }
        }
    }
}