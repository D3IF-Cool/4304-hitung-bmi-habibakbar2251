package org.d3if4304.week10.ui.riwayat

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.d3if4304.week10.R
import org.d3if4304.week10.databinding.FragmentRiwayatBinding
import org.d3if4304.week10.db.BMIDB

class RiwayatFragment: Fragment() {

    private val viewModel: RiwayatViewModel by lazy {
        val db = BMIDB.getInstance(requireContext())
        val factory = RiwayatViewModelFactory(db.dao)
        ViewModelProvider(this, factory).get(RiwayatViewModel::class.java)
    }

    private lateinit var binding: FragmentRiwayatBinding
    private lateinit var myAdapter: RiwayatAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentRiwayatBinding.inflate(layoutInflater,
            container, false)

        myAdapter = RiwayatAdapter()
        with(binding.recyclerView) {
            addItemDecoration(
                DividerItemDecoration(context,
                RecyclerView.VERTICAL))
            adapter = myAdapter
            setHasFixedSize(true)
        }
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.data.observe(viewLifecycleOwner, {
            binding.emptyView.visibility = if (it.isEmpty())
                View.VISIBLE else View.GONE
            myAdapter.updateData(it)
        })

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.riwayat_menu, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_hapus) {
            hapusData()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    private fun hapusData() {
        MaterialAlertDialogBuilder(requireContext())
            .setMessage(R.string.konfirmasi_hapus)
            .setPositiveButton(getString(R.string.hapus_data)) { _, _ ->
                viewModel.deleteData()
            }
            .setNegativeButton(getString(R.string.batal)) { dialog, _ ->
                dialog.cancel()
            }
            .show()
    }
}
