package com.example.inventory

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.inventory.databinding.ActivityMainBinding
import com.example.inventory.fragments.ArchivePageFragment
import com.example.inventory.fragments.MainPageFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomNavBar.setOnNavigationItemReselectedListener {
            when(it.itemId){
                R.id.mainPageFragment -> setFragment(MainPageFragment())
                R.id.archivePageFragment -> setFragment(ArchivePageFragment())
                else -> setFragment(ArchivePageFragment())
            }
        }
    }

    private fun setFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainerView, fragment)
            commit()
        }
    }

}