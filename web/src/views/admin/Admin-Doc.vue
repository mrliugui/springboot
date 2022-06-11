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
              >   <a-form-item :name="['doc', 'ebookId']" label="ebookId" v-show="false">
                  <a-input v-model:value="formState.doc.ebookId" />
                  </a-form-item>

                  <a-form-item :name="['doc', 'name']" label="name" :rules="[{ required: true }]">
                      <a-input v-model:value="formState.doc.name" />
                  </a-form-item>

                  <a-form-item :name="['doc', 'parent']" label="parent" :rules="[{required: true}]">
                      <!--<a-input v-model:value="formState.doc.parent"/>-->
                      <a-tree-select
                          v-model:value="formState.doc.parent"
                          show-search
                          style="width: 100%"
                          :dropdown-style="{ maxHeight: '400px', overflow: 'auto' }"
                          placeholder="Please select"
                          allow-clear
                          tree-default-expand-all
                          :tree-data="treeSelectData"
                          :field-names="{title:'name',value:'id',key:'id',children:'children'}"
                  ></a-tree-select>
                  </a-form-item>
                      <!--<a-form-item :name="['doc', 'parent']" label="parent" :rules="[{required: true}]">-->
                      <!--<a-select-->
                              <!--ref="select"-->
                              <!--v-model:value="formState.doc.parent"-->
                      <!--&gt;-->
                          <!--<a-select-option  :value="0">无</a-select-option>-->
                          <!--<a-select-option  v-for="item in level" :value="item.id"-->
                                            <!--:key="item.id"-->
                                            <!--:disabled="formState.doc.id === item.id">-->
                              <!--{{item.name}}-->
                          <!--</a-select-option>-->
                      <!--</a-select>-->

                  <!--</a-form-item>-->
                  <a-form-item :name="['doc', 'sort']" label="sort" :rules="[{type:'number',min: 0, max: 10}]">
                      <a-input-number  v-model:value="formState.doc.sort" />
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
    import {useRoute} from 'vue-router'
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
        doc: {
            id:'',
            ebookId:'',
            name: '',
            parent:'',
            sort:''
        },
    });
    const visible = ref(false);
    const useModelConfirm =(treeSelectData: any,setDisable: any,level: any) => {
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
           formState.doc = Tool.copy(record)
            console.log(formState.doc)
            // 不能选择当前节点及其所有子孙节点，作为父节点，会使树断开
            treeSelectData.value = Tool.copy(level.value);
            setDisable(treeSelectData.value, record.id);

            // 为选择树添加一个"无"
            treeSelectData.value.unshift({id: 0, name: '无'});
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
        const route = useRoute()
        const book = ref('')
           const name = ref("")
            const layout = {
                labelCol: { span: 8 },
                wrapperCol: { span: 16 },
            };
            const add = () => {
                console.log(route)
                visible.value  =true
                formState.doc = {
                    id: '',
                    ebookId:route.params.id[0],
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
                const { doc } = reactive(values)
                console.log("values:{}",values)

                const data = {
                    "id": doc.id,
                    "ebookId":doc.ebookId,
                    "name": doc.name,
                    "parent": doc.parent,
                    "sort": doc.sort,
                }
                console.log(data)
                try{
                  await axios.post("/doc/update",data,
                      { headers: {
                        'Content-Type': 'application/json'
                    },
                      }).then((response)=>{
                  if(response.data.code === 10000){
                      visible.value = false
                      message.success("更新成功")
                      handleQuerry()
                  }
                  else{
                      message.error("更新失败")
                  }
              })}catch(e){
                    message.error("找不到该页面")
                }
            };
            const handleConfirm = (id: number) =>{
                console.log(id)
                axios.delete(`/doc/delete/${id}`).then(
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
                axios.get("/doc/list",{params:{name:searchValue}}).then(
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
            // 因为树选择组件的属性状态，会随当前编辑的节点而变化，所以单独声明一个响应式变量
            const treeSelectData = ref();
            treeSelectData.value = [];
            /**
             * 将某节点及其子孙节点全部置为disabled
             */
            const setDisable = (treeSelectData: any, id: any) => {
                // console.log(treeSelectData, id);
                // 遍历数组，即遍历某一层节点
                for (let i = 0; i < treeSelectData.length; i++) {
                    const node = treeSelectData[i];
                    if (node.id === id) {
                        // 如果当前节点就是目标节点
                        console.log("disabled", node);
                        // 将目标节点设置为disabled
                        node.disabled = true;

                        // 遍历所有子节点，将所有子节点全部都加上disabled
                        const children = node.children;
                        if (Tool.isNotEmpty(children)) {
                            for (let j = 0; j < children.length; j++) {
                                setDisable(children, children[j].id)
                            }
                        }
                    } else {
                        // 如果当前节点不是目标节点，则到其子节点再找找看。
                        const children = node.children;
                        if (Tool.isNotEmpty(children)) {
                            setDisable(children, id);
                        }
                    }
                }
            };
            const handleQuerry = () => {
               loading.value  = true
               axios.get("/doc/all").then((response) => {
                       if(response.data.code === 10000){
                           loading.value = false
                           books.value = response.data.data
                          const result = Tool.array2Tree(books.value,0)
                           level.value = []
                          level.value = result
                           console.log(level.value)
                           // 父文档下拉框初始化，相当于点击新增
                           treeSelectData.value = Tool.copy(level.value) || [];
                           // 为选择树添加一个"无"
                           treeSelectData.value.unshift({id: 0, name: '无'});
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
            const { modalText, confirmLoading, showModal, handleOk,exit} = useModelConfirm(treeSelectData,setDisable,level)
            const { onFinish, layout,name, validateMessages, add,handleConfirm, onSearch,book} = useFormConfirm(handleQuerry,level)
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
                level,
                book,
                treeSelectData
            };
        },
    });
</script>
<style scoped>
  .ant-spin-nested-loading{
  min-height: 456px;
  }
</style>
