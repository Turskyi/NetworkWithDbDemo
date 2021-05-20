package io.github.turskyi.networkwithdbdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import io.github.turskyi.networkwithdbdemo.databinding.ActivityMainBinding
import io.github.turskyi.networkwithdbdemo.features.restaurants.RestaurantAdapter
import io.github.turskyi.networkwithdbdemo.features.restaurants.RestaurantViewModel

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

            viewModel.restaurants.observe(this@MainActivity) { restaurants ->
                restaurantAdapter.submitList(restaurants)
            }
        }
    }
}