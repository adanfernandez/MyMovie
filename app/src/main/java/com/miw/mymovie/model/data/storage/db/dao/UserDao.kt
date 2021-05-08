package com.miw.mymovie.model.data.storage.db.dao
import androidx.room.*
import com.miw.mymovie.model.User
import com.miw.mymovie.model.data.storage.db.entities.UserEntity
import com.miw.mymovie.model.data.storage.db.entities.UserTable

@Dao
interface UserDao {

    @Insert
    fun insert (user: UserEntity): Long

    @Update
    fun update (vararg user: UserEntity)

    @Delete
    fun delete (user: UserEntity)

    @Query("DELETE FROM ${UserTable.TABLE_NAME}")
    fun clear()

    @Query("SELECT * FROM ${UserTable.TABLE_NAME}")
    fun getAll(): List<UserEntity>


    @Query("SELECT * FROM ${UserTable.TABLE_NAME} WHERE ${UserTable.USERNAME} LIKE :username AND ${UserTable.PASSWORD} LIKE :password")
    fun findByUsernameAndPassword(username: String, password: String): UserEntity?

    @Query("SELECT * FROM ${UserTable.TABLE_NAME} WHERE ${UserTable.USERNAME} LIKE :username")
    fun findByUsername(username: String): UserEntity?

}