import HelloWorld from "./components/HelloWorld.vue";
import AsmaeMofassirin from "./components/AsmaeMofassirin.vue";
import TafassirPage from "./components/TafassirPage.vue";
import ContactUs  from "./components/ContactUs.vue";
import { createRouter, createWebHistory } from "vue-router";

const routes = [
  {
    name: "HelloWorld",
    component: HelloWorld,
    path: "/",
  },
  {
    name: "AsmaeMofassirin",
    component: AsmaeMofassirin,
    path: "/AsmaeMofassirin",
  },
  {
    name: "TafassirPage",
    component: TafassirPage,
    path: "/Tafassir",
  },
  {
    name: "ContactUs",
    component: ContactUs,
    path: "/ContactUs",
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
