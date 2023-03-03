package uz.rakhmonov.valyutalarkursi.DB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.rakhmonov.valyutalarkursi.models.MyCurrency

//@Database(entities = [MyCurrency::class], version = 1)
//abstract class AppDataBase: RoomDatabase() {
//    abstract fun myDao():myCurrencyDao
//
//    companion object {
//        private var instance:AppDataBase?=null
//
//        @Synchronized
//        fun getInstance(context: Context):AppDataBase{
//            if (instance==null){
//                instance= Room.databaseBuilder(context,AppDataBase::class.java,"myDBUsers")
//                    .fallbackToDestructiveMigration()
//                    .allowMainThreadQueries()
//                    .build()
//
//            }
//
//            return instance!!
//        }
//    }
//}