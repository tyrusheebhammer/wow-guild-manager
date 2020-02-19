package com.sonnebtb.wowguildmanager.responses

import android.util.Log
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.sonnebtb.wowguildmanager.Constants

object BlizzardEndpointHelper {
    private val mapper = jacksonObjectMapper()

    private const val API_CHARACTERS = "/wow/user/characters"
    private const val API_GUILD = "/data/wow/guild"
    private const val API_CHARACTER = "/wow/character"
    private const val AUTH_USER_INFO = "/oauth/userinfo"

    private const val HOST_AUTH = "https://us.battle.net"
    private const val HOST_API = "https://us.api.blizzard.com"
    private var token: String? = null
    private var tokenParam: String? = null
    fun setToken(token: String) {
        this.token = token
        this.tokenParam = "access_token=$token"
    }

    //would be good idea to add a delegate here to check that the token has been completed
    fun getUserInfo() : UserInfo? {
        val userInfoEndpoint = "$HOST_AUTH$AUTH_USER_INFO?$tokenParam"
        var userInfo: UserInfo? = null
        userInfoEndpoint.httpGet().responseString { request, response, result ->
            when(result) {
                is Result.Success -> {

                    userInfo = mapper.readValue<UserInfo>(result.get())
                    Log.d(Constants.TAG, "got user info: $userInfo")
                }
                is Result.Failure -> Log.d(Constants.TAG, "Failed yo ${result.error}")
            }
        }
        return userInfo
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

    private fun compoundCheck(guild: Guild, new: String): Boolean { return guild.compound == new }
}



//fun getUniqueGuilds(characters): {
//    let guilds = []
//    characters.forEach(character => {
//        let guildCompound = character.guild + character.guildRealm
//        let testingFunction = guild => guild.compound === guildCompound
//                if (!guilds.some(testingFunction)) {
//                    if (!isString(guildCompound)) return
//                    guilds.push({
//                        compound: guildCompound,
//                        name: character.guild,
//                        realm: character.guildRealm,
//                        characters: [character]
//                    })
//                } else {
//                    let guildIndex = guilds.findIndex(testingFunction)
//                    guilds[guildIndex].characters.push(character);
//                }
//    });
//    return guilds
//}