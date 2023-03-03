package uz.rakhmonov.valyutalarkursi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uz.rakhmonov.valyutalarkursi.databinding.ActivityConvertBinding

class ConvertActivity : AppCompatActivity() {
    private val binding by lazy { ActivityConvertBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }
}