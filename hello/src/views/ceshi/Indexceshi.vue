<template>
  <div calss=caigou align="center">

    <h1>食品采购清单表</h1>
    <h3>食品总价:({{total}})元</h3>
    <button class="tap"  @click="export2Excel">导出表格</button>
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
          <template slot-scope="scope">
            <span>{{scope.row.many}}</span>
          </template>
        </el-table-column>


          <el-table-column align="center"  label="单位" prop="caigou.danwei">
            <template slot-scope="scope">
              <span>{{scope.row.danwei}}</span>
            </template>
          </el-table-column>

           <el-table-column  align="center" label="单价" prop="caigou.danjia">
             <template slot-scope="scope">
          <span>{{scope.row.danjia}}</span>
           </template>
          </el-table-column>
            <el-table-column  align="center" label="金额" prop="caigou.zongjia" >
              <template slot-scope="scope">
                <span>{{scope.row.zongjia}}元</span>
              </template>
               </el-table-column>

    </el-table>
    <h3>食品总价:({{total}})元</h3>
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
        caigousPrice(caigou) {
            var sum = 0;
            if (caigou.isCheck) {
                sum = caigou.many * caigou.danjia;
            }
            return sum;
        },
        findAll() {
            instance.get("caigous").then(res => {
                this.caigous = res.data;
            });
        },
        detailCaigou(id) {
            instance.get("caigou?id=" + id).then(
                res => this.caigou = res.data);
        },


        export2Excel() {
            require.ensure([], () => {
                const { export_json_to_excel } = require('../vendor/Export2Excel');
                const tHeader = ['编号', '商品类型', '菜品名称','基础数量','单位','单价','金额'];  // 设置Excel的表格第一行的标题
                const filterVal = ['caigou.id', 'caigou.leixing', 'caigou.name', 'caigou.many', 'caigou.danjia', 'total'];  // index、nickName、name是tableData里对象的属性
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
            isAllChecked: {
                get() {
                    return this.caigous.every(caigou => caigou.isCheck)
                },
                set(val) {
                    console.log(val);
                    return this.caigous.forEach(caigou => caigou.isCheck = val);
                }
            },
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
