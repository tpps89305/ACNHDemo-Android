package com.dispy.acnhdemo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.dispy.acnhdemo.R
import com.dispy.acnhdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_dashboard -> {
                    val navGraph = navController.navInflater.inflate(R.navigation.nav_graph)
                    navGraph.setStartDestination(R.id.dashboardFragment)
                    navController.graph = navGraph
                }
                R.id.nav_villagers -> {
                    val navGraph = navController.navInflater.inflate(R.navigation.nav_graph)
                    navGraph.setStartDestination(R.id.villagersFragment)
                    navController.graph = navGraph
                }
                R.id.nav_catalog -> {
                    val navGraph = navController.navInflater.inflate(R.navigation.nav_graph)
                    navGraph.setStartDestination(R.id.catalogFragment)
                    navController.graph = navGraph
                }
            }
            true
        }
        binding.bottomNavigationView.itemIconTintList = null

    }
}