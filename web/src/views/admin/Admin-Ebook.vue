<template>

  <a-layout-content style="padding: 0 50px">
    <a-layout style="padding: 24px 0; background: #fff;min-height: 280px;">
      <div class="about">
        <a-table
                :columns="columns"
                :data-source="books"
                :pagination="pagination"
                :loading="loading"
                @change="handleChange"
        >
          <template #headerCell="{ column }">
            <template v-if="column.key === 'cover'">
        <span>
          封面
        </span>
          </template>
          </template>

          <template #bodyCell="{ column, record }">
            <template v-if="column.key === 'cover'">
              <a>
                <a-image
                        :width="70"
                        :height="70"
                        src="column.cover"
                        fallback="data:image/png;base64,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"
                />
              </a>
            </template>
            <!--<template v-else-if="column.key === 'tags'">-->
             <!--<span>{{record.name}}</span>-->
            <!--</template>-->
            <template v-else-if="column.key === 'action'">
        <span>
          <a-space>
          <a-button type="primary" size="large" @click="exit(record)">编辑</a-button>
          <a-button type="danger" size="large">删除</a-button>
            </a-space>
        </span>
            </template>
          </template>
        </a-table>
      </div>
    </a-layout>
  </a-layout-content>
  <template>
    <div>
      <a-button type="primary" @click="showModal">Open Modal with async logic</a-button>
      <a-modal
              v-model:visible="visible"
              title="书籍"
              :confirm-loading="confirmLoading"
              @ok="handleOk"
      >
              <a-form
                      :model="formState"
                      v-bind="layout"
                      name="nest-messages"
                      :validate-messages="validateMessages"
                      @finish="onFinish"
              >   <a-form-item :name="['UpdateEbookReq', 'id']" label="id" v-show="false">
                  <a-input v-model:value="formState.UpdateEbookReq.id" />
              </a-form-item>
                  <a-form-item :name="['UpdateEbookReq', 'cover']" label="Cover" :rules="[{ required: true }]">
                      <a-input v-model:value="formState.UpdateEbookReq.cover" />
                  </a-form-item>
                  <a-form-item :name="['UpdateEbookReq', 'name']" label="Name" :rules="[{ required: true }]">
                      <a-input v-model:value="formState.UpdateEbookReq.name" />
                  </a-form-item>
                  <a-form-item :name="['UpdateEbookReq', 'docCount']" label="docCount" :rules="[{ type: 'number', min: 0, max: 99 }]">
                      <a-input-number v-model:value="formState.UpdateEbookReq.docCount" />
                  </a-form-item>
                  <a-form-item :name="['UpdateEbookReq', 'category1Id']" label="Category1">
                      <a-input v-model:value="formState.UpdateEbookReq.category1Id" />
                  </a-form-item>
                  <a-form-item :name="['UpdateEbookReq', 'category2Id']" label="Category1">
                      <a-input v-model:value="formState.UpdateEbookReq.category2Id" />
                  </a-form-item>
                  <a-form-item :name="['UpdateEbookReq', 'description']" label="description">
                      <a-textarea v-model:value="formState.UpdateEbookReq.description" />
                  </a-form-item>
                  <a-form-item :wrapper-col="{ ...layout.wrapperCol, offset: 8 }">
                      <a-button type="primary" html-type="submit"

                      >Submit</a-button>
                  </a-form-item>
              </a-form>
      </a-modal>
    </div>
  </template>
</template>
<script lang="ts">
    import {DownOutlined, SmileOutlined} from '@ant-design/icons-vue';
    import {defineComponent, onMounted, reactive, ref, toRefs} from 'vue';
    import axios from "axios";
    import {message} from "ant-design-vue";

    const columns = [
        {
            name: '封面',
            dataIndex: 'cover',
            key: 'cover',
        },
        {
            title: '名称',
            dataIndex: 'name',
            key: 'tags',
        },
        {
            title: '分类一',
            dataIndex: 'category1Id',
            key: 'category1Id',
        },
        {
            title: '分类二',
            key: 'category2Id',
            dataIndex: 'category2Id',
        },
        {
            title: '文档数',
            key: 'doc_count',
            dataIndex: 'docCount',
        },
        {
            title: '阅读数',
            key: 'view_count',
            dataIndex: 'viewCount',
        },
        {
            title: '点赞数',
            key: 'vote_count',
            dataIndex: 'voteCount',
        },
        {
            title: 'Action',
            key: 'action',
        },
    ];
    let formState = reactive({
        UpdateEbookReq: {
            id:'',
            cover:'',
            name: '',
            docCount: undefined,
            category1Id: '',
            category2Id: '',
            description: '',
        },
    });
    const visible = ref(false);
    const useModelConfirm =() => {
        const modalText = ref("Content of the modal");
        const confirmLoading = ref(false);
        const showModal = () => {
            visible.value = true;
        };

        const handleOk = () => {
            modalText.value = 'The modal will be closed after two seconds';
            confirmLoading.value = true;
            setTimeout(() => {
                visible.value = false;
                confirmLoading.value = false;
            }, 2000);
        }
        const exit = (record: any) => {
            visible.value = true;
           formState.UpdateEbookReq = record
        }
        return {
            modalText,
            visible,
            confirmLoading,
            showModal,
            handleOk,
            exit,
        };
    }
    const useFormConfirm = () =>{
            const layout = {
                labelCol: { span: 8 },
                wrapperCol: { span: 16 },
            };

            const validateMessages = {
                required: '${label} is required!',
                types: {
                    email: '${label} is not a valid email!',
                    number: '${label} is not a valid number!',
                },
                number: {
                    range: '${label} must be between ${min} and ${max}',
                },
            };

            const onFinish = async (values: any) => {
                console.log(values)
                try{
                  await axios.post("/ebook/update",{values}).then((response)=>{
                  if(response.data.code === 10000){
                      visible.value = false
                      message.success("更新成功")

                  }
                  else{
                      message.error("更新失败")
                  }
              })}catch(e){
                    message.error("找不到该页面")
                }
            };
            return {
                onFinish,
                layout,
                validateMessages,
            };
        }

    export default defineComponent({
        components: {
            SmileOutlined,
            DownOutlined,
        },
        setup() {
            const { modalText, confirmLoading, showModal, handleOk,exit} = useModelConfirm()
            const { onFinish, layout, validateMessages } = useFormConfirm()

            const books = ref('')
            const loading = ref(false)
            const pagination = reactive({
                current:1,
                defaultPageSize:4,
                total:0
            })
            const { total } = toRefs(pagination)
            const handleQuerry = (params: any) => {
               loading.value  = true
               axios.get("/ebook/list",{
                   params:{
                       pageNum:params.pageNum,
                       pageSize:params.pageSize,
                   }
               }).then((response) => {
                   loading.value = false
                   books.value  =response.data.data.content
                   pagination.current = params.pageNum
                   total.value = response.data.data.total
               })
            }
            const handleChange = (pagination: any) => {
             handleQuerry({
                page:pagination.current,
                size:pagination.pageSize
            })
            }
            onMounted(() => {
            handleQuerry({
                pageNum:1,
                pageSize:pagination.defaultPageSize,
            });
            })
            return {
                // data,
                visible,
                columns,
                books,
                pagination,
                loading,
                handleChange,
                modalText,
                confirmLoading,
                showModal,
                handleOk,
                exit,
                formState,
                onFinish,
                layout,
                validateMessages,
            };
        },
    });
</script>
<style scoped>
  .ant-spin-nested-loading{
  min-height: 456px;
  }
</style>
