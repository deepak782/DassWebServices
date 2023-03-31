package com.androbim.umariwenservices.searchwithname

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import com.androbim.umariwenservices.R
import com.androbim.umariwenservices.apiIntertace.MyApinterface
import com.androbim.umariwenservices.databinding.ActivitySearchWithNameBinding
import com.androbim.umariwenservices.retrofitInstance.CallRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class SearchWithNameActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchWithNameBinding
    lateinit var apinterface: MyApinterface
    lateinit var progressDialog: ProgressDialog
    var arrayList=ArrayList<String>()
    lateinit var arrayAdapter: ArrayAdapter<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySearchWithNameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        progressDialog=ProgressDialog(this)
        progressDialog.setCancelable(false)
        apinterface= CallRetrofit.retrofitInstance().create(MyApinterface::class.java)

        arrayAdapter=
            ArrayAdapter(this@SearchWithNameActivity,android.R.layout.simple_list_item_1,arrayList)
        binding.userList.adapter=arrayAdapter

        binding.btnSearch.setOnClickListener {
            searchUseristWithName()
        }

    }

    private fun searchUseristWithName() {

        arrayList.clear()
        progressDialog.show()
        progressDialog.setMessage("Loading....")

        val call=apinterface.searchWithName(binding.edtSearch.text.toString())

        call.enqueue(object :Callback<List<NameModelItem>>{
            override fun onResponse(
                call: Call<List<NameModelItem>>,
                response: Response<List<NameModelItem>>
            ) {
                progressDialog.dismiss()
                when(response.code())
                {
                    200->{
                        Log.d("TAG","response Code "+response.code())
                        val userNameList:List<NameModelItem> = response.body()!!

                        for(userData in userNameList)
                        {
                            val id=userData.id
                            val name=userData.name
                            val gender=userData.gender
                            val mail=userData.email
                            val status=userData.status

                            arrayList.add("$id\n$name\n$gender\n$mail\n$status")
                            arrayAdapter.notifyDataSetChanged()
                        }
                    }
                    201 ->{
                        Log.d("TAG","response Code "+response.code())
                    }
                    404->{
                        Log.d("TAG","response Code "+response.code())

                    }
                }
            }

            override fun onFailure(call: Call<List<NameModelItem>>, t: Throwable) {
                progressDialog.dismiss()
                Toast.makeText(this@SearchWithNameActivity, ""+t.localizedMessage, Toast.LENGTH_SHORT).show();
            }

        })
    }
}