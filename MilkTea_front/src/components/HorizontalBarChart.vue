<script setup>
import * as echarts from 'echarts';
import { ref, onMounted, watch } from 'vue';
const scheduleProps = defineProps({
    "data": {
        type: Array,
        default: []
    }
});
const barContainer = ref(null);
let myChart = null;
watch(() => scheduleProps.data, function (newValue, oldValue) {
    reader();
}, { deept: true });
const reader = () => {
    if (myChart == null) {
        return;
    }
    myChart.setOption({
        dataset: {
            source: [
                ['amount', 'product'],
                ...scheduleProps.data
            ]
        }
    })
}
onMounted(() => {

    let option = {
        backgroundColor: 'transparent',
        title: {
            text: '全国销售额',
            left: '30%',
            top: '5%',
            textStyle: {
                color: '#fff',
                fontSize: 20,
            }
        },

        dataset: {
            source: [
                // ['amount', 'product'],
                // [58212, 'xxxxxxxxx'],
                // [78254, 'Milk Tea'],
                // [41032, 'Cheese Cocoa'],
                // [12755, 'Cheese Brownie'],
                // [20145, 'Matcha Cocoa'],
                // [79146, 'Tea'],
                // [91852, 'Orange Juice'],
                // [101852, 'Lemon Juice'],
                // [20112, 'Walnut Brownie']
            ]
        },
        grid: { containLabel: true },
        //xAxis: {  },
        xAxis: {
            show: false,
            name: ''
        },
        //yAxis: { type: 'category' },
        yAxis: {
            show: true,
            type: 'category',
            
            axisLabel: {
                color: 'white'
            },
            axisLine: {
                show: false,
                
                lineStyle: {
                    color: '#FFFFFF'
                }
            },
            axisTick: {
                show: false
            }
        },
        grid: {
            show: false,
            containLabel: true
        },

        series: [
            {
                type: 'bar',
                encode: {
                    // Map the "amount" column to X axis.
                    x: 'amount',
                    // Map the "product" column to Y axis
                    y: 'product'
                },
                barWidth: '60%',
                itemStyle: {
                    color: '#B2FF59',
                },
                label: {
                    show: true,
                    position: 'right',
                    color: '#FFFFFF'
                }

            }
        ]
    };
    myChart = echarts.init(barContainer.value, "dark", {
        renderer: "canvas",
        useDirtyRect: false,
    });
    option && myChart.setOption(option);

    window.addEventListener('resize', () => {
        myChart.resize();
    });
});
</script>
<template>
    <!-- 给 div 设置具体的宽度和高度 -->
    <div ref="barContainer" style="width: 100%; height: 100%; color: #fff;"></div>
</template>

<style>
</style>