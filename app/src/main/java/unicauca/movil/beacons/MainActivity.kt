package unicauca.movil.beacons

import android.content.IntentFilter
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.estimote.coresdk.common.requirements.SystemRequirementsChecker
import unicauca.movil.beacons.receivers.BeaconReceiver

class MainActivity : AppCompatActivity() {

    private val beaconReceiver:BeaconReceiver = BeaconReceiver(this::beaconDetected)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        SystemRequirementsChecker.checkWithDefaultDialogs(this)
    }

    override fun onResume() {
        super.onResume()
        val filter = IntentFilter(BeaconReceiver.ACTION)
        registerReceiver(beaconReceiver, filter)
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(beaconReceiver)
    }

    fun beaconDetected(minor:Int, major:Int){

    }
}
