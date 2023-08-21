<template>
  <div class="footer">
    <div class="links">
      <a>ChangChengLu</a>
      <github-filled />
    </div>
    <div class="copyright">
      Copyright<copyright-circle-outlined /> {{ copyRight }}
    </div>
  </div>
</template>

<script lang="ts" setup>
import { GithubFilled } from "@ant-design/icons-vue";
import { CopyrightCircleOutlined } from "@ant-design/icons-vue";
import { copyRight } from "@/const/const";
import MySlot from "@/components/MySlot.vue";
</script>

<style lang="less" scoped>
.footer {
  padding: 48px 16px 24px;
  text-align: center;
  .copyright {
    color: rgba(0, 0, 0, 0.45);
    font-size: 14px;
    i {
      margin: 0 4px;
    }
  }
  .links {
    margin-bottom: 8px;
    a:not(:last-child) {
      margin-right: 40px;
    }
    a {
      color: rgba(0, 0, 0, 0.45);
      -webkit-transition: all 0.3s;
      transition: all 0.3s;
    }
  }
}
</style>
