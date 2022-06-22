<template>
    <a-layout style="padding: 24px 0; background: #fff">
        <a-layout-content :style="{ padding: '0 24px', minHeight: '280px' }">
            <a-row>
                <a-col :span="6">
                    <a-tree
                            :tree-data="level"
                            :field-names="{title:'name',value:'id',key:'id',children:'children'}"
                            :defaultExpandAll="true"
                            @select="onSelect"
                    ></a-tree>
                </a-col>
                <a-col :span="18" >
                    <h2>{{doc.name}}</h2>
                    <span>阅读数：{{doc.view}}</span>
                    <span style="margin-left:10px">点赞数：{{doc.vote}}</span>
                    <a-divider>yesterday</a-divider>
                    <div :innerHTML="innerHTML"></div>
                    <a-divider>tomorrow</a-divider>
                    <div class="like">
                           <a @click="handleVote"> <like-filled  /> {{doc.vote}}</a>
                            <a><dislike-filled /> 7</a>
                    </div>
                </a-col>
            </a-row>
        </a-layout-content>
    </a-layout>
</template>
<script lang="ts">
    import {LaptopOutlined, NotificationOutlined, UserOutlined} from '@ant-design/icons-vue';
    import {defineComponent, onMounted, reactive, ref} from 'vue';
    import {useRoute} from 'vue-router'
    import axios from "axios";
    import {Tool} from "../../util/tool";
    import {message} from "ant-design-vue";

    export default defineComponent({
        components: {
        },
        setup() {
            const route = useRoute()
            const books = ref()
            const level = ref()
            const innerHTML = ref()
            const doc = reactive({
                id:0,
                name:"",
                view:0,
                vote:0,
            })
            // const changeClick = ref(false)
            // const changeClickBoolean = () => {
            //     changeClick.value=!changeClick.value
            // }
            innerHTML.value = {}
            innerHTML.value = "<h1>欢迎来到文档页面</h1>"
            const handleVote = () =>{
                axios.get("/doc/vote/"+doc.id).then(
                    (response) =>{
                   if(response.data.code === 10000){
                       doc.vote++
                   }else{
                       message.warn(response.data.msg)
                   }
                    }
                )
            }
            const handleQuerry = async () => {
                await axios.get("/doc/all/"+route.query.ebookId).then((response) => {
                    if(response.data.code === 10000){
                        books.value = response.data.data
                        console.log("books",books)
                        const result = Tool.array2Tree(books.value,0)
                        console.log("result",result)
                        doc.id=result[0].id;
                        doc.name=result[0].name
                        doc.view=result[0].viewCount
                        doc.vote=result[0].voteCount
                        level.value = []
                        level.value = result
                }
                    else{
                        message.error(response.data.msg)
                    }

                })
            }
            const onSelect  = (selectKeys: any,e: any) => {
            console.log("选中节点",selectKeys)
                console.log("e",e)
                axios.get("/doc/content/"+1
                )
                    .then((response) => {
                        doc.name=e.selectedNodes[0].name
                        doc.view=e.selectedNodes[0].viewCount
                        doc.vote=e.selectedNodes[0].voteCount
                        doc.id=e.selectedNodes[0].id
                        innerHTML.value = response.data.data
                    })
            }
            onMounted(
                handleQuerry
            )

            return {
                level,
                length,
                onSelect,
                innerHTML,
                doc,
                handleVote
            };
        },
    });
</script>
<style>
    .like{
        text-indent: 30px;
    }

</style>