<template>
    <a-cascader v-model:value="value" :options="options" placeholder="Please select" />
</template>
<script>
    import { defineComponent, ref } from 'vue';
    const options = [{
        value: '1',
        label: 'zhejiang',
        children: [{
            value: 'hangzhou',
            label: 'Hangzhou',
            children: [{
                value: 'xihu',
                label: 'West Lake',
            }],
        }],
    }, {
        value: '2',
        label: 'jiangsu',
        children: [{
            value: 'nanjing',
            label: 'Nanjing',
            children: [{
                value: 'zhonghuamen',
                label: 'Zhong Hua Men',
            }],
        }],
    }];
    export default defineComponent({
        setup() {
            return {
                value: ref([1,2]),
                options,
            };
        },

    });
</script>