<template>
    <meh-outlined style="float:left;"/><h3 style="display: block">in no case shall we lose in the future,come on boy</h3>
<div>
        <a-row :gutter="16">
            <a-col :span="8">
                <a-card>
                <a-statistic title="总阅读量" :value="statistic.viewCount" style="margin-right: 50px" >
              <template #prefix>
                  <read-outlined  style="margin-right: 10px"/>
              </template>
                </a-statistic>
                </a-card>
            </a-col>
            <a-col :span="8">
                <a-card>
                <a-statistic title="总点赞量" :value="statistic.voteCount">
                <template #prefix>
                    <like-outlined style="margin-right: 10px"/>
                </template>
                </a-statistic>
                </a-card>
            </a-col>
            <a-col :span="8">
                <a-card>
                <a-statistic title="点赞率" :precision="2"
                             suffix="%" :value="statistic.voteCount/statistic.viewCount*100" />
                </a-card>
            </a-col>
        </a-row>
</div>
        <div>
            <a-row :gutter="16">
                <a-col :span="6">
                    <a-card>
                        <a-statistic title="今日阅读" :value="statistic.todayViewCount" style="margin-right: 50px" >
                            <template #prefix>
                                <read-outlined  style="margin-right: 10px"/>
                            </template>
                        </a-statistic>
                        </a-card>
                </a-col>
                <a-col :span="6">
                    <a-card>
                        <a-statistic title="今日点赞" :value="statistic.todayVoteCount">
                            <template #prefix>
                                <like-outlined style="margin-right: 10px"/>
                            </template>
                        </a-statistic>
                    </a-card>
                </a-col>
                <a-col :span="6">
                    <a-card>
                            <a-statistic title="预计今日阅读" :value="statistic.todayForeseeVoteCount" style="margin-right: 50px" >
                                <template #prefix>
                                    <read-outlined  style="margin-right: 10px"/>
                                </template>
                            </a-statistic>
                        </a-card>
                </a-col>
                <a-col :span="6">
                    <a-card>
                        <a-statistic
                                title="预计今日阅读增长"
                                :value="statistic.todayForeseeVoteCountRate"
                                :precision="2"
                                suffix="%"
                                class="demo-class"
                                :value-style="statistic.todayVoteCount>statistic.yesterdayVoteCount?{color:'green'}:{ color: '#cf1322' }"
                        >
                            <template #prefix>
                                <arrow-down-outlined v-if="statistic.todayVoteCount<=statistic.yesterdayVoteCount"/>
                                <arrow-up-outlined v-if="statistic.todayVoteCount>statistic.yesterdayVoteCount"/>
                            </template>
                        </a-statistic>
                    </a-card>
                </a-col>
            </a-row>
            <a-row>
                <a-col :span="24">
                    <div id="main" style="width: 100%;height:400px;"></div>
                </a-col>
            </a-row>
        </div>
</template>
<script lang="ts">
    import {defineComponent, onMounted, reactive, toRefs} from 'vue';
    import {ArrowDownOutlined, ArrowUpOutlined} from '@ant-design/icons-vue';
    import axios from "axios";

    declare let echarts: any;
    export default defineComponent({
        name: 'the-welcome',
        components: {
            ArrowUpOutlined,
            ArrowDownOutlined,
        },
        setup () {
            const data = reactive({statistic:{
                    voteCount:0,
                    viewCount:0,
                    todayVoteCount:0,
                    todayViewCount:0,
                    yesterdayVoteCount:0,
                    todayForeseeVoteCount:0,
                    todayForeseeVoteCountRate:0
                }})
            const date1: any[]=[]
            const voteIncrease: any[]=[]
            const viewIncrease: any[]=[]
            const categoryies=['voteIncrease','viewIncrease']

           axios.get("/ebook-snapShot/get-statistic").then(
                (response) => {
                    console.log(response)
                    data.statistic=response.data.data;
                }
            )
            // const testMyChart = () => {
            //     const myChart = echarts.init(document.getElementById('main'));
            //
            //     // 指定图表的配置项和数据
            //     const option = {
            //         title: {
            //             text: 'ECharts 入门示例'
            //         },
            //         tooltip: {},
            //         legend: {
            //             data: ['销量']
            //         },
            //         xAxis: {
            //             data: ['衬衫', '羊毛衫', '雪纺衫', '裤子', '高跟鞋', '袜子']
            //         },
            //         yAxis: {},
            //         series: [
            //             {
            //                 name: '销量',
            //                 type: 'bar',
            //                 data: [5, 20, 36, 10, 10, 20]
            //             }
            //         ]
            //     };
            //     // 使用刚指定的配置项和数据显示图表。
            //     myChart.setOption(option);
            //
            // }

            const myChart = async () => {
               await axios.get("/ebook-snapShot/get-30statistic").then(
                    (response) => {
                        console.log("echarts",response)
                        for(let i: any=0;i<response.data.data.length;i++){
                            date1.push(response.data.data[i].date)
                            voteIncrease.push(response.data.data[i].voteIncrease)
                            viewIncrease.push(response.data.data[i].viewIncrease)
                        }
                        console.log("date",date1)
                    })
//                 // 修改数据
//                 this.echartsOption.series[0].data = that.listData1
// //为了好看放了一个加载
//                 this.echartsLoading=true
// // 利用setTimeout延迟获取和赋值图表
//                 setTimeout(()=>{
//                     this.echartsLoading=false
//                     let tabEcharts = echarts.init(document.getElementById("tabEcharts"))
//                     tabEcharts.setOption(this.echartsOption)
//                 },2000)

                // setTimeout(() => {
                    const myChart = echarts.init(document.getElementById('main'));
                    const option = {
                        title: {
                            text: '关于文档点赞阅读趋势图'
                        },
                        toolbox: {
                            feature: {
                                saveAsImage: {}
                            }
                        },
                        tooltip:{
                            show : true,
                            trigger : 'axis',
                            axisPointer:'shadow'
                        } ,
                        legend: {
                            data: categoryies
                        },
                        grid: {
                            left: '3%',
                            right: '4%',
                            bottom: '3%',
                            containLabel: true
                        },
                        xAxis: {
                            type: 'category',
                            boundaryGap: false,
                            data: date1
                        },
                        yAxis: {
                            type: 'value'
                        },
                        series: [
                            {
                                name: categoryies[0],
                                type: 'line',
                                stack: 'Total',
                                data: voteIncrease
                            },
                            {
                                name: categoryies[1],
                                type: 'line',
                                stack: 'Total',
                                data: viewIncrease
                            },
                        ]
                    }
                    // 使用刚指定的配置项和数据显示图表。
                    myChart.setOption(option);
                // },1000)


            }



         onMounted(()=>{
             myChart()
             axios.get("/ebook-snapShot/get-statistic").then(
                 (response) => {
                     console.log("statistic", response)
                     // data.statistic=response.data.data;
                     const today=response.data.data.length==1?response.data.data[0]:response.data.data[1]
                     const yesterday=response.data.data.length==1?{}:response.data.data[0]
                     data.statistic.voteCount=today.voteCount
                     data.statistic.viewCount=today.viewCount
                     data.statistic.todayVoteCount=today.voteIncrease
                     data.statistic.todayViewCount=today.viewIncrease
                     const now = new Date()
                     const rate: any = ((now.getHours()*60+now.getMinutes())/(24*60)).toFixed(2)
                     //关于今天预计等于当前读入的数据除以当前时间的百分比
                     data.statistic.todayForeseeVoteCount=Math.ceil(today.viewIncrease/rate)
                     data.statistic.yesterdayVoteCount=yesterday.viewIncrease || 0

                     data.statistic.todayForeseeVoteCountRate=Math.abs((
                         (today.viewIncrease-yesterday.viewIncrease)/yesterday.viewIncrease*100)
                     ) || 0
                 }
             )
         })
            const { statistic} = toRefs(data)
           return{
          statistic
            }
        }
    });
</script>
