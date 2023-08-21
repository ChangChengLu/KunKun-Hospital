<template>
  <div class="login-layout">
    <div class="content"><slot></slot></div>
    <PageFooter></PageFooter>
  </div>
</template>

<script lang="ts" setup></script>

<style lang="less" scoped>
.login-layout {
  display: flex;
  flex-direction: column;
  height: 100vh;
  overflow: auto;
  background-color: #f0f2f5;
  background-image: url("https://gw.alipayobjects.com/zos/rmsportal/TVYTbAXWheQpRcWDaDMu.svg");
  background-repeat: no-repeat;
  background-position-x: center;
  background-position-y: 110px;
  background-size: 100%;
  .content {
    padding: 32px 0;
    flex: 1;
    @media (min-width: 768px) {
      padding: 112px 0 24px;
    }
  }
}
</style>
<script>
import PageFooter from "@/layouts/footer/PageFooter";
export default {
  components: { PageFooter },
};
</script>
