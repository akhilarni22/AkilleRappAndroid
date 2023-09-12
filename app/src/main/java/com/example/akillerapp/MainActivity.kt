@file:Suppress("KotlinConstantConditions")

package com.example.akillerapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.akillerapp.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.rudderstack.android.sdk.core.RudderClient
import com.rudderstack.android.sdk.core.RudderConfig
import com.rudderstack.android.sdk.core.RudderOption
import com.rudderstack.android.sdk.core.RudderProperty
import com.rudderstack.android.sdk.core.RudderTraits
import java.util.Date


class MainActivity : AppCompatActivity() {

    private lateinit var firebaseAnalytics: FirebaseAnalytics
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAnalytics = Firebase.analytics

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)


        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }


        val rudderClient =
            RudderClient.getInstance(
                this,
                "2U6vaA92DXZkTcWp3uMQGLV4CPQ",
                RudderConfig.Builder()
                    .withDataPlaneUrl("https://rudderstachcyf.dataplane.rudderstack.com")
                    .withTrackLifecycleEvents(true)
                    .withRecordScreenViews(true)
                    .withLogLevel(3)
                    .build()
            )
        val pageCall = findViewById<Button>(R.id.page)
        pageCall.setOnClickListener {
            rudderClient.screen(
                "MainActivity",
                "HomeScreen",
                RudderProperty().putValue("foo", "bar"),
                null
            )

            Toast.makeText(this, "Page Clicked", Toast.LENGTH_SHORT).show()
        }

        val identifyCall = findViewById<Button>(R.id.identify)
        identifyCall.setOnClickListener {
            val externalId = firebaseAnalytics.appInstanceId.toString()
            val traits = RudderTraits()
            traits.putBirthday(Date())
            traits.putEmail("abc@123.com")
            traits.putFirstName("First")
            traits.putLastName("Last")
            traits.putGender("m")
            traits.putPhone("5555555555")
            //traits.put("ga4AppInstanceId", externalId.toString() )
            //traits.put("externalId", externalId.toString() )

            val opt=RudderOption()
            opt.putExternalId("ga4AppInstanceId",externalId)


            val address = RudderTraits.Address()
            address.putCity("City")
            address.putCountry("USA")
            traits.putAddress(address)
            traits.put("boolean", true)
            traits.put("integer", 50)
            traits.put("float", 120.4f)
            traits.put("long", 1234L)
            traits.put("string", "hello")
            traits.put("date", Date(System.currentTimeMillis()))
            //val externalIdstring = externalId.toString()

            //val snackbar=Snackbar.make(snackbarview,this)

            rudderClient.identify("test_user_id", traits, opt)

            Toast.makeText(this, "Identify Clicked", Toast.LENGTH_SHORT).show()
            Toast.makeText(this, externalId, Toast.LENGTH_LONG).show()


            
        }

        val trackCall = findViewById<Button>(R.id.track)
        trackCall.setOnClickListener {
            rudderClient.track(
                "Track event",
                RudderProperty().putValue("testID", "product_001")
            )

            Toast.makeText(this, "Track Clicked", Toast.LENGTH_SHORT).show()

        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }


}


