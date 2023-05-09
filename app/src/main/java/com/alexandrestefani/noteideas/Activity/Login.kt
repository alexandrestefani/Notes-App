package com.alexandrestefani.noteideas.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.alexandrestefani.noteideas.DataClass.AppDatabase
import com.alexandrestefani.noteideas.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {
    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    private val users_Dao by lazy {
        val db = AppDatabase.instance(this)
        db.usersDao()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            var userID = binding.useridLogin.text.toString()
            var password = binding.editTextTextPassword.text.toString()

            users_Dao.autentication(userID,password)?.let { users ->
                intent = Intent(this,MainActivity::class.java)
                intent.putExtra("USER_ID", userID)
                Log.i("confirmacao","confirmacao do ID $userID")
                startActivity(intent)

            } ?:  Toast.makeText(this,"Usuario ou senha incorretos", Toast.LENGTH_SHORT).show()

        }
        binding.textButton.setOnClickListener {
            intent = Intent(this,MakeProfile::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        binding.useridLogin.setText("")
        binding.editTextTextPassword.setText("")
    }
}