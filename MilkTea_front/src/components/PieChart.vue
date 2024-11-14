<script setup>
import { ref, onMounted, watch } from "vue";
import * as echarts from "echarts";

const pieCon = ref(null);
let myChart = ref(null);

const piePropers = defineProps({
  "pieTitle": {
    type: String,
    default: "",
    require: true,
  },
  "pieData":{
    type:Array,
    default:[],
  }

});

let option = {
    backgroundColor: 'transparent',
    title: {
      text: piePropers.pieTitle,
      left: "center",
    },
    tooltip: {//品牌详情
      trigger: "item",
      formatter: function (params) {
            return "营业额：" + params.value ;
          },
    },
    
    series: [
      {
        name: piePropers.pieTitle,
        type: "pie",
        center: ['50%', '50%'], 
        radius: ["50%", "70%"],
        avoidLabelOverlap: false,
        label: {
          show: true,
          position: "outside",
          formatter: function (params) {
            return params.name + '\n' + ' (' + params.percent + '%)';
          },
        },
        emphasis: {
          label: {
            show: true,
            fontSize: 15, //字体大小
            fontWeight: "bold",
          },
        },
        labelLine: {
          show: true, // 确保引导线显示
          length: 12, // 引导线长度
          length2: 16, // 引导线第二段长度，通常用于标签在扇区内部时
          lineStyle: {
            color: "rgba(255, 255, 255, 0.5)", // 引导线颜色
            width: 2, // 线条宽度
            type: "solid", // 线条类型
          },
        },
        data:[],
      },
    ],
  };

watch(()=>piePropers.pieData,function(newValue, oldValue){
  if(myChart){
    option.series[0].data = newValue;
    myChart.setOption(option);
  }
},{deept:true})

onMounted(() => {
  myChart = echarts.init(pieCon.value, "dark",{
    renderer: "canvas",
    useDirtyRect:false,
    
  });

  option && myChart.setOption(option);

  window.addEventListener('resize', () => {
        myChart.resize();
    });

});
</script>
<template>
  <div class="chart-containe" ref="pieCon"></div>
</template>
<style scoped>
.chart-containe {
  height: 100%;
  width: 100%;
}
</style>
