package com.miw.mymovie.model.data.storage.db.entities

import androidx.room.ColumnInfo
import org.jetbrains.annotations.NotNull
import androidx.room.Entity
import androidx.room.PrimaryKey

object UserTable {
    const val TABLE_NAME = "User"
    const val NAME = "name"
    const val USERNAME = "username"
    const val PASSWORD = "password"
}

@Entity(tableName = UserTable.TABLE_NAME)
data class UserEntity (
    @ColumnInfo(name = UserTable.NAME) @NotNull val name: String,
    @ColumnInfo(name = UserTable.PASSWORD) @NotNull val password: String,
    @PrimaryKey @ColumnInfo(name = UserTable.USERNAME) @NotNull val username: String
    )