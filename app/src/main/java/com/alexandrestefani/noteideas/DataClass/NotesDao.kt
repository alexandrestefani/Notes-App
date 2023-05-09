package com.alexandrestefani.noteideas.DataClass

import androidx.room.*
import com.alexandrestefani.noteideas.Model.Notes

@Dao
interface NotesDao {
    @Query("SELECT*FROM Notes")
    fun getAllProduct():List<Notes>

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    fun save(productList: Notes)

    @Delete
    fun delete(product: Notes)

    @Update
    fun update(product: Notes)

    @Query("SELECT * FROM Notes WHERE id = :id")
    fun buscaPorId(id: Long) : Notes?

    @Query("SELECT * FROM Notes WHERE usuarioId =:user_id")
    fun getAllFromThisId(user_id:String?):List<Notes>

}