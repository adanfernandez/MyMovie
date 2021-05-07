package com.miw.mymovie.activity

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.miw.mymovie.R
import com.miw.mymovie.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), LatestFragment.OnLatestFragmentInteractionListener,
    FavouritesFragment.OnFavouritesFragmentInteractionListener {

    private lateinit var binding: ActivityMainBinding

    companion object {
        private const val LATEST_FRAGMENT = 0
        private const val FAVOURITES_FRAGMENT = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initialize()
    }

    private fun initialize() {
        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_item_latest_fragment -> launchFragment(LATEST_FRAGMENT)
                R.id.bottom_item_favourites_fragment -> launchFragment(FAVOURITES_FRAGMENT)
            }
            true
        }

    }


    private fun launchFragment(position: Int) {
        val fragment = when (position) {
            LATEST_FRAGMENT -> {
                binding.bottomNavigation.apply {
                    menu.findItem(R.id.bottom_item_latest_fragment).isChecked = true
                    //getOrCreateBadge(R.id.bottom_item_latest_fragment).isVisible = false
                    //getOrCreateBadge(R.id.bottom_item_favourites_fragment).isVisible = true
                }
                LatestFragment()
            }
            else -> {
                binding.bottomNavigation.apply {
                    menu.findItem(R.id.bottom_item_favourites_fragment).isChecked = true
                    //getOrCreateBadge(R.id.bottom_item_latest_fragment).isVisible = true
                    //getOrCreateBadge(R.id.bottom_item_favourites_fragment).isVisible = false
                }
                FavouritesFragment()
            }
        }
        supportFragmentManager.beginTransaction().apply {
            replace(binding.fragmentContainer.id, fragment)
            commit()
        }
    }

    override fun onNextButtonClick() {
        launchFragment(LATEST_FRAGMENT)
    }

    override fun onBackButtonClick() {
        launchFragment(FAVOURITES_FRAGMENT)
    }
}
