package com.example.catapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Toast.makeText(this, "Calling API", Toast.LENGTH_LONG).show()

        GlobalScope.launch{

            try{
                val result = CatApiService.create().getCats(50,5,100,"json", "RANDOM")
                Log.i("My tag", "Success: retrieved size ${result.size}")
            }catch (e: Exception){
                e.printStackTrace()
            }

        }

    }
}