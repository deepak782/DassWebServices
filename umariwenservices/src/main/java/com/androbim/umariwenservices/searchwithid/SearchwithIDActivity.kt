package com.androbim.umariwenservices.searchwithid

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.androbim.umariwenservices.R
import com.androbim.umariwenservices.apiIntertace.MyApinterface
import com.androbim.umariwenservices.databinding.ActivitySearchwithIdactivityBinding
import com.androbim.umariwenservices.retrofitInstance.CallRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchwithIDActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchwithIdactivityBinding

    lateinit var apinterface: MyApinterface
    lateinit var progressDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySearchwithIdactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        progressDialog=ProgressDialog(this)
        progressDialog.setCancelable(false)
        apinterface= CallRetrofit.retrofitInstance().create(MyApinterface::class.java)

        binding.btnSearch2.setOnClickListener {

            searchUserwithID()
        }
    }

    private fun searchUserwithID() {
        binding.displayText.text=""
        progressDialog.show()
        progressDialog.setMessage("Loading!!!!")

        val call=apinterface.searchWithID(binding.edtId.text.toString())

        call.enqueue(object :Callback<IDModel>{
            override fun onResponse(call: Call<IDModel>, response: Response<IDModel>) {
                progressDialog.dismiss()
                when(response.code())
                {
                    200->{

                        val name=response.body()!!.name
                        val id=response.body()!!.id
                        val gender=response.body()!!.gender
                        val mail=response.body()!!.email
                        val status=response.body()!!.status
                        binding.displayText.text="$name\n$id\n$gender\n$mail\n$status"

                    }
                    404->{
                        Toast.makeText(this@SearchwithIDActivity, "No Record Found With this ID", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            override fun onFailure(call: Call<IDModel>, t: Throwable) {
                progressDialog.dismiss()
                Toast.makeText(this@SearchwithIDActivity, ""+t.localizedMessage, Toast.LENGTH_SHORT).show();
            }

        })
    }
}