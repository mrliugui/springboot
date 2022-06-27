<template>
    <meh-outlined style="float:left;"/><h3 style="display: block">in no case shall we lose in the future,come on boy</h3>
<div style="background: #ececec; padding: 30px;padding-bottom:15px ">
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
        <div style="background: #ececec; padding: 30px;padding-top: 15px">
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
                                :value-style="{ color: '#cf1322' }"
                        >
                            <template #prefix>
                                <arrow-down-outlined />
                            </template>
                        </a-statistic>
                    </a-card>
                </a-col>
            </a-row>
        </div>

</template>
<script lang="ts">
    import {defineComponent, onMounted, reactive, toRefs} from 'vue';
    import {ArrowDownOutlined, ArrowUpOutlined} from '@ant-design/icons-vue';
    import axios from "axios";

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
                    todayForeseeVoteCount:0,
                    todayForeseeVoteCountRate:0
                }})
           axios.get("/ebook-snapShot/get-statistic").then(
                (response) => {
                    console.log(response)
                    data.statistic=response.data.data;
                }
            )
         onMounted(()=>{

             axios.get("/ebook-snapShot/get-statistic").then(
                 (response) => {
                     console.log(response)
                     // data.statistic=response.data.data;
                     const today=response.data.data[1]
                     const yesterday=response.data.data[0]
                     data.statistic.voteCount=today.voteCount
                     data.statistic.viewCount=today.viewCount
                     data.statistic.todayVoteCount=today.voteIncrease
                     data.statistic.todayViewCount=today.viewIncrease
                     const now = new Date()
                     const rate: any = ((now.getHours()*60+now.getMinutes())/(24*60)).toFixed(2)
                     //关于今天预计等于当前读入的数据除以当前时间的百分比
                     data.statistic.todayForeseeVoteCount=today.viewCount/rate

                     data.statistic.todayForeseeVoteCountRate=Math.abs((
                         (today.viewIncrease-yesterday.viewIncrease)/yesterday.viewIncrease*100)
                     )
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
