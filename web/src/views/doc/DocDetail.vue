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
                <a-col :span="18" :innerHTML="innerHTML"></a-col>
            </a-row>
        </a-layout-content>
    </a-layout>
</template>
<script lang="ts">
    import {LaptopOutlined, NotificationOutlined, UserOutlined} from '@ant-design/icons-vue';
    import {defineComponent, onMounted, ref,} from 'vue';
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
            innerHTML.value = {}
            innerHTML.value = "<h1>欢迎来到文档页面</h1>"
            const handleQuerry = async () => {
                await axios.get("/doc/all/"+route.query.ebookId).then((response) => {
                    if(response.data.code === 10000){
                        books.value = response.data.data
                        const result = Tool.array2Tree(books.value,0)
                        console.log(result)
                        level.value = []
                        level.value = result
                    }
                    else{
                        message.error(response.data.msg)
                    }

                })
            }
            const onSelect  = (selectKeys: any,e: any) => {
            console.log(selectKeys)
                axios.get("/doc/content/"+1
                )
                    .then((response) => {
                        console.log(response.data);
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
                innerHTML
            };
        },
    });
</script>
<style>

</style>