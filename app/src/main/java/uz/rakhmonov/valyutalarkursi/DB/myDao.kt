package uz.rakhmonov.valyutalarkursi.DB

import androidx.room.*
import io.reactivex.Flowable
import io.reactivex.Single
import uz.rakhmonov.valyutalarkursi.models.MyCurrency

//@Dao
//interface myCurrencyDao {
//    @Insert
//    fun addUser(myUser: MyCurrency):Single<Long>
//
//    @Query("select * from MyCurrency")
//    fun getAllUsers():Flowable<List<MyCurrency>>
//
//    @Delete
//    fun deleteUser(myUser: MyCurrency)
//
//    @Update
//    fun editUser(myUser: MyCurrency)
//
//
//}