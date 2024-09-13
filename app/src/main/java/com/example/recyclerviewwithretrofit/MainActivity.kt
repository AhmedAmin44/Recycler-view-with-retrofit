package com.example.recyclerviewwithretrofit

import UserAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewwithretrofit.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        fetchUsers()
    }

    private fun fetchUsers() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val users = RetrofitInstance.api.getUsers()
                withContext(Dispatchers.Main) {
                    userAdapter = UserAdapter(users)
                    recyclerView.adapter = userAdapter
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
