package com.example.mywheaterapp.view

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SearchView
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mywheaterapp.adapter.WeatherAdapter
import com.example.mywheaterapp.viewmodel.WeatherViewModel
import kotlinx.android.synthetic.main.activity_main.*
import android.location.Geocoder
import android.os.Looper
import android.provider.Settings
import com.example.mywheaterapp.R
import com.example.mywheaterapp.repository.model.*
import com.example.mywheaterapp.util.*
import com.google.android.gms.location.*
import java.util.*


class MainActivity : AppCompatActivity(){

    private lateinit var mFusedLocationClient: FusedLocationProviderClient
    private var adapter1: WeatherAdapter? = null
    private var adapter2: WeatherAdapter? = null
    private val LOCATION_PERMISSION_ID: Int = 100
    private val LOCATION_SETTINGS_REQUEST: Int = 101
    private var cityName : String = ""
    private val weatherViewModel: WeatherViewModel? by lazy {
         ViewModelProviders.of(this, WeatherViewModelFactory(application,cityName = cityTV.text.toString())).get(WeatherViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclers()
        getCurrentLocationAndGetWeather()
    }

    private fun initRecyclers(){
        weatherTodayRecycler.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL ,false)
        weatherNext5daysRecycler.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL ,false)
        adapter1 = WeatherAdapter()
        adapter2 = WeatherAdapter()
    }

    private fun getCurrentLocationAndGetWeather(){
      showLoading()
      if (checkPermissions()) {
        if (isLocationEnabled()) {

            mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

            mFusedLocationClient.lastLocation.addOnCompleteListener(this) { task ->
                var location: Location? = task.result
                if (location == null) {
                    requestNewLocationData()
                } else {
                    updateLocationUIAndGetWeather(location)
                }
            }
          } else {
            Toast.makeText(this, "Turn on location", Toast.LENGTH_LONG).show()
            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            startActivityForResult(intent, LOCATION_SETTINGS_REQUEST)
          }
        }else{
          requestPermissions()
      }
    }


    private fun updateLocationUIAndGetWeather(location: Location){
        Log.i("myTag", "onLocationChanged")
        val geocoder = Geocoder(applicationContext, Locale.getDefault())
        val addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)
        if (addresses.isEmpty()) {
            toastError(ErrorTypes.NO_RESULT_FOUND)
        }else{
            cityName = addresses[0].locality
            cityTV.text = cityName
            getWeatherByLocation("")
            getHourlyForecast()
            getDailyForecast()
        }
    }

    private fun requestNewLocationData() {
        var mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = 0
        mLocationRequest.fastestInterval = 0
        mLocationRequest.numUpdates = 1

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        mFusedLocationClient!!.requestLocationUpdates(
            mLocationRequest, mLocationCallback,
            Looper.myLooper()
        )
    }

    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            var mLastLocation: Location = locationResult.lastLocation
            updateLocationUIAndGetWeather(mLastLocation)
        }
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION),
            LOCATION_PERMISSION_ID
        )
    }
    private fun checkPermissions(): Boolean {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            return true
        }
        return false
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == LOCATION_PERMISSION_ID) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                getCurrentLocationAndGetWeather()
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == LOCATION_SETTINGS_REQUEST) {
            getCurrentLocationAndGetWeather()
        }
    }

    private fun isLocationEnabled(): Boolean {
        var locationManager: LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }
    private fun getWeatherByLocation(searchText:String) {

        if(searchText.isEmpty()) {
            weatherViewModel?.getWeatherLiveData(application)?.observe(this, Observer<CurrentWeather> { result ->
                    setWeatherUI(result)
                    Log.i("myTag","getWeatherByLocation")
            })
        }else{
            //searching
            weatherViewModel?.updateWeatherBySearch(application,searchText)
            Log.i("myTag","searchWeatherByLocation")
        }

    }
    private fun getHourlyForecast(){

        weatherViewModel?.getHourlyForecastLiveData(application)?.observe(this, Observer<List<WeatherItemModel>> { result ->
            adapter1?.addWeather(result)
            weatherTodayRecycler.adapter = adapter1
        })
    }

    private fun getDailyForecast(){
        weatherViewModel?.getDailyForecastLiveData(application)?.observe(this, Observer<List<WeatherItemModel>> { result ->
            adapter2?.addWeather(result)
            weatherNext5daysRecycler.adapter = adapter2
        })
        hideLoading()
    }


    fun setWeatherUI(response: CurrentWeather) {
        humadityTV.text = response.humidity.toString()+"%"
        minTempTV.text = (response.tempMin).toString() + "˚"
        maxTempTV.text = response.tempMax.toString()+"˚"
        pressureTV.text = response.pressure.toString()
        temperatureTV.text = ""+response.temp?.toInt()+"˚"
        cloudsTV.text = response.description.toString()
        cityTV.text = response.name.toString()
    }


    fun showErrorToast(errorType: ErrorTypes) {
        when (errorType) {
            ErrorTypes.CALL_ERROR -> toast("connection error")
            ErrorTypes.MISSING_API_KEY -> toast("API KEY is missing")
            ErrorTypes.NO_RESULT_FOUND -> toast("City not found")
        }
        loadingSpinner.visibility = View.GONE
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        val menuItem = menu?.findItem(R.id.search_button)
        val searchMenuItem = menuItem?.actionView

        if (searchMenuItem is SearchView) {
            searchMenuItem.queryHint = "search"
            searchMenuItem.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {

                    getWeatherByLocation(query)
                    menuItem.collapseActionView()
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }

            })
        }
        return true
    }
}
