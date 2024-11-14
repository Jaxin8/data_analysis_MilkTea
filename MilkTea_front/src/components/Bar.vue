<template>
    <div ref="barContainer" class="bar">
    </div>
</template>

<script setup>
import * as echarts from 'echarts';
import { onMounted, ref,watch } from 'vue';

const barContainer = ref(null);

const props = defineProps({
    title: {
        type: String,
        default: ''
    },
    vipData: {
        type: Array,
        default: []
    },
    bookData: {
        type: Array,
        default: []
    }
});

let myChart = null;

let option = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                crossStyle: {
                    color: '#000'
                }
            }
        },

        legend: {
            // data: ['销售额', '新增会员'], // 图例数据
            data: ['', ''], // 图例数据
            textStyle: {
                    color: '#fff' // 设置为红色  
                    }
        },
        xAxis: [
            {
                type: 'category',
                data: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
                axisPointer: {
                    type: 'shadow'
                },
                axisLabel: {
                    textStyle: {
                    color: '#fff' // 设置为红色  
                    }
                } 

            }
        ],
        title: {
        text: props.title,
        textStyle: {  
            fontSize: 16,
            color: '#fff' // 设置标题颜色为红色  
        }
    },
        yAxis: [
            {
                type: 'value',
                name: '销售额/万元',
                nameTextStyle: {  
                color: '#fff' // 设置为红色  
                },
                min: 0,
                max: 250,
                interval: 50,
                axisLabel: {
                    formatter: '{value}',
                    textStyle: {
                    color: '#fff' // 设置为红色  
                    }
                }
            },
            {
                type: 'value',
                name: '新增会员/万人',
                nameTextStyle: {  
                color: '#fff' // 设置为红色  
                },
                min: 0,
                max: 25,
                interval: 5,
                axisLabel: {
                    formatter: '{value}',
                    textStyle: {
                    color: '#fff' // 设置为红色  
                    }
                }
            }
        ],
        series: [
            {
                name: '销售额',
                type: 'bar',
                color:'#FFFF01',
                tooltip: {
                    valueFormatter: function (value) {
                        return value + ' 万元';
                    }
                },
                //data: [2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3]
                data: []
            },
            {
                name: '新增会员',
                type: 'line',
                yAxisIndex: 1,
                color:'#fc5185',
                tooltip: {
                    valueFormatter: function (value) {
                        return value + ' 万人';
                    }
                },
                data: []
            }
        ]
    };

watch(()=>props.bookData,(newValule,oldValue)=>{
    if(newValule.length>0){
        //alert('数据更新了' + newValule)
        option.series[0].data = newValule;
        myChart.setOption(option);
    }
},{deep:true})

watch(()=>props.vipData,(newValule,oldValue)=>{
    if(newValule.length>0){
        //alert('数据更新了' + newValule)
        option.series[1].data = newValule;
        myChart.setOption(option);
    }
},{deep:true})

onMounted(() => {
    myChart = echarts.init(barContainer.value);
    myChart.setOption(option);
    
    // 监听浏览器窗口大小变化
    window.addEventListener('resize', () => {
        myChart.resize();
    });
});

</script>

<style>
.bar {
    width: 100%;
    height: 100%;
    color: #fff;
}
</style>