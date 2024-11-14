<template>
  
  <el-table :data=BookList stripe class="eltable" style="text-align: center; line-height: 25px; font-size: 12px;">
    <p class="title" style="font-size: 15px; margin-bottom: 10px;">订单与金额匹配明细</p>
    <el-table-column prop="id" label="序号" width="35px"/>
    <el-table-column prop="phone" label="手机号"  />
    <el-table-column prop="brand" label="品牌"  />
    <el-table-column prop="total" label="成交额" />
    <el-table-column prop="createtime" label="下单时间" />
  </el-table>
</template>

<script lang="ts" setup>

import axios from 'axios'
import {ref} from "vue";
let BookList=ref([]);


const getBookyData = async () => {
  try {
    const response = await axios.get('http://localhost/book');
    const data = response.data.data;
    console.log(response.data.data);
    
    const formattedData = data.map(item => ({
      ...item,
      createtime: formatCreatetime(item.createtime)
    }));
    BookList.value = formattedData;
  } catch (error) {
    console.log("连接错误", error);
  }
};

const formatCreatetime = (dateStr) => {
  const date = new Date(dateStr);
  const hours = String(date.getHours()).padStart(2, '0');
  const minutes = String(date.getMinutes()).padStart(2, '0');
  const seconds = String(date.getSeconds()).padStart(2, '0');
  return ` ${hours}:${minutes}:${seconds}`;
};

// 调用方法以获取数据
getBookyData();

setInterval(()=>{
  getBookyData(); 
 },15000);


 
</script>


<style scoped>
.eltable{
  height:100%;
  text-align: center;
  color: white;
  font-size: 12px;
}
.eltable p .title{
  background-color: #cd1e1e;
  /* margin-bottom: 15px; */
  text-align: center;
  font-size: 15px;
  text-align: left;

}
</style>