import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParseException
import com.google.gson.JsonDeserializationContext
import com.hellosplash.androidassignment.photolist.data.Photo
import java.lang.reflect.Type

class PhotoDeserializer : JsonDeserializer<Photo> {

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Photo {
        if (json == null || json !is JsonObject) {
            throw JsonParseException("Invalid JSON for Photo object")
        }

        val jsonObject = json.asJsonObject

        val id = jsonObject.get("id")?.asString ?: throw JsonParseException("Missing 'id' field")

        val likes = getJsonString(jsonObject, "likes")
        val description = getJsonString(jsonObject, "alt_description")
        val urlRegular = getJsonString(jsonObject.getAsJsonObject("urls"), "regular")
        val locationName = getJsonString(jsonObject.getAsJsonObject("location"), "name")

        return Photo(
            id = id,
            urlRegular = urlRegular,
            locationName = locationName,
            likes = likes,
            description = description
        )
    }

    private fun getJsonString(jsonObject: JsonObject?, field: String): String? {
        if (jsonObject == null) return null

        val element = jsonObject.get(field)
        return when {
            element == null || element.isJsonNull -> null
            else -> element.asString
        }
    }
}
