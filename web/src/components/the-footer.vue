<template>
    <a-layout-footer style="text-align: center">
        Ant Design ©2018 Created by Ant UED
    </a-layout-footer>
</template>
<script lang="ts">
    import {defineComponent, onMounted} from 'vue';
    import {Tool} from '../util/tool';
    import {notification} from "ant-design-vue";

    export default defineComponent({
        name: 'the-footer',
        setup(){
            let webSocket: any;
            let token: any;
            const onOpen = () =>{
                console.log("WebSocket连接成功，",webSocket.readyState)
            }
            const onMessage = (event: any) =>{
                console.log("发送消息成功，",event.data)
                notification['success']({
                    message: '发来消息',
                    description:event.data
                });
            }
            const onError = () =>{
                console.log("WebSocket连接失败，",webSocket.readyState)
            }
            const onClose = () =>{
                console.log("WebSocket连接关闭，",webSocket.readyState)
            }
            const initWebsocket = () =>{
            webSocket.onopen=onOpen;
            webSocket.onmessage=onMessage;
            webSocket.onerror=onError;
            webSocket.onclose=onClose;
            }
            onMounted(
                () => {
                    if('WebSocket'in window){
                    token = Tool.uuid(10)
                        webSocket = new WebSocket(process.env.VUE_APP_WS_SERVER+'/ws/'+token)
                        initWebsocket();
                    } else{
                        alert("该浏览器不支持")
                    }
                }
            )
        }
    });
</script>