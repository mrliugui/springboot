<template>
    <a-tree
            :tree-data="treeData"
            :field-names="{
                children: 'children',
                title: 'tit',
            }"
    >

        <!--<template #title="{ name, key }">-->
            <!--<span v-if="key === '0-0-1'" style="color: #1890ff">{{ name }}</span>-->
            <!--<template v-else>{{ name }}</template>-->
        <!--</template>-->
    </a-tree>
</template>
<script lang="ts">
    import {defineComponent} from 'vue';
    import {TreeProps} from "ant-design-vue/lib/vc-tree/props";

    export default defineComponent({
        setup() {
            const fieldNames: TreeProps['fieldNames'] = {
                children: 'children',
                title: 'tit',
            };

            // const treeData: TreeProps['treeData'] = [
            const treeData=[
                {
                    tit: 'Node1',//按照官方文档这里的键值对应该是title 下面就不写注释了
                    name: '0-0', //按照光放文档这里的键值对应该是value 下面就不写注释了
                    key: '0-0',
                    children: [
                        {
                            tit: 'Child Node1', //title
                            name: '0-0-1', // value
                            key: '0-0-1',
                        },
                        {
                            tit: 'Child Node2',
                            name: '0-0-2',
                            key: '0-0-2',
                        },
                    ],
                },
                {
                    tit: 'Node2',
                    name: '0-1',
                    key: '0-1',
                    children: [
                        {
                            tit: 'Child Node2-1',
                            name: '0-2-1',
                            key: '0-2-1',
                        },
                        {
                            tit: 'Child Node2-2',
                            name: '0-2-2',
                            key: '0-2-2',
                        },
                    ],
                },
            ];


            return {
                fieldNames,
                // treeData: ref(treeData),
                treeData
            };
        },
    });
</script>