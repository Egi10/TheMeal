package id.co.egifcb.themeal.ui.detailmeals

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import id.co.egifcb.themeal.R
import id.co.egifcb.themeal.api.ApiConfig
import id.co.egifcb.themeal.model.Meals
import kotlinx.android.synthetic.main.activity_detail_meals.*
import java.util.*
import kotlin.collections.ArrayList

class DetailMealsActivity : AppCompatActivity(), DetailMealsView {
    private lateinit var detailMealsPresenter: DetailMealsPresenter
    private var id: String? = null
    private lateinit var ivImage: ImageView
    private lateinit var tvMeals: TextView
    private lateinit var tvCategory: TextView
    private lateinit var tvArea: TextView
    private lateinit var tvInstructions: TextView
    private lateinit var tvTags: TextView
    private lateinit var ivPathYoutube: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_meals)
        setSupportActionBar(toolbar)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(supportActionBar)?.setDisplayHomeAsUpEnabled(true)
        }
        title = "Detail Meals"

        ApiConfig.getApi(this)
        detailMealsPresenter = DetailMealsPresenter(this)

        val intent = intent
        intent?.let {
            id = it.getStringExtra("id")
            loadDetail(id)
        }

        ivImage = findViewById(R.id.iv_image)
        tvMeals = findViewById(R.id.tv_meals)
        tvCategory = findViewById(R.id.tv_category)
        tvArea = findViewById(R.id.tv_area)
        tvInstructions = findViewById(R.id.tv_instructions)
        tvTags = findViewById(R.id.tv_tags)
        ivPathYoutube = findViewById(R.id.iv_path_youtube)

        fab.setOnClickListener {
            val intentSend = Intent()
            intentSend.action = Intent.ACTION_SEND
            intentSend.putExtra(Intent.EXTRA_TEXT, tvMeals.text)
            intentSend.type = "text/plain"
            startActivity(intentSend)
        }
    }

    private fun loadDetail(id: String?) {
        detailMealsPresenter.getDetail(id)
    }

    override fun showLoading() {

    }

    override fun onResponse(list: ArrayList<Meals>?) {
        list?.let { it ->
            for (i in it.indices) {
                Glide.with(this).load(list[i].strMealThumb).into(ivImage)
                tvMeals.text = list[i].strMeal
                tvCategory.text = list[i].strCategory
                tvArea.text = list[i].strArea
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    tvInstructions.text = Html.fromHtml(list[i].strInstructions, Html.FROM_HTML_MODE_COMPACT)
                } else {
                    tvInstructions.text = Html.fromHtml(list[i].strInstructions)
                }
                tvTags.text = list[i].strTags
                Glide.with(this).load(R.drawable.ic_youtube).into(ivPathYoutube)

                ivPathYoutube.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(list[i].strYoutube))
                    startActivity(intent)
                }
            }
        }
    }

    override fun onError(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun hideLoading() {

    }
}
