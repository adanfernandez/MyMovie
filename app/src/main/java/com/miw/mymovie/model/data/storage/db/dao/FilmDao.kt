package com.miw.mymovie.model.data.storage.db.dao

import androidx.room.*
import com.miw.mymovie.model.data.storage.db.entities.FilmEntity
import com.miw.mymovie.model.data.storage.db.entities.FilmTable

@Dao
interface FilmDao {
    @Insert
    fun insert (film: FilmEntity?): Long

    @Update
    fun update (vararg film: FilmEntity)

    @Delete
    fun delete (film: FilmEntity?)

    @Query("DELETE FROM ${FilmTable.TABLE_NAME}")
    fun clear()

    @Query("SELECT * FROM ${FilmTable.TABLE_NAME} WHERE ${FilmTable.USERNAME} LIKE :username")
    fun getAll(username: String): List<FilmEntity>
}