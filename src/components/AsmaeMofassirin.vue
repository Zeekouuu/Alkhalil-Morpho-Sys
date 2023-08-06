<template>
  <!-- nav bar -->
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
  <!-- right page -->
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
  <div class="names">
    <h1>قائمة المؤلفون</h1>
    <hr />
    <div class="grid-container">
      <div class="grid-items" v-for="name in names" :key="name.ntafsir">
        <p>
          <a href="#" @click="afficherChihaja(name)">{{ name.nom_S }}</a>
        </p>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "AsmaeMofassirin",
  data() {
    return {
      searchQuery: "",
      searchResults: [],
      names: [],
      tafassir: [],
      kolchi: [],
      showAlert: false,
      alertMessage: "",
    };
  },
  // affichage des authors
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
    // barre de recherche
    async performSearch() {
      const response = await axios.get(
        `http://localhost:9090/api/search?query=${this.searchQuery}`
      );
      this.searchResults = response.data;
    },
    async afficherChihaja(name) {
      try {
        console.log(name.nTafsir);
        const response = await axios.get(
          `http://localhost:8081/Tafssir/all/${name.nTafsir}`
        );
        const kolchi = response.data;
        const uniqueData = this.getUniqueData(kolchi);
        const message = this.buildMessage(uniqueData);
        window.alert(message);
      } catch (error) {
        console.error("Error retrieving tafsir:", error);
      }
    },
    getUniqueData(data) {
      const uniqueSet = new Set();
      data.forEach((item) => {
        const key = `${item.nom_s}-${item.aya}-${item.noms}`;
        uniqueSet.add(key);
      });
      const uniqueData = Array.from(uniqueSet);
      return uniqueData;
    },
    // afficher les information de author
    buildMessage(data) {
      let message = "";
      data.forEach((item) => {
        const [nom_s, aya, noms] = item.split("-");
        message += `الاية فسرها المؤلف: ${nom_s},\n سورة ${noms} : \n ${aya} \n`;
      });
      return message;
    },
  },
};
</script>

<style>
.alert {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  padding: 20px;
  background-color: #fff;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  z-index: 9999;
  font-family: Arial, sans-serif;
  font-size: 16px;
}

.alert-title {
  font-weight: bold;
  margin-bottom: 10px;
}

.alert-content {
  margin-bottom: 10px;
}

.alert-button {
  padding: 8px 16px;
  background-color: #4caf50;
  color: #fff;
  border: none;
  border-radius: 4px;
  font-weight: bold;
  cursor: pointer;
}

.alert-button:hover {
  background-color: #45a049;
}

.btnm2i {
  background: none;
  box-shadow: none;
  padding: 0px !important;
}

hr {
  width: 100vh;
}
.names {
  margin-top: 145px;
}
.grid-container {
  display: grid;
  grid-template-columns: repeat(
    4,
    1fr
  ); /* Adjust the number of columns as needed */
  grid-gap: 10px;
}
.grid-items {
  background-color: #cccccc5f;
  padding: 10px;
  border-radius: 10px;
  color: #fff;
  font-weight: 300;
  border: 0;
  display: inline-flex;
  align-items: center;
  justify-content: center;
  box-shadow: 0 12px 30px -10px #777cb0;
  transition: all 0.35s;
  cursor: pointer;
}
.grid-items a {
  text-align: center;
  text-decoration: none;
  color: black;
  font-size: large;
}
.grid-items[_ngcontent-joa-c11] {
  width: 100%;
  border-radius: 12px;
}
.grid-items:hover {
  background: linear-gradient(
    90deg,
    rgba(2, 0, 36, 0.707) 0%,
    rgba(150, 155, 153, 0.872) 33%,
    rgb(255, 255, 255) 100%
  );
}
</style>
