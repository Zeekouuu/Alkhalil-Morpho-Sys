<template>
  <!-- navbar -->
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
  <!-- affichage de recherche -->
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
  <!-- formulair de contact us  -->
  <div class="contact">
    <h1>اتصل بنا</h1>

    <form @submit.prevent="submitForm">
      <div>
        <label for="name">Name:</label>
        <input
          type="text"
          id="name"
          placeholder="ادخل اسمك الكامل"
          v-model="formData.name"
          required
        />
      </div>
      <div>
        <label for="email">Email:</label>
        <input
          type="email"
          id="email"
          placeholder="ادخل عنوان بريدك"
          v-model="formData.email"
          required
        />
      </div>
      <div>
        <label for="message">Message:</label>
        <textarea
          id="message"
          placeholder="اكتب رسالة"
          v-model="formData.message"
          required
        ></textarea>
      </div>
      <button type="submit">ارسال</button>
    </form>
    <p v-if="submissionMessage">{{ submissionMessage }}</p>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      searchQuery: "",
      searchResults: [],
      formData: {
        name: "",
        email: "",
        message: "",
      },
      submissionMessage: "",
    };
  },
  methods: {
    async performSearch() {
      const response = await axios.get(
        `http://localhost:9090/api/search?query=${this.searchQuery}`
      );
      this.searchResults = response.data;
    },
    // envoyer le message a database
    submitForm() {
      axios
        .post(
          `http://localhost:8081/contact?name=${this.formData.name}&email=${this.formData.email}&message=${this.formData.message}`
        )
        .then(() => {
          console.log(this.formData);

          console.log("Form submitted successfully");
          this.submissionMessage = "Form submitted successfully";
          this.$router.push("/ContactUs");
        })
        .catch((error) => {
          console.error("Error submitting form:", error);
          console.log(this.formData);

          this.submissionMessage =
            "Failed to submit the form. Please try again.";
        });
    },
    resetForm() {
      this.formData = {
        name: "",
        email: "",
        message: "",
      };
    },
  },
};
</script>

<style>
.contact {
  margin-top: 140px;
}
textarea#message {
  padding: 14px;
  box-shadow: 0 12px 31px -10px #777cb0;
  border: none;
}
label {
  font-size: 16px;
  display: table-cell;
  box-shadow: 0 12px 31px -10px #777cb0;
}
input {
  padding: 10px;
  box-shadow: 0 12px 31px -10px #777cb0;
  border: none;
  border-radius: 5px;
  width: 90%;
  /* background-color: azure; */
}
button {
  border: none;
  padding: 11px;
  border-radius: 10px;
  background: linear-gradient(45deg, #897474, transparent);
  font-size: large;
  cursor: pointer;
  box-shadow: 0 12px 31px -10px #777cb0;
}
form {
  background: #daf3fa;
  width: 50vh;
  height: 50vh;
  border: none;
  border-radius: 10px;
  -webkit-backdrop-filter: blur(60px);
  backdrop-filter: blur(60px);
  display: inline-grid;
  justify-content: center;
  align-content: space-evenly;
  box-shadow: 0 12px 31px -10px #777cb0;

  justify-content: space-around;
}
</style>
