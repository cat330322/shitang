<template>
  <div align="center" style="width: 100%">

    <h1>食品采购清单表</h1>
    <h3>食品总价:({{total}})元</h3>
    <button @click="export2Excel">导出表格</button>
    <button @click="">Update全部</button>
    <button @click="">Save全部</button>
    <button @click="">View全部</button>
    <el-table   :data="caigous.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))" style="width: 70%">
      <el-table-column align="center" label="编号" prop="id" ></el-table-column>
      <el-table-column  align="center" label="商品类型" prop="leixing" ></el-table-column>
      <el-table-column align="center" width="180" label="菜品名称" prop="name"></el-table-column>
      <el-table-column align="center" label="基础数量" prop="many" @cell-click="celledit">
      </el-table-column>
      <el-table-column align="center" label="单位" prop="danwei"></el-table-column>
      <el-table-column v-model="caigou.danjia" align="center" label="单价" prop="danjia" @cell-click="celledit">
      </el-table-column>
      <el-table-column  align="center" label="合计" prop="zongjia"></el-table-column>
    </el-table>

  </div>
</template>
<script>
    import instance from "../../utils/request";
    export default {
        name: "Index",
         data() {
              return {
                  caigous: [],
                    caigou: {},
            }

        },
        methods: {
            celledit(row, column, cell, event) {
                cell.contentEditable = true;
                cell.focus()
            },

            findAll() {
                instance.get("caigous").then(res => {
                    this.caigous = res.data;
                })
            },

            detailCaigou(id) {
                instance.get("caigou?id=" + id).then(
                    res => this.caigou = res.data);
            },
             export2Excel() {
                require.ensure([], () => {
                    const { export_json_to_excel } = require('../vendor/Export2Excel');
                    const tHeader = ['编号', '商品类型', '菜品名称','基础数量','单位','单价','金额'];  // 设置Excel的表格第一行的标题
                    const filterVal = ['id', 'leixing', 'name', 'many', 'danwei', 'danjia','caigousPrice(caigou)'];  // index、nickName、name是tableData里对象的属性
                    const list = this.caigous;  //把data里的tableData存到list
                    const data = this.formatJson(filterVal, list);
                    export_json_to_excel(tHeader, data, '导出excel');  //导出Excel 文件名
                })
            },
            formatJson(filterVal, jsonData) {
                return jsonData.map(v => filterVal.map(j => v[j]))
            }

        },
        created() {
            this.findAll();
        },
       /*created(){
           this.axios.get("/findAll()")
       },*/
        computed: {

            total() {
                var sum = 0;
                this.caigous.forEach(caigou => {
                    if (caigou.isCheck) {
                        sum += caigou.many * caigou.danjia;
                    }
                });
                return sum;

            },
        }
    }
</script>

<style scoped>

</style>
