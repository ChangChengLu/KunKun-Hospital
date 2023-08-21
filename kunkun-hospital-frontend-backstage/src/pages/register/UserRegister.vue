<template>
  <LoginLayout>
    <div class="top">
      <div class="header">
        <img alt="logo" class="logo" src="../../assets/logo.png" />
        <span class="title">仙医馆籍</span>
      </div>
      <div class="desc">
        <p>九重天子寰中贵，五等诸侯门外尊。</p>
        <p>争似布衣狂醉客，不教性命属乾坤。</p>
      </div>
      <div class="register">
        <a-form
          :model="userRegister"
          name="basic"
          autocomplete="off"
          @finish="onFinish"
          @finishFailed="onFinishFailed"
        >
          <div style="margin-top: 40px">
            <a-form-item>
              <a-form-item
                name="userAccount"
                :rules="[
                  {
                    required: true,
                    message: 'Please input your user account!',
                  },
                ]"
              >
                <a-input
                  v-model:value="userRegister.userAccount"
                  autocomplete="autocomplete"
                  size="large"
                  placeholder="请输入坤名"
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
                  v-model:value="userRegister.userPassword"
                  size="large"
                  style="width: 100%"
                  autocomplete="autocomplete"
                  type="password"
                  placeholder="请输入密码"
                >
                  <template #prefix>
                    <LockOutlined class="site-form-item-icon" />
                  </template>
                </a-input>
              </a-form-item>
              <a-form-item
                name="checkPassword"
                :rules="[
                  {
                    required: true,
                    message: 'Please input your checked password!',
                  },
                ]"
              >
                <a-input
                  v-model:value="userRegister.checkPassword"
                  size="large"
                  style="width: 100%"
                  autocomplete="autocomplete"
                  type="password"
                  placeholder="请输入重复密码"
                >
                  <template #prefix>
                    <LockOutlined class="site-form-item-icon" />
                  </template>
                </a-input>
              </a-form-item>
              <a-button
                :loading="loginLoading"
                htmlType="submit"
                size="large"
                style="width: 100%"
                type="primary"
                @click="handleSubmit(userRegister)"
                >注册</a-button
              >
            </a-form-item>
          </div>
        </a-form>
      </div>
    </div>
  </LoginLayout>
</template>

<script lang="ts" setup>
import { LockOutlined, UserOutlined } from "@ant-design/icons-vue/lib/icons";
import { ref } from "vue";
import LoginLayout from "@/layouts/LoginLayout.vue";
import { userRegisterUsingPOST } from "@/services/userController";
import { useRouter } from "vue-router";
import { message } from "ant-design-vue";

const error = "";
const loginLoading = ref<boolean>(false);
// eslint-disable-next-line no-undef
const userRegister = ref<API.UserRegisterRequest>({});
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
const handleSubmit = async (userRegisterRequest: API.UserRegisterRequest) => {
  loginLoading.value = true;
  await userRegisterUsingPOST(userRegisterRequest).then((res) => {
    loginLoading.value = false;
    if (res) {
      router.push("/user/login");
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
  .register {
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
