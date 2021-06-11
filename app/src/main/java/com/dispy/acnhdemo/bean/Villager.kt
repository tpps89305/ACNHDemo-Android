package com.dispy.acnhdemo.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Villager (
    val id: Long,
    @SerializedName("file-name") val fileName: String,
    val name: Name,
    val personality: Personality,
    @SerializedName("birthday-string") val birthdayString: String,
    val birthday: String,
    val species: String,
    val gender: Gender,
    val subtype: Subtype,
    val hobby: Hobby,
    @SerializedName("catch-phrase") val catchPhrase: String,
    @SerializedName("icon_uri") val iconURI: String,
    @SerializedName("image_uri") val imageURI: String,
    @SerializedName("bubble-color") val bubbleColor: String,
    @SerializedName("text-color") val textColor: String,
    val saying: String,
    @SerializedName("catch-translations") val catchTranslations: CatchTranslations
) : Serializable

data class CatchTranslations (
    @SerializedName("catch-USen") val catchUSen: String,
    @SerializedName("catch-EUen") val catchEUen: String,
    @SerializedName("catch-EUde") val catchEUde: String,
    @SerializedName("catch-EUes") val catchEUes: String,
    @SerializedName("catch-USes") val catchUSes: String,
    @SerializedName("catch-EUfr") val catchEUfr: String,
    @SerializedName("catch-USfr") val catchUSfr: String,
    @SerializedName("catch-EUit") val catchEUit: String,
    @SerializedName("catch-EUnl") val catchEUnl: String,
    @SerializedName("catch-CNzh") val catchCNzh: String,
    @SerializedName("catch-TWzh") val catchTWzh: String,
    @SerializedName("catch-JPja") val catchJPja: String,
    @SerializedName("catch-KRko") val catchKRko: String,
    @SerializedName("catch-EUru") val catchEUru: String
)

enum class Gender {
    Female,
    Male
}

enum class Hobby {
    Education,
    Fashion,
    Fitness,
    Music,
    Nature,
    Play
}

data class Name (
    @SerializedName("name-USen") val nameUSen: String,
    @SerializedName("name-EUen") val nameEUen: String,
    @SerializedName("name-EUde") val nameEUde: String,
    @SerializedName("name-EUes") val nameEUes: String,
    @SerializedName("name-USes") val nameUSes: String,
    @SerializedName("name-EUfr") val nameEUfr: String,
    @SerializedName("name-USfr") val nameUSfr: String,
    @SerializedName("name-EUit") val nameEUit: String,
    @SerializedName("name-EUnl") val nameEUnl: String,
    @SerializedName("name-CNzh") val nameCNzh: String,
    @SerializedName("name-TWzh") val nameTWzh: String,
    @SerializedName("name-JPja") val nameJPja: String,
    @SerializedName("name-KRko") val nameKRko: String,
    @SerializedName("name-EUru") val nameEUru: String
)

enum class Personality {
    Cranky,
    Jock,
    Lazy,
    Normal,
    Peppy,
    Smug,
    Snooty,
    Uchi
}

enum class Subtype {
    A,
    B
}
