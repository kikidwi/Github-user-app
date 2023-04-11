package com.kiki.githubuser.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kiki.githubuser.R
import com.kiki.githubuser.ui.detail.DetailUserActivity
import com.kiki.githubuser.databinding.ActivityMainBinding
import com.kiki.githubuser.local.favoriteActivity
import com.kiki.githubuser.model.user
import com.kiki.githubuser.theme.*
import com.kiki.githubuser.ui.viewmodel.MainViewModel
import com.kiki.githubuser.ui.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel : MainViewModel
    private lateinit var adapter : userAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        adapter = userAdapter()
        adapter.notifyDataSetChanged()


        adapter.setOnItemClickCallback(object : userAdapter.OnItemClickCallback {
            override fun onItemClicked(data: user) {
                Intent(this@MainActivity, DetailUserActivity::class.java).also {
                    it.putExtra(DetailUserActivity.EXTRA_USERNAME, data.login)
                    it.putExtra(DetailUserActivity.EXTRA_ID, data.id)
                    it.putExtra(DetailUserActivity.EXTRA_AVATAR, data.avatar_url)
                    startActivity(it)
                }
            }

        })

        val pref = SettingPreferences.getInstance(dataStore)
        val mainViewModel = ViewModelProvider(this, ViewModelFactory(pref)).get(
            MainViewModel::class.java
        )
        mainViewModel.getThemeSettings().observe(this) { isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            MainViewModel::class.java)
        setLoading(true)
        viewModel.setSearchUser("kikidwi")
        binding.apply {
            rv.layoutManager = LinearLayoutManager(this@MainActivity)
            rv.setHasFixedSize(true)
            rv.adapter = adapter

            icon.setOnClickListener{
                search()
            }

            query.setOnKeyListener { v, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER){
                    search()
                    return@setOnKeyListener(true)
                }
                return@setOnKeyListener(false)
            }
        }

        viewModel.getSearchUser().observe(this, {
            if (it!=null){
                adapter.setList(it)
                setLoading(false)
            }
        })
    }
    private fun search(){
        binding.apply {
            val query = query.text.toString()
            if (query.isEmpty()) return
            setLoading(true)
            viewModel.setSearchUser(query)
        }
    }

    private fun setLoading(state : Boolean){
        if (state){
            binding.loading.visibility = View.VISIBLE
        }else{
            binding.loading.visibility = View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.favorite_menu){
            Intent(this, favoriteActivity::class.java).also {
                startActivity(it)
            }
        }else if (item.itemId == R.id.setting_theme){
            Intent(this, ThemeActivity::class.java).also {
                startActivity(it)
            }
        }
        return super.onOptionsItemSelected(item)
    }


}