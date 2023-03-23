package com.androbim.umariwenservices

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.androbim.umariwenservices.createuser.CreateRecordActivity
import com.androbim.umariwenservices.databinding.ActivityMainBinding
import com.androbim.umariwenservices.listUsers.UserListActivity
import com.androbim.umariwenservices.updateuser.UpdateRecordActivity

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.listUser.setOnClickListener {

            startActivity(Intent(this@MainActivity,UserListActivity::class.java))
        }
        binding.CreateUsers.setOnClickListener {

            startActivity(Intent(this@MainActivity,CreateRecordActivity::class.java))
        }
        binding.UpdateUsers.setOnClickListener {

            startActivity(Intent(this@MainActivity,UpdateRecordActivity::class.java))
        }
    }
}