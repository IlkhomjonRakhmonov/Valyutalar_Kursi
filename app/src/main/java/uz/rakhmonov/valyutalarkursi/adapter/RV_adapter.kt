package uz.rakhmonov.valyutalarkursi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.rakhmonov.valyutalarkursi.databinding.RvItemBinding
import uz.rakhmonov.valyutalarkursi.models.MyCurrency

class RV_adapter (val list:List<MyCurrency>):RecyclerView.Adapter<RV_adapter.VH>(){

    inner class VH (val rvItemBinding: RvItemBinding): RecyclerView.ViewHolder(rvItemBinding.root){
        fun onHolder(money: MyCurrency,position:Int){
            rvItemBinding.tvName.text=money.CcyNm_UZ
            rvItemBinding.tvKurs.text=money.Rate
            rvItemBinding.tvBirlik.text=money.Ccy

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(RvItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onHolder(list[position],position)

    }

    override fun getItemCount(): Int=list.size




}