package com.example.ttt.model

    import androidx.lifecycle.ViewModel
    import com.example.ttt.classs.Student
    import com.example.ttt.repository.SqliteDBRepository

    class DetailsActivityViewModel(private val repository:SqliteDBRepository): ViewModel() {


        fun createData(fName: String, lName: String, phone: String){
            repository.createData(fName, lName, phone)
        }

        fun getAllData(): ArrayList<Student>{
            return repository.getAllData()
        }

        fun deleteSingleRecord(rowId: Int){
            repository.deleteSingleRecord(rowId)
        }

    }