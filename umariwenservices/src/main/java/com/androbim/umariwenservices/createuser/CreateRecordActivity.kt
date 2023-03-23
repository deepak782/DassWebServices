package com.androbim.umariwenservices.createuser

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.androbim.umariwenservices.R
import com.androbim.umariwenservices.apiIntertace.MyApinterface
import com.androbim.umariwenservices.databinding.ActivityCreateRecordBinding
import com.androbim.umariwenservices.retrofitInstance.CallRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateRecordActivity : AppCompatActivity() {
    private  lateinit var  binding: ActivityCreateRecordBinding
    lateinit var apinterface: MyApinterface
    lateinit var progressDialog: ProgressDialog
    lateinit var nameString: String
    lateinit var mailString: String
    lateinit var genderString: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCreateRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        progressDialog=ProgressDialog(this)
        progressDialog.setCancelable(false)
        apinterface= CallRetrofit.retrofitInstance().create(MyApinterface::class.java)

        binding.submitBtn.setOnClickListener {
            createUser()
        }
    }

    private fun createUser() {
        progressDialog.show()
        progressDialog.setMessage("Creating User!!!")

        nameString=binding.name.text.toString()
        mailString=binding.mail.text.toString()
        genderString=binding.gender.text.toString()

        var userParameters=UserParameters(nameString,mailString,genderString,"active")

        val call=apinterface.createUser(userParameters)

        call.enqueue(object :Callback<UserResponse>
        {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                progressDialog.dismiss()
                when(response.code())
                {
                    201 ->{

                        Toast.makeText(this@CreateRecordActivity, "Record Created SuccessFully", Toast.LENGTH_SHORT).show();
                        Log.d("ResponseCode","201Check -> "+response.code())
                        Log.d("ResponseCode","201Check -> "+response.body())
                    }
                    422->{
                        Toast.makeText(this@CreateRecordActivity, "Email Already Exist", Toast.LENGTH_SHORT).show();
                        Log.d("ResponseCode","422Check ->"+response.code())
                        Log.d("ResponseCode","422Check -> "+response.body())
                    }
                    else ->{
                        Log.d("ResponseCode","Else ->"+response.code())
                        Log.d("ResponseCode","Else ->"+response.body())

                    }
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                progressDialog.dismiss()

                Log.d("ResponseCode","Error Response ->" +t.message)
            }

        })


    }
}