package com.miw.mymovie.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.service.autofill.Validators.not
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.miw.mymovie.R
import com.miw.mymovie.api.FilmClient
import com.miw.mymovie.databinding.ActivityLoginBinding
import com.miw.mymovie.model.User

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnLogin.setOnClickListener { register() }
    }

    private fun Context.toast(message: Int, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, duration).show()
    }

    private fun register() {
        val username = binding.lgetUsername.text.toString()
        val password = binding.lgetPassword.text.toString()

        if (isInvalideFields(username, password)) {
            return
        }

        val user = User(
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
        username: String,
        password: String,
    ): Boolean {
        var error = false
        when {
            username.isEmpty()
                    or password.isEmpty() -> {
                toast(R.string.register_validation_empty_fields)
                error = true
            }
        }
        return error
    }

}