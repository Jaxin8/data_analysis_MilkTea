<script setup>
import chinaView from "@/components/ChinaMapView.vue"
import Bar from '@/components/Bar.vue'
import HorizontalBarChart from "@/components/HorizontalBarChart.vue";
import Piechart from '@/components/PieChart.vue'
import BookView from "@/components/BookView.vue";
import { ref, onMounted, reactive } from 'vue';
import PieTitle from "@/components/PieTitle.vue";

import axios from "axios";

//右中组件Start
let nameType = ref([]);
let num = ref([]);
let ad=50;
const getNumberDate = () => {
  axios.get('http://localhost/number')
    .then((response) => {
      let numberData = response.data.data;

      let nameTypeList = [];
      let numList = [];
      ad=ad+20;
      for (let number of numberData) {
        nameTypeList.push(number.nameType)

        numList.push(number.num+ad)
      }

      nameType.value = nameTypeList;
      num.value = numList;

    }).catch((error) => {
      console.log(error);
    })
}
getNumberDate();
//右中组件End


//右上角组件Start
let vipCountList = reactive([]);
const getVipCountList = async () => {
  const response = await axios.get('http://localhost/vip/vipOrderByMonth');
  // 处理数据
  const countList = response.data.data.map(item => item.total);
  // 先清空
  vipCountList.splice(0);
  // 再添加
  vipCountList.push(...countList);
}

let bookCountList = reactive([]);
const getBookCountList = async () => {
  const response = await axios.get('http://localhost/book/bookOrderByMonth');
  // 处理数据
  const countList = response.data.data.map(item => item.totalPrice);
  // 先清空
  bookCountList.splice(0);
  // 再添加
  bookCountList.push(...countList);
}
//右上角组件End


//左上角Start
let horizontalBarData = ref([]);
const getNaichaScheduleData = () => {
  axios.get('http://localhost/schedule')
    .then((response) => {
      let naichaScheduleData = response.data.data;
      let result = [];
      for (let item of naichaScheduleData) {
        result.push([item.number, item.name]);
      }
      horizontalBarData.value = result;
    }).catch((error) => {
      console.log(error);
    })
}
getNaichaScheduleData();
//左上角End

//右下角热门店铺Start
let shopPieData = ref([]);
let count = 0;

const getShopListData = () => {
  axios.get('http://localhost/admin/shop')
    .then((response) => {
      let shopData = response.data.data;

      let temp = [];
      for (let shop of shopData) {
        if (count < 5) {
          temp.push({ "value": shop.saleNumber, "name": shop.shopName });
          count++;
        } else {
          count = 0;
          break;
        }
      }

      shopPieData.value = temp;
    }).catch((error) => {
      console.log(error);
    })
}
getShopListData();
//右下角热门店铺End

//中间大地图Start
let allShopNum = ref()
let salesAmount = ref()
const getAllShopNum = () => {
  axios.get('http://localhost/Map/MapData/AllNum')
    .then((response) => {
      allShopNum = response.data.data;
    }).catch((error) => {
      console.log(error);
    })
}
const getSalesAmount = () => {
  axios.get('http://localhost/Map/MapData/SalesAmount')
    .then((response) => {
      salesAmount = response.data.data;
    }).catch((error) => {
      console.log(error);
    })
}
getAllShopNum();
getSalesAmount();
//中间大地图End

//定时器
setInterval(() => {
  getNumberDate();
  getVipCountList();
  getBookCountList();
  getShopListData();
  getNaichaScheduleData();
  getAllShopNum();
  getAllShopNum();
  getSalesAmount();
}, 3000);



</script>

<template>
  <div class="wrapper">
    <header>
      <!-- <img src="../assets/imges/logo.png" /> -->
      <div class="left" ></div>
      <div class="right">
        <i class="fa fa-clock-o"></i>
      </div>
    </header>
    <div class="main">
      <aside>
        <div class="grid_box_1">

        </div>

        <div class="grid_box_2">
          <HorizontalBarChart :data="horizontalBarData"></HorizontalBarChart>
        </div>

        <div class="grid_box_3">
          <!-- 左下组件 -->
          <BookView />

        </div>
      </aside>
      
      <section>
        <div class="map_header"></div>
        <div class="map">
          <!-- 中国地图 -->
          <chinaView />
        </div>

        <div class="statistics">
          <ul>
            <li>
              <i class="fa fa-user-o"></i>
              <div>
                <h4>{{ allShopNum }}家</h4>
                <p>全国门店数量</p>
              </div>
            </li>
            <li>
              <i class="fa fa-sun-o"></i>
              <div>
                <h4>
                  <p style="display: inline; font-size: 10px;">预计：</p>200+
                </h4>
                <p>海外门店数量</p>
              </div>
            </li>
            <li>
              <i class="fa fa-life-ring"></i>
              <div>
                <h4>{{ salesAmount }}￥</h4>
                <p>总盈利额</p>
              </div>
            </li>
          </ul>
        </div>
      </section>
      <article>
        <div class="grid_box_4">
          <bar title="销售额与新增会员分析" :vip-data=vipCountList :book-data="bookCountList" />
        </div>

        <div class="grid_box_5">
          <PieTitle :nameType="nameType" :num="num"></PieTitle>
        </div>

        <div class="grid_box_6">
          <Piechart pieTitle="优秀店铺" :pieData="shopPieData"></Piechart>
        </div>
      </article>
    </div>
  </div>

</template>





<style scoped>
.wrapper {
  width: 100%;
  height: 100%;
  background-image: linear-gradient(15deg, #00304e 0%, #067a6d 100%);

  font-weight: 300;
  color: #aaf9fc;
  position: relative;

  display: flex;
  justify-content: space-between;

  flex: 0 0 2vw;
}

header img {
  display: block;
  width: 100%;
}

header div {
  position: absolute;
  font-size: 1vw;
}

header .left {
  left: 20px;
  top: 0.9vw;
}

header .right {
  right: 20px;
  top: 0.9vw;
}

.main {
  flex: 1;
  width: 100%;
  display: flex;
}

.main aside,
.main article {
  margin-right: 15px;
  flex: 1;
  display: flex;
  flex-direction: column;
}

.main aside div,
.main article div {
  flex: 1;
}

.main section {
  flex: 0 0 44%;
  display: flex;
  flex-direction: column;
}

.main section .map_header {
  width: 100%;
  height: 120px;
  /* background-color: #dd2d2d; */
}

.main section .map {
  padding: 20px;
  margin: 0 20px 0 20px;
  margin-bottom: 10px;
  border-radius: 25px;
  box-shadow: 0 0 15px #4d4d4d;
  background-color: #ffffff1f;
  flex: 3;
  margin-left: -0.5vw;
}


.main aside .grid_box_1 {
  margin-left: 25px;
  margin-top: 30px;
  background-image: url('../assets/imges/logo.png');
  background-size: 130% 70%;
  background-repeat: no-repeat;
  width: 350px;
  height: 300px;
  flex: 1;
}

.main aside .grid_box_2 {
  width: 100%;
  height: 150%;
  flex: 3
}

.main aside .grid_box_3 {
  margin: 10px;
  margin-bottom: 25px;
  padding: 10px;
  background-color: rgba(92, 92, 92, 0.301);
  border-radius: 35px;
  box-shadow: 0 0 15px rgba(43, 43, 43, 0.301);
  flex: 2;
}

.main .grid_box_4 {
  margin-top: 30px;
  width: 100%;
  height: 100%;
  display: flex;
}

.main .grid_box_5 {
  margin-bottom: 15px;
  width: 100%;
  height: 100%;
  display: flex;
  background-color: #ffffff1a;
  border-radius: 15px;
  box-shadow: 0 0 15px #22222269;

}

.main section .statistics {
  margin-bottom: 13vh;
}

.main section .statistics ul {
  display: flex;
  justify-content: space-around;
  margin-top: 20px;
  color: #fff;
}

.main section .statistics ul li {
  display: flex;
  align-items: center;
}

.main section .statistics ul li .fa {
  font-size: 36px;
  color: #32ccee;
  margin-right: 10px;
}

.main section .statistics ul li div h4 {
  font-size: 26px;
}

.main section .statistics ul li div p {
  font-size: 14px;
  margin-top: 3px;
}
</style>
