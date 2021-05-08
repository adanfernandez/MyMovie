package com.miw.mymovie.model.data.storage.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

object FilmTable {
    const val TABLE_NAME = "Film"
    const val ID_REMOTE = "ID"
    const val TITLE = "TITLE"
    const val SEEN = "SEEN"
    const val USERNAME = "USERNAME"
}

@Entity(tableName=FilmTable.TABLE_NAME)
data class FilmEntity(
    @ColumnInfo(name = FilmTable.TITLE) @NotNull val title: String,
    @ColumnInfo(name = FilmTable.USERNAME) @NotNull val username: String,
    @ColumnInfo(name = FilmTable.SEEN) @NotNull val seen: Boolean,
    @PrimaryKey @ColumnInfo(name = FilmTable.ID_REMOTE) @NotNull val id_remote: Long
)


