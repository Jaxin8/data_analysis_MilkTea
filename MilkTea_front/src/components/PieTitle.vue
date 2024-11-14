<script setup>
import { ref, onMounted, watch } from "vue";
import * as echarts from "echarts";

const pieContainer = ref(null);   //<div ref="pieContainer" 

const pieProps = defineProps({
  "nameType": {
    type: Array,
    default: []
  },
  "num": {
    type: Array,
    default: []
  }
});

watch(() => pieProps.nameType, function (newValue, oldValue) {
  render();
}, { deept: true })
watch(() => pieProps.num, function (newValue, oldValue) {
  render();
}, { deept: true })



let myChart = null;

// 更新数据的函数  
const render = () => {
  if (myChart == null) {
    return;
  }
  // 使用更新后的数据重新设置图表选项  
  myChart.setOption({
    series: [
      {
        data: pieProps.num

      }
    ],
    yAxis: [
      {
        data: pieProps.nameType

      }
    ]
  });
}


onMounted(() => {

  myChart = echarts.init(pieContainer.value, "", {
    renderer: "canvas",
    useDirtyRect: false,
  });


  let option = {
    gradientColor:'#f6efa6',
    xAxis: {
      max: 'dataMax', // x轴最大值自动根据数据最大值设置
      show:false  
    },
    yAxis: {
      type: 'category', // y轴类型为类目轴  
      data: pieProps.nameType, // y轴上的类目数据  
      inverse: true, // 翻转类目轴  
      animationDuration: 1000, // 初始动画的时长  
      animationDurationUpdate: 1000, // 数据更新动画的时长  
      max: 2 , //最大显示数量条数0开始
      
      axisLabel: {
        textStyle: {
          color: '#fff' // 设置为红色  
        }
      }

    },
    series: [
      {
        realtimeSort: true, // 开启实时排序  
        name: '数据',// 系列名称  
        type: 'bar', // 图表类型为柱状图  
        data: pieProps.num, // 数据内容  
        label: {
          show: true, // 显示标签  
          position: 'right', // 标签位置在右侧  
          valueAnimation: true // 数值动画  
        },
        color: '#B2FF59',
        
      }
      

    ],
    legend: {
      show: true // 显示图例  
    },
    animationDuration: 1000, // 初始动画的时长，这里设置为0表示不播放初始动画  
    animationDurationUpdate: 1000, // 数据更新动画的时长  
    animationEasing: 'linear', // 初始动画的缓动效果  
    animationEasingUpdate: 'linear' // 数据更新动画的缓动效果  
  };

  // 最后，将配置项应用到图表实例上  
  option && myChart.setOption(option);

  window.addEventListener('resize', () => {
        myChart.resize();
    });

}) 
</script>

<template>

  <div ref="pieContainer" class="chart-container" >
  </div>

</template>

<style scoped>
.chart-container {
  
  height: 100%;
  width: 100%;
  color:#fff;
}
</style>
