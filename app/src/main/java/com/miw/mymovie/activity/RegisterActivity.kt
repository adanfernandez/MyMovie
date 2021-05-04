package com.miw.mymovie.activity

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.miw.mymovie.R
import com.miw.mymovie.databinding.ActivityRegisterBinding
import com.miw.mymovie.model.User

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnRegister.setOnClickListener { register() }
    }

    private fun Context.toast(message: Int, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, duration).show()
    }

    private fun register() {
        val username = binding.etUsername.text.toString()
        val password = binding.etPassword.text.toString()
        val repassword = binding.etRepassword.text.toString()

        validateFields(username, password, repassword)

        val user = User(
            username=username,
            password=password
        )
    }

    private fun Context.validateFields(username: String, password: String, repassword: String) {
        when {
            username.isEmpty()
                    or password.isEmpty()
                    or repassword.isEmpty() -> {
                toast(R.string.register_validation_empty_fields)
            }
            password != repassword -> toast(R.string.register_validation_wrong_passwords)

        }
    }
}