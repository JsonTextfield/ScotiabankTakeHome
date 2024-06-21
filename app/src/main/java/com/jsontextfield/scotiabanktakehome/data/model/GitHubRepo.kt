package com.jsontextfield.scotiabanktakehome.data.model

import android.os.Parcel
import android.os.Parcelable
import org.json.JSONObject

data class GitHubRepo(
    val name: String = "",
    val description: String = "",
    val lastUpdated: String = "",
    val stars: Int = 0,
    val forks: Int = 0,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readInt(),
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeString(lastUpdated)
        parcel.writeInt(stars)
        parcel.writeInt(forks)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GitHubRepo> {
        override fun createFromParcel(parcel: Parcel): GitHubRepo {
            return GitHubRepo(parcel)
        }

        override fun newArray(size: Int): Array<GitHubRepo?> {
            return arrayOfNulls(size)
        }

        fun fromJson(jsonObject: JSONObject): GitHubRepo {
            val description = if (jsonObject.isNull("description")) {
                ""
            } else {
                jsonObject.optString("description")
            }
            return GitHubRepo(
                name = jsonObject.optString("name"),
                description = description,
                lastUpdated = jsonObject.optString("updated_at"),
                stars = jsonObject.optInt("stargazers_count"),
                forks = jsonObject.optInt("forks"),
            )
        }
    }
}
