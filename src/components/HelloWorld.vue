<template>
  <!-- navbar  -->
  <div class="navbar">
    <ul>
      <li class="logo">
        <a href="/"><img src="../assets/logoFso.png" alt="" /></a>
      </li>
    </ul>
    <ul class="acces">
      <li>
        <span class="input-icon"><i class="ico-search2"></i></span
        ><input
          v-model="searchQuery"
          type="search"
          name="S"
          placeholder="بحث في الايات و التفاسير"
          aria-label="Search"
        /><button class="button2" @click="performSearch">بحث</button>
      </li>
      <li><a href="/ContactUs">مساعدة</a></li>
      <li><a href="/AsmaeMofassirin">المؤلفون</a></li>
      <li><a href="/">المصحف الكريم</a></li>
      <li><a href="/Tafassir">التفاسير</a></li>
    </ul>
    <ul class="login">
      <li>
        <button class="btnm2i" role="button">
          <img src="../assets/M2i.png" alt="" srcset="" />
        </button>
      </li>
    </ul>
  </div>
  <!-- Affichage de recherche -->
  <div class="names1" v-if="searchResults.length > 0">
    <table class="table table-striped">
      <thead>
        <th>السورة</th>
        <th>الاية</th>
        <th>المفسر</th>
        <th>التفسير</th>
      </thead>
      <tbody>
        <tr v-for="result in searchResults" :key="result.Nom">
          <td>{{ result.Nom }}</td>
          <td>{{ result.Aya }}</td>
          <td>{{ result.Nom_S }}</td>
          <td>{{ result.Texte }}</td>
        </tr>
      </tbody>
    </table>
  </div>
  <!-- Home page -->
  <div class="row">
    <!-- right page -->
    <div class="right">
      <div class="bar-search">
        <input
          type="text"
          v-model="searchQuery1"
          @input="searchSouar"
          placeholder="ابحث عن سورة"
        />

        <table v-if="searchQuery1 != ''">
          <tbody>
            <tr
              v-for="soura in souar"
              :key="soura.id"
              @click="showSourate(soura)"
            >
              <td>
                <a href="javascript:void(1)" @click="showSourate(soura)">{{
                  soura.nom
                }}</a>
              </td>
            </tr>
          </tbody>
        </table>
        <table v-else>
          <tbody>
            <tr v-for="surah in surahs" :key="surah.number">
              <td>
                <a href="javascript:void(1)" @click="showSourate(surah)">{{
                  surah.nom
                }}</a>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    <!-- middle page @click="affichTafassir" -->
    <div class="middle">
      <div class="liste-sourat">
        <h2 v-if="currentsurah">سورة {{ currentsurah.nom }}</h2>
        <template v-if="ayat.length === 0">
          <p>Aucune ayah disponible.</p>
        </template>
        <template v-else>
          <p>
            <img src="../assets/basmala.png" alt="basmala" srcset="" />
            <a
              v-for="ayah in ayat"
              :key="ayah.naya"
              href="javascript:void(2)"
              v-on:click="afficherLemme(ayah)"
            >
              {{ ayah.naya }} {{ ayah.aya }}
            </a>
          </p>
        </template>
      </div>
    </div>

    <!-- left page -->
    <div class="left">
      <h1>التفاسير</h1>
      <div
        class="tafsir"
        v-for="item in tafassir"
        :key="item.naya"
        href="javascript:void(2)"
      >
        <h3>{{ item.nom_s }} : {{ item.nom_t }}<br /></h3>
        <p>{{ item.texte }} <br /></p>
      </div>
    </div>
  </div>
</template>
<script>
// Optional JavaScript for hover effect
import axios from "axios";

export default {
  name: "LandingPage",
  // importer
  data() {
    return {
      searchQuery: "",
      searchResults: [],
      selectedTafassir: [],
      surahs: [],
      souar: [],
      ayat: [],
      tafassir: [],
      selectedLemme: "",
      selectedNom_S: "",
      searchQuery1: "",
      currentsurah: null,
    };
  },

  // afficher souar
  methods: {
    async performSearch() {
      const response = await axios.get(
        `http://localhost:9090/api/search?query=${this.searchQuery}`
      );
      this.searchResults = response.data;
    },
    // afficher ayat
    showSourate(surah) {
      this.currentsurah = surah;
      axios
        .get(`http://localhost:8081/ayat/nsoura/${surah.nsoura}`)
        .then((response) => {
          this.ayat = response.data;
        })
        .catch((error) => {
          console.error("Error retrieving ayat:", error);
        });
    },
    afficherLemme(ayah) {
      console.log(ayah.naya);
      console.log(ayah.nsoura);

      axios
        .get(
          `http://localhost:8081/Tafssir/getAllTafassir/${ayah.naya}/${ayah.nsoura}`
        )
        .then((response) => {
          this.tafassir = response.data;
        })
        .catch((error) => {
          console.error("Erreur lors de la récupération du lemme:", error);
        });
    },
    // chercher souar
    searchSouar() {
      axios
        .get(`http://localhost:8081/souar/search?query=${this.searchQuery1}`)
        .then((response) => {
          this.souar = response.data;
        })
        .catch((error) => {
          console.error("Error searching souar:", error);
        });
    },
  },

  // afficher nom de souar
  created() {
    axios
      .get("http://localhost:8081/souar")
      .then((response) => {
        this.surahs = response.data;
      })
      .catch((error) => {
        console.error("Error retrieving surahs:", error);
      });
  },
};
</script>

<style>
.tafsir {
  margin-right: 10px;
  /* background-color: #daf3fa00; */
}
img {
  height: 88px;
}
.liste-sourat p {
  text-align: center;

  margin-right: 100px;
}
.liste-sourat p a {
  text-decoration: none;
  color: #000;
  padding: 0px;
  font-size: xx-large;
}
.liste-sourat p a:hover {
  text-decoration: none;
  background-color: #d0b3a869;
  color: #533502;
}
.liste-sourat h2 {
  text-align: center;
  margin-right: 100px;
  background-color: #e6e0db;
  padding: 10px;
  border-radius: 25% 0% 25% 0%;
}
/* css for the right column */
td {
  border: 2px solid #ffffff;
  padding: 10px;
  width: 200vh;
  background-color: #f0f0f09d;

  backdrop-filter: blur(30px);
}
td a {
  text-decoration: none;
  color: #000000;
  font-size: 20px;
}
td a:hover {
  color: #7d2a0f;
}
.ul-left {
  display: flex;
  justify-content: space-between;
  list-style-type: none;
  padding: 0;
  margin: 0;
}
.ul-left li {
  display: flex;
  align-items: center;
  flex: 1 1 auto;
  text-align: center;
  margin-bottom: 0 !important;
  background-color: rgb(240, 240, 240);
  border-right: 1px solid #ccc;
}
.ul-left li:last-child {
  border-right: none; /* Suppression de la bordure droite pour le dernier élément */
}
.ul-left li a {
  color: #353535;
  padding: 15px 10px;
  display: block;
  text-align: center;
  border: none !important;
  text-align: center;
  border-bottom: 2px solid transparent !important;
  border-radius: 0;
  font-weight: 600;
  font-size: 17px;
}
.ul-left li input {
  width: 100px;
}
.nav {
  align-items: center;
  z-index: 99;
  position: relative;
  margin-right: 20px;
}
.ul-left {
  margin-left: -0px;
  margin-right: 0px;
}
.ul-left li a:hover {
  color: aqua;
}

.bar-search {
  margin-left: 0px;
}
.bar-search input {
  width: 93.5%;
  min-width: 30px;
  max-width: 100%;
  padding: 10px;
  border: none;
  border-radius: 10px;
  background-color: #daf3fa;
  margin-left: 0px;
}
.bar-search button {
  width: 68px;
  padding: 8px;
  border: none;
  border-radius: 10px;
  background-color: #e6e0db;
  cursor: pointer;
  font-size: large;
  /* margin-right: 79px; */
}

/* first page css */
.row:after {
  content: "";
  display: table;
  clear: both;
}
.row {
  margin: auto;
  margin-top: 120px;
}
.left {
  float: left;
  width: 35%;
  position: relative;
  border-right: 1px solid #000000;
  height: 82vh;
  overflow-y: scroll;
}
.right {
  float: right;
  width: 24%;
  position: relative;
  height: 82vh;
  overflow-y: scroll;
}
.middle {
  float: right;
  width: 35%;
  position: relative;
  height: 82vh;
  overflow-y: scroll;
  border-right: 1px solid #000000;
}

/* navbar css */
.navbar {
  height: 172px;
  width: 100%;
  display: flex;
  position: fixed;
  backdrop-filter: blur(30px);
  top: 0;
  flex-wrap: nowrap;
  justify-content: center;
  align-items: center;
  flex-direction: row;
  margin-top: -30px;
  padding: 0px;
}

ul {
  display: flex;
  flex-direction: row;
  flex-wrap: nowrap;
  justify-content: center;
  align-items: center;
}

li {
  display: flex;
  flex-direction: row;
  flex-wrap: nowrap;
  align-content: center;
  justify-content: center;
  align-items: center;
  padding-left: 70px;
}

li a {
  text-decoration: none;
  border-radius: 10px;
  padding: 5px 5px;
  font-size: large;
}

.acces a:hover {
  box-shadow: rgba(42, 42, 54, 0.745) 0 -25px 18px -14px inset,
    rgba(44, 187, 99, 0.25) 0 1px 2px, rgba(44, 187, 99, 0.25) 0 2px 4px,
    rgba(44, 187, 99, 0.25) 0 4px 8px, rgba(44, 187, 99, 0.25) 0 8px 16px,
    rgba(44, 187, 99, 0.25) 0 16px 32px;
  transform: scale(1.05) rotate(-1deg);
}

.logo {
  width: 17px;
  display: flex;
  margin-left: -600px;
  justify-content: left;
}

.logo img {
  width: 248px;
}

body {
  background-color: rgba(240, 255, 255, 0.651);
}
input[type="search"] {
  width: -webkit-fill-available;
  border: none;
  padding: 12px;
  border-radius: 10px;
  /* margin-top: 8px; */
  background: rgb(135 207 235 / 25%);
  transition: all 0.35s;
}
.button2 {
  width: -webkit-fill-available;
  border: none;
  padding: 12px;
  border-radius: 10px;
  cursor: pointer;
  /* margin-top: 8px; */
  background: #d0b3a869;
  transition: all 0.35s;
}
.login button {
  margin-right: -304px;

  border: none;

  cursor: pointer;
}

/* CSS */
.button-33 {
  background-image: linear-gradient(163deg, #353535, #42f3bb 50%, #f5ffff);
  border-radius: 100px;
  box-shadow: rgba(44, 187, 99, 0.2) 0 -25px 18px -14px inset,
    rgba(44, 187, 99, 0.15) 0 1px 2px, rgba(44, 187, 99, 0.15) 0 2px 4px,
    rgba(44, 187, 99, 0.15) 0 4px 8px, rgba(44, 187, 99, 0.15) 0 8px 16px,
    rgba(44, 187, 99, 0.15) 0 16px 32px;
  color: rgb(13, 66, 13);
  cursor: pointer;
  display: inline-block;
  font-family: CerebriSans-Regular, -apple-system, system-ui, Roboto, sans-serif;
  padding: 10px 25px;
  text-align: center;
  text-decoration: none;
  transition: all 250ms;
  border: 0;
  font-size: 20px;
  user-select: none;
  -webkit-user-select: none;
  touch-action: manipulation;
}

.button-33:hover {
  box-shadow: rgba(44, 187, 99, 0.35) 0 -25px 18px -14px inset,
    rgba(44, 187, 99, 0.25) 0 1px 2px, rgba(44, 187, 99, 0.25) 0 2px 4px,
    rgba(44, 187, 99, 0.25) 0 4px 8px, rgba(44, 187, 99, 0.25) 0 8px 16px,
    rgba(44, 187, 99, 0.25) 0 16px 32px;
  transform: scale(1.05) rotate(-1deg);
}
@font-face {
  font-family: "MeQuran";
  src: url("//cdn.alquran.cloud/public/fonts/me_quran-webfont.otf");
}

@font-face {
  font-family: "MeQuran2";
  src: url("//cdn.alquran.cloud/public/fonts/me_quran_2.ttf");
}

@font-face {
  font-family: "NooreHidayah";
  src: url("//cdn.alquran.cloud/public/fonts/noorehidayat.ttf");
}

@font-face {
  font-family: "Othmani";
  src: url("//cdn.alquran.cloud/public/fonts/ar-Othmani.ttf");
}

@font-face {
  font-family: "Quran";
  src: url("//cdn.alquran.cloud/public/fonts/QUR_STD.TTF");
}

@font-face {
  font-family: "Uthmani";
  src: url("//cdn.alquran.cloud/public/fonts/uthman_tn09.otf")
    format("opentype");
}
</style>
