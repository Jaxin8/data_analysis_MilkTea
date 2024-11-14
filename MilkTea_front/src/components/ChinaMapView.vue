
<template> 
    <div ref="chinaContainer" class="chart-container">

    </div> 
</template>

<style scoped>
.chart-container {
    height: 100%;
    width: 100%;
    color: #fff;
}
</style>

<script setup>
import axios from "axios";
import { ref, onMounted, watch} from "vue";
import * as echarts from "echarts";



import chinaJSON from '@/assets/js/china.json'
echarts.registerMap('china', chinaJSON);

let mapData = ref()
let chinaContainer = ref(null);
let myChart = null;

const getShopData = ()=>{
    axios.get('http://localhost/Map/MapData/ProvinceNum')
      .then((response)=>{
        mapData.value = response.data.data;
      }).catch((error)=>{
        console.log(error);
      })
}

getShopData();

setInterval(()=>{
  getShopData(); 
},3000);

let option = {
        // backgroundColor: "#001432", 
        series: {
          type: 'map',
          map: 'china',
          zoom:1.2, 
          label: {
              show: true,
              color: '#fff',
              fontSize:10,
          },
          emphasis:{
            label:{
              show: true,
              color: 'green'
            }
          },
          data: [],
          name: '现有门店',
          
        },
        visualMap: {
          left: 'left',
          top: 'bottom',
          itemWidth: 10, 
          itemHeight: 10, 
          textStyle:{
            color:'#fff'
          },

          splitList: [
            {
              start: 20
            }, 
            {
              start: 15,
              end: 20,
            },{
              start: 10,
              end: 15,
            }, {
              start: 0,
              end: 10,
            }
          ],
          color: ['#64DD16','#76FF02','#B2FF59','#CCFF90'],
          show: true,
          
          
        },
        tooltip: {
          // trigger: 'item',
          
          
        }
      }

onMounted(() => {
  myChart = echarts.init(chinaContainer.value); 
  myChart.setOption(option); 

  window.addEventListener('resize', () => {
        myChart.resize();
    });
})


watch(()=>mapData.value,function(newValue,oldValue){
   if(myChart){
      option.series.data = newValue;
      myChart.setOption(option);  
   } 
},{deept:true})

</script>
