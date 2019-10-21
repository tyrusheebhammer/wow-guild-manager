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
