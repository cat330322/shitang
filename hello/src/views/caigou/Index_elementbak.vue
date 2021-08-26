<template>
  <div align="center">

    <h1>食品采购清单表</h1>
    <h3>食品总价:({{total}})元</h3>
    <button @click="export2Excel">导出表格</button>
          <el-table :data="caigous" style="width: 60%" >
          <el-table-column align="center" label="编号" prop="caigou.id" >
            <template slot-scope="scope">
                <span>{{scope.row.id}}</span>
            </template>
          </el-table-column>

           <el-table-column  align="center" label="商品类型" prop="caigou.leixing" >
             <template slot-scope="scope">
                    <span>{{scope.row.leixing}}</span>
              </template>
           </el-table-column>
      <el-table-column align="center" width="200" label="菜品名称" prop="caigou.name" >
        <template slot-scope="scope">
          <span>{{scope.row.name}}</span>
        </template>
      </el-table-column>
        <el-table-column align="center"  label="基础数量" prop="caigou.many">
          <template slot-scope="{row,$index}">
            <div style="cursor: pointer;" v-model="caigou.many" @click="clickDiv" @blur="divBlur($event,$index,'many')" >{{row.many}}</div>
          </template>
        </el-table-column>
          <el-table-column align="center"  label="单位" prop="caigou.danwei">
            <template slot-scope="scope">
              <span>{{scope.row.danwei}}</span>
            </template>
          </el-table-column>

           <el-table-column  align="center" label="单价" prop="caigou.danjia">
             <template slot-scope="{row,$index}">
               <div style="cursor: pointer;"   v-model="caigou.danjia" @click="clickDiv" @blur="divBlur($event,$index,'danjia')" >{{row.danjia}}</div>
           </template>
          </el-table-column>
            <el-table-column  align="center" label="金额" prop="caigou.zongjia" >
              <template slot-scope="scope">
                <span>{{caigousPrice(caigou)}}元</span>
              </template>
               </el-table-column>
            <el-table-column  align="center" label="计算"  >
              <template slot-scope="scope">
                <input type="checkbox" v-model='caigou.isCheck'>
              </template>
            </el-table-column>
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
        computed: {

            },

        }
}
</script>

<style scoped>

</style>
