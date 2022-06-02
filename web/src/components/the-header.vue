<template>
    <a-layout-header class="header">
        <div class="logo" />
        <a-menu
                v-model:selectedKeys="selectedKeys1"
                theme="dark"
                mode="horizontal"
                :style="{ lineHeight: '64px' }"
        >
            <a-menu-item key="1"><router-link to="/">首页</router-link></a-menu-item>
            <a-menu-item key="2"><router-link to="/admin/ebook">管理页面</router-link></a-menu-item>
            <a-menu-item key="3"><router-link to="/about">我的</router-link></a-menu-item>
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
