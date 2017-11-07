package unicauca.movil.beacons.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class BeaconReceiver(val callback:(minor:Int, major:Int)->Unit):BroadcastReceiver(){

    override fun onReceive(context: Context?, intent: Intent) {
        val minor = intent.extras.getInt(EXTRA_MINOR)
        val major = intent.extras.getInt(EXTRA_MAJOR)
        callback(minor, major)
    }

    companion object {
        val ACTION = "unicauca.movil.beacons.region"
        val EXTRA_MAJOR = "major"
        val EXTRA_MINOR = "minor"
    }

}
