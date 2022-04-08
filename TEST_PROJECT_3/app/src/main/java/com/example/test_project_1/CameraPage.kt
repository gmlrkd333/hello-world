package com.example.test_project_1

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.text.SimpleDateFormat

class CameraPage: AppCompatActivity() {
    lateinit var photoFile: File
    private var photoUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.camera)



        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        photoFile = File(
            File("${filesDir}/image").apply {
                if (!this.exists()) {
                    this.mkdirs()
                }
            },
            newJpgFileName()
        )
        photoUri = FileProvider.getUriForFile(
            this,
            "com.example.test_project_1.fileprovider",
            photoFile
        )

        takePictureIntent.resolveActivity(packageManager)?.also {
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
            startActivityForResult(takePictureIntent, 0)
        }




    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var retro = Retro()
        var retrofit = retro.retrofit

        if (resultCode == Activity.RESULT_OK) {
            val file = File(photoFile.absolutePath)
            val requestFile = RequestBody.create("image/*".toMediaTypeOrNull(), file)
            val image = MultipartBody.Part.createFormData("proFile", file.name, requestFile)

            var picture = retrofit.create(Picture::class.java)

            var imageView: ImageView = findViewById(R.id.imageView)
            var calorie: TextView = findViewById(R.id.calorie)
            var btn: Button = findViewById(R.id.btn)

            picture.requestPicture(image).enqueue(object : Callback<Cal> {
                override fun onResponse(call: Call<Cal>, response: Response<Cal>) {
                    var result = response.body()
                    if (result?.code == "0000") {
                        Toast.makeText(applicationContext, "성공 ", Toast.LENGTH_SHORT).show()
                        val imageBytes = Base64.decode(result?.img, 0)
                        val image = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
                        imageView.setImageBitmap(image)

                        var foods = result.foods
                        for(i in 0 until foods.size){
                            println(foods[i])
                        }

                        btn.setOnClickListener {
                            Toast.makeText(applicationContext, "저장", Toast.LENGTH_SHORT).show()
                            var id = intent.getStringExtra("textId") as String
                            var sex = intent.getStringExtra("sex") as String
                            var time = intent.getStringExtra("time") as String
                            var user_weight = intent.getIntExtra("weight", 0)
                            var height = intent.getIntExtra("height", 0)
                            var age = intent.getIntExtra("age", 0)
                            var savefood = retrofit.create(SaveFood::class.java)
                            for(i in 0 until foods.size){
                                savefood.saveFood(foods[i], time.toInt(), "1", id, sex, user_weight, height, age).enqueue(object: Callback<Food> {
                                    override fun onResponse(call: Call<Food>, response: Response<Food>) {
                                        var food = response.body() as Food
                                        if(food.code == "0000"){
                                            Toast.makeText(applicationContext, "성공", Toast.LENGTH_SHORT).show()
                                        }
                                        else{
                                            Toast.makeText(applicationContext, "없어", Toast.LENGTH_SHORT).show()
                                        }
                                    }
                                    override fun onFailure(call: Call<Food>, t: Throwable) {
                                        Toast.makeText(applicationContext, "통신 실패", Toast.LENGTH_SHORT).show()
                                    }
                                })
                            }

                            var outIntent = Intent(applicationContext, CalendarPage::class.java)
                            setResult(Activity.RESULT_OK, outIntent)
                            finish()
                        }

                    } else {
                        Toast.makeText(applicationContext, "실패", Toast.LENGTH_SHORT).show()
                        val imageBytes = Base64.decode(result?.img, 0)
                        val image = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
                        imageView.setImageBitmap(image)

                        btn.setOnClickListener {
                            var outIntent = Intent(applicationContext, CalendarPage::class.java)
                            setResult(Activity.RESULT_OK, outIntent)
                            finish()
                        }
                    }
                }
                override fun onFailure(call: Call<Cal>, t: Throwable) {
                    Toast.makeText(applicationContext, "통신실패", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    private fun newJpgFileName(): String {
        val sdf = SimpleDateFormat("yyyyMMdd_HHmmss")
        val filename = sdf.format(System.currentTimeMillis())
        return "${filename}.jpg"
    }
}