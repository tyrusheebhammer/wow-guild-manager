package com.sonnebtb.wowguildmanager.responses

import android.os.Parcelable
import android.util.Log
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.sonnebtb.wowguildmanager.Constants
import com.sonnebtb.wowguildmanager.guildinteraction.members.ApiCharacter
import com.sonnebtb.wowguildmanager.guildinteraction.members.ApiMember
import kotlinx.android.parcel.Parcelize

object BlizzardEndpointHelper {
    private val mapper = jacksonObjectMapper()

    private const val API_CHARACTERS = "/wow/user/characters"
    private const val API_GUILD = "/data/wow/guild"
    private const val API_CHARACTER = "/wow/character"
    private const val AUTH_USER_INFO = "/oauth/userinfo"
    private const val FUNCTIONS_BLIZZARD_API_GET = "/blizzardApiGet"

    private const val HOST_AUTH = "https://us.battle.net"
    private const val HOST_API = "https://us.api.blizzard.com"
    private const val HOST_FUNCTIONS = "https://us-central1-wow-guild-manager.cloudfunctions.net"

    private var token: String? = null
    private var tokenParam: String? = null
    fun setToken(token: String) {
        this.token = token
        this.tokenParam = "access_token=$token"
    }

    //would be good idea to add a delegate here to check that the token has been completed
    fun getUserInfo(callback: (userInfo: UserInfo) -> Unit){
        val userInfoEndpoint = "$HOST_AUTH$AUTH_USER_INFO?$tokenParam"
        var userInfo: UserInfo? = null
        userInfoEndpoint.httpGet().responseString { request, response, result ->
            when(result) {
                is Result.Success -> {

                    userInfo = mapper.readValue<UserInfo>(result.get())
                    Log.d(Constants.TAG, "got user info: $userInfo")
                    callback(userInfo!!)
                }
                is Result.Failure -> Log.d(Constants.TAG, "Failed yo ${result.error}")
            }
        }
    }

    fun getUserCharacters(callback: (characters: List<Character>?) -> Unit){
        val charactersEndpoint = "$HOST_API$API_CHARACTERS?$tokenParam"
        Log.d(Constants.TAG, charactersEndpoint)
        var characters: List<Character>? = null
        charactersEndpoint.httpGet().responseString { request, response, result ->
            when(result) {
                is Result.Success -> {
                    var charactersString = result.get()
                    val regex = Regex(pattern = "class")

                    //Snipping off the dictionary containing the list
                    charactersString = charactersString
                        .replace(regex, "_class")
                        .replace("{\"characters\":","")
                    charactersString = charactersString
                        .substring(0, charactersString.length - 1)

                    characters = mapper.readValue(charactersString)
                    callback(characters)
                }

                is Result.Failure -> Log.d(Constants.TAG, "Failed yo ${result.error}")
            }
        }
    }

    fun getUniqueGuilds(characters: List<Character>): List<Guild> {
        var guilds = ArrayList<Guild>()

        for(c in characters) {
            var guildCompound = c.guild + c.guildRealm
            if(!guilds.any { guild -> compoundCheck(guild, guildCompound) }) {
                val guild = Guild(
                    compound = guildCompound,
                    name = c.guild,
                    realm = c.guildRealm,
                    characters = arrayListOf(c))
                Log.d(Constants.TAG, "$guild")
                guilds.add(guild)
            } else {
                val guild = guilds.find { guild -> compoundCheck(guild, guildCompound) }
                guild?.characters?.add(c)
            }
        }
        return guilds.filter { it.compound !== "" }
    }

    fun generateRosterForSelectedGuild(
            guild: Guild,
            callback: (guildMembers: ArrayList<MemberCharacter>) -> Unit
    ) {
        Log.d(Constants.TAG, "generating roster")
        var guildNameSlug = generateSlug(guild.name)
        var guildRealmSlug = generateSlug(guild.realm)

        var functionsEndpoint =
        HOST_FUNCTIONS +
                FUNCTIONS_BLIZZARD_API_GET +
                "?action=roster" +
                "&host=${HOST_API}" +
                "&guildendpoint=${API_GUILD}" +
                "&characterendpoint=${API_CHARACTER}" +
                "&realmslug=${guildRealmSlug}" +
                "&guildslug=${guildNameSlug}" +
                "&namespace=profile-us" +
                "&locale=en_US" +
                "&access_token=${token}"
        Log.d(Constants.TAG, "endpoint: $functionsEndpoint")
        functionsEndpoint.httpGet().responseString {request, response, result ->
            when(result) {
                is Result.Success -> {
                    val resultStr = result.get().replace("\"class", "\"_class")
                    val apiMembersCharacters : ApiMembersCharacters = mapper.readValue(resultStr)
                    Log.d(Constants.TAG, "$apiMembersCharacters")

                    callback(parseApiMembersCharacters(apiMembersCharacters))
                }
                is Result.Failure -> {Log.e(Constants.TAG, "failed members ${result.getException()}")}
            }
        }

//        await axios
//                .get(functionsEndpoint)
//            .then(res => {
//                let members = res.data.members
//                        let characters = res.data.characters
//                        let guildMembers = []
//                members.forEach((member, index) => {
//                let character = characters.find(character => member.character.name === character.name)
//                if (isUndefined(character)) {
//                    return
//                }
//                let fullCharacter = Object.assign({}, member.character, character)
//                let guildMember = Object.assign({}, member, {
//                    character: fullCharacter
//            })
//                guildMembers.push(guildMember)
//            })
//
//
//                commit('updateMembersAndCharactersForGuild', {
//                    guildMembers
//                })
//            })
    }

    private fun parseApiMembersCharacters(apiMembersCharacters: ApiMembersCharacters): ArrayList<MemberCharacter> {
        var guildMembers = ArrayList<MemberCharacter>()
        for(m in apiMembersCharacters.members) {
            var character = apiMembersCharacters.characters.find {
                it.name == m.character.name
            }

            character?.let {
                guildMembers.add(
                    MemberCharacter(m, it)
                )
            }
        }
        Log.d(Constants.TAG, "$guildMembers")
        return guildMembers
    }

    private fun compoundCheck(guild: Guild, new: String): Boolean { return guild.compound == new }

    fun generateSlug(value: String): String {
        return value
            .toLowerCase()
            .replace("'", "")
            .replace(" ", "-")

    }
}

@Parcelize
data class MemberCharacter(
    var member: ApiMember,
    var character: Character
): Parcelable

@Parcelize
data class ApiMembersCharacters(
    var characters: MutableList<Character> = ArrayList(),
    var members: MutableList<ApiMember> = ArrayList()
): Parcelable