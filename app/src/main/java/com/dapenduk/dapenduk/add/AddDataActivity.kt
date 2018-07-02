package com.dapenduk.dapenduk.add

import android.app.DatePickerDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.widget.RadioButton
import com.dapenduk.dapenduk.R
import com.dapenduk.dapenduk.data.DapendukDatabase
import com.dapenduk.dapenduk.data.DapendukRepository
import com.dapenduk.dapenduk.showSnackBar
import com.dapenduk.dapenduk.util.AppExecutors
import kotlinx.android.synthetic.main.activity_add_data.*
import java.text.SimpleDateFormat
import java.util.*

class AddDataActivity : AppCompatActivity(),AddDataScreen {

    override lateinit var presenter: AddDataPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_data)

        val database = DapendukDatabase.getInstance(applicationContext)
        val repository = DapendukRepository(AppExecutors(),database.dapendukDAO())

        presenter = AddDataPresenterImpl(this,repository)

        addDateBirthField.setOnClickListener {
            presenter.onDateOfBirthTapped()
        }

        addButton.setOnClickListener {
            val name = addNameField.text.toString()
            var sex = -1
            val buttonChoice = findViewById<RadioButton>(sexRadioGroup.checkedRadioButtonId)
            if(buttonChoice.text.toString()==getString(R.string.man_choice_label))
                sex = 1
            else if(buttonChoice.text.toString()==getString(R.string.women_choice_label))
                sex = 0
            val placeOfBirth = addBornField.text.toString()
            val dateOfBirth = addDateBirthField.text.toString()
            val occupation = addJobField.text.toString()
            val address = addAddressField.text.toString()
            presenter.onAddButtonTapped(name,sex,placeOfBirth,dateOfBirth,occupation,address)
        }
    }

    override fun showCalendar() {
        val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        val calendar = Calendar.getInstance()
        DatePickerDialog(this, DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            val newDate = Calendar.getInstance()
            newDate.set(year,monthOfYear,dayOfMonth)
            addDateBirthField.setText(dateFormat.format(newDate.time))
        },calendar[Calendar.YEAR],calendar[Calendar.MONTH],calendar[Calendar.DAY_OF_MONTH]).show()
    }

    override fun show(message: String) {
        var text = message
        container.showSnackBar(text, Snackbar.LENGTH_LONG)
    }

}
