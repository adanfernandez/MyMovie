package com.miw.mymovie.model.datasources

import com.miw.mymovie.model.Film
import com.miw.mymovie.model.User
import com.miw.mymovie.model.data.storage.db.repositories.UserRepository

object UserProvider {
    private val userRepository: UserRepository = UserRepository()

    fun requestUserByUsernameAndPassword(username: String, password: String): User? {
        return userRepository.requestUserByUsernameAndPassword(username, password)
    }
    fun requestSaveNewUser(user: User): Boolean {
        return userRepository.requestSaveNewUser(user)
    }
    fun requestUserByUsername(username: String): User? {
        return userRepository.requestUserByUsername(username)
    }
}