<template>
    <a-layout-header class="header">
        <div class="logo" ></div>
        <a><span v-show="!userSession.name" @click="handleLogin" style="color:rgba(255, 255, 255, 0.65);float: right;">登录</span></a>
        <a-popconfirm
                title="您确定退出登录吗？"
                ok-text="是"
                cancel-text="否"
                @confirm="logout"
        >
            <a style="float: right;" ><span v-show="userSession.name" style="color:rgba(255, 255, 255, 0.65);padding-left: 15px;margin-right: 5px">退出登录</span></a>
        </a-popconfirm>
        <a><span v-show="userSession.name" style="color:rgba(255, 255, 255, 0.65);float: right;">用户:{{userSession.name}}</span></a>
        <a-menu
                v-model:selectedKeys="selectedKeys1"
                theme="dark"
                mode="horizontal"
                :style="{ lineHeight: '64px' }"
        >
            <a-menu-item key="1"><router-link to="/">首页</router-link></a-menu-item>
            <a-menu-item key="2"><router-link to="/admin/user">用户管理</router-link></a-menu-item>
            <a-menu-item key="3"><router-link to="/admin/ebook">电子书管理</router-link></a-menu-item>
            <a-menu-item key="4"><router-link to="/admin/category">分类管理</router-link></a-menu-item>
            <a-menu-item key="5"><router-link to="/about">我的</router-link></a-menu-item>
        </a-menu>

    </a-layout-header>
    <a-modal v-model:visible="loginShow" title=" 登录框" @ok="handleOk" :footer="null">
        <a-form
                :model="formState"
                name="登录"
                :label-col="{ span: 8 }"
                :wrapper-col="{ span: 12 }"
                @finish="onFinish"
                @finishFailed="onFinishFailed"
        >
            <a-form-item
                    label="Username"
                    name="username"
                    :rules="[{ required: true, message: 'Please input your username!' }]"
            >
                <a-input v-model:value="formState.username" />
            </a-form-item>

            <a-form-item
                    label="Password"
                    name="password"
                    :rules="[{ required: true, message: 'Please input your password!' }]"
            >
                <a-input-password v-model:value="formState.password" />
            </a-form-item>

            <a-form-item name="remember" :wrapper-col="{ offset: 8, span: 16 }">
                <a-checkbox v-model:checked="formState.remember">Remember me</a-checkbox>
            </a-form-item>

            <a-form-item :wrapper-col="{ offset: 8, span: 16 }">
                <a-button type="primary" html-type="submit">Submit</a-button>
            </a-form-item>
        </a-form>
    </a-modal>

</template>
<script lang="ts">
    import {computed, defineComponent, reactive, ref} from 'vue';
    import axios from "axios";
    import {message} from "ant-design-vue";
    import {useStore} from "vuex";

    interface FormState {
        username: string;
        password: string;
        remember: boolean;
    }
    const user = ref();
    user.value = {};
    export default defineComponent({
        name: 'the-header',
        setup () {
            const store = useStore();
            const userSession = computed(() => store.state.user)
            const loginShow = ref(false);
            let formState = reactive<FormState>({
                username: '',
                password: '',
                remember: true,
            });
            const logout = () =>{
                axios.get("/user/logout/"+store.state.user.token).then(
                    (response) => {
                        if(response.data.code === 10000){
                            console.log("用户名称{}",store.state.user.name)
                            //赋值空对象，不赋值null，是因为避免空指针异常
                            store.commit("setUser",{})
                            message.success("退出登录成功")
                        }
                    }
                )
            }
            const handleLogin = () => {
                loginShow.value=true;
                formState.password="liugui"
                formState.username="liugui1"
            }
            const onFinish = (values: any) => {
                console.log('Success:', values);
                const data = {
                    "username": values.username,
                    "password": values.password,
                }
                axios.post("/user/login",data,{
                    headers: {
                        'Content-Type': 'application/json'
                    }
                }).then((response) => {
                   if(response.data.code===10000){
                       loginShow.value = false
                       user.value = response.data.data;
                       console.log(user.value)
                       store.commit("setUser",user.value);
                       message.success("登录成功")
                   }else{
                       message.error(response.data.msg);
                   }
                })
            };
           const onFinishFailed = (errorInfo: any) => {
                console.log('Failed:', errorInfo);
            };
           return{
                selectedKeys1: ref<string[]>(['1']),
               formState,
               onFinish,
               onFinishFailed,

               handleLogin,
               loginShow,

               user,
               userSession,

               logout
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