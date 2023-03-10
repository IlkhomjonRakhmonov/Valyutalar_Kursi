package uz.rakhmonov.valyutalarkursi

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SpinnerAdapter
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import uz.rakhmonov.valyutalarkursi.adapter.RV_adapter
import uz.rakhmonov.valyutalarkursi.adapter.spinnerAdapter
import uz.rakhmonov.valyutalarkursi.databinding.ActivityConvertBinding
import uz.rakhmonov.valyutalarkursi.models.MyCourse
import uz.rakhmonov.valyutalarkursi.models.MyCurrency
import uz.rakhmonov.valyutalarkursi.utils.myFlags.qiymatList
import java.net.HttpURLConnection
import java.net.URL
import java.time.Year
import java.time.YearMonth
import java.util.*
import kotlin.collections.ArrayList

class ConvertActivity : AppCompatActivity() {
    private val binding by lazy { ActivityConvertBinding.inflate(layoutInflater) }
    lateinit var spinnerAdapter: spinnerAdapter
    lateinit var list: List<MyCurrency>
    lateinit var myCourse: MyCourse

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        list = ArrayList<MyCurrency>()
        myCourse = MyCourse("")
        val sana = Date()



        binding.convertation.setOnClickListener {
            aylan()
        }

        binding.conversely.setOnClickListener {
            teskarisi()
        }





        if (hasConnection(this)) {
            getCurrency()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    spinnerAdapter = spinnerAdapter(it)
                    binding.spinnerFrom.adapter = spinnerAdapter
                    binding.spinnerTo.adapter = spinnerAdapter

                    binding.sana.text = qiymatList[binding.spinnerFrom.selectedItemPosition].Date


                }

        } else {

            Toast.makeText(this, "Iltimos mobil internet sozligini tekshiring", Toast.LENGTH_SHORT)
                .show()
        }
    }


    private fun getCurrency(): Observable<ArrayList<MyCurrency>> {
        return Observable.create {
            val url = URL("http://cbu.uz/uzc/arkhiv-kursov-valyut/json/")
            val list = ArrayList<MyCurrency>()
            val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
            connection.connect()
            val inputStream = connection.inputStream
            val bufferReader = inputStream.bufferedReader()
            val gsonString = bufferReader.readLine()
            val gson = Gson()

            val type = object : TypeToken<ArrayList<MyCurrency>>() {}.type
            list.addAll(gson.fromJson<ArrayList<MyCurrency>>(gsonString, type))
            it.onNext(list)
        }

    }

    fun hasConnection(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        if (wifiInfo != null && wifiInfo.isConnected) {
            true
        }
        wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
        if (wifiInfo != null && wifiInfo.isConnected) {
            true
        }
        wifiInfo = cm.activeNetworkInfo
        return (wifiInfo != null) && wifiInfo.isConnected

    }

    fun aylan() {
        binding.fromTV.text =
            "${binding.EDTMiqdor.text} ${qiymatList[binding.spinnerFrom.selectedItemPosition].CcyNm_UZ} "
        var a = binding.EDTMiqdor.text.toString().toDouble()
        var b = qiymatList[binding.spinnerFrom.selectedItemPosition].Rate.toDouble()
        var c = qiymatList[binding.spinnerTo.selectedItemPosition].Rate.toDouble()


        binding.toTV.text =
            (b / c * a).toString() + " ${qiymatList[binding.spinnerTo.selectedItemPosition].CcyNm_UZ}"

    }

    fun teskarisi() {
        binding.fromTV.text =
            "${binding.EDTMiqdor.text} ${qiymatList[binding.spinnerTo.selectedItemPosition].CcyNm_UZ} "
        var a = binding.EDTMiqdor.text.toString().toDouble()
        var b = qiymatList[binding.spinnerFrom.selectedItemPosition].Rate.toDouble()
        var c = qiymatList[binding.spinnerTo.selectedItemPosition].Rate.toDouble()



        binding.toTV.text =
            "${(c / b * a).toString()}" + " ${qiymatList[binding.spinnerFrom.selectedItemPosition].CcyNm_UZ}"

    }
}