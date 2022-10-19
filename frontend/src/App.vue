<template>
  <el-config-provider namespace="ep">
    <BaseHeader />
    <div class="tables mt-2">
      <table-teacher/>
    </div>
    <div class="tables mt-2">
      <table-discipline/>  
    </div>
  </el-config-provider>
</template>

<style>
#app {
  text-align: center;
  color: var(--ep-text-color-primary);
}

.element-plus-logo {
  width: 50%;
}

.tables {
  display: flex;
  justify-content: center;
}
</style>
