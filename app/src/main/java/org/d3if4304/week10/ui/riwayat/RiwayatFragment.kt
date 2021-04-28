package org.d3if4304.week10.ui.riwayat

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import org.d3if4304.week10.databinding.FragmentRiwayatBinding
import org.d3if4304.week10.db.BMIDB

class RiwayatFragment: Fragment() {

    private val viewModel: RiwayatViewModel by lazy {
        val db = BMIDB.getInstance(requireContext())
        val factory = RiwayatViewModelFactory(db.dao)
        ViewModelProvider(this, factory).get(RiwayatViewModel::class.java)
    }

    private lateinit var binding: FragmentRiwayatBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentRiwayatBinding.inflate(layoutInflater,
            container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.data.observe(viewLifecycleOwner, {
            Log.d("HistoriFragment", "Jumlah data: ${it.size}")
        })

    }

}