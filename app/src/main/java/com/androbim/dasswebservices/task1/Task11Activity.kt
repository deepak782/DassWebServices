package com.androbim.dasswebservices.task1

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.androbim.dasswebservices.R
import com.androbim.dasswebservices.apiinterface.TaskApiInterface
import com.androbim.dasswebservices.databinding.ActivityTask11Binding
import com.androbim.dasswebservices.retrofit.CallRetrofit
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Task11Activity : AppCompatActivity() {
    private lateinit var binding: ActivityTask11Binding
    lateinit var progressDialog: ProgressDialog
    lateinit var taskApiInterface: TaskApiInterface
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityTask11Binding.inflate(layoutInflater)
        setContentView(binding.root)

        progressDialog= ProgressDialog(this)
        progressDialog.setCancelable(false)
        taskApiInterface=CallRetrofit.retrofitInstance().create(TaskApiInterface::class.java)

        binding.task11Btn.setOnClickListener {

            progressDialog.show()
            progressDialog.setMessage("loading!!")

            val call=taskApiInterface.getTask11()

            call.enqueue(object :Callback<Task11Model>{
                override fun onResponse(call: Call<Task11Model>, response: Response<Task11Model>) {
                    progressDialog.dismiss()

                    when(response.code())
                    {
                        200 ->{

                            val name=response.body()?.StudentName
                            val mobile=response.body()?.StudentMobile
                            val course=response.body()?.StudentCourse
                            val image=response.body()?.StudentImage

                            Glide.with(this@Task11Activity)
                                .load(image)
                                .centerCrop()
                                .placeholder(R.drawable.ic_launcher_background)
                                .into(binding.imgResponse);
                            binding.txtResponse.text="$name\n\n$mobile\n\n$course\n\n$image"
                        }
                        else ->{
                            Toast.makeText(this@Task11Activity, ""+response.body(), Toast.LENGTH_SHORT).show();
                        }
                    }


                }

                override fun onFailure(call: Call<Task11Model>, t: Throwable) {
                    progressDialog.dismiss()
                }

            })
        }
    }
}