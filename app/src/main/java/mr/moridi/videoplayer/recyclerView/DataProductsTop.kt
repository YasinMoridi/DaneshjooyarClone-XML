package mr.moridi.videoplayer.recyclerView

import android.os.Parcel
import android.os.Parcelable

data class DataProductsTop(
    val title: String,
    val imgAddress: Int
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeInt(imgAddress)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DataProductsTop> {
        override fun createFromParcel(parcel: Parcel): DataProductsTop {
            return DataProductsTop(parcel)
        }

        override fun newArray(size: Int): Array<DataProductsTop?> {
            return arrayOfNulls(size)
        }
    }
}

