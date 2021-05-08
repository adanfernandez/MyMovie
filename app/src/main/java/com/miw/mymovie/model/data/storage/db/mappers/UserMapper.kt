package com.miw.mymovie.model.data.storage.db.mappers

import com.miw.mymovie.model.User
import com.miw.mymovie.model.data.storage.db.entities.UserEntity

object UserMapper  {
    fun convertToDomain(user: UserEntity?) = convertUserToDomain(user)

    private fun convertUserToDomain(user: UserEntity?): User? {
        if(user != null) {
            return User(user.username, user.password, user.name)
        }
        return null
    }

    fun convertFromDomain(user: User) = convertUserFromDomain(user)

    private fun convertUserFromDomain(user: User): UserEntity? {
        return UserEntity(user.username, user.password, user.name)
        return null
    }
}