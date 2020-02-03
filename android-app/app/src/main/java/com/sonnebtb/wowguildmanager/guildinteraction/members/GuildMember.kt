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
    return GuildMember(characterClass.name, characterClass, characterLevel, characterRank, characterItemLevel, characterRaiderIOScore)
}

@Parcelize
data class GuildMember(
    val name: String,
    val characterClass: CharacterClass,
    val level: Int,
    val rank: Int,
    val itemLevel: Int,
    val raiderIOScore: Int
): Parcelable

@Parcelize
data class CharacterClass(val name: String, val color: Int): Parcelable

object CharacterClasses {
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
    val ALL = arrayListOf(DEATH_KNIGHT, DEMON_HUNTER, DRUID, HUNTER, MAGE, MONK, PALADIN, PRIEST, ROGUE, SHAMAN, WARLOCK, WARRIOR)
}