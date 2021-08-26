import Vue from 'vue'
import Router from 'vue-router'
import Tables from "../components/Tables";

Vue.use(Router)

export default new Router({
  routes: [
    /*{
      path: '/',
      name: "",
      component:""
    }*/
    {path: '/login', name: "Login", component:()=>import('../views/login/Login')},

    {path: '/', redirect:'/index'},
    {path: '/index', name: "Index", component:()=>import('../views/Index'),
      children:[{path:'emps', name:'Emps', component:()=>import('../views/emp/Index')},
        {path:'caigous', name:'Caigous', component:()=>import('../views/caigou/Index')},
        {path:'caigous02', name:'Caigous02', component:()=>import('../views/caigou/Index_element')
        },
        {path: 'thedays', name: "Thedays", component:()=>import('../views/deving')},
        {path: 'abouts', name: "Abouts", component:()=>import('../views/about/Index'),},
        {path: 'vxe-tables', name: "Vxe-tables", component:()=>import('../views/caigou/Index-vxe'),
        }
        ]
    },
    {path: 'table',component:Tables},
    {path:'*',component:()=>import('../views/404'),},
  ]
})
