<template>
  <div><h1>食品采购清单表</h1>
      <h3>食品总价:({{total}})元</h3>
    <input type="button" value="显示数据" @click="create()">
   <!-- <button class="tap"  @click="findAll()">更新数据</button>-->
    <button class="tap"  @click="export2Excel">导出表格</button>
  <table border="1" align="center" width="55%">
      <tr>
        <th style="text-align: center">全选<input type="checkbox" v-model='isAllChecked'></th>
        <th style="text-align: center">编号</th>
        <th style="text-align: center">商品类型</th>
        <th style="text-align: center">菜品名称</th>
        <th style="text-align: center">基础数量</th>
        <th style="text-align: center">单位</th>
        <th style="text-align: center">单价</th>
        <th style="text-align: center">金额</th>
      </tr>
      <tr v-for="caigou,index in caigous" :key="caigou.id">
        <td><input type="checkbox" v-model='caigou.isCheck'></td>
        <td v-model="caigou.id" >{{caigou.id}}</td>
        <td>{{caigou.leixing}}</td>
        <td>{{caigou.name}}</td>
        <td contenteditable="true" v-model="caigou.many" @blur="caigou.many=$event.target.innerText">{{caigou.many}}</td>
        <td>{{caigou.danwei}}</td>
        <td contenteditable="true" v-model="caigou.danjia" @blur="caigou.danjia=$event.target.innerText">{{caigou.danjia}}</td>
        <td>{{caigousPrice(caigou)}}元</td>
         </tr>
    </table>
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
            let sum = 0;
            if (caigou.isCheck) {
                sum = caigou.many * caigou.danjia;
            }
            return sum;
        },
        detailCaigou(id) {
            instance.get("caigou?id=" + id).then(
                res => this.caigou = res.data);
        },
        export2Excel() {
            require.ensure([], () => {
                const { export_json_to_excel } = require('../vendor/Export2Excel');
                const tHeader = ['编号', '商品类型', '菜品名称','基础数量','单位','单价','金额'];  // 设置Excel的表格第一行的标题
                const filterVal = ['id', 'leixing', 'name', 'many', 'danwei', 'danjia','{{caigousPrice(caigou)'];  // index、nickName、name是tableData里对象的属性
                const list = this.caigous;  //把data里的tableData存到list
                const data = this.formatJson(filterVal, list);
                export_json_to_excel(tHeader, data, '导出excel');  //导出Excel 文件名
            })
        },
        formatJson(filterVal, jsonData) {
            return jsonData.map(v => filterVal.map(j => v[j]))
        },

        update() {
            instance.get("caigous").then(res => {
                this.caigous = res.data;
            });
        },
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
