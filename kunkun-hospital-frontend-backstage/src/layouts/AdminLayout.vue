<template>
  <a-layout id="components-layout-demo-custom-trigger" class="site-layout">
    <a-layout-sider v-model:collapsed="collapsed" :trigger="null" collapsible>
      <a-menu v-model:selectedKeys="selectedKeys" theme="dark" mode="inline">
        <a-sub-menu key="user">
          <template #title>
            <span>
              <user-outlined />
              用户管理
            </span>
          </template>
          <a-menu-item key="1">添加用户</a-menu-item>
          <a-menu-item key="2">管理用户</a-menu-item>
        </a-sub-menu>
        <a-sub-menu key="hospital">
          <template #title>
            <span>
              <user-outlined />
              医院管理
            </span>
          </template>
          <a-menu-item key="3">添加医院</a-menu-item>
          <a-menu-item key="4">管理医院</a-menu-item>
        </a-sub-menu>
        <a-sub-menu key="doctor">
          <template #title>
            <span>
              <user-outlined />
              医生管理
            </span>
          </template>
          <a-menu-item key="5">添加医生</a-menu-item>
          <a-menu-item key="6">管理医生</a-menu-item>
        </a-sub-menu>
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
          <a-avatar id="avatar" :src="userVO.userAvatar" />
          <a-dropdown-button id="dropdown">
            {{ initUserVO.userName }}
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
        <router-view />
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

const initUserVO = {
  userName: "user",
  userAvatar: "#",
};
// eslint-disable-next-line no-undef
let userVO = ref<API.UserVO>(initUserVO);
const loadData = async (id: number) => {
  try {
    const res = await getUserVOByIdUsingGET(id);
    console.log(res);
    console.log(initUserVO);
    userVO.value.userName = res.userName;
    userVO.value.userAvatar = res.userAvatar;
  } catch (error) {
    console.log(error);
  }
};

const doLoginOut = async () => {
  await userLogoutUsingPOST();
  userVO.value = {};
  router.push("/user/login");
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
  padding-right: 15px;
  height: 100%;
}

#dropdown {
  position: relative;
  padding-left: 10px;
  /*top: 16px;*/
  /*right: 14px;*/
}

#avatar {
  position: relative;
  /*top: 17px;*/
  /*right: 100px;*/
}

.site-layout .site-layout-background {
  background: #fff;
}
</style>
