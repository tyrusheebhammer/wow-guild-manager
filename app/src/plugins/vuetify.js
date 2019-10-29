import Vue from 'vue';
import Vuetify from 'vuetify/lib';

import colors from 'vuetify/lib/util/colors'
Vue.use(Vuetify);

export default new Vuetify({
  theme: {
    dark: true,
    themes: {
      dark: {
        primary:"#F57CFF",
        secondary: colors.white,
        darkgray: "#2B2B2B",
        lightgray: "#828282",
        background: "#444444",
        deathknight: '#C41F3B',
        demonhunter: '#A330C9',
        druid: '#FF7D0A',
        hunter: '#A9D271',
        mage: '#40C7EB',
        monk: '#00FF96',
        paladin: '#F58CBA',
        priest: '#FFFFFF',
        rogue: '#FFF569',
        shaman: '#0070DE',
        warlock: '#8787ED',
        warrior: '#C79C6E'
      },
      
    },
    options: {
      customProperties: true,
    },
  },
  icons: {
    iconfont: 'mdi'
  },
});
