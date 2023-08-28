<template>
  <admin-layout>
    <content-layout>
      <div class="top">
        <div class="register">
          <a-form
            :model="userAdd"
            name="basic"
            autocomplete="off"
            @finish="onFinish"
            @finishFailed="onFinishFailed"
          >
            <div style="margin-top: 40px">
              <a-form-item>
                <a-form-item name="userAccount">
                  <a-input
                    v-model:value="userAdd.userAccount"
                    autocomplete="autocomplete"
                    size="large"
                    placeholder="User Account"
                  >
                    <template #prefix>
                      <UserOutlined class="site-form-item-icon" />
                    </template>
                  </a-input>
                </a-form-item>
                <a-form-item name="userAvatar">
                  <a-input
                    v-model:value="userAdd.userAvatar"
                    size="large"
                    style="width: 100%"
                    autocomplete="autocomplete"
                    type="password"
                    placeholder="User Avatar"
                  >
                    <template #prefix>
                      <LockOutlined class="site-form-item-icon" />
                    </template>
                  </a-input>
                </a-form-item>
                <a-form-item name="userName">
                  <a-input
                    v-model:value="userAdd.userName"
                    size="large"
                    style="width: 100%"
                    autocomplete="autocomplete"
                    type="password"
                    placeholder="User Name"
                  >
                    <template #prefix>
                      <LockOutlined class="site-form-item-icon" />
                    </template>
                  </a-input>
                </a-form-item>
                <a-form-item label="Profile">
                  <a-textarea v-model="userAdd.userProfile" />
                </a-form-item>
                <a-button
                  :loading="loginLoading"
                  htmlType="submit"
                  size="large"
                  style="width: 100%"
                  type="primary"
                  @click="handleSubmit(userAdd)"
                  >添加用户</a-button
                >
              </a-form-item>
            </div>
          </a-form>
        </div>
      </div>
    </content-layout>
  </admin-layout>
</template>

<script lang="ts" setup>
import { LockOutlined, UserOutlined } from "@ant-design/icons-vue/lib/icons";
import { ref } from "vue";
import { addUserUsingPOST, userRegisterUsingPOST } from "@/services/userController";
import { useRouter } from "vue-router";
import { message } from "ant-design-vue";
import ContentLayout from "@/layouts/ContentLayout.vue";
import AdminLayout from "@/layouts/AdminLayout.vue";

const error = "";
const loginLoading = ref<boolean>(false);
// eslint-disable-next-line no-undef
const userAdd = ref<API.UserAddRequest>({});
const router = useRouter();

// eslint-disable-next-line no-undef
const onFinish = (values: API.UserLoginRequest) => {
  console.log("Success:", values);
};

const onFinishFailed = (errorInfo: never) => {
  console.log("Failed:", errorInfo);
  const defaultLoginFailureMessage = "添加失败，请重试！";
  console.log(error);
  message.error(defaultLoginFailureMessage);
};
// eslint-disable-next-line no-undef
const handleSubmit = async (userAddRequest: API.UserAddRequest) => {
  loginLoading.value = true;
  const res = await addUserUsingPOST(userAddRequest);
  loginLoading.value = false;
  if (res) {
    router.push("/");
  }
};
</script>

<style lang="less" scoped>
.content-layout {
  .top {
    text-align: center;
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
}
</style>
