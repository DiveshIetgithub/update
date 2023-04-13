package com.example.ttt.activity


import android.app.ActionBar
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.ttt.R
import com.example.ttt.adapters.ListViewAdapter
import com.example.ttt.databinding.ActivityDetailsBinding
import com.example.ttt.databinding.LayoutCreateDataBinding
import com.example.ttt.factory.DetailsActivityViewModelFactory
import com.example.ttt.model.DetailsActivityViewModel
import com.example.ttt.repository.SqliteDBRepository

class DetailsActivity : AppCompatActivity(),
    View.OnClickListener, AdapterView.OnItemClickListener,
    AdapterView.OnItemLongClickListener/*, AdapterView.OnItemSelectedListener*/ {
    lateinit var binding:ActivityDetailsBinding
    //    val data = arrayOf("AA", "BB", "CC", "DD", "EE")
    lateinit var factory: DetailsActivityViewModelFactory
    lateinit var viewModel:DetailsActivityViewModel
    private var rowId = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details)

//        val dataAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data)
//        binding.spinner.adapter = dataAdapter
//
//        binding.spinner.onItemSelectedListener = this


        factory = DetailsActivityViewModelFactory(SqliteDBRepository(this))
        viewModel = ViewModelProvider(this, factory)[DetailsActivityViewModel::class.java]

        binding.btnCreateData.setOnClickListener(this)

//        Log.d("listOfStudents", " $listOfStudents")

//        val arrayAdaper = ArrayAdapter(this, android.R.layout.simple_list_item_1, data)
//        binding.listView.adapter = arrayAdaper
        setListview()
//        binding.listView.setOnItemClickListener(this)
        registerForContextMenu(binding.listView)
        binding.listView.setOnItemLongClickListener(this)
    }

    override fun onClick(view: View?) {
        val layoutCustomBinding = LayoutCreateDataBinding.inflate(layoutInflater)
        val dialog = Dialog(this)
        dialog.setContentView(layoutCustomBinding.root)
        dialog.setCancelable(false)
        val windowManager = WindowManager.LayoutParams()
        windowManager.width = ActionBar.LayoutParams.MATCH_PARENT
        windowManager.height = ActionBar.LayoutParams.WRAP_CONTENT
        dialog.window?.attributes = windowManager
        dialog.show()


        layoutCustomBinding.btnSubmit.setOnClickListener {
            viewModel.createData(layoutCustomBinding.tilFname.editText?.text.toString(), layoutCustomBinding.tilLname.editText?.text.toString(), layoutCustomBinding.tilPhone.editText?.text.toString())
            setListview()
            dialog.dismiss()
        }
    }

    override fun onItemClick(adapter: AdapterView<*>?, view: View?, position: Int, rowItem: Long) {
        val data = adapter!!.getItemAtPosition(position).toString()
        Toast.makeText(this, "clicked $data", Toast.LENGTH_SHORT).show()
    }

//    override fun onItemSelected(adapter: AdapterView<*>?, view: View?, position: Int, row: Long) {
//        val data = adapter?.getItemAtPosition(position)
//        Toast.makeText(this, "You are selected $data", Toast.LENGTH_SHORT).show()
//    }
//
//    override fun onNothingSelected(p0: AdapterView<*>?) {

//
//    }

    fun setListview(){
        val listOfStudents = viewModel.getAllData()
        val myAdapter = ListViewAdapter(listOfStudents)
        binding.listView.adapter = myAdapter
    }

    override fun onItemLongClick(adapter: AdapterView<*>?, view: View?, position: Int, p3: Long): Boolean {
        rowId = position
        return false
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.options_menu, menu)
    }


    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_delete -> {
//                Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show()
                viewModel.deleteSingleRecord(rowId)
                setListview()
            }

            R.id.item_update -> {
                Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show()
            }
        }
        return super.onContextItemSelected(item)
    }

}