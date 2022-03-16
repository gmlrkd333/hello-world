package com.example.test_project_1
import com.example.test_project_1.foodrecy.Foodmodel
import com.example.test_project_1.login.Login
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import java.util.ArrayList

data class Food(
    var foods : Array<Array<String>>,
    var code : String
)