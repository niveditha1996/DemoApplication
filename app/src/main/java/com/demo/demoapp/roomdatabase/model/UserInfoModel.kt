package com.demo.demoapp.roomdatabase.model

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
class UserInfoModel : Serializable {
    @PrimaryKey
    @NonNull
    @SerializedName("id")
    @Expose
    var id: Int = 0
    @SerializedName("email")
    @Expose
    var email: String? = null
    @SerializedName("first_name")
    @Expose
    var firstName: String? = null
    @SerializedName("last_name")
    @Expose
    var lastName: String? = null
    @SerializedName("avatar")
    @Expose
    var avatar: String? = null

}