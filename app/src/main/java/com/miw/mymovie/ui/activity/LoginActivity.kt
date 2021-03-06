package com.miw.mymovie.ui.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.miw.mymovie.R
import com.miw.mymovie.databinding.ActivityLoginBinding
import com.miw.mymovie.model.User
import com.miw.mymovie.model.data.storage.local.Settings
import com.miw.mymovie.model.datasources.UserProvider
import com.miw.mymovie.server.FilmServer

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnLogin.setOnClickListener { login() }
        binding.txGoRegister.setOnClickListener { goRegister() }
    }

    private fun Context.toast(message: Int, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, duration).show()
    }

    private fun login() {
        val username = binding.lgetUsername.text.toString()
        val password = binding.lgetPassword.text.toString()

        if (isInvalideFields(username, password)) {
            return
        }

        val user: User? = UserProvider.requestUserByUsernameAndPassword(username, password)
        if(user == null) {
            toast(R.string.login_error)
        } else {

            guardarLogin(username)

            // Comprobamos que no exista el usuario y lo añadimos al modelo
            startActivity(
                Intent(
                    this, MainActivity::class.java
                )
            )
        }
    }

    private fun goRegister() {
        startActivity(
            Intent(this, RegisterActivity::class.java)
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



    private fun guardarLogin(username: String) {
        Settings(this@LoginActivity).login = username
    }
}