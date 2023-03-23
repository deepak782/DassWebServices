package com.androbim.umariwenservices.updateuser

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.androbim.umariwenservices.R
import com.androbim.umariwenservices.apiIntertace.MyApinterface
import com.androbim.umariwenservices.databinding.ActivityUpdateRecordBinding
import com.androbim.umariwenservices.retrofitInstance.CallRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateRecordActivity : AppCompatActivity() {

    private  lateinit var  binding: ActivityUpdateRecordBinding
    lateinit var apinterface: MyApinterface
    lateinit var progressDialog: ProgressDialog
    lateinit var nameString: String
    lateinit var mailString: String
    lateinit var userIdString: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUpdateRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        progressDialog=ProgressDialog(this)
        progressDialog.setCancelable(false)
        apinterface= CallRetrofit.retrofitInstance().create(MyApinterface::class.java)

        binding.updateBtn.setOnClickListener {
            updateUsers()
        }

        binding.deleteBtn.setOnClickListener {

            deleteUser()
        }
    }

    private fun deleteUser() {

        progressDialog.show()
        progressDialog.setMessage("Deleting the Users")

        userIdString=binding.userID.text.toString()

        val call=apinterface.deleteUser(userIdString)

        call.enqueue(object :Callback<UserResponse2>{
            override fun onResponse(call: Call<UserResponse2>, response: Response<UserResponse2>) {
                progressDialog.dismiss()
                when(response.code())
                {
                    204 ->{

                        Toast.makeText(this@UpdateRecordActivity, "Record Deleted SuccessFully", Toast.LENGTH_SHORT).show();
                        Log.d("ResponseCode","204 -> "+response.code())
                        Log.d("ResponseCode","204 -> "+response.body())
                    }
                    404->{
                        Toast.makeText(this@UpdateRecordActivity, "User Id not Found", Toast.LENGTH_SHORT).show();
                        Log.d("ResponseCode","404 ->"+response.code())
                        Log.d("ResponseCode","404 -> "+response.body())
                    }
                    else ->{
                        Log.d("ResponseCode","Else ->"+response.code())
                        Log.d("ResponseCode","Else ->"+response.body())

                    }
                }
            }

            override fun onFailure(call: Call<UserResponse2>, t: Throwable) {
                progressDialog.dismiss()
                Log.d("ResponseCode","Error Response ->" +t.message)
            }

        })


    }

    private fun updateUsers() {
        progressDialog.show()
        progressDialog.setMessage("Update the Users")

        nameString=binding.name.text.toString()
        mailString=binding.mail.text.toString()
        userIdString=binding.userID.text.toString()

        var userParameters2=UserParameters2("",nameString,mailString,"active")

        val call=apinterface.updateUser(userIdString,userParameters2)

        call.enqueue(object : Callback<UserResponse2>{
            override fun onResponse(call: Call<UserResponse2>, response: Response<UserResponse2>) {

                progressDialog.dismiss()

                when(response.code())
                {
                    200 ->{

                        Toast.makeText(this@UpdateRecordActivity, "Record Updated SuccessFully", Toast.LENGTH_SHORT).show();
                        Log.d("ResponseCode","200 -> "+response.code())
                        Log.d("ResponseCode","200 -> "+response.body())
                    }
                    404->{
                        Toast.makeText(this@UpdateRecordActivity, "User Id not Found", Toast.LENGTH_SHORT).show();
                        Log.d("ResponseCode","404 ->"+response.code())
                        Log.d("ResponseCode","404 -> "+response.body())
                    }
                    else ->{
                        Log.d("ResponseCode","Else ->"+response.code())
                        Log.d("ResponseCode","Else ->"+response.body())

                    }
                }
            }

            override fun onFailure(call: Call<UserResponse2>, t: Throwable) {
                progressDialog.dismiss()
                Log.d("ResponseCode","Error Response ->" +t.message)
            }

        })
    }
}