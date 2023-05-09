package com.alexandrestefani.noteideas.DataClass

import androidx.room.*
import com.alexandrestefani.noteideas.Model.Notes
import com.alexandrestefani.noteideas.Model.Users

@Dao
interface UsersDao {

    @Query("SELECT*FROM Users")
    fun getAllUsers(): List<Users>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(users: Users)

    @Delete
    fun delete(users: Users)

    @Update
    fun update(users: Users)

    @Query("SELECT*FROM Users WHERE id =:userId AND password=:password")
    fun autentication(userId: String, password:String): Users?

    @Query("SELECT*FROM Users WHERE id = :signUpId")
    fun verifyId(signUpId: String?):Users?

}
