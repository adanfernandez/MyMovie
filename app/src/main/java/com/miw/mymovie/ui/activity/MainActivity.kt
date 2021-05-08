package com.miw.mymovie.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.miw.mymovie.R
import com.miw.mymovie.databinding.ActivityLoginBinding
import com.miw.mymovie.databinding.ActivityMainBinding
import com.miw.mymovie.model.data.storage.local.Settings
import com.miw.mymovie.ui.adapters.FilmListAdapter

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
        binding.logout.setOnClickListener { logout() }
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
        launchFragment(LATEST_FRAGMENT)
    }

    private fun logout() {
        Settings(this@MainActivity).clearAll()
        startActivity(
            Intent(
                this, LoginActivity::class.java
            )
        )
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
