package pl.com.mmotak.simpleweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navigationController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        navigationController = Navigation.findNavController(this, R.id.nav_host_fragment)
        bottom_navigation.setupWithNavController(navigationController)
        NavigationUI.setupActionBarWithNavController(this, navigationController)
    }

    override fun onSupportNavigateUp(): Boolean = NavigationUI.navigateUp(navigationController, null )
}
