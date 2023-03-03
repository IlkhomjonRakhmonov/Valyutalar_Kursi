package uz.rakhmonov.valyutalarkursi

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import uz.rakhmonov.valyutalarkursi.adapter.RV_adapter
import uz.rakhmonov.valyutalarkursi.databinding.ActivityMainBinding
import uz.rakhmonov.valyutalarkursi.models.MyCurrency
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    lateinit var rvAdapter: RV_adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

if (hasConnection(this)){
    getCurrency()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe {
            rvAdapter=RV_adapter(it)
            binding.RV.adapter=rvAdapter
        }

}else{
    binding.noconnection.visibility=View.VISIBLE

    Toast.makeText(this, "Iltimos mobil internet sozligini tekshiring", Toast.LENGTH_SHORT).show()
}

        binding.btnNext.setOnClickListener {
            if (hasConnection(this)){
                startActivity(Intent(this,ConvertActivity::class.java))
            }else{
                startActivity(Intent(this,MainActivity::class.java))
            }


        }
        binding.noconnection.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }

    }


    private fun getCurrency():Observable<List<MyCurrency>> {
        return Observable.create{
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
//        return Observable.create {
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
//            it.onNext(true)
//    }

    }


}