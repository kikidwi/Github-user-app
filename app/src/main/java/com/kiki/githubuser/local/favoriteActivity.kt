package com.kiki.githubuser.local

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kiki.githubuser.databinding.ActivityFavoriteBinding
import com.kiki.githubuser.ui.detail.DetailUserActivity
import com.kiki.githubuser.model.user
import com.kiki.githubuser.ui.main.userAdapter
import com.kiki.githubuser.ui.viewmodel.favoriteViewModel

class favoriteActivity : AppCompatActivity() {

    private lateinit var binding : ActivityFavoriteBinding
    private lateinit var adapter : userAdapter
    private lateinit var viewModel : favoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = userAdapter()
        adapter.notifyDataSetChanged()

        viewModel = ViewModelProvider(this).get(favoriteViewModel::class.java)

        adapter.setOnItemClickCallback(object : userAdapter.OnItemClickCallback {
            override fun onItemClicked(data: user) {
                Intent(this@favoriteActivity, DetailUserActivity::class.java).also {
                    it.putExtra(DetailUserActivity.EXTRA_USERNAME, data.login)
                    it.putExtra(DetailUserActivity.EXTRA_ID, data.id)
                    it.putExtra(DetailUserActivity.EXTRA_AVATAR, data.avatar_url)
                    startActivity(it)
                }
            }

        })

        binding.apply {
            rvUser.setHasFixedSize(true)
            rvUser.layoutManager = LinearLayoutManager(this@favoriteActivity)
            rvUser.adapter = adapter
        }

        viewModel.getFavoriteUser()?.observe(this, {
            if (it!=null){
                val list = mapList(it)
                adapter.setList(list)
            }
        })
    }

    private fun mapList(users : List<favoriteUser>) : ArrayList<user> {
        val listUsers = ArrayList<user>()
        for (user in users ){
            val userMapped = user(
                user.login,
                user.id,
                user.avatar_url

            )
            listUsers.add(userMapped)
        }
        return listUsers
    }
}