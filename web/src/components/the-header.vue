<template>
    <a-layout-header class="header">
        <div class="logo" ></div>
        <a-menu
                v-model:selectedKeys="selectedKeys1"
                theme="dark"
                mode="horizontal"
                :style="{ lineHeight: '64px' }"
        >
            <a-menu-item key="1"><router-link to="/">首页</router-link></a-menu-item>
            <a-menu-item key="2"><router-link to="/admin/ebook">电子书管理</router-link></a-menu-item>
            <a-menu-item key="3"><router-link to="/admin/category">分类管理</router-link></a-menu-item>
            <a-menu-item key="4"><router-link to="/about">我的</router-link></a-menu-item>
        </a-menu>
    </a-layout-header>
</template>about
<script lang="ts">
    import {defineComponent, ref} from 'vue';

    export default defineComponent({
        name: 'the-header',
        setup () {
           return{
                selectedKeys1: ref<string[]>(['2'])
            }
        }
    });
</script>
<style scoped>
    .logo {
        float: left;
        width: 120px;
        height: 31px;
        margin: 16px 24px 16px 0;
        background: rgba(255, 255, 255, 0.3);
    }

    .ant-row-rtl #components-layout-demo-top-side-2 .logo {
        float: right;
        margin: 16px 0 16px 24px;
    }

</style>