package com.alexandrestefani.noteideas.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.alexandrestefani.noteideas.DataClass.AppDatabase
import com.alexandrestefani.noteideas.Model.Users
import com.alexandrestefani.noteideas.R
import com.alexandrestefani.noteideas.databinding.ActivityLoginBinding
import com.alexandrestefani.noteideas.databinding.ActivityMakeProfileBinding
import kotlinx.coroutines.launch

class MakeProfile : AppCompatActivity() {

    private val binding by lazy {
        ActivityMakeProfileBinding.inflate(layoutInflater)
    }

    private val users_Dao by lazy {
        val db = AppDatabase.instance(this)
        db.usersDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

         binding.buttonSignup.setOnClickListener {
             var signUpId = binding.signupUser.text.toString()
             var signUpPassword = binding.signuppassword.text.toString()
             users_Dao.verifyId(signUpId)?.let { users ->
                 Toast.makeText(this, "usuario ja cadastrado", Toast.LENGTH_SHORT).show()
             }?: makeSignUp(signUpId, signUpPassword)

         }


    }

    private fun makeSignUp(signUpId: String, signUpPassword: String) {
        val user = Users(
            signUpId,
            signUpPassword
        )
        lifecycleScope.launch {
            users_Dao.save(user)
            finish()
        }
    }
}