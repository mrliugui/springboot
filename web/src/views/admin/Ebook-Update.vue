<!--<template>-->
    <!--<template>-->
        <!--<div>-->
            <!--<a-button type="primary" @click="showModal">Open Modal with async logic</a-button>-->
            <!--<a-modal-->
                    <!--v-model:visible="visible"-->
                    <!--title="书籍"-->
                    <!--:confirm-loading="confirmLoading"-->
                    <!--@ok="handleOk"-->
            <!--&gt;-->
                <!--<a-form-->
                        <!--:model="formState"-->
                        <!--v-bind="layout"-->
                        <!--name="nest-messages"-->
                        <!--:validate-messages="validateMessages"-->
                        <!--@finish="onFinish"-->
                <!--&gt;-->
                    <!--<a-form-item :name="['UpdateEbookReq', 'cover']" label="Cover" :rules="[{ required: true }]">-->
                        <!--<a-input v-model:value="formState.UpdateEbookReq.cover" />-->
                    <!--</a-form-item>-->
                    <!--<a-form-item :name="['UpdateEbookReq', 'name']" label="Name" :rules="[{ required: true }]">-->
                        <!--<a-input v-model:value="formState.UpdateEbookReq.name" />-->
                    <!--</a-form-item>-->
                    <!--<a-form-item :name="['UpdateEbookReq', 'docCount']" label="docCount" :rules="[{ type: 'number', min: 0, max: 99 }]">-->
                        <!--<a-input-number v-model:value="formState.UpdateEbookReq.docCount" />-->
                    <!--</a-form-item>-->
                    <!--<a-form-item :name="['UpdateEbookReq', 'category1']" label="Category1">-->
                        <!--<a-input v-model:value="formState.UpdateEbookReq.category1" />-->
                    <!--</a-form-item>-->
                    <!--<a-form-item :name="['UpdateEbookReq', 'category2']" label="Category1">-->
                        <!--<a-input v-model:value="formState.UpdateEbookReq.category2" />-->
                    <!--</a-form-item>-->
                    <!--<a-form-item :name="['UpdateEbookReq', 'introduction']" label="Introduction">-->
                        <!--<a-textarea v-model:value="formState.UpdateEbookReq.introduction" />-->
                    <!--</a-form-item>-->
                    <!--<a-form-item :wrapper-col="{ ...layout.wrapperCol, offset: 8 }">-->
                        <!--<a-button type="primary" html-type="submit">Submit</a-button>-->
                    <!--</a-form-item>-->
                <!--</a-form>-->
            <!--</a-modal>-->
        <!--</div>-->
    <!--</template>-->
<!--</template>-->
<!--<script lang="ts">-->
    <!--import { ref, reactive, defineComponent, inject } from 'vue'-->
    <!--const useModelConfirm =() => {-->
        <!--const visible = ref(false)-->
        <!--const modalText = ref("Content of the modal");-->
        <!--const confirmLoading = ref(false);-->
        <!--const showModal = () => {-->
            <!--visible.value = true;-->
        <!--};-->

        <!--const handleOk = () => {-->
            <!--modalText.value = 'The modal will be closed after two seconds';-->
            <!--confirmLoading.value = true;-->
            <!--setTimeout(() => {-->
                <!--visible.value = false;-->
            <!--confirmLoading.value = false;-->
        <!--}, 2000);-->
        <!--}-->
        <!--return {-->
            <!--modalText,-->
            <!--visible,-->
            <!--confirmLoading,-->
            <!--showModal,-->
            <!--handleOk,-->
        <!--};-->
    <!--}-->
    <!--const useFormConfirm = () =>{-->
        <!--const layout = {-->
            <!--labelCol: { span: 8 },-->
            <!--wrapperCol: { span: 16 },-->
        <!--};-->

        <!--const validateMessages = {-->
            <!--required: '${label} is required!',-->
            <!--types: {-->
                <!--email: '${label} is not a valid email!',-->
                <!--number: '${label} is not a valid number!',-->
            <!--},-->
            <!--number: {-->
                <!--range: '${label} must be between ${min} and ${max}',-->
            <!--},-->
        <!--};-->

        <!--const formState = reactive({-->
            <!--UpdateEbookReq: {-->
                <!--cover:'',-->
                <!--name: '',-->
                <!--docCount: undefined,-->
                <!--category1: '',-->
                <!--category2: '',-->
                <!--introduction: '',-->
            <!--},-->
        <!--});-->
        <!--const onFinish = (values: any) => {-->
            <!--console.log('Success:', values);-->
        <!--};-->
        <!--return {-->
            <!--formState,-->
            <!--onFinish,-->
            <!--layout,-->
            <!--validateMessages,-->
        <!--};-->
    <!--}-->
    <!--export default defineComponent({-->
        <!--name:'EbookUpdate',-->
        <!--props:["record"],-->
        <!--setup() {-->
            <!--const visisble = inject("visible");-->
            <!--const {modalText, visible, confirmLoading, showModal, handleOk} = useModelConfirm()-->
            <!--const {formState, onFinish, layout, validateMessages,} = useFormConfirm()-->
            <!--return{-->
                <!--modalText,-->
                <!--visible,-->
                <!--confirmLoading,-->
                <!--showModal,-->
                <!--handleOk,-->
                <!--formState,-->
                <!--onFinish,-->
                <!--layout,-->
                <!--validateMessages-->
            <!--}-->
        <!--}-->
    <!--})-->
<!--</script>-->