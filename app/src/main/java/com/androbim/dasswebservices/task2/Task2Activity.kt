package com.androbim.dasswebservices.task2

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.androbim.dasswebservices.R
import com.androbim.dasswebservices.apiinterface.TaskApiInterface
import com.androbim.dasswebservices.databinding.ActivityTask2Binding
import com.androbim.dasswebservices.retrofit.CallRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Task2Activity : AppCompatActivity() {
    private lateinit var binding: ActivityTask2Binding
    lateinit var taskApiInterface: TaskApiInterface
    lateinit var arrayAdapter: ArrayAdapter<*>
    var arrayList=ArrayList<String>()
    lateinit var  progressDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityTask2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        progressDialog= ProgressDialog(this)
        progressDialog.setCancelable(false)
        taskApiInterface=CallRetrofit.retrofitInstance().create(TaskApiInterface::class.java)

        arrayAdapter= ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList)
        binding.task2List.adapter=arrayAdapter

        binding.loadListBtn.setOnClickListener {
            arrayList.clear()
            progressDialog.show()
            progressDialog.setMessage("List is Loading!!")

            val call=taskApiInterface.getTask2()

            call.enqueue(object : Callback<List<Task2ModelItem>>{
                override fun onResponse(call: Call<List<Task2ModelItem>>, response: Response<List<Task2ModelItem>>
                ) {
                    progressDialog.dismiss()

                    when(response.code())
                    {
                        200->{
                            val listTask2ModelItem: List<Task2ModelItem> ?= response.body()

                            if(listTask2ModelItem != null)
                            {

                                for(allListItem in listTask2ModelItem)
                                {
                                    val value1 = allListItem.albumId
                                    val value2 = allListItem.id
                                    val value3 = allListItem.title
                                    //Images
                                    val value4 = allListItem.url
                                    val value5 = allListItem.thumbnailUrl

                                    arrayList.add("$value1\n$value2\n$value3\n$value4\n$value5")
                                    arrayAdapter.notifyDataSetChanged()

                                }

                            }
                        }
                        else ->{
                            Toast.makeText(this@Task2Activity, ""+response.body(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                override fun onFailure(call: Call<List<Task2ModelItem>>, t: Throwable) {
                    progressDialog.dismiss()
                }

            })
        }


    }
}