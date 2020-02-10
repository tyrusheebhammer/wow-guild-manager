package com.sonnebtb.wowguildmanager.guildselection

data class Guild(
    var guildName: String = "Name",
    var guildRealm: String = "Realm"
){
    companion object{
        const val EXTRA_GUILD_NAME = "EXTRA_GUILD_NAME"
        const val EXTRA_GUILD_REALM = "EXTRA_GUILD_REALM"
    }
}