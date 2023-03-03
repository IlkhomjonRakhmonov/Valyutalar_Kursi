package uz.rakhmonov.valyutalarkursi.models

import androidx.room.Entity
import androidx.room.PrimaryKey

//@Entity
data class MyCurrency(
    val Ccy: String,
    val CcyNm_EN: String,
    val CcyNm_RU: String,
    val CcyNm_UZ: String,
    val CcyNm_UZC: String,
    val Code: String,
    val Date: String,
    val Diff: String,
    val Nominal: String,
    val Rate: String,
//    @PrimaryKey(autoGenerate = true)
    val id: Int
)