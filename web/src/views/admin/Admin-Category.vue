<template>
  <a-layout-content style="padding: 0 50px">
    <a-layout style="padding: 24px 0; background: #fff;min-height: 280px;">
      <div class="about">
          <a-input-search
                  style="width: 300px;margin-left: 10px;margin-bottom:5px"
                  v-bind:value="book"
                  placeholder="请输入你要查询的种类"
                  enter-button="查询"
                  size="large"
                  @search="onSearch"
          />
              <a-button type="primary" @click="add" size="large" style="margin-left: 15px;margin-bottom: 5px">新增</a-button>

        <a-table
                :columns="columns"
                :row-key="record => record.id"
                :data-source="level"
                :pagination="false"
                :loading="loading"
        >
          <template #headerCell="{ column }">
    
          </template>

          <template #bodyCell="{ column, record }">
          
            <!--<template v-else-if="column.key === 'tags'">-->
             <!--<span>{{record.name}}</span>-->
            <!--</template>-->
            <template v-if="column.key === 'action'">
        <span>
          <a-space>
          <a-button type="primary" size="large" @click="exit(record)">编辑</a-button>
               <a-popconfirm
                       title="你确认删除这本书籍吗？"
                       ok-text="是"
                       cancel-text="否"
                       @confirm="handleConfirm(record.id)"
               >
          <a-button type="danger" size="large">删除</a-button>
               </a-popconfirm>
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
              >   <a-form-item :name="['category', 'id']" label="id" v-show="false">
                  <a-input v-model:value="formState.category.id" />
              </a-form-item>
                  <a-form-item :name="['category', 'name']" label="name" :rules="[{ required: true }]">
                      <a-input v-model:value="formState.category.name" />
                  </a-form-item>
                  <a-form-item :name="['category', 'parent']" label="parent" :rules="[{required: true}]">
                      <a-input-number v-model:value="formState.category.parent" />
                      <a-select
                              ref="select"
                              v-model:value="formState.category.parent"
                      >
                          <a-select-option  :value="0">无</a-select-option>
                          <a-select-option  v-for="item in level" :value="item.id"
                                            :key="item.id"
                                            :disabled="formState.category.id === item.id">
                              {{item.name}}
                          </a-select-option>
                      </a-select>

                  </a-form-item>
                  <a-form-item :name="['category', 'sort']" label="Category1" :rules="[{type:'number',min: 0, max: 10}]">
                      <a-input-number  v-model:value="formState.category.sort" />
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
    import {defineComponent, onMounted, reactive, ref} from 'vue';
    import axios from "axios";
    import {message} from "ant-design-vue";
    import {Tool} from "../../util/tool";

    const columns = [

        {
            title: '名称',
            dataIndex: 'name',
            key: 'name',
        },
        {
            title: '分类',
            dataIndex: 'parent',
            key: 'parent',
        },
              {
            title: '顺序',
            dataIndex: 'sort',
            key: 'sort',
        },
        {
            title: 'Action',
            key: 'action',
        },
    ];
    let formState = reactive({
        category: {
            id:'',
            name: '',
            parent:'',
            sort:''
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
           formState.category = Tool.copy(record)
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
    const useFormConfirm = (handleQuerry: any, level: any) =>{
        const book = ref('')
           const name = ref("")
            const layout = {
                labelCol: { span: 8 },
                wrapperCol: { span: 16 },
            };
            const add = () => {
                visible.value  =true
                formState.category = {
                    id:'',
                    name: '',
                    parent:'',
                    sort:''
                }
            }


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
                const { category } = reactive(values)
                console.log(values)

                const data = {
                    "id": category.id,
                    "name": category.name,
                    "parent": category.parent,
                    "sort": category.sort,
                }
                console.log(data)
                try{
                  await axios.post("/category/update",data,
                      { headers: {
                        'Content-Type': 'application/json'
                    },
                      }).then((response)=>{
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
            const handleConfirm = (id: number) =>{
                axios.delete(`/category/delete/${id}`).then(
                    (response)=>{
                       if(response.data.code === 10000){
                           handleQuerry();
                       }
                       else{
                           message.error(response.data.msg)
                       }
                    }
                )
            }
            function onSearch(searchValue: string){
                axios.get("/category/list",{params:{name:searchValue}}).then(
                    (response) => {
                        if(response.data.code === 10000){
                            level.value = response.data.data.content;
                        }else{
                            message.error(response.data.msg);
                        }

                    }
                )
            }
            return {
                name,
                onFinish,
                layout,
                validateMessages,
                add,
                handleConfirm,
                onSearch,
                book
            };
        }

    export default defineComponent({
        components: {
            SmileOutlined,
            DownOutlined,
        },
        setup() {
            const books = ref("")
            const loading = ref(false)
            const level = ref<any>()
            const handleQuerry = () => {
               loading.value  = true
               axios.get("/category/all").then((response) => {
                       if(response.data.code === 10000){
                           loading.value = false
                           books.value = response.data.data
                          const result = Tool.array2Tree(books.value,0)
                           level.value = []
                          level.value = result
                           console.log(level.value)
                       }
                       else{
                           message.error(response.data.msg)
                       }

               })
            }
            const handleChange = () => {
             handleQuerry()
            }
            onMounted(() => {
            handleQuerry();
            })
            const { modalText, confirmLoading, showModal, handleOk,exit} = useModelConfirm()
            const { onFinish, layout,name, validateMessages, add,handleConfirm, onSearch} = useFormConfirm(handleQuerry,level)
            return {
                // data,
                visible,
                columns,
                // books,
                loading,
                modalText,
                confirmLoading,
                showModal,
                handleOk,
                exit,
                formState,
                onFinish,
                layout,
                validateMessages,
                add,
                handleConfirm,
                onSearch,
                name,
                level
            };
        },
    });
</script>
<style scoped>
  .ant-spin-nested-loading{
  min-height: 456px;
  }
</style>
