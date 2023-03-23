package com.androbim.dasswebservices.task3

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.androbim.dasswebservices.R
import com.androbim.dasswebservices.apiinterface.TaskApiInterface
import com.androbim.dasswebservices.databinding.ActivityTask3Binding
import com.androbim.dasswebservices.retrofit.CallRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Task3Activity : AppCompatActivity() {
    private lateinit var binding: ActivityTask3Binding
    lateinit var taskApiInterface: TaskApiInterface
    lateinit var arrayAdapter: ArrayAdapter<*>
    var arrayList=ArrayList<String>()
    lateinit var  progressDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityTask3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        progressDialog= ProgressDialog(this)
        progressDialog.setCancelable(false)
        taskApiInterface= CallRetrofit.retrofitInstance().create(TaskApiInterface::class.java)

        arrayAdapter= ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList)
        binding.task3List.adapter=arrayAdapter

        binding.loadList3Btn.setOnClickListener {

            arrayList.clear()
            progressDialog.show()
            progressDialog.setMessage("Loading Task3 List!!!!")

            val call=taskApiInterface.getTask3()
            call.enqueue(object : Callback<Task3Model>{
                override fun onResponse(call: Call<Task3Model>, response: Response<Task3Model>) {
                  progressDialog.dismiss()
                    when(response.code())
                    {
                        200->{
                            val movizList:List<Moviz>?= response.body()!!.moviz

                            if(movizList!=null)
                            {
                                for(allList in movizList)
                                {
                                    val value1 = allList.albumId
                                    val value2 = allList.id
                                    val value3 = allList.title
                                    //Images
                                    val value4 = allList.url
                                    val value5 = allList.thumbnailUrl

                                    arrayList.add("$value1\n$value2\n$value3\n$value4\n$value5")
                                    arrayAdapter.notifyDataSetChanged()}
                            }
                        }
                        else->{
                            Toast.makeText(this@Task3Activity, ""+response.body(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                override fun onFailure(call: Call<Task3Model>, t: Throwable) {
                    progressDialog.dismiss()
                }

            })
        }

    }
}