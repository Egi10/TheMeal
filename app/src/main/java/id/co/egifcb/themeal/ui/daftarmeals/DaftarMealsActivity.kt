package id.co.egifcb.themeal.ui.daftarmeals

import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import id.co.egifcb.themeal.R
import id.co.egifcb.themeal.adapter.AdapterMeals
import id.co.egifcb.themeal.api.ApiConfig
import id.co.egifcb.themeal.model.Meals
import id.co.egifcb.themeal.ui.detailmeals.DetailMealsActivity
import kotlinx.android.synthetic.main.activity_daftar_meals.*
import java.util.*

class DaftarMealsActivity : AppCompatActivity(), DaftarMealsView {
    private lateinit var daftarMealsPresenter: DaftarMealsPresenter
    private lateinit var adapterMeals: AdapterMeals
    private val listMeals: MutableList<Meals> = mutableListOf()
    private var category: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar_meals)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(supportActionBar)?.setDisplayHomeAsUpEnabled(true)
        }
        title = "Food List"

        ApiConfig.getApi(this)
        daftarMealsPresenter = DaftarMealsPresenter(this)

        val intent = intent
        intent?.let {
            category = it.getStringExtra("category")
        }

        swipeRefresh.post {
            loadData(category)
        }

        swipeRefresh.setOnRefreshListener {
            loadData(category)
        }
    }

    private fun loadData(category: String?) {
        daftarMealsPresenter.getMeals(category)
        adapterMeals = AdapterMeals(this, listMeals) {
            val intent = Intent(this, DetailMealsActivity::class.java)
            intent.putExtra("id", it.idMeal)
            startActivity(intent)
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapterMeals
    }

    override fun showLoading() {
        swipeRefresh.isRefreshing = true
    }

    override fun onResponse(list: ArrayList<Meals>?) {
        listMeals.clear()
        list?.let {
            listMeals.addAll(list)
        }
        adapterMeals.notifyDataSetChanged()
    }

    override fun onError(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun hideLoading() {
        swipeRefresh.isRefreshing = false
    }
}
