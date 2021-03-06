package com.example.room.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import com.example.room.R
import com.example.room.data.Citizen
import com.example.room.data.CitizenDatabase
import com.example.room.data.CitizenRepository
import com.example.room.showSnackBar
import com.example.room.util.AppExecutors
import com.example.room.util.setupActionBar
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), DetailScreen {

    override lateinit var presenter: DetailPresenter
    var data: Citizen? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setupActionBar(R.id.include) {
            title = getString(R.string.title_detail)
            setDisplayHomeAsUpEnabled(true)
        }

        val idDapenduk = intent.getStringExtra("id")

        val database = CitizenDatabase.getInstance(applicationContext)
        val repository = CitizenRepository(AppExecutors(),database.dapendukDAO())
        presenter = DetailPresenterImpl(this,repository)
        presenter.loadData(idDapenduk)

        deleteButton.setOnClickListener {
            if (data!=null)
                presenter.delete(data!!)
        }

    }

    override fun bind(data: Citizen) {
        this.data = data
        detailName.text = data.name
        detailSex.text = if (data.sex==0) getString(R.string.women_choice_label) else getString(R.string.man_choice_label)
        detailAddress.text = data.address
        detailPob.text = data.placeOfBirth
        detailDob.text = data.dateOfBirth
        detailOccupation.text = data.occupation
    }

    override fun showEditForm(id: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun show(message: String) {
        container.showSnackBar(message,Snackbar.LENGTH_LONG)
    }

    override fun back() {
        finish()
    }

}
