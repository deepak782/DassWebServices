package com.androbim.dasswebservices.task1

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.CallScreeningService.CallResponse
import android.util.Log
import android.widget.Toast
import com.androbim.dasswebservices.R
import com.androbim.dasswebservices.apiinterface.TaskApiInterface
import com.androbim.dasswebservices.databinding.ActivityTask1Binding
import com.androbim.dasswebservices.retrofit.CallRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Task1Activity : AppCompatActivity() {
    private lateinit var binding: ActivityTask1Binding
    lateinit var  progressDialog: ProgressDialog
    lateinit var taskApiInterface: TaskApiInterface
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityTask1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        progressDialog= ProgressDialog(this)
        progressDialog.setCancelable(false)


        taskApiInterface=CallRetrofit.retrofitInstance().create(TaskApiInterface::class.java)

        binding.getBtn.setOnClickListener {
            progressDialog.show()
            progressDialog.setMessage("Loading!!!")

            val call=taskApiInterface.getTask1()

            call.enqueue(object : Callback<Task1Model>{
                override fun onResponse(call: Call<Task1Model>, response: Response<Task1Model>) {
                    progressDialog.dismiss()

                    if(response.isSuccessful)
                    {
                        val value1=response.body()?.userId
                        val value2=response.body()?.id
                        val value3=response.body()?.title
                        val value4=response.body()?.body

                        binding.txtResposne.text="$value1\n$value2\n$value3\n$value4"
                    }
                    else
                    {
                        Toast.makeText(this@Task1Activity, ""+response.body(), Toast.LENGTH_SHORT).show();
                    }
                }

                override fun onFailure(call: Call<Task1Model>, t: Throwable) {
                    progressDialog.dismiss()
                    Toast.makeText(this@Task1Activity, ""+t.localizedMessage, Toast.LENGTH_SHORT).show();

                }

            })
        }

    }
}