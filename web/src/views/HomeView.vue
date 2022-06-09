<template>
  <a-layout-content style="padding: 0 50px">
    <a-layout style="padding: 24px 0; background: #fff">
      <a-layout-sider width="200" style="background: #fff">
        <a-menu
                v-model:selectedKeys="selectedKeys2"
                v-model:openKeys="openKeys"
                mode="inline"
                style="height: 100%"

        >
          <a-sub-menu v-for="item in level"
                      :key="item.id">
            <template #title>
                <span>
                  <UpdateEbookReq-outlined />
                  {{item.name}}
                </span>
            </template>
            <a-menu-item v-for="childrenItem in item.children" :key="childrenItem.id">{{childrenItem.name}}</a-menu-item>
          </a-sub-menu>
        </a-menu>
      </a-layout-sider>
      <a-layout-content :style="{ padding: '0 24px', minHeight: '280px' }">
        <a-list item-layout="vertical" size="large" :data-source="books" :grid="{ gutter: 30, column: 3 }">
        <template #renderItem="{ item }">
            <a-list-item key="item.name">
              <template #actions>
          <span v-for="{ type, text } in actions" :key="type">
            <component :is="type" style="margin-right: 8px" />
            {{ text }}
          </span>
              </template>
              <a-list-item-meta :description="item.description">
                <template #title>
                  <a :href="item.cover">{{ item.name }}</a>
                </template>
                <template #avatar><a-avatar :src="item.avatar" /></template>
              </a-list-item-meta>
            </a-list-item>
          </template>
        </a-list>
      </a-layout-content>
    </a-layout>
  </a-layout-content>
</template>

<script lang="ts">
    import {defineComponent, onMounted, ref} from 'vue';
    import {message} from "ant-design-vue";
    import {Tool} from "../util/tool";
    import {
        LaptopOutlined,
        LikeOutlined,
        MessageOutlined,
        NotificationOutlined,
        StarOutlined,
        UserOutlined
    } from '@ant-design/icons-vue';
    import axios from 'axios';

    export default defineComponent({
     name: 'HomeView',
     components:{
         UserOutlined,
         LaptopOutlined,
         NotificationOutlined,
         StarOutlined,
         LikeOutlined,
         MessageOutlined,
     },
        setup(){
            const level = ref<any>()
            const handleCategoryQuerry = () => {
                axios.get("/category/all").then((response) => {
                    if(response.data.code === 10000){
                        const categoryBooks = response.data.data
                        const result = Tool.array2Tree(categoryBooks,0)
                        level.value = []
                        level.value = result
                        console.log(level.value)
                    }
                    else{
                        message.error(response.data.msg)
                    }

                })
            }
         const books = ref('')
            onMounted(() => {
                handleCategoryQuerry()
                axios.get('/ebook/list',{
                    params:{
                        pageSize:100,
                        pageNum:1
                    }
                }).then((response) =>{
                    console.log(response)
                    books.value = response.data.data.content
                })
            })
            const pagination = {
                onChange: (page: number) => {
                    console.log(page);
                },
                pageSize: 3,
            };
            const actions: Record<string, string>[] = [
                { type: 'StarOutlined', text: '156' },
                { type: 'LikeOutlined', text: '156' },
                { type: 'MessageOutlined', text: '2' },
            ];
            return {
                selectedKeys2: ref<string[]>(['1']),
                openKeys: ref<string[]>(['sub1']),
                books,
                pagination,
                actions,
                level
            };
        }
});
</script>
<style scoped>
  .ant-avatar{
    width:50px;
    height:50px;
    border-radius:8%;
    margin-top:8px;
    line-height: 40px;
  }

</style>