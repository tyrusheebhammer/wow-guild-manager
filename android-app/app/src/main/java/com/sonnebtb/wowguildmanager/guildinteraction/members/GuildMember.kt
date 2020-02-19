package com.sonnebtb.wowguildmanager.guildinteraction.members

import android.os.Parcelable
import com.sonnebtb.wowguildmanager.R
import kotlinx.android.parcel.Parcelize
import java.util.*

const val MAX_LEVEL = 120

fun generateRandomMember(): GuildMember {
    val characterClass = CharacterClasses.ALL.random()
    val characterLevel = Random().nextInt(MAX_LEVEL)
    val characterRank = Random().nextInt(10)
    val characterItemLevel = Random().nextInt(485)
    val characterRaiderIOScore = Random().nextInt(4000)
    return GuildMember(
        characterClass.name,
        characterClass,
        characterLevel,
        characterRank,
        characterItemLevel,
        characterRaiderIOScore
    )
}

@Parcelize
data class GuildMember(
    val name: String,
    val characterClass: CharacterClass,
    val level: Int,
    val rank: Int,
    val itemLevel: Int,
    val raiderIOScore: Int
) : Parcelable

@Parcelize
data class CharacterClass(val name: String, val color: Int) : Parcelable

object CharacterClasses {
    fun idToClass(id: Int): CharacterClass {
        return when (id) {

            1 -> WARRIOR
            2 -> PALADIN //
            3 -> HUNTER //
            4 -> ROGUE //
            5 -> PRIEST //
            6 -> DEATH_KNIGHT //
            7 -> SHAMAN //
            8 -> MAGE //
            9 -> WARLOCK //
            10 -> MONK //
            11 -> DRUID //
            12 -> DEMON_HUNTER
            else -> EMPTY
        }
    }

    val DEATH_KNIGHT = CharacterClass("Death Knight", R.color.colorDK)
    val DEMON_HUNTER = CharacterClass("Demon Hunter", R.color.colorDH)
    val DRUID = CharacterClass("Druid", R.color.colorDr)
    val HUNTER = CharacterClass("Hunter", R.color.ColorHu)
    val MAGE = CharacterClass("Mage", R.color.ColorMa)
    val MONK = CharacterClass("Monk", R.color.ColorMo)
    val PALADIN = CharacterClass("Paladin", R.color.ColorPa)
    val PRIEST = CharacterClass("Priest", R.color.ColorPr)
    val ROGUE = CharacterClass("Rogue", R.color.ColorRo)
    val SHAMAN = CharacterClass("Shaman", R.color.ColorSh)
    val WARLOCK = CharacterClass("Warlock", R.color.ColorWarlock)
    val WARRIOR = CharacterClass("Warrior", R.color.ColorWarrior)
    val EMPTY = CharacterClass("EMPTY", android.R.color.darker_gray)
    val ALL = arrayListOf(
        DEATH_KNIGHT,
        DEMON_HUNTER,
        DRUID,
        HUNTER,
        MAGE,
        MONK,
        PALADIN,
        PRIEST,
        ROGUE,
        SHAMAN,
        WARLOCK,
        WARRIOR
    )
}

@Parcelize
data class ApiMember(
    var character: ApiCharacter,
    var rank: Int = 0
) : Parcelable

@Parcelize
data class ApiCharacter(
    var name: String = "",
    var id: Int = 0,
    var realm: ApiRealm = ApiRealm(),
    var level: Int = 0,
    var playable_class: PlayableClass = PlayableClass(),
    var playable_race: PlayableRace = PlayableRace()
) : Parcelable

@Parcelize
data class ApiRealm(
    var key: ApiKey = ApiKey(),
    var id: Int = 0,
    var slug: String = ""
) : Parcelable

@Parcelize
data class ApiKey(
    var href: String = ""
) : Parcelable

@Parcelize
data class PlayableClass(
    var key: ApiKey = ApiKey(),
    var id: Int = 0
) : Parcelable

@Parcelize
data class PlayableRace(
    var key: ApiKey = ApiKey(),
    var id: Int = 0
) : Parcelable