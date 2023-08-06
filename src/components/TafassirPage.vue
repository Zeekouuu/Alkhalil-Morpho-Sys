<template>
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
  <!-- barre de recherche -->

  <!-- Home page -->

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

  <div class="names2">
    <h1>قائمة التفاسير</h1>
    <hr />
    <div class="grid-container2">
      <!-- chercher un tafssir -->
      <input
        style="width: 96%"
        type="text"
        v-model="searchQuery1"
        @input="searchSouar"
        placeholder="ابحث عن تفسير"
      />
      <div v-if="searchQuery1 != ''">
        <div
          class="grid-items2"
          v-for="name in serchTafssir"
          :key="name.ntafsir"
        >
          <a href="javascript:void(1)" @click="showtafassir(name)"
            >{{ name.nom }}
          </a>
        </div>
      </div>
      <div v-else>
        <div class="grid-items2" v-for="name in names" :key="name.ntafsir">
          <a href="javascript:void(1)" @click="showtafassir(name)"
            >{{ name.nom }}
          </a>
        </div>
      </div>
    </div>
  </div>

  <!-- affichage page  -->
  <div class="left2">
    <h1>التفاسير</h1>
    <ul>
      <li v-for="tafsir in tafassir" :key="tafsir.nTafsir">
        <div class="man3rf" v-html="tafsir.description"></div>
      </li>
    </ul>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "AsmaeMofassirin",
  // data
  data() {
    return {
      searchQuery: "",
      searchResults: [],
      names: [],
      tafassir: [],
      serchTafssir: [],
      searchQuery1: "",
    };
  },
  // affichage des tafassir
  created() {
    axios
      .get("http://localhost:8081/tafassir")
      .then((response) => {
        this.names = response.data;
      })
      .catch((error) => {
        console.error("Error retrieving surahs:", error);
      });
  },
  methods: {
    // chercher avec solr
    async performSearch() {
      const response = await axios.get(
        `http://localhost:9090/api/search?query=${this.searchQuery}`
      );
      this.searchResults = response.data;
    },
    // chercher un tafssir function
    searchSouar() {
      axios
        .get(`http://localhost:8081/tafassir/search?query=${this.searchQuery1}`)
        .then((response) => {
          this.serchTafssir = response.data;
        })
        .catch((error) => {
          console.error("Error searching souar:", error);
        });
    },
    // afficher les information sur tafssir
    showtafassir(name) {
      axios
        .get(`http://localhost:8081/tafassir/${name.nTafsir}`)
        .then((response) => {
          this.tafassir = response.data;
        })
        .catch((error) => {
          console.error("Error retrieving surahs:", error);
        });
    },
  },
};
</script>
<style>
.recherche {
  float: inline-start;
  margin-top: 160px;
  position: relative;
}
hr {
  width: 100vh;
}
p {
  text-align: right;
}
.grid-items2 a:focus {
  color: rgb(0, 0, 0);
  background: linear-gradient(
    90deg,
    rgb(80, 80, 80) 0%,
    rgba(165, 165, 165, 0.872) 33%,
    rgb(237, 237, 237) 100%
  );
}
.names1 {
  margin-top: 25px;
  float: right;
  width: 100%;
  height: 90vh;
  position: relative;
  overflow-y: scroll;
  overflow-x: hidden;
}
.names2 {
  margin-top: 0px;
  float: right;
  width: 34%;
  height: 86vh;
  position: relative;
  overflow-y: scroll;
  overflow-x: hidden;
}
::-webkit-scrollbar {
  display: none;
}

.left2 {
  width: 66%;
  margin-top: 100px;
  height: 80vh;
  position: relative;
  overflow-y: scroll;
}
.grid-container2 {
  display: grid;
  grid-template-columns: repeat(1, 1fr);
  grid-gap: 10px;
}

.grid-items2 a {
  width: 70vh;
  padding: 10px;
  border-radius: 15px;
  color: #fff;
  font-weight: 300;
  border: 0;
  display: inline-flex;
  align-items: center;
  justify-content: center;
  box-shadow: 0 12px 31px -10px #777cb0;
  transition: all 0.35s;
  cursor: pointer;
  text-align: center;
  text-decoration: none;
  color: black;
  font-size: large;
}
/* .grid-items2 a {
  text-align: center;
  text-decoration: none;
  color: black;
  font-size: large;
} */
</style>
