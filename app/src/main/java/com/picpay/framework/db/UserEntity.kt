package com.picpay.framework.db

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.picpay.core.data.User
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id") val id: Int = 0,
    @SerializedName("name") val name: String,
    @SerializedName("username") val username: String,
    @SerializedName("img") val img: String
):Parcelable {
    companion object {
        fun fromUser(user: User) = UserEntity(
            id = user.id,
            name = user.name,
            img = user.img,
            username = user.username
        )
    }
    fun toUSer() = User(id, name, username, img)

}