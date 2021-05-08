package com.miw.mymovie.ui.activity

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.miw.mymovie.R
import com.miw.mymovie.server.FilmServer
import com.miw.mymovie.databinding.ActivityRegisterBinding
import com.miw.mymovie.model.User
import com.miw.mymovie.model.data.storage.local.Settings
import com.miw.mymovie.model.datasources.UserProvider


class RegisterActivity : AppCompatActivity() {
    private val REQUEST_INTERNET_PERMISSIONS = 1
    private val REQUEST_ACCESS_NETWORK_STATE = 2

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnRegister.setOnClickListener { askForPermission() }
        binding.txGoLogin.setOnClickListener { goLogin() }
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


        val user_register: User? = UserProvider.requestUserByUsername(username)


        if(user_register != null) {
            toast(R.string.register_error)
        } else {
            val register: Boolean = UserProvider.requestSaveNewUser(user)
            if(!register) {
                // Comprobamos que no exista el usuario y lo añadimos al modelo
                toast(R.string.register_error, 6)
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




    }

    private fun goLogin() {
        startActivity(
            Intent(this, LoginActivity::class.java)
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

    private fun askForPermission() {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if(cm.activeNetwork!=null)
            register()
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.INTERNET),
                REQUEST_INTERNET_PERMISSIONS
            )
        }
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE)
//            == PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_NETWORK_STATE),
//                REQUEST_ACCESS_NETWORK_STATE)
//        }

        register()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        var canRegister = true
        when (requestCode) {
            REQUEST_INTERNET_PERMISSIONS -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    canRegister = false
                } else {
                    register()
                }
            }
//            REQUEST_ACCESS_NETWORK_STATE -> {
//                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
//                    canRegister = false
//                }
//            }
        }
        if (canRegister)
            register()
    }



    private fun guardarLogin(username: String) {
        Settings(this@RegisterActivity).login = username
    }
}