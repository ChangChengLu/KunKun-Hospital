<template>
  <a-layout id="components-layout-demo-custom-trigger" class="site-layout">
    <a-layout-sider v-model:collapsed="collapsed" :trigger="null" collapsible>
      <a-menu v-model:selectedKeys="selectedKeys" theme="dark" mode="inline">
        <a-menu-item key="1">
          <user-outlined />
          <span><router-link to="#">用户管理</router-link></span>
        </a-menu-item>
        <a-menu-item key="2">
          <video-camera-outlined />
          <span>医院管理</span>
        </a-menu-item>
        <a-menu-item key="3">
          <upload-outlined />
          <span>医生管理</span>
        </a-menu-item>
      </a-menu>
    </a-layout-sider>
    <a-layout>
      <a-layout-header style="background: #fff; padding: 0">
        <div id="left">
          <menu-unfold-outlined
            v-if="collapsed"
            class="trigger"
            @click="doTrigger"
          />
          <menu-fold-outlined v-else class="trigger" @click="doTrigger" />
        </div>
        <div id="right">
          <a-avatar :src="userVO.userAvatar" />
          <my-slot />
          <a-dropdown-button>
            {{ userVO.userName }}
            <template #overlay>
              <a-menu @click="handleMenuClick">
                <a-menu-item key="1">
                  <UserOutlined />
                  个人中心
                </a-menu-item>
                <a-menu-item key="2">
                  <setting-outlined />
                  设置
                </a-menu-item>
                <a-menu-item key="3" @click="doLoginOut">
                  <poweroff-outlined />
                  退出登录
                </a-menu-item>
              </a-menu>
            </template>
            <template #icon><UserOutlined /></template>
          </a-dropdown-button>
        </div>
      </a-layout-header>
      <a-layout-content
        class="site-layout-background"
        :style="{
          margin: '24px 16px',
          padding: '24px',
          background: '#fff',
          minHeight: '100vh',
        }"
      >
        <a-breadcrumb style="margin: 16px 0">
          <a-breadcrumb-item>User</a-breadcrumb-item>
          <a-breadcrumb-item>Bill</a-breadcrumb-item>
        </a-breadcrumb>
        {{ userVO }}
        <slot></slot>
      </a-layout-content>
      <page-footer />
    </a-layout>
  </a-layout>
</template>

<script setup lang="ts">
import { computed, ref } from "vue";
import {
  MenuFoldOutlined,
  MenuUnfoldOutlined,
  PoweroffOutlined,
  SettingOutlined,
  UploadOutlined,
  UserOutlined,
  VideoCameraOutlined,
} from "@ant-design/icons-vue";
import PageFooter from "@/layouts/footer/PageFooter.vue";
import MySlot from "@/components/MySlot.vue";
import type { MenuProps } from "ant-design-vue";
import {
  getUserVOByIdUsingGET,
  userLogoutUsingPOST,
} from "@/services/userController";
import { useRouter } from "vue-router";

const router = useRouter();
const selectedKeys = ref<string[]>(["1"]);
const collapsed = ref<boolean>(false);

const doTrigger = () => {
  collapsed.value = !collapsed.value;
};

const handleMenuClick: MenuProps["onClick"] = (e) => {
  console.log("click", e);
};

// eslint-disable-next-line no-undef
const userVO = ref<API.UserVO>({});
const loadData = async (id: number) => {
  const res = await getUserVOByIdUsingGET(id).then((res) => {
    console.log(res);
    userVO.value = { ...res.data };
  });
};

const doLoginOut = async () => {
  await userLogoutUsingPOST().then(() => {
    userVO.value = {};
    router.push("/");
  });
};

loadData(1);
</script>

<style scoped>
#left {
  float: left;
  padding-left: 25px;
}

#right {
  float: right;
  padding-right: 10px;
}

.site-layout .site-layout-background {
  background: #fff;
}
</style>
