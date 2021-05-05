package com.miw.mymovie.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.service.autofill.Validators.not
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.miw.mymovie.R
import com.miw.mymovie.api.FilmClient
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
        val name = binding.etName.text.toString()
        val username = binding.etUsername.text.toString()
        val password = binding.etPassword.text.toString()
        val repassword = binding.etRepassword.text.toString()

        if (isInvalideFields(name, username, password, repassword)) {
            return
        }

        val user = User(
            name = name,
            username = username,
            password = password
        )
        FilmClient().getLatestFilms()

        // Comprobamos que no exista el usuario y lo añadimos al modelo
        startActivity(
            Intent(
                this, LatestsActivity::class.java
            )
        )
    }

    private fun Context.isInvalideFields(
        name: String,
        username: String,
        password: String,
        repassword: String
    ): Boolean {
        var error = false
        when {
            name.isEmpty()
                    or username.isEmpty()
                    or password.isEmpty()
                    or repassword.isEmpty() -> {
                toast(R.string.register_validation_empty_fields)
                error = true
            }
            password != repassword -> {
                toast(R.string.register_validation_wrong_passwords)
                error = true
            }
        }
        return error
    }

}