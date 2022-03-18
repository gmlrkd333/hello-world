package com.example.test_project_1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.example.test_project_1.calrecy.CalendarAdapter
import com.example.test_project_1.calrecy.CalendarDateModel
import com.example.test_project_1.databinding.CalendarItemBinding
import com.example.test_project_1.foodrecy.Foodmodel
import com.example.test_project_1.foodrecy.RecyclerAdapter
import com.example.test_project_1.login.Login
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.time.Year
import java.util.*

class CalendarPage : Fragment() {
    private val cal = Calendar.getInstance()
    private val currentDate = Calendar.getInstance()
    private lateinit var adapter: CalendarAdapter
    private val date = ArrayList<Date>()
    private val calList = ArrayList<CalendarDateModel>()
    private val sdf = SimpleDateFormat("yyyy년 MM월")
    private val sdf2 = SimpleDateFormat("yyyyMMdd")
    var today = sdf2.format(System.currentTimeMillis())
    var selectDay = today

    private var daynum: Int = 0

    private lateinit var icpre: ImageView
    private lateinit var icnext: ImageView
    private lateinit var calmonth: TextView
    private lateinit var todaykcal: TextView
    private lateinit var goalkcal: TextView
    private lateinit var camerabt: Button

    private lateinit var maxbar: View
    private lateinit var todaybar: View

    private lateinit var meal_time: RadioGroup
    private lateinit var breakfast: RadioButton
    private lateinit var lunch: RadioButton
    private lateinit var dinner: RadioButton

    private lateinit var calrecyview: RecyclerView
    private lateinit var foodrecyview: RecyclerView

    private var todayTotal: Int = 0
    private var maxkcal: Int = 2500

    private var maxwidth: Int = 0
    private var barwidth: Int = 0

    var time: String = "0"
    lateinit var textId: String
    lateinit var mDatas: ArrayList<Foodmodel>

    var retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.35.13:8000")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    var foodService = retrofit.create(FoodService::class.java)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.calendar_page, container, false)

        calrecyview = view.findViewById(R.id.recy_date)
        foodrecyview = view.findViewById(R.id.recyview)

        mDatas = requireActivity().intent!!.extras!!.get("DataList") as ArrayList<Foodmodel>
        textId = requireActivity().intent!!.extras!!.get("textId") as String
        var sex = requireActivity().intent!!.extras!!.get("sex") as String
        var weight = requireActivity().intent!!.extras!!.get("weight") as Int
        var height = requireActivity().intent!!.extras!!.get("height") as Int
        var age = requireActivity().intent!!.extras!!.get("age") as Int

        daynum = currentDate.get(Calendar.YEAR) * 10000 + (currentDate.get(Calendar.MONTH)+1) * 100 + currentDate.get(Calendar.DAY_OF_MONTH)

        icpre = view.findViewById(R.id.cal_pre)
        icnext = view.findViewById(R.id.cal_next)
        calmonth = view.findViewById(R.id.cal_month)
        todaykcal = view.findViewById(R.id.todaykcal)
        goalkcal = view.findViewById(R.id.goalkcal)
        camerabt = view.findViewById(R.id.camera_btn)

        maxbar = view.findViewById(R.id.maxbar)
        todaybar = view.findViewById(R.id.todaybar)

        breakfast = view.findViewById(R.id.breakfast)
        lunch = view.findViewById(R.id.lunch)
        dinner = view.findViewById(R.id.dinner)
        meal_time = view.findViewById(R.id.meal_time)

        foodService.searchFood(today+time, textId).enqueue(object: Callback<Food>{
            override fun onResponse(call: Call<Food>, response: Response<Food>) {
                var food = response.body() as Food
                if(food.code == "0000"){
                    mDatas.clear()
                    for (f in food.foods){
                        mDatas.add(Foodmodel("", f[0], f[1].toInt(), f[2].toInt(), f[3].toInt(), f[4].toInt(), f[5].toInt() ))
                    }
                }
                else{
                    Toast.makeText(getActivity(), "없어", Toast.LENGTH_SHORT).show()
                    mDatas.clear()
                }
                val recyadapter= RecyclerAdapter(requireContext(), mDatas)
                foodrecyview.adapter=recyadapter
                val mLayoutManager = LinearLayoutManager(context)
                foodrecyview.layoutManager = mLayoutManager
                foodrecyview.setHasFixedSize(true)
            }

            override fun onFailure(call: Call<Food>, t: Throwable) {
                Toast.makeText(getActivity(), "통신 실패", Toast.LENGTH_SHORT).show()
            }

        })

        meal_time.setOnCheckedChangeListener { radioGroup, i ->
            when(i){
                R.id.breakfast -> time = "0"
                R.id.lunch -> time = "1"
                R.id.dinner -> time = "2"
            }
            //test

            foodService.searchFood(selectDay+time, textId).enqueue(object: Callback<Food>{
                override fun onResponse(call: Call<Food>, response: Response<Food>) {
                    var food = response.body() as Food
                    if(food.code == "0000"){
                        mDatas.clear()
                        for (f in food.foods){
                            mDatas.add(Foodmodel("", f[0], f[1].toInt(), f[2].toInt(), f[3].toInt(), f[4].toInt(), f[5].toInt() ))
                        }
                    }
                    else{
                        Toast.makeText(getActivity(), "없어", Toast.LENGTH_SHORT).show()
                        mDatas.clear()
                    }
                    val recyadapter= RecyclerAdapter(requireContext(), mDatas)
                    foodrecyview.adapter=recyadapter
                    val mLayoutManager = LinearLayoutManager(context)
                    foodrecyview.layoutManager = mLayoutManager
                    foodrecyview.setHasFixedSize(true)
                }

                override fun onFailure(call: Call<Food>, t: Throwable) {
                    Toast.makeText(getActivity(), "통신 실패", Toast.LENGTH_SHORT).show()
                }

            })
        }


        setUpAdapter(mDatas, calrecyview)
        setUpClickListener(calmonth)
        setUpCalendar(calmonth)

        camerabt.setOnClickListener {
            var intent = Intent(getActivity(), CameraPage::class.java)
            intent.putExtra("textId", textId)
            intent.putExtra("time", time)
            intent.putExtra("sex", sex)
            intent.putExtra("weight", weight)
            intent.putExtra("height", height)
            intent.putExtra("age", age)
            startActivityForResult(intent, 0)
        }


        val recyadapter= RecyclerAdapter(requireContext(), mDatas)
        foodrecyview.adapter=recyadapter
        val mLayoutManager = LinearLayoutManager(context)
        foodrecyview.layoutManager = mLayoutManager
        foodrecyview.setHasFixedSize(true)

        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            var cal = data!!.getStringExtra("cal")
            Toast.makeText(getActivity(), cal, Toast.LENGTH_SHORT).show()
            
            foodService.searchFood(today+time, textId).enqueue(object: Callback<Food>{
                override fun onResponse(call: Call<Food>, response: Response<Food>) {
                    var food = response.body() as Food
                    if(food.code == "0000"){
                        mDatas.clear()
                        for (f in food.foods){
                            mDatas.add(Foodmodel("", f[0], f[1].toInt(), f[2].toInt(), f[3].toInt(), f[4].toInt(), f[5].toInt() ))
                        }
                    }
                    else{
                        Toast.makeText(getActivity(), "없어", Toast.LENGTH_SHORT).show()
                        mDatas.clear()
                    }
                    val recyadapter= RecyclerAdapter(requireContext(), mDatas)
                    foodrecyview.adapter=recyadapter
                    val mLayoutManager = LinearLayoutManager(context)
                    foodrecyview.layoutManager = mLayoutManager
                    foodrecyview.setHasFixedSize(true)
                }

                override fun onFailure(call: Call<Food>, t: Throwable) {
                    Toast.makeText(getActivity(), "통신 실패", Toast.LENGTH_SHORT).show()
                }

            })
        }
    }

    private fun setUpClickListener(calmonth: TextView) {
        icnext.setOnClickListener {
            cal.add(Calendar.MONTH, 1)
            setUpCalendar(calmonth)
        }
        icpre.setOnClickListener {
            cal.add(Calendar.MONTH, -1)
            if (cal == currentDate)
                setUpCalendar(calmonth)
            else
                setUpCalendar(calmonth)
        }
    }

    private fun setUpAdapter(mDatas: ArrayList<Foodmodel>, recyclerView: RecyclerView) {
        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)
        adapter = CalendarAdapter { calendarDateModel: CalendarDateModel, position: Int ->
            calList.forEachIndexed { index, calendarModel ->
                calendarModel.isSelected = index == position
            }
            calList.forEach {
                if(it.isSelected) {
                    daynum = sdf2.format(it.data).toString().toInt()
                    selectDay = sdf2.format(it.data)
                    goalkcal.setText(daynum.toString())

                    //test
                    foodService.searchFood(selectDay+time, textId).enqueue(object: Callback<Food>{
                        override fun onResponse(call: Call<Food>, response: Response<Food>) {
                            var food = response.body() as Food
                            if(food.code == "0000"){
                                Toast.makeText(getActivity(), "성공", Toast.LENGTH_SHORT).show()
                                mDatas.clear()
                                for (f in food.foods){
                                    mDatas.add(Foodmodel("", f[0], f[1].toInt(), f[2].toInt(), f[3].toInt(), f[4].toInt(), f[5].toInt() ))
                                }
                            }
                            else{
                                Toast.makeText(getActivity(), "없어", Toast.LENGTH_SHORT).show()
                                mDatas.clear()
                            }
                            val recyadapter= RecyclerAdapter(requireContext(), mDatas)
                            foodrecyview.adapter=recyadapter
                            val mLayoutManager = LinearLayoutManager(context)
                            foodrecyview.layoutManager = mLayoutManager
                            foodrecyview.setHasFixedSize(true)
                        }

                        override fun onFailure(call: Call<Food>, t: Throwable) {
                            Toast.makeText(getActivity(), "통신 실패", Toast.LENGTH_SHORT).show()
                        }

                    })
                }
            }
            adapter.setData(calList)
            setbar(mDatas)
        }

        recyclerView.adapter = adapter
    }

    private fun setUpCalendar(calmonth: TextView) {
        val calendarList = ArrayList<CalendarDateModel>()
        calmonth.text = sdf.format(cal.time)

        val monthCalendar = cal.clone() as Calendar
        val maxDaysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH)
        date.clear()
        monthCalendar.set(Calendar.DAY_OF_MONTH, 1)
        while (date.size < maxDaysInMonth) {
            date.add(monthCalendar.time)
            calendarList.add(CalendarDateModel(monthCalendar.time))
            monthCalendar.add(Calendar.DAY_OF_MONTH, 1)
        }
        calList.clear()
        calList.addAll(calendarList)
        adapter.setData(calendarList)
    }

    fun todaysum(mDatas: ArrayList<Foodmodel>, daynum: Int): Int {
        var total = 0
        mDatas.forEach {
            if((it.food_time / 10) == daynum)
                total += it.food_kcal
        }
        return total
    }

    fun kcalbar(width: Int, todayTotal: Int, maxkcal: Int): Int {
        var rate = (todayTotal*100) / maxkcal
        if(rate > 100) rate = 100
        var ratewidth = (width * rate) / 100

        return ratewidth
    }

    fun setbar(mDatas: ArrayList<Foodmodel>) {
        todayTotal = todaysum(mDatas, daynum)

        todaykcal.setText(todayTotal.toString())
        goalkcal.setText(maxkcal.toString())
        // 진행 바
        maxbar.viewTreeObserver.addOnGlobalLayoutListener(object: ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                maxwidth = maxbar.width
                barwidth = kcalbar(maxwidth, todayTotal, maxkcal)
                val params: FrameLayout.LayoutParams = todaybar.getLayoutParams() as FrameLayout.LayoutParams
                params.width = barwidth
                todaybar.setLayoutParams(params)
            }
        })
        if(todayTotal > maxkcal) {
            todaybar.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.red))
        }
        else {
            todaybar.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.orange))
        }
    }
}