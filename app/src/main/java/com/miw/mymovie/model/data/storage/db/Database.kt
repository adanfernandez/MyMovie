package com.miw.mymovie.model.data.storage.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.miw.mymovie.App
import com.miw.mymovie.model.data.storage.db.dao.FilmDao
import com.miw.mymovie.model.data.storage.db.dao.UserDao
import com.miw.mymovie.model.data.storage.db.entities.FilmEntity
import com.miw.mymovie.model.data.storage.db.entities.UserEntity

@Database(entities = [FilmEntity::class, UserEntity::class], version = 1)
abstract class MyDatabase: RoomDatabase() {
    abstract fun filmDao(): FilmDao
    abstract fun userDao(): UserDao

    companion object {
        val instance by lazy {
            Room.databaseBuilder(
                App.instance,
                MyDatabase::class.java,
                "database-name" // Nombre de la BD
            ).build()
        }
    }
}
