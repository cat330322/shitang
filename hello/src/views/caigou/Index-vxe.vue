<template>
  <div><h1>食品采购清单表</h1>
      <h3>食品总价:{{total}}元</h3>
   <button  @click="findAll()">更新数据库数据</button>
    <button @click="export2Excel">导出表格</button>
    <vxe-table
      border
      resizable
      show-overflow
      :data="caigous"
      :footer-method="footerMethod"
      :edit-config="{trigger: 'click', mode: 'cell'}">
      <vxe-table-column field="id" title="编号" width="60"></vxe-table-column>
      <vxe-table-column field="leixing" title="类型"></vxe-table-column>
      <vxe-table-column field="name" title="食品名称" :edit-render="{name: 'input'}"></vxe-table-column>
      <vxe-table-column field="danwei" title="单位" ></vxe-table-column>
      <vxe-table-column field="many" title="数量" :edit-render="{name: '$input', props: {type: 'number'}}"></vxe-table-column>
      <vxe-table-column field="danjia" title="单价" :edit-render="{name: '$input', props: {type: 'number'}}"></vxe-table-column>
      <vxe-table-column title="总价" >
        <template #default="{ row }">
          <span>{{ countAmount(row) }} 元</span>
          </template>
      </vxe-table-column>
    </vxe-table>
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
        sumNum (list, field) {
            let count = 0
            list.forEach(item => {
                count += Number(item[field])
            })
            return count
        },
        countAmount (row) {
            return row.danjia * row.many
        },
        countAllAmount (data) {
            let count = 0
            data.forEach(row => {
                count += this.countAmount(row)
            })
            return count
        },
        footerMethod ({ columns, data }) {
            return [
                columns.map((column, columnIndex) => {
                    if (columnIndex === 0) {
                        return '合计'
                    }
                    if (columnIndex === 4) {
                        return `${this.sumNum(data, 'number')} 本`
                    } else if (columnIndex === 6) {
                        return `共 ${this.countAllAmount(data)} 元`
                    }
                    return '-'
                })
            ]
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
                const filterVal = ['id', 'leixing', 'name', 'many', 'danwei', 'danjia','{{caigousPrice(caigou)'];  // index、nickName、name是tableData里对象的属性
                const list = this.caigous;  //把data里的tableData存到list
                const data = this.formatJson(filterVal, list);
                export_json_to_excel(tHeader, data, '导出excel');  //导出Excel 文件名
            })
        },
        formatJson(filterVal, jsonData) {
            return jsonData.map(v => filterVal.map(j => v[j]))
        },

    },
    created() {
        this.findAll();
    },

    computed: {

    }

}
</script>

<style scoped>

</style>

