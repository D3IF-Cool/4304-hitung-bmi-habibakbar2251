package org.d3if4304.week10.ui.riwayat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.d3if4304.week10.R
import org.d3if4304.week10.data.HitungBmi
import org.d3if4304.week10.databinding.ItemRiwayatBinding
import org.d3if4304.week10.db.BMIEntity
import java.text.SimpleDateFormat
import java.util.*

class RiwayatAdapter : RecyclerView.Adapter<RiwayatAdapter.ViewHolder>() {

    private val data = mutableListOf<BMIEntity>()

    fun updateData(newData: List<BMIEntity>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRiwayatBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }
    override fun getItemCount(): Int {
        return data.size
    }
    class ViewHolder(
        private val binding: ItemRiwayatBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        private val dateFormatter = SimpleDateFormat("dd MMMM yyyy",
            Locale("id", "ID")
        )
        fun bind(item: BMIEntity) = with(binding) {
            tanggalTextView.text = dateFormatter.format(Date(item.tanggal))
            val hasilBmi = HitungBmi.hitung(item)
            kategoriTextView.text = hasilBmi.kategori.toString()
            bmiTextView.text = root.context.getString(
                R.string.bmi_x,
                hasilBmi.bmi)
            beratTextView.text = root.context.getString(R.string.berat_x,
                item.berat)
            tinggiTextView.text = root.context.getString(R.string.tinggi_x,
                item.tinggi)
            val stringRes = if (item.isMale) R.string.pria else R.string.wanita
            genderTextView.text = root.context.getString(stringRes)
        }
    }
}