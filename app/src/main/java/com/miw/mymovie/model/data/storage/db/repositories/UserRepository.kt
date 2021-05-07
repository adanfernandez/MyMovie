package com.miw.mymovie.model.data.storage.db.repositories

import com.miw.mymovie.model.User
import com.miw.mymovie.model.data.storage.db.MyDatabase
import com.miw.mymovie.model.data.storage.db.mappers.UserMapper

class UserRepository {
    private val userDao = MyDatabase.instance.userDao();

    fun requestUserByUsernameAndPassword(username: String, password: String): User? {
        val userDb = userDao.findByUsernameAndPassword(username, password)
        return UserMapper.convertToDomain(userDb)
    }

    fun requestSaveNewUser(user: User): Boolean {
        val userDb = UserMapper.convertFromDomain(user)
        if(userDb != null) {
            userDao.insert(userDb)
            return true
        }
        return false
    }

    fun requestUserByUsername(username: String): User? {
        val userDb = userDao.findByUsername(username)
        return UserMapper.convertToDomain(userDb)
    }

}