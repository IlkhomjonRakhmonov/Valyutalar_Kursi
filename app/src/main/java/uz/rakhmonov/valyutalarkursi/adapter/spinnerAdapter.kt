package uz.rakhmonov.valyutalarkursi.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import uz.rakhmonov.valyutalarkursi.R
import uz.rakhmonov.valyutalarkursi.databinding.ItemSpinnerBinding
import uz.rakhmonov.valyutalarkursi.models.MyCurrency

class spinnerAdapter(var list:ArrayList<MyCurrency>):BaseAdapter() {
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()

    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val itemSpinnerBinding=ItemSpinnerBinding.inflate(LayoutInflater.from(parent?.context),parent,false)
        itemSpinnerBinding.country.text=list[position].CcyNm_UZ
        itemSpinnerBinding.birlik.text=list[position].Rate
        return itemSpinnerBinding.root
    }
}