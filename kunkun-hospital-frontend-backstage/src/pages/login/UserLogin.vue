<template>
  <LoginLayout>
    <div class="top">
      <div class="header">
        <img alt="logo" class="logo" src="../../assets/logo.png" />
        <span class="title">{{ systemName }}</span>
      </div>
      <div class="desc">
        <p>九重天子寰中贵，五等诸侯门外尊。</p>
        <p>争似布衣狂醉客，不教性命属乾坤。</p>
      </div>
      <div class="login">
        <a-form
          :model="userLogin"
          name="basic"
          autocomplete="off"
          @finish="onFinish"
          @finishFailed="onFinishFailed"
        >
          <a-tabs
            size="large"
            :tabBarStyle="{ textAlign: 'center' }"
            style="padding: 0 2px"
          >
            <a-tab-pane tab="账户密码登录" key="account">
              <a-alert
                type="error"
                :closable="true"
                v-show="error"
                :message="error"
                showIcon
                style="margin-bottom: 24px"
              />
              <a-form-item
                name="username"
                :rules="[
                  { required: true, message: 'Please input your username!' },
                ]"
              >
                <a-input
                  v-model:value="userLogin.userAccount"
                  autocomplete="autocomplete"
                  size="large"
                  placeholder="admin"
                >
                  <template #prefix>
                    <UserOutlined class="site-form-item-icon" />
                  </template>
                </a-input>
              </a-form-item>
              <a-form-item
                name="password"
                :rules="[
                  { required: true, message: 'Please input your password!' },
                ]"
              >
                <a-input
                  v-model:value="userLogin.userPassword"
                  size="large"
                  style="width: 100%"
                  placeholder="888888"
                  autocomplete="autocomplete"
                  type="password"
                >
                  <template #prefix>
                    <LockOutlined class="site-form-item-icon" />
                  </template>
                </a-input>
              </a-form-item>
            </a-tab-pane>
            <a-tab-pane tab="手机号登录" key="phone">
              <a-form-item>
                <a-input size="large" placeholder="mobile number">
                  <template #prefix>
                    <phone-outlined class="site-form-item-icon" />
                  </template>
                </a-input>
              </a-form-item>
              <a-form-item>
                <a-row :gutter="8" style="margin: 0 -4px">
                  <a-col :span="16">
                    <a-input size="large" placeholder="captcha">
                      <template #prefix>
                        <whats-app-outlined class="site-form-item-icon" />
                      </template>
                    </a-input>
                  </a-col>
                  <a-col :span="8" style="padding-left: 4px">
                    <a-button
                      style="width: 100%"
                      class="captcha-button"
                      size="large"
                      >获取验证码</a-button
                    >
                  </a-col>
                </a-row>
              </a-form-item>
            </a-tab-pane>
          </a-tabs>
          <div>
            <a-checkbox
              :checked="checkRemember"
              style="float: left"
              @click="checkRemember = !checkRemember"
              >自动登录</a-checkbox
            >
            <a style="float: right">忘记密码</a>
          </div>
          <div style="margin-top: 40px">
            <a-form-item>
              <a-button
                :loading="loginLoading"
                htmlType="submit"
                size="large"
                style="width: 100%"
                type="primary"
                @click="handleSubmit(userLogin)"
                >登录</a-button
              >
            </a-form-item>
          </div>
          <div>
            <p style="float: left; color: rgba(0, 0, 0, 0.45)">
              其他登录方式
              <MySlot />
              <qq-circle-filled />
              <MySlot />
              <wechat-filled />
            </p>
            <router-link style="float: right" to="/user/register"
              >注册账户</router-link
            >
          </div>
        </a-form>
      </div>
    </div>
  </LoginLayout>
</template>

<script lang="ts" setup>
import {
  UserOutlined,
  LockOutlined,
  QqCircleFilled,
  WechatFilled,
  PhoneOutlined,
  WhatsAppOutlined,
} from "@ant-design/icons-vue/lib/icons";
import { systemName } from "@/const/const";
import { ref } from "vue";
import LoginLayout from "@/layouts/LoginLayout.vue";
import MySlot from "@/components/MySlot.vue";
import { userLoginUsingPOST } from "@/services/userController";
import { useRouter } from "vue-router";
import { message } from "ant-design-vue";

const error = "";
const checkRemember = ref<boolean>(true);
const loginLoading = ref<boolean>(false);
// eslint-disable-next-line no-undef
const userLogin = ref<API.UserLoginRequest>({});
const router = useRouter();

// eslint-disable-next-line no-undef
const onFinish = (values: API.UserLoginRequest) => {
  console.log("Success:", values);
};

const onFinishFailed = (errorInfo: never) => {
  console.log("Failed:", errorInfo);
  const defaultLoginFailureMessage = "登录失败，请重试！";
  console.log(error);
  message.error(defaultLoginFailureMessage);
};
// eslint-disable-next-line no-undef
const handleSubmit = async (userLoginRequest: API.UserLoginRequest) => {
  loginLoading.value = true;
  await userLoginUsingPOST(userLoginRequest).then((res) => {
    loginLoading.value = false;
    if (res) {
      router.push("/");
    }
  });
};
</script>

<style lang="less" scoped>
.login-layout {
  .top {
    text-align: center;
    .header {
      height: 44px;
      line-height: 44px;
      a {
        text-decoration: none;
      }
      .logo {
        height: 44px;
        vertical-align: top;
        margin-right: 16px;
      }
      .title {
        font-size: 33px;
        color: rgba(0, 0, 0, 0.85);
        font-family: "Myriad Pro", "Helvetica Neue", Arial, Helvetica,
          sans-serif;
        font-weight: 600;
        position: relative;
        top: 2px;
      }
    }
    .desc {
      font-size: 14px;
      color: rgba(0, 0, 0, 0.45);
      margin-top: 12px;
      margin-bottom: 40px;
    }
  }
  .login {
    width: 368px;
    margin: 0 auto;
    @media screen and (max-width: 576px) {
      width: 95%;
    }
    @media screen and (max-width: 320px) {
      .captcha-button {
        font-size: 14px;
      }
    }
    .icon {
      font-size: 24px;
      color: rgba(0, 0, 0, 0.45);
      margin-left: 16px;
      vertical-align: middle;
      cursor: pointer;
      transition: color 0.3s;

      &:hover {
        color: rgba(blue, 0.45);
      }
    }
  }
}
</style>
