package com.androbim.umariwenservices.listUsers

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.androbim.umariwenservices.R
import com.androbim.umariwenservices.apiIntertace.MyApinterface
import com.androbim.umariwenservices.databinding.ActivityUserListBinding
import com.androbim.umariwenservices.retrofitInstance.CallRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserListActivity : AppCompatActivity() {
    lateinit var binding: ActivityUserListBinding
    lateinit var apinterface: MyApinterface
    lateinit var progressDialog: ProgressDialog
    lateinit var arrayAdapter: ArrayAdapter<*>
    var arrayList=ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUserListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        progressDialog=ProgressDialog(this)
        progressDialog.setCancelable(false)
        apinterface=CallRetrofit.retrofitInstance().create(MyApinterface::class.java)

        loadUsersList()
        arrayAdapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList)
        binding.UserList.adapter=arrayAdapter

    }

    private fun loadUsersList() {
        progressDialog.show()
        progressDialog.setMessage("Gorest User List Loading!!!!!")

        var  call=apinterface.getUserList();
        call.enqueue(object : Callback<List<UserListModelItem>>{
            override fun onResponse(
                call: Call<List<UserListModelItem>>,
                response: Response<List<UserListModelItem>>
            ) {

                progressDialog.dismiss()
                when(response.code())
                {
                    200 ->{

                        val userListModelItem: List<UserListModelItem>? = response.body()

                        if (userListModelItem != null) {

                            for(allUsers in userListModelItem) {

                                val id=allUsers.id
                                val name=allUsers.name
                                val mail=allUsers.email
                                val status=allUsers.status
                                val gender=allUsers.gender
                                arrayList.add("$id\n$name\n$mail\n$status\n$gender")
                                arrayAdapter.notifyDataSetChanged()
                            }
                        }
                    }
                    else ->{
                        Toast.makeText(this@UserListActivity, ""+response.body(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            override fun onFailure(call: Call<List<UserListModelItem>>, t: Throwable) {

                progressDialog.dismiss()
            }

        })
    }
}