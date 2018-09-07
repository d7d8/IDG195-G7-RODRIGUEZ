package mx.cetys.arambula.angel.micampus.model


import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Tutor(@SerializedName("nombre") val nombre: String) : Parcelable