package com.alexandrestefani.noteideas.Model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Notes(
    @PrimaryKey(autoGenerate = true)
    val id : Long = 0L,
    var titleNotes: String,
    var textNotesval : String,
    var usuarioId: String
):Parcelable
