<template>
<a-layout-content style="padding: 0 50px">
    <a-layout style="padding: 24px 0; background: #fff;min-height: 280px;">
      <div class="about">
          <a-input-search
                  style="width: 300px;margin-left: 10px;margin-bottom:5px"
                  v-bind:value="book"
                  placeholder=" 请输入你要查找的用户"
                  enter-button="Search"
                  size="large"
                  @search="onSearch"
          />
              <a-button type="primary" @click="add" size="large" style="margin-left: 15px;margin-bottom: 5px">新增</a-button>

        <a-table
                :columns="columns"
                :data-source="books"
                :pagination="pagination"
                :loading="loading"
                @change="handleChange"
        >
          <template #headerCell="{ column }">
              <template v-if="column.key === 'loginName'">
        <span>
          用户名称
        </span>
              </template>
          </template>

          <template #bodyCell="{ column, record }">
              <!--<template v-else-if="column.key === 'category1Id'">-->
                 <!--<span>{{ getCategoryName(record.category1Id) }}</span>-->
              <!--</template>-->
              <!--<template v-else-if="column.key === 'category2Id'">-->
                 <!--<span>{{getCategoryName(record.category2Id)}}</span>-->
              <!--</template>-->
              <!--<template v-slot:category="{text,record}">-->
                  <!--<span>分类</span>-->
              <!--</template>-->
            <!--<template v-else-if="column.key === 'tags'">-->
             <!--<span>{{record.name}}</span>-->
            <!--</template>-->
            <template v-if="column.key === 'action'">
        <span>
          <a-space>
          <a-button type="primary" size="large" @click="exit(record)">编辑</a-button>
               <a-popconfirm
                       title="你确认删除这本用户吗？"
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
              title="用户"
              :confirm-loading="confirmLoading"
              @ok="handleOk"
      >
              <a-form
                      :model="formState"
                      v-bind="layout"
                      name="nest-messages"
                      :validate-messages="validateMessages"
                      @finish="onFinish"
              >   <a-form-item :name="['user', 'id']" label="id" v-show="false">
                  <a-input v-model:value="formState.user.id" />
              </a-form-item>
                  <a-form-item :name="['user', 'loginName']" label="LoginName" :rules="[{ required: true }]">
                      <a-input v-model:value="formState.user.loginName" :disabled="formState.user.loginName!=''?true:false"/>
                  </a-form-item>
                  <a-form-item :name="['user', 'name']" label="Name" :rules="[{ required: true }]">
                      <a-input v-model:value="formState.user.name" />
                  </a-form-item>
                  <a-form-item :name="['user', 'password']" label="password" :rules="[{ required:true }]">
                      <a-input v-model:value="formState.user.password" />
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
            title: '用户名称',
            dataIndex: 'loginName',
            key: 'loginName',
        },
        {
            title: '用户昵称',
            dataIndex: 'name',
            key: 'name',
        },
        {
            title: '密码',
            dataIndex: 'password',
            key: 'password',
        },
        {
            title: 'Action',
            key: 'action',
        },
    ];
    let formState = reactive({
        user: {
            id:'',
            loginName:'',
            name:'',
            password:'',
        },
    });
    const visible = ref(false);
    let categoryIds  = ref()
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
            formState.user = Tool.copy(record)
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
    const useFormConfirm = (handleQuerry: any,pagination: any,books: any,loading: any) =>{
        const book = ref('')
        const name = ref("")
        // let category: any
        const level1 = ref<any>()
        // const handleCategoryQuerry = () => {
        //     loading.value  = true
        //     axios.get("/category/all").then((response) => {
        //         if(response.data.code === 10000){
        //             loading.value = false
        //             category = response.data.data
        //             const result = Tool.array2Tree(category,0)
        //             level1.value = []
        //             level1.value = result
        //             console.log(level1.value)
        //         }
        //         else{
        //             message.error(response.data.msg)
        //         }
        //
        //     })
        // }
            const layout = {
                labelCol: { span: 8 },
                wrapperCol: { span: 16 },
            };
            const add = () => {
                visible.value  =true
                formState.user = {
                    id:'',
                    loginName:'',
                    name: '',
                    password:'',
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
                const { user } = reactive(values)
                console.log(values)

                const data = {
                    "id": user.id,
                    "loginName":user.loginName,
                    "name": user.name,
                    "password":user.password
                }
                console.log(data)
                try{
                  await axios.post("/user/update",data,
                      { headers: {
                        'Content-Type': 'application/json'
                    },
                      }).then((response)=>{
                  if(response.data.code === 10000){
                      visible.value = false
                      message.success("更新成功")
                      handleQuerry({
                          pageNum:pagination.current,
                          pageSize:pagination.defaultPageSize,
                      })
                  }
                  else{
                      message.error(response.data.msg)
                  }
              })}catch(e){
                    message.error("找不到该页面")
                }
            };
            const handleConfirm = (id: number) =>{
                axios.delete(`/user/delete/${id}`).then(
                    (response)=>{
                       if(response.data.code === 10000){
                           handleQuerry({
                               pageNum:pagination.current,
                               pageSize:pagination.defaultPageSize,
                           });
                       }
                       else{
                           message.error(response.data.msg)
                       }
                    }
                )
            }
            function onSearch(searchValue: string){
                axios.get("/user/list",{params:{name:searchValue}}).then(
                    (response) => {
                        if(response.data.code === 10000){
                            pagination.total = response.data.data.total
                            books.value = response.data.data.content;
                        }else{
                            message.error(response.data.msg);
                        }

                    }
                )
            }
            // const getCategoryName = (id: any) => {
            //     let result: any
            //     for (let categoryKey in category) {
            //         if(category[categoryKey].id === id){
            //             result = category[categoryKey].name
            //         }
            //     }
            //     return result;}
            return {
                name,
                onFinish,
                layout,
                validateMessages,
                add,
                handleConfirm,
                onSearch,
                level1,
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
            const pagination = reactive({
                current:1,
                defaultPageSize:4,
                total:0
            })
            // computed: {
            //     layout() {
            //         const layout = this.$route.meta.layout || 'default'
            //         return () => import(`@/app/layouts/${layout}.vue`)
            //     }
            // }
            // const { current, defaultPageSize } = pagination
            // const { total } = toRefs(pagination)
            const handleQuerry = (params: any) => {
               loading.value  = true
               axios.get("/user/list",{
                   params:{
                       pageNum:params.pageNum,
                       pageSize:params.pageSize,
                   }
               }).then((response) => {
                       if(response.data.code === 10000){
                           loading.value = false
                           const apiResponse = response.data
                           books.value  =apiResponse.data.content
                           pagination.current = params.pageNum
                           pagination.total = apiResponse.data.total
                       }
                       else{
                           message.error(response.data.msg)
                       }

               })
            }
            const handleChange = (pagination: any) => {
             handleQuerry({
                 pageNum:pagination.current,
                 pageSize:pagination.pageSize
            })
            }
            const { modalText, confirmLoading, showModal, handleOk,exit} = useModelConfirm()
            const { onFinish, layout,name, validateMessages,
                add,handleConfirm, onSearch,
                level1} = useFormConfirm(handleQuerry,pagination,books,loading)
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
                add,
                handleConfirm,
                onSearch,
                name,
                    level1,
                    categoryIds,
            };
        },
    });
</script>
<style scoped>
  .ant-spin-nested-loading{
  min-height: 456px;
  }
</style>
