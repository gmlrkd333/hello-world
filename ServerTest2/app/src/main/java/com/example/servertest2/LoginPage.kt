package com.example.servertest2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login_page.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        join_btn.setOnClickListener {
            var intent = Intent(applicationContext, JoinPage::class.java)
            startActivity(intent)
        }

        var retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.35.27:8000")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var loginService = retrofit.create(LoginService::class.java)

        login_btn.setOnClickListener {
            var textId = ID.text.toString()
            var textPw = PW.text.toString()

            loginService.requestLogin(textId, textPw).enqueue(object: Callback<Login>{
                override fun onResponse(call: Call<Login>, response: Response<Login>) {
                    var login = response.body()
                    if(login?.code == "0000"){
                        Toast.makeText(applicationContext, "로그인 성공", Toast.LENGTH_SHORT).show()
                        var intent= Intent(applicationContext, MainActivity::class.java)
                        startActivity(intent)
                    }
                    else{
                        Toast.makeText(applicationContext, "ID 또는 PW 틀렸다", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Login>, t: Throwable) {
                    Toast.makeText(applicationContext, "통신 실패", Toast.LENGTH_SHORT).show()
                }

            })
        }
    }
}